package en.lib.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BasicServer extends Server {
	public void onMessageReceived(String message, ClientConnection connection) {
		sendToAll(connection.nickname + ": " + message);
		System.out.println(connection.nickname + ": " + message);
	}
}
