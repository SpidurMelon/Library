package en.lib.input;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import en.lib.setup.Panel;

public abstract class KeyBinding {
	public KeyBinding(int keyCode, Panel parent, boolean onRelease) {
		parent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, 0, onRelease), keyCode + "," + onRelease);
		parent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK, onRelease), keyCode + "," + onRelease);
		parent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, InputEvent.SHIFT_DOWN_MASK, onRelease), keyCode + "," + onRelease);
		parent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, onRelease), keyCode + "," + onRelease);
			
		parent.getActionMap().put(keyCode + "," + onRelease, new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				onAction();
			}
		});
	}
	
	public abstract void onAction();
}
