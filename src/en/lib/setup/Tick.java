package en.lib.setup;

import java.awt.Rectangle;
import java.util.ArrayList;

import en.lib.shapes.ShapeUtils;


public class Tick {
	public static boolean running = false;
	public static ArrayList<Panel> tickList = new ArrayList<Panel>();
	
	private static Thread timer = new Thread(new Runnable() {
		public void run() {
			int FPSGoal = 60;
			long optimalTime = 1000000000/FPSGoal;
			long lastTime = System.nanoTime();
			double delta = 0;
			while(true) {
				if (running) {
					long currentTime = System.nanoTime();
					delta = (currentTime-lastTime)/(double)optimalTime;
					lastTime = System.nanoTime();
					
					tickAll(delta);
				}
				try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	});
	
	private static void tickAll(double delta) {
		synchronized(tickList) {
			for (int i = 0; i < tickList.size(); i++) {
				tickList.get(i).executeTick(delta);
			}
			for (int i = 0; i < tickList.size(); i++) {
				for (Rectangle r:getAreaToPaint(tickList.get(i).getBounds())) {
					tickList.get(i).paintImmediately(r);
					alreadyPainted.add(r);
				}
			}
			resetAreaToPaint();
		}
	}
	
	private static ArrayList<Rectangle> alreadyPainted = new ArrayList<Rectangle>();
	private static ArrayList<Rectangle> getAreaToPaint(Rectangle wantToPaint) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		boolean freeToPaint = true;
		for (Rectangle r:alreadyPainted) {
			if (r.intersects(wantToPaint)) {
				freeToPaint = false;
				result.addAll(ShapeUtils.cutOut(wantToPaint, r));
				break;
			}
		}
		if (freeToPaint) {
			result.add(wantToPaint);
		} else {
			boolean finishedCutting = false;
			while(!finishedCutting) {
				finishedCutting = true;
				for (int i = 0; i < result.size(); i++) {
					for (Rectangle r:alreadyPainted) {
						if (result.get(i).intersects(r)) {
							for (Rectangle newCutout:ShapeUtils.cutOut(result.get(i), r)) {
								if (!result.contains(newCutout)) {
									result.add(newCutout);
									finishedCutting = false;
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	private static void resetAreaToPaint() {
		alreadyPainted.clear();
	}
	
	public static void start() {
		running = true;
		timer.start();
	}
	
	public static void pauseUnpause() {
		running = !running;
		
	}
}
