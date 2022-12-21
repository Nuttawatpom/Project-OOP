package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Manager.Content;
import Manager.Data;
import Manager.GameStateManager;
import Manager.Keys;
import Manager.JukeBox;

public class GameOverState extends GameState {
	
	private BufferedImage endscreen;

	
	private int rank;
	private long ticks;

	private int tick=0;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		try {
			JukeBox.load("/SFX/change.wav", "change");
			endscreen = ImageIO.read(getClass().getResourceAsStream("/HUD/endscreen.gif"));
			ticks = Data.getTime();
			if(ticks < 5400) rank = 1;
			else if(ticks < 9000) rank = 2;
			else if(ticks < 11600) rank = 3;
			else rank = 4;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(tick>30){
			handleInput();
		}
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(endscreen, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2, null);
		
		Content.drawString(g, "time using", 45, 76);
		
		int minutes = (int) (ticks / 1800);
		int seconds = (int) ((ticks / 30) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 84, 100);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 84, 100);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 84, 100);
			else Content.drawString(g, minutes + ":" + seconds, 84, 100);
		}
		
		Content.drawString(g, "rank", 93, 139);
		if(rank == 1) Content.drawString(g, "mother ducker", 23, 163);
		else if(rank == 2) Content.drawString(g, "cool", 93, 163);
		else if(rank == 3) Content.drawString(g, "not bad", 71, 163);
		else if(rank == 4) Content.drawString(g, "noob", 93, 163);

		tick++;
		handleInput();
	}

	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {
			JukeBox.play("change");
			gsm.setState(GameStateManager.MENU);
		}
	}
}