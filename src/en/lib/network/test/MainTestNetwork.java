package en.lib.network.test;

import en.lib.setup.RegularFrame;

public class MainTestNetwork {
	public static void main(String[] args) {
		RegularFrame frame = new RegularFrame(800, 800, false);
		DrawPanel dp = new DrawPanel();
		frame.addPanel(dp);
	}
}
