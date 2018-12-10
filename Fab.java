/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fab;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.net.*;
import java.util.Date;
/**
* @author mercenery
*
*/
public class Fab {

/**
* @param args
*/
public static void main(String[] args) {
int port = 8080;
// стартуем сервер на порту 8080 и инициализируем переменную для обработки консольных команд с самого сервера
try (ServerSocket server = new ServerSocket(port)) {
String ip=InetAddress.getLocalHost().getHostAddress();

Date date = new Date();
System.out.println("Seichas : " + date);
System.out.println("Vklychen Server Ip:" + ip + " Port:" + 8080 + " I ozhidaet podklycheniya ");
long index = 1;
long min = 1000000;
long max = 2000000;

// стартуем цикл при условии что серверный сокет не закрыт
while (!server.isClosed()) {

Socket client = server.accept();

Thread thread = new Thread(new Handler(client, index, min, max));
thread.start();

index += 1;
min += 1000000;
max += 1000000;
//System.out.print("Connection accepted.");
}

// закрытие пула нитей после завершения работы всех нитей
} catch (Exception e) {
e.printStackTrace();
}
}
}