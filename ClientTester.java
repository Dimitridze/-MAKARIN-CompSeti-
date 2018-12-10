package fab;

import java.util.List;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.net.InetAddress;

public class ClientTester {

/**
* 
* @param args
* @throws InterruptedException
*/
public static void main(String[] args) throws InterruptedException {
// запускаем подключение сокета по известным координатам и нициализируем приём сообщений с консоли клиента 
//String ip = "192.168.113.5";
String ip = "127.0.0.1";
int port = 8080;
/*if (args.length>0){
//ip = args[0];
if (args.length>1){
port = Integer.parseInt(args[1]);
}
}*/
long start = System.currentTimeMillis(); 
try{
Socket socket = new Socket(ip, port); 
BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
DataInputStream ois = new DataInputStream(socket.getInputStream()); 


long index = ois.readLong();
long min = ois.readLong();
long max = ois.readLong();

System.out.println("Klient #" + index + " Interval: " + min +":" + max);

List<Long> result = new ArrayList<Long>();

for(Long i = min; i<=max; i++){
if(i % 11 == 0 && i % 13 == 0 && i % 17 == 0){
result.add(i);
}
}

Integer resultArrSize = result.size();
long t1 = System.currentTimeMillis() - start;
InetAddress iAddress = InetAddress.getLocalHost();
String currentIp = iAddress.getHostAddress();

oos.writeUTF("Klient #"+index + " IP: " + currentIp + " nashel "+ resultArrSize + " chisel v " + min + ":" + max + " za " + t1 + " ms");
oos.writeUTF("Klient #"+index + " Otklychilsya");
System.out.println("Klient #"+index + " IP: " + currentIp + " nashel "+ resultArrSize + " chisel v " + min + ":" + max + " za " + t1 + " ms");
System.out.println("Klient #"+index + " Otklychilsya");

System.out.println("___________________________________________________________________");

} catch (IOException e) {
System.out.println("Ошибка с " + ip + ":" + port);
//e.printStackTrace();
}
}
}
