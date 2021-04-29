package en.lib.test;

import en.lib.setup.RegularFrame;

public class MainTest {
	public static void main(String[] args) {
		RegularFrame frame = new RegularFrame(800, 800, false);
		TestPanel panel = new TestPanel();
		frame.addPanel(panel);
	}
}
