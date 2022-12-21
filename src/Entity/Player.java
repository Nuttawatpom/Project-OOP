package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import Manager.JukeBox;
import TileMap.TileMap;


public class Player extends Entity {

	// sprites
	private BufferedImage[] downSprites;
	private BufferedImage[] leftSprites;
	private BufferedImage[] rightSprites;
	private BufferedImage[] upSprites;
	private BufferedImage[] downBoatSprites;
	private BufferedImage[] leftBoatSprites;
	private BufferedImage[] rightBoatSprites;
	private BufferedImage[] upBoatSprites;
	
	// animation
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWNBOAT = 4;
	private final int LEFTBOAT = 5;
	private final int RIGHTBOAT = 6;
	private final int UPBOAT = 7;
	
	// gameplay
	private int numDuck;
	private int totalDuck;
	private boolean hasBlackKey;
	private boolean hasBlueKey;
	private boolean hasGrayKey;
	private boolean hasGrayKey1;
	private boolean hasGrayKey2;
	private boolean hasGrayKey3;
	private boolean hasGrayKey4;
	private boolean hasGreenKey;
	private boolean hasOrangeKey;
	private boolean hasPurpleKey;
	private boolean hasPlank;
	private boolean hasPlank1;
	private boolean onWater;
	private long ticks=0;
	private long tick=0;

	protected int health = 1;

	public Player(TileMap tm) {
		
		super(tm);
		
		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;
		
		moveSpeed = 6;
		
		numDuck = 0;

		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];
		downBoatSprites = Content.PLAYER[4];
		leftBoatSprites = Content.PLAYER[5];
		rightBoatSprites = Content.PLAYER[6];
		upBoatSprites = Content.PLAYER[7];
		
