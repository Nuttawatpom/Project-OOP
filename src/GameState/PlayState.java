package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Entity.Duck;
import Entity.Item;
import Entity.Player;
import Entity.Sparkle;
import HUD.Hud;
import Main.GamePanel;
import Manager.Data;
import Manager.GameStateManager;
import Manager.JukeBox;
import Manager.Keys;
import TileMap.TileMap;

public class PlayState extends GameState {
	
	// player
	private Player player;
	
	// tilemap
	private TileMap tileMap;
	
	// duck
	private ArrayList<Duck> duck;
	
	// items
	private ArrayList<Item> items;
	
	// sparkles
	private ArrayList<Sparkle> sparkles;
	
	// camera position
	private int xsector;
	private int ysector;
	private int sectorSize; 
	
	// hud
	private Hud hud;
	
	// events
	private boolean blockInput;
	private boolean eventStart;
	private boolean eventFinish;
	private int eventTick;
	
	// transition box
	private ArrayList<Rectangle> boxes;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		// create lists
		duck = new ArrayList<Duck>();
		sparkles = new ArrayList<Sparkle>();
		items = new ArrayList<Item>();
		
		// load map
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/testtileset.gif");
		tileMap.loadMap("/Maps/testmap.map");
		
		// create player
		player = new Player(tileMap);
		
		// fill lists
		populateDuck();
		populateItems();
		
		// initialize player
		player.setTilePosition(150, 28);
		player.setTotalDuck(duck.size());
		
		// set up camera position
		sectorSize = GamePanel.WIDTH;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);
		
		// load hud
		hud = new Hud(player, duck);
		
		// load music
		JukeBox.load("/Music/bgmusic.mp3", "music1");
		JukeBox.loop("music1", 1000, 1000, JukeBox.getFrames("music1") - 1000);
		JukeBox.setVolume("music1", -5);
		JukeBox.load("/Music/finish.mp3", "finish");
		JukeBox.setVolume("finish", -10);
		
		// load sfx
		JukeBox.load("/SFX/collect.wav", "collect");
		JukeBox.setVolume("collect", -10);
		JukeBox.load("/SFX/collect2.wav", "collect2");
		JukeBox.setVolume("collect2", -10);
		JukeBox.load("/SFX/mapmove.wav", "mapmove");
		JukeBox.load("/SFX/tilechange.wav", "tilechange");
		JukeBox.setVolume("tilechange", 5);
		
		// start event
		boxes = new ArrayList<Rectangle>();
		eventStart = true;
		eventStart();
			
	}
	
	private void populateDuck() {
		
		Duck d;
		
		d = new Duck(tileMap);
		d.setTilePosition(10, 72);
		duck.add(d);
	}

	private void populateItems() {
		
		Item item;

		item = new Item(tileMap);
		item.setType(Item.GREENKEY);
		item.setTilePosition(116, 12);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.BLUEKEY);
		item.setTilePosition(107, 57);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.PURPLEKEY);
		item.setTilePosition(98, 51);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.BLACKKEY);
		item.setTilePosition(93, 10);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.ORANGEKEY);
		item.setTilePosition(71, 1);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.PLANK);
		item.setTilePosition(125, 10);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.PLANK1);
		item.setTilePosition(97, 48);
		items.add(item);

