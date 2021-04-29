package en.lib.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection {
	public Socket socket;
	public PrintWriter writer;
	public BufferedReader reader;
	public String nickname;
	public ClientConnection(Socket socket, PrintWriter writer, BufferedReader reader, String nickname) {
		this.socket = socket;
		this.writer = writer;
		this.reader = reader;
		this.nickname = nickname;
	}
	public void close() {
		try {
			writer.close();
			reader.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
