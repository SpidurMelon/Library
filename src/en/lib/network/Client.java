package en.lib.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Client {
	private ServerConnection connection;
	private Scanner scan;
	public Client(String nickname) {
		try {
			Socket socket = new Socket("localhost", 7777);
			PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			connection = new ServerConnection(socket, toServer, fromServer);
		} catch (IOException e) {
			System.out.println("Server not open");
			System.exit(0);
			e.printStackTrace();
		}
		connection.writer.println(nickname);
		
		listenForMessages.start();
	}
	
	public abstract void onMessageReceived(String message);
	
	public void sendToServer(String message) {
		connection.writer.println(message);
	}
	
	private Thread listenForMessages = new Thread(new Runnable() {
		public void run() {
			while (true) {
				try {
					String messageFromServer = connection.reader.readLine();
					onMessageReceived(messageFromServer);
				} catch (IOException e) {
					connection.close();
					System.out.println("Disconnected");
					break;
				}
			}
		}
	});
	
}