//============================================= ปราสาท
		item = new Item(tileMap);
		item.setType(Item.GRAYKEY);
		item.setTilePosition(28, 3);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.GRAYKEY1);
		item.setTilePosition(21, 34);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.GRAYKEY2);
		item.setTilePosition(55, 53);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.GRAYKEY3);
		item.setTilePosition(43, 25);
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.GRAYKEY4);
		item.setTilePosition(63, 17);
		items.add(item);
	}
	
	public void update() {
		
		// check keys
		handleInput();
		
		// check events
		if(eventStart) eventStart();
		if(eventFinish) eventFinish();
		
		if(player.numDuck() == player.getTotalDuck()) {
			eventFinish = blockInput = true;
		}
		
		// update camera
		int oldxs = xsector;
		int oldys = ysector;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
		tileMap.update();
		
		if(oldxs != xsector || oldys != ysector) {
			JukeBox.play("mapmove");
		}
		
		if(tileMap.isMoving()) return;
		
		// update player
		player.update();
		
		// update duck
		for(int i = 0; i < duck.size(); i++) {
			
			Duck d = duck.get(i);
			d.update();
			
			// player collects duck
			if(player.intersects(d)) {
				
				// remove from list
				duck.remove(i);
				i--;
				
				// increment amount of collected duck
				player.collectedDuck();
				
				// play collect sound
				JukeBox.play("collect");
				
				// make any changes to tile map
				ArrayList<int[]> ali = d.getChanges();
				for(int[] j : ali) {
					tileMap.setTile(j[0], j[1], j[2]);
				}
				if(ali.size() != 0) {
					JukeBox.play("tilechange");
				}
				
			}
		}

		// update sparkles
		for(int i = 0; i < sparkles.size(); i++) {
			Sparkle s = sparkles.get(i);
			s.update();
			if(s.shouldRemove()) {
				sparkles.remove(i);
				i--;
			}
		}
		
		// update items
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if(player.intersects(item)) {
				items.remove(i);
				i--;
				item.collected(player);
				JukeBox.play("collect2");
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(item.getx(), item.gety());
				sparkles.add(s);
			}
		}
	}
	
	public void draw(Graphics2D g) {
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw duck
		for(Duck d : duck) {
			d.draw(g);
		}
		
		// draw sparkles
		for(Sparkle s : sparkles) {
			s.draw(g);
		}
		
		// draw items
		for(Item i : items) {
			i.draw(g);
		}

		// draw hud
		hud.draw(g);
		
		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < boxes.size(); i++) {
			g.fill(boxes.get(i));
		}
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			JukeBox.stop("music1");
			gsm.setPaused(true);
		}
		if(blockInput) return;
		if(Keys.isDown(Keys.LEFT)) player.setLeft();
		if(Keys.isDown(Keys.RIGHT)) player.setRight();
		if(Keys.isDown(Keys.UP)) player.setUp();
		if(Keys.isDown(Keys.DOWN)) player.setDown();
		if(Keys.isPressed(Keys.SPACE)) player.openBlackDoor();
		if(Keys.isPressed(Keys.SPACE)) player.openGrayDoor();
		if(Keys.isPressed(Keys.SPACE)) player.openBlueDoor();
		if(Keys.isPressed(Keys.SPACE)) player.openGreenDoor();
		if(Keys.isPressed(Keys.SPACE)) player.openOrangeDoor();
		if(Keys.isPressed(Keys.SPACE)) player.openPurpleDoor();
		if(Keys.isPressed(Keys.SPACE)) player.placePlank();
		if(Keys.isPressed(Keys.SPACE)) player.placePlank1();
		if(Keys.isPressed(Keys.SPACE)) player.openGraySiteDoor();
	}
	
	//===============================================
	
	private void eventStart() {
		eventTick++;
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				boxes.add(new Rectangle(0, i * 32, GamePanel.WIDTH, 32));
			}
		}
		if(eventTick > 1 && eventTick < 32) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					r.x -= 4;
				}
				else {
					r.x += 4;
				}
			}
		}
		if(eventTick == 33) {
			boxes.clear();
			eventStart = false;
			eventTick = 0;
		}
	}
	
	private void eventFinish() {
		eventTick++;
		if(eventTick == 1) {
			boxes.clear();
			for(int i = 0; i < 9; i++) {
				if(i % 2 == 0) boxes.add(new Rectangle(-128, i * 32, GamePanel.WIDTH, 32));
				else boxes.add(new Rectangle(128, i * 32, GamePanel.WIDTH, 32));
			}
			JukeBox.stop("music1");
			JukeBox.play("finish");
		}
		if(eventTick > 1) {
			for(int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if(i % 2 == 0) {
					if(r.x < 0) r.x += 4;
				}
				else {
					if(r.x > 0) r.x -= 4;
				}
			}
		}
		if(eventTick > 33) {
			if(!JukeBox.isPlaying("finish")) {
				Data.setTime(player.getTicks());
				gsm.setState(GameStateManager.GAMEOVER);
			}
		}
	}
	
}
