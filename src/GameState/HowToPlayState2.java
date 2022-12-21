package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Manager.GameStateManager;
import Manager.JukeBox;

import Manager.Keys;

public class HowToPlayState2 extends GameState {
	
	private BufferedImage howtoplay2;
	
	private int alpha;
	
	public HowToPlayState2(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		try {
			howtoplay2 = ImageIO.read(getClass().getResourceAsStream("/HUD/howtoplay2.gif"));
			JukeBox.load("/SFX/change.wav", "change");

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
		g.drawImage(howtoplay2, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2, null);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.LEFT)) {
			gsm.setState(GameStateManager.HOWTOPLAY);
		}
	}
	
}