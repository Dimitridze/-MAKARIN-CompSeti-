/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Handler implements Runnable {

public Socket clientDialog;
public long index;
public long min;
public long max;

public Handler(Socket client, long index, long min, long max) {
this.clientDialog = client;
this.index = index;
this.min = min;
this.max = max;
}

public void run() {

try {
// инициируем каналы общения в сокете, для сервера

// канал чтения из сокета
DataInputStream in = new DataInputStream(clientDialog.getInputStream());
//System.out.println("DataInputStream created");

// канал записи в сокет
DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());

// основная рабочая часть //

// начинаем диалог с подключенным клиентом в цикле, пока сокет не
// закрыт клиентом
System.out.println("Klient #" + this.index + " Podklychilsya");
System.out.println("Klient #" + this.index + " polychil interval: " + this.min + ":" + this.max);

// серверная нить ждёт в канале чтения (inputstream) получения
// данных клиента после получения данных считывает их

// out.writeUTF(index + " " + min + " " + max);

out.writeLong(this.index);
out.writeLong(this.min);
out.writeLong(this.max);

String entry = in.readUTF();

// Long entry = in.readLong();

// после получения данных считывает их
System.out.println(entry);

// освобождаем буфер сетевых сообщений
System.out.println("\n");

out.flush();
// закрываем сначала каналы сокета !
in.close();
out.close();

// потом закрываем сокет общения с клиентом в нити моносервера
clientDialog.close();

//System.out.println("Closing connections & channels - DONE.");
} catch (Exception e) {
e.printStackTrace();
}
}
}