		animation.setFrames(downSprites);
		animation.setDelay(10);
		
	}
	
	private void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}
	
	public void collectedDuck() { numDuck++; }
	public int numDuck() { return numDuck; }
	public int getTotalDuck() { return totalDuck; }
	public void setTotalDuck(int i) { totalDuck = i; }
	
	public void gotBlackKey() { hasBlackKey = true; }
	public void gotBlueKey() { hasBlueKey = true; }
	public void gotGrayKey() { hasGrayKey = true; }
	public void gotGrayKey1() { hasGrayKey1 = true; }
	public void gotGrayKey2() { hasGrayKey2 = true; }
	public void gotGrayKey3() { hasGrayKey3 = true; }
	public void gotGrayKey4() { hasGrayKey4 = true; }
	public void gotGreenKey() { hasGreenKey = true; }
	public void gotOrangeKey() { hasOrangeKey = true; }
	public void gotPurpleKey() { hasPurpleKey = true; }
	public void gotPlank() { hasPlank = true; }
	public void gotPlank1() { hasPlank1 = true; }
	
	public boolean hasBlackKey() { return hasBlackKey; }
	public boolean hasBlueKey() { return hasBlueKey; }
	public boolean hasGrayKey() { return hasGrayKey; }
	public boolean hasGrayKey1() { return hasGrayKey1; }
	public boolean hasGrayKey2() { return hasGrayKey2; }
	public boolean hasGrayKey3() { return hasGrayKey3; }
	public boolean hasGrayKey4() { return hasGrayKey4; }
	public boolean hasGreenKey() { return hasGreenKey; }
	public boolean hasOrangeKey() { return hasOrangeKey; }
	public boolean hasPurpleKey() { return hasPurpleKey; }
	public boolean hasPlank() { return hasPlank; }
	public boolean hasPlank1() { return hasPlank1; }
	
	// Used to update time.
	public long getTicks() { return ticks; }
	
	// Keyboard input. Moves the player.
	public void setDown() {
		super.setDown();
	}
	public void setLeft() {
		super.setLeft();
	}
	public void setRight() {
		super.setRight();
	}
	public void setUp() {
		super.setUp();
	}

	
	public void openBlackDoor() {
		if(hasBlackKey) {
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 491 && tileMap.getIndex(rowTile - 1, colTile + 1) == 492) {
					tileMap.setTile(rowTile - 1, colTile, 29);
					tileMap.setTile(rowTile - 1, colTile + 1, 30);
					tileMap.setTile(rowTile - 2, colTile + 1, 28);
					tileMap.setTile(rowTile - 2, colTile, 27);
					JukeBox.load("/SFX/tilechange.wav", "tilechange");
					JukeBox.play("tilechange");
			}
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 492 && tileMap.getIndex(rowTile - 1, colTile - 1) == 491) {	
					tileMap.setTile(rowTile - 1, colTile, 30);
					tileMap.setTile(rowTile - 1, colTile - 1, 29); 
					tileMap.setTile(rowTile - 2, colTile - 1, 27); 
					tileMap.setTile(rowTile - 2, colTile, 28); 
					JukeBox.load("/SFX/tilechange.wav", "tilechange");
					JukeBox.play("tilechange");
			}
		}
	}

	public void openGrayDoor() { 
		if(hasGrayKey && hasGrayKey1 && hasGrayKey2 && hasGrayKey3){
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 481 && tileMap.getIndex(rowTile - 1, colTile + 1) == 482) {		
				tileMap.setTile(rowTile - 1, colTile, 33); 
				tileMap.setTile(rowTile - 1, colTile + 1, 34); 
				tileMap.setTile(rowTile - 2, colTile + 1, 32); 
				tileMap.setTile(rowTile - 2, colTile, 31); 
				JukeBox.load("/SFX/tilechange.wav", "tilechange");
				JukeBox.play("tilechange");
			}
		}
		if(hasGrayKey && hasGrayKey1 && hasGrayKey2 && hasGrayKey3){
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 482 && tileMap.getIndex(rowTile - 1, colTile - 1) == 481) {		
				tileMap.setTile(rowTile - 1, colTile, 34); 
				tileMap.setTile(rowTile - 1, colTile - 1, 33); 
				tileMap.setTile(rowTile - 2, colTile - 1, 31); 
				tileMap.setTile(rowTile - 2, colTile, 32); 
				JukeBox.load("/SFX/tilechange.wav", "tilechange");
				JukeBox.play("tilechange");
			}
		}
	}

	public void openBlueDoor() { 
		if(hasBlueKey) { 
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 473 && tileMap.getIndex(rowTile - 1, colTile + 1) == 474) {
					
					tileMap.setTile(rowTile - 1, colTile, 17); 
					tileMap.setTile(rowTile - 1, colTile + 1, 18); 
					tileMap.setTile(rowTile - 2, colTile + 1, 16); 
					tileMap.setTile(rowTile - 2, colTile, 15); 
					JukeBox.load("/SFX/tilechange.wav", "tilechange");
					JukeBox.play("tilechange");
			}
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 474 && tileMap.getIndex(rowTile - 1, colTile - 1) == 473) {	
					
					tileMap.setTile(rowTile - 1, colTile, 18); 
					tileMap.setTile(rowTile - 1, colTile - 1, 17); 
					tileMap.setTile(rowTile - 2, colTile - 1, 15); 
					tileMap.setTile(rowTile - 2, colTile, 16); 
					JukeBox.load("/SFX/tilechange.wav", "tilechange");
					JukeBox.play("tilechange");
			}
		}
	}

	public void openPurpleDoor() { 
		if(hasPurpleKey) { 
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 471 && tileMap.getIndex(rowTile - 1, colTile + 1) == 472) {
					
					tileMap.setTile(rowTile - 1, colTile, 25); 
					tileMap.setTile(rowTile - 1, colTile + 1, 26); 
					tileMap.setTile(rowTile - 2, colTile + 1, 24); 
					tileMap.setTile(rowTile - 2, colTile, 23); 
					JukeBox.load("/SFX/tilechange.wav", "tilechange");
					JukeBox.play("tilechange");
			}
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 472 && tileMap.getIndex(rowTile - 1, colTile - 1) == 471) {	
					
					tileMap.setTile(rowTile - 1, colTile, 26); 
					tileMap.setTile(rowTile - 1, colTile - 1, 25); 
					tileMap.setTile(rowTile - 2, colTile - 1, 23); 
					tileMap.setTile(rowTile - 2, colTile, 24); 
					JukeBox.load("/SFX/tilechange.wav", "tilechange");
					JukeBox.play("tilechange");
			}
		}
	}

	public void openGreenDoor() { 
		if(hasGreenKey) { 
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 485 ) {
				
				tileMap.setTile(rowTile, colTile - 1, 38); 
				tileMap.setTile(rowTile - 1, colTile - 1, 36); 
				tileMap.setTile(rowTile, colTile - 2, 37);
				tileMap.setTile(rowTile - 1, colTile - 2, 35);
				JukeBox.load("/SFX/tilechange.wav", "tilechange");
				JukeBox.play("tilechange");
			}
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 486 ) {
				
				tileMap.setTile(rowTile, colTile - 1, 36); 
				tileMap.setTile(rowTile + 1, colTile - 1, 38); 
				tileMap.setTile(rowTile, colTile - 2, 35);
				tileMap.setTile(rowTile + 1, colTile - 2, 37); 
				JukeBox.load("/SFX/tilechange.wav", "tilechange");
				JukeBox.play("tilechange");
			}
		}
	}

	public void openOrangeDoor() { 
		if(hasOrangeKey) { 
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 477 && tileMap.getIndex(rowTile - 1, colTile + 1) == 478) {
					
					tileMap.setTile(rowTile - 1, colTile, 21); 
					tileMap.setTile(rowTile - 1, colTile + 1, 22); 
					tileMap.setTile(rowTile - 2, colTile + 1, 20); 
					tileMap.setTile(rowTile - 2, colTile, 19); 
					JukeBox.load("/SFX/tilechange.wav", "tilechange");
					JukeBox.play("tilechange");
			}
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 478 && tileMap.getIndex(rowTile - 1, colTile - 1) == 477) {	
					
					tileMap.setTile(rowTile - 1, colTile, 22); 
					tileMap.setTile(rowTile - 1, colTile - 1, 21); 
					tileMap.setTile(rowTile - 2, colTile - 1, 19); 
					tileMap.setTile(rowTile - 2, colTile, 20); 
					JukeBox.load("/SFX/tilechange.wav", "tilechange");
					JukeBox.play("tilechange");
			}
		}
	}

	public void placePlank() { 
		if(hasPlank) { 
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 496 && tileMap.getIndex(rowTile - 1, colTile + 1) == 497) {
					tileMap.setTile(rowTile - 1, colTile, 49); 
					tileMap.setTile(rowTile - 1, colTile + 1, 50); 
					tileMap.setTile(rowTile - 2, colTile + 1, 48); 
					tileMap.setTile(rowTile - 2, colTile, 47); 
					JukeBox.load("/SFX/splash.wav", "splash");
					JukeBox.play("splash");
					hasPlank = false;
			}
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 497 && tileMap.getIndex(rowTile - 1, colTile - 1) == 496) {	
					tileMap.setTile(rowTile - 1, colTile, 50); 
					tileMap.setTile(rowTile - 1, colTile - 1, 49); 
					tileMap.setTile(rowTile - 2, colTile - 1, 47); 
					tileMap.setTile(rowTile - 2, colTile, 48); 
					JukeBox.load("/SFX/splash.wav", "splash");
					JukeBox.play("splash");
					hasPlank = false;
			}
		}
		else if(hasPlank1) { 
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 496 && tileMap.getIndex(rowTile - 1, colTile + 1) == 497) {
					
					tileMap.setTile(rowTile - 1, colTile, 49); 
					tileMap.setTile(rowTile - 1, colTile + 1, 50); 
					tileMap.setTile(rowTile - 2, colTile + 1, 48); 
					tileMap.setTile(rowTile - 2, colTile, 47); 
					JukeBox.load("/SFX/splash.wav", "splash");
					JukeBox.play("splash");
					hasPlank1 = false;
			}
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 497 && tileMap.getIndex(rowTile - 1, colTile - 1) == 496) {	
					
					tileMap.setTile(rowTile - 1, colTile, 50); 
					tileMap.setTile(rowTile - 1, colTile - 1, 49); 
					tileMap.setTile(rowTile - 2, colTile - 1, 47); 
					tileMap.setTile(rowTile - 2, colTile, 48); 
					JukeBox.load("/SFX/splash.wav", "splash");
					JukeBox.play("splash");
					hasPlank1 = false;
			}
		}
	}

	public void placePlank1() {
		if(hasPlank || hasPlank1) {
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 500 && tileMap.getIndex(rowTile - 1, colTile + 1) == 501) {
					tileMap.setTile(rowTile - 1, colTile, 49); 
					tileMap.setTile(rowTile - 1, colTile + 1, 50); 
					tileMap.setTile(rowTile - 2, colTile + 1, 48); 
					tileMap.setTile(rowTile - 2, colTile, 47); 
					JukeBox.load("/SFX/splash.wav", "splash");
					JukeBox.play("splash");
			}
			if(currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 501 && tileMap.getIndex(rowTile - 1, colTile - 1) == 500) {	
					tileMap.setTile(rowTile - 1, colTile, 50);
					tileMap.setTile(rowTile - 1, colTile - 1, 49);
					tileMap.setTile(rowTile - 2, colTile - 1, 47);
					tileMap.setTile(rowTile - 2, colTile, 48);
					JukeBox.load("/SFX/splash.wav", "splash");
					JukeBox.play("splash");
			}
		}
	}

	public void openGraySiteDoor() {
		if(hasGrayKey4) {
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 487 ) {
				tileMap.setTile(rowTile, colTile - 1, 46);
				tileMap.setTile(rowTile - 1, colTile - 1, 44);
				tileMap.setTile(rowTile, colTile - 2, 45);
				tileMap.setTile(rowTile - 1, colTile - 2, 43);
				JukeBox.load("/SFX/tilechange.wav", "tilechange");
				JukeBox.play("tilechange");
				tick++;
			}
			if(currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 488 ) {
				tileMap.setTile(rowTile, colTile - 1, 44);
				tileMap.setTile(rowTile + 1, colTile - 1, 46);
				tileMap.setTile(rowTile, colTile - 2, 43);
				tileMap.setTile(rowTile + 1, colTile - 2, 45);
				JukeBox.load("/SFX/tilechange.wav", "tilechange");
				JukeBox.play("tilechange");
				tick++;
			}
		}
	}

	public void update() {
		
		ticks++;
		if(tick > 0){
			tick++;
		}
		
		// check if on water
		boolean current = onWater;
		if(tileMap.getIndex(ydest / tileSize, xdest / tileSize) == 0) { // ถ้าพื้น เป็นเลข 0 ให้นั่งเรือในบล็อคนั้น
			onWater = true;
		}
		else {
			onWater = false;
		}
		// if going from land to water
		if(!current && onWater) {
			JukeBox.play("splash");
		}
		
		// set animation
		if(down) {
			if(onWater && currentAnimation != DOWNBOAT) {
				setAnimation(DOWNBOAT, downBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != DOWN) {
				setAnimation(DOWN, downSprites, 10);
			}
		}
		if(left) {
			if(onWater && currentAnimation != LEFTBOAT) {
				setAnimation(LEFTBOAT, leftBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != LEFT) {
				setAnimation(LEFT, leftSprites, 10);
			}
		}
		if(right) {
			if(onWater && currentAnimation != RIGHTBOAT) {
				setAnimation(RIGHTBOAT, rightBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != RIGHT) {
				setAnimation(RIGHT, rightSprites, 10);
			}
		}
		if(up) {
			if(onWater && currentAnimation != UPBOAT) {
				setAnimation(UPBOAT, upBoatSprites, 10);
			}
			else if(!onWater && currentAnimation != UP) {
				setAnimation(UP, upSprites, 10);
			}
		}
		
		// update position
		super.update();
		
	}
	
	// Draw Player.
	public void draw(Graphics2D g){
		super.draw(g);
		if(tick > 0 && tick < 80){
			Content.drawString(g, "Find 4 more keys", -1, 70);
			Content.drawString(g, "to exit castle", 12, 100);
		}
	}
	
}