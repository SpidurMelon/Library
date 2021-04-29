package en.lib.network.test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import en.lib.io.IO;
import en.lib.network.Client;
import en.lib.setup.Panel;

public class DrawPanel extends Panel implements MouseMotionListener {
	public static int WIDTH = 800, HEIGHT = 800;
	private Point mousePoint = new Point();
	private HashMap<String, Point> otherPoints = new HashMap<String, Point>();
	private String nickname;
	
	private Client client;
	
	public DrawPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your nickname:");
		nickname = scan.nextLine();
		
		client = new Client(nickname) {
			public void onMessageReceived(String message) {
				if (message.contains(":")) {
					String nicknameR = message.substring(0, message.indexOf(":"));
					String contentR = message.substring(message.indexOf(":")+2);
					synchronized(otherPoints) {
						if (nicknameR != nickname) {
							otherPoints.put(nicknameR, new Point(IO.getNumber(contentR, 0), IO.getNumber(contentR, 1)));
						}
					}
				} else if (message.contains("disconnected")) {
					otherPoints.remove(message.substring(0, message.indexOf(" ")));
				}
			}
		};
		
		addMouseMotionListener(this);
	}
	
	public void draw(Graphics2D g2) {
		g2.drawRect(mousePoint.x, mousePoint.y, 50, 50);
		
		synchronized(otherPoints) {
			for (String key:otherPoints.keySet()) {
				g2.drawString(key, otherPoints.get(key).x, otherPoints.get(key).y);
				g2.drawRect(otherPoints.get(key).x, otherPoints.get(key).y, 50, 50);
			}
		}
	}
	
	public void tick(double delta) {
		client.sendToServer(mousePoint.x + ", " + mousePoint.y);
		repaint();
	}
	
	public void mouseDragged(MouseEvent m) {
		mousePoint.setLocation(m.getPoint());
	}
	
	public void mouseMoved(MouseEvent m) {
		mousePoint.setLocation(m.getPoint());
	}
}
