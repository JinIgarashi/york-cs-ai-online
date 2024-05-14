import java.net.*;
import java.io.*; 

public class DatagramSender {

	public static void main(String[] args) {
		try {
			InetAddress receiverHost = InetAddress.getByName(args[0]);
			int receiverPort = Integer.parseInt(args[1]);
			String message = args[2];
			DatagramSocket mySocket = new DatagramSocket( );
			byte[] buffer = message.getBytes( );
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverHost, receiverPort); 
				
			mySocket.send(packet); 
			
			// Prepare buffer to receive the response
            byte[] receiveBuffer = new byte[60];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            // Wait for the response from the receiver
            mySocket.receive(receivePacket);

            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received response: " + receivedMessage);
			
			mySocket.close();
		} catch (Exception e) {
			e.printStackTrace(); 
		}

	}

}
