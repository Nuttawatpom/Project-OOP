package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Manager.GameStateManager;
import Manager.Keys;

public class IntroState1 extends GameState {
	
	private BufferedImage logo2;
	
	private int alpha;
	private int ticks;
	
	private final int LENGTH = 100;
	private final int FADE_OUT = 30;
	
	public IntroState1(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		ticks = 0;
		try {
			logo2 = ImageIO.read(getClass().getResourceAsStream("/Logo/logo2.gif"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		handleInput();
		ticks++;
		if(ticks > LENGTH) {
			alpha = (int) (255 * (1.0 * ticks - LENGTH) / FADE_OUT);
			if(alpha > 255) alpha = 255;
		}
		if(ticks > LENGTH + FADE_OUT) {
			gsm.setState(GameStateManager.MENU);
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
		g.drawImage(logo2, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2, null);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {
			gsm.setState(GameStateManager.MENU);
		}
	}
	
}