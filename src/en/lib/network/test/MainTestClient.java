package en.lib.network.test;

import java.util.Scanner;

import en.lib.network.Client;

public class MainTestClient {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your nickname:");
		String nickname = scan.nextLine();
		
		Client client = new Client(nickname) {
			public void onMessageReceived(String message) {
				if (message.contains(":")) {
					String nickname = message.substring(0, message.indexOf(":"));
					String content = message.substring(message.indexOf(":") + 2, message.length());
					System.out.println(nickname + ": " + content);
				} else {
					System.out.println(message);
				}
			}
		};
		
		while (true) {
			String message = scan.nextLine();
			client.sendToServer(message);
		}
	}
}
