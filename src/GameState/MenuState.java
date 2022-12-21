package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import Manager.GameStateManager;
import Manager.JukeBox;
import Manager.Keys;

public class MenuState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage duck;
	
	private int currentOption = 0;
	private String[] options = {
		"GAME START",
		"HOW TO PLAY",
		"CREDIT",
		"EXIT"
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.MENUBG[0][0];
		duck = Content.DUCK[0][0];
		JukeBox.load("/SFX/change.wav", "change");
		JukeBox.load("/SFX/menuoption.wav", "menuoption");
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, 256, 272, null);
		
		Content.drawString(g, options[0], 45, 155);
		Content.drawString(g, options[1], 45, 175);
		Content.drawString(g, options[2], 45, 195);
		Content.drawString(g, options[3], 45, 215);

		if(currentOption == 0) g.drawImage(duck, 30, 156, null);
		else if(currentOption == 1) g.drawImage(duck, 30, 176, null);
		else if(currentOption == 2) g.drawImage(duck, 30, 196, null);
		else if(currentOption == 3) g.drawImage(duck, 30, 216, null);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
			JukeBox.play("menuoption");
			currentOption++;
		}
		if(Keys.isPressed(Keys.UP) && currentOption > 0) {
			JukeBox.play("menuoption");
			currentOption--;
		}
		if(Keys.isPressed(Keys.ENTER)) {
			JukeBox.play("change");
			selectOption();
		}
	}
	
	private void selectOption() {
		if(currentOption == 0) {
			gsm.setState(GameStateManager.PLAY);
		}
		if(currentOption == 1) {
			gsm.setState(GameStateManager.HOWTOPLAY);
		}
		if(currentOption == 2) {
			gsm.setState(GameStateManager.CREDIT);
		}
		if(currentOption == 3) {
			System.exit(0);
		}
	}
	
}
