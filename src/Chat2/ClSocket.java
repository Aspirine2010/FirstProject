package Chat2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClSocket {
    public static void main(String[] args) {
  new myWindow();
    }

//просто комментарий
    public static class myWindow  extends JFrame {
        private JTextField textField;
        private  JTextArea textArea;
        private final String SERVER_ADDR = "localhost";
        private final  int SERVER_PORT = 8189;
        private Socket socket;
        private Scanner scanner;
        private PrintWriter printWriter;
        myWindow(){
            try {
                socket =  new Socket(SERVER_ADDR,SERVER_PORT);
                scanner = new Scanner(socket.getInputStream());
                printWriter = new PrintWriter(socket.getOutputStream());
            }
            catch (IOException e){
                e.printStackTrace();
            }
            setBounds(600,300,500,500);
            setTitle("Client");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            add(scrollPane, BorderLayout.CENTER);
            JPanel panel = new JPanel(new BorderLayout());
            add(panel,BorderLayout.SOUTH);
            JButton button = new JButton("send");
            panel.add(button,BorderLayout.EAST);
            textField = new JTextField();
            panel.add(textField,BorderLayout.CENTER);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!textField.getText().trim().isEmpty()){
                        sendMsg();
                        textField.grabFocus();
                    }
                }
            });
            textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sendMsg();
                }
            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            if(scanner.hasNext()){
                                String w = scanner.nextLine();
                                if(w.equalsIgnoreCase("end session"))break;
                                textArea.append(w);
                                textArea.append("\n");
                            }
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    try {
                        printWriter.println("end");
                        printWriter.flush();
                        socket.close();
                        printWriter.close();
                        scanner.close();

                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            });
            setVisible(true);
        }
        public void sendMsg(){
            printWriter.println(textField.getText());
            printWriter.flush();
            textField.setText("");
        }

    }

}