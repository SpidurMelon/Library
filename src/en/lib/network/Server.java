package en.lib.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public abstract class Server {
	public ServerSocket ss;
	
	public static ArrayList<ClientConnection> clientConnections = new ArrayList<ClientConnection>();
	
	public Server() {
		try {
			ss = new ServerSocket(7777);
		} catch (IOException e) {
			e.printStackTrace();
		}
		listenForClients.start();
	}
	
	public void sendToAll(String message) {
		for (int i = 0; i < clientConnections.size(); i++) {
			clientConnections.get(i).writer.println(message);
		}
	}
	
	public abstract void onMessageReceived(String message, ClientConnection connection);
	
	private Thread listenForClients = new Thread(new Runnable() {
		public void run() {
			while (true) {
				try {
					Socket newClient = ss.accept();
					PrintWriter clientWriter = new PrintWriter(newClient.getOutputStream(), true);
					BufferedReader fromClient = new BufferedReader(new InputStreamReader(newClient.getInputStream()));
					String nickname = fromClient.readLine();
					
					ClientConnection newClientConnection = new ClientConnection(newClient, clientWriter, fromClient, nickname);
					clientConnections.add(newClientConnection);
					
					listenForMessages(newClientConnection).start();
					
					sendToAll(nickname + " connected.");
					System.out.println(nickname + " connected.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	private Thread listenForMessages(ClientConnection clientConnection) {
		return new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						String messageFromClient = clientConnection.reader.readLine();
						onMessageReceived(messageFromClient, clientConnection);
					} catch (IOException e) {
						System.out.println(clientConnection.nickname + " disconnected.");
						clientConnection.close();
						clientConnections.remove(clientConnection);
						sendToAll(clientConnection.nickname + " disconnected.");
						break;
					}
				}
			}
		});
	}
	
}
