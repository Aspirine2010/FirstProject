package Messager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {
    public static void main(String[] args) {
       Socket socket =null;
       ServerSocket serverSocket = null;
       try {
           serverSocket = new ServerSocket(8989);
           socket = serverSocket.accept();
           System.out.println("Сервер запущен и ожидает ебаного кожаного ублюдка");
           Scanner scanner = new Scanner(socket.getInputStream());
           PrintWriter writer = new PrintWriter(socket.getOutputStream());
           while (true){
               String mes = scanner.nextLine();
               if(mes.equalsIgnoreCase("end"))break;
               writer.println("Эхо :"+ mes);
               writer.flush();
           }

       }
       catch (IOException e){
           e.printStackTrace();
           System.out.println("Ошибка ебаного сервера, он чё-то выёбывается");
       }
       finally {
           try {
               serverSocket.close();
           }
           catch (Exception e){
               e.printStackTrace();
               System.out.println("Сервер выебнулся и не хочет выключаться");
           }
       }
    }
}
