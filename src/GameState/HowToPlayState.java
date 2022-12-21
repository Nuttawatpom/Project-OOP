package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Manager.GameStateManager;
import Manager.JukeBox;

import Manager.Keys;

public class HowToPlayState extends GameState {
	
	private BufferedImage howtoplay;
	
	private int alpha;
	
	public HowToPlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		try {
			howtoplay = ImageIO.read(getClass().getResourceAsStream("/HUD/howtoplay.gif"));
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
		g.drawImage(howtoplay, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2, null);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.RIGHT)) {
			gsm.setState(GameStateManager.HOWTOPLAY2);
		}
		if(Keys.isPressed(Keys.ESCAPE)) {
			JukeBox.play("change");
			gsm.setState(GameStateManager.MENU);
		}
	}
	
}