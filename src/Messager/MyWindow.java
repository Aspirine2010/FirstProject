package Messager;

import javax.swing.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class MyWindow extends JFrame {
    JTextArea textArea;
    JTextField textField;
    JButton button;
    Socket socket = null;
    MyWindow(){
        try {
            socket = new Socket("localhost",8989);
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
    public static void main(String[] args) {

    }

}
