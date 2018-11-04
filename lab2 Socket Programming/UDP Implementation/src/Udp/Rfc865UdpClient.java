/*
 * This is the source code for implementation of a Client for the service 
 */
package Udp;
import java.io.*;
import java.net.*;

public class Rfc865UdpClient {

	 public static void main(String[] argv) throws UnknownHostException {
	 //////////////////////////////////////////////////////////////
	 // 1. Open UDP socket									///////
	 //////////////////////////////////////////////////////////////
	 DatagramSocket socket = null;
	 try {

	 //Input the name of server connecting to here:
	 //Using getByName to get using the name of Server Station (however a direct IPV4 from ipconfig will do)
	 InetAddress serverAddress = InetAddress.getByName("localhost");
	 System.out.println("Connecting to Server: "+serverAddress);
	 socket = new DatagramSocket();

	 //Init Connection to Server with address above
	 socket.connect(serverAddress, 17);
	 } catch (SocketException e) {
		 System.out.println("Socket error: " + e.getMessage());}

	 try {
	 ////////////////////////////////////////////////////////////
	 // 2. Send UDP request to server						/////
	 //														/////
	 ////////////////////////////////////////////////////////////
	 
	 //Using getByName to get using the name of Server Station (however a direct IPV4 from ipconfig will do)
	 //get Ipv4 address of the server that we are sending to again
	 InetAddress serverAddress = InetAddress.getByName("localhost");
	 //get our Ipv4 address of our client (being sent to the server)
	 System.out.println("Sending Datagram to Server: "+serverAddress);
	 InetAddress clientAddress = InetAddress.getByName("localhost");

	 //form the String of information as request by the question.
	 byte[] buffer = ((String)("Information content from "+ clientAddress)).getBytes();

	 //sending the packet above to the server, port 17 to get the QOTD back
	 DatagramPacket request = new DatagramPacket(buffer, buffer.length,serverAddress, 17);
	 socket.send(request);

	 ////////////////////////////////////////////////////////////

	 ////////////////////////////////////////////////////////////
	 // 3. Receive UDP reply from server					/////
	 //														/////
	 ////////////////////////////////////////////////////////////
	 //Create a buffer to store message received (max size to be 512 bytes)
	 //Receive reply/quote package using this buffer
	 buffer = new byte[512];
	 DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
	 socket.receive(reply);

	 //Decode the quote from byte to String and print it out.
	 String quote = new String(buffer, 0, reply.getLength());
	 System.out.println(quote);
	 } catch (IOException e) {
		 System.out.println("Client error: " + e.getMessage());
         e.printStackTrace();
	 }
	 //close the connection once the quote is receive successfully
	 System.out.println("Disconnecting...");
	 socket.disconnect();
	 }
	}
