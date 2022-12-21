package GameState;

import java.awt.Graphics2D;

import Manager.Content;
import Manager.GameStateManager;
import Manager.JukeBox;
import Manager.Keys;

public class PauseState extends GameState {
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		Content.drawString(g, "paused", 80, 60);
		
		Content.drawString(g, "arrow", 24, 132);
		Content.drawString(g, "keys", 32, 100);
		Content.drawString(g, ": move", 104, 100);
		
		Content.drawString(g, "space", 24, 132);
		Content.drawString(g, ": action", 104, 132);
		
		Content.drawString(g, "F1:", 72, 164);
		Content.drawString(g, "return", 136, 156);
		Content.drawString(g, "to menu", 136, 172);
		
	}
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(false);
			JukeBox.resumeLoop("music1");
		}
		if(Keys.isPressed(Keys.F1)) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENU);
		}
	}
	
}
