package HUD;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entity.Duck;
import Entity.Player;
import Main.GamePanel;
import Manager.Content;

public class Hud {
	
	private int yoffset;
	
	private BufferedImage bar;

	private BufferedImage bluekey;
	private BufferedImage orangekey;
	private BufferedImage purplekey;
	private BufferedImage blackkey;
	private BufferedImage greenkey;
	private BufferedImage graykey;


	
	private Player player;	
	
	public Hud(Player p, ArrayList<Duck> d) {
		
		player = p;
		yoffset = GamePanel.HEIGHT;
		
		bar = Content.BAR[0][0];
		bluekey = Content.ITEMS[0][0];
		orangekey = Content.ITEMS[0][1];
		purplekey = Content.ITEMS[0][2];
		blackkey = Content.ITEMS[0][3];
		greenkey = Content.ITEMS[0][4];
		graykey = Content.ITEMS[0][5];
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw hud
		g.drawImage(bar, 0, yoffset, null);
		
		// draw items
		if(player.hasBlueKey()) g.drawImage(bluekey, 169, yoffset, null);
		if(player.hasOrangeKey()) g.drawImage(orangekey, 183, yoffset, null);
		if(player.hasPurpleKey()) g.drawImage(purplekey, 197, yoffset, null);
		if(player.hasBlackKey()) g.drawImage(blackkey, 211, yoffset, null);
		if(player.hasGreenKey()) g.drawImage(greenkey, 225, yoffset, null);
		if(player.hasGrayKey() || player.hasGrayKey1() || player.hasGrayKey2() || player.hasGrayKey3() || player.hasGrayKey4()) g.drawImage(graykey, 239, yoffset, null);
		
		// draw time
		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if(seconds >= 0){
			if(minutes < 10) {
				if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 170, 3);
				else Content.drawString(g, "0" + minutes + ":" + seconds, 170, 3);
			}
			else {
				if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 170, 3);
				else Content.drawString(g, minutes + ":" + seconds, 170, 3);
			}
		}	
	}
}
