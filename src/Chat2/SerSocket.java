package Chat2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class SerSocket {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен и ожидает подключения");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            while (true){
                String s = scanner.nextLine();
                if(s.equalsIgnoreCase("end"))break;
                writer.println("Эхо :"+ s);
                writer.flush();
            }

        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Ошибка инициализации сервера");
        }
        finally {
            try {
                serverSocket.close();
            }
            catch (IOException e){
                System.out.println("Сервер не закончил работу");
            }
        }
    }
}
