package Udp;
import java.net.*;
import java.io.*;

public class Rfc865UdpServer {
	 public static void main(String[] argv) {
		 //
		 // 1. Open UDP socket at well-known port
		 //
		 DatagramSocket socket = null;
		 
		 try {
		 socket = new DatagramSocket(17);
		 } catch (SocketException e) {
			 System.out.println("Socket error: " + e.getMessage());
		 }
		 while (true) {
		 try {
		 //
		 // 2. Listen for UDP request from client
		 //
		 byte[] buffer = new byte[512];
		 DatagramPacket request = new DatagramPacket(buffer,buffer.length);
		 socket.receive(request);
		 System.out.println("Received Request");
		 String dataFromClient = new String(buffer,0,request.getLength());
		 System.out.println("Log data: " + dataFromClient);
		 InetAddress clientAddress = request.getAddress();
		 int clientPort = request.getPort();
		 System.out.println("Client Address = " + clientAddress);
		 //
		 // 3. Send UDP reply to client
		 //
		 buffer = "Hello World!".getBytes();
		 DatagramPacket reply = new DatagramPacket(buffer,buffer.length,clientAddress,clientPort);
		 socket.send(reply);
		 System.out.println("Sent Reply");
		 //break;
		 } catch (IOException e) {
			 System.out.println("Server error: " + e.getMessage());
	         socket.close();
		 }
	 }
 }
}