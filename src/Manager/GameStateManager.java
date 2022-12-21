package Manager;

import java.awt.Graphics2D;

import GameState.GameOverState;
import GameState.GameState;
import GameState.IntroState;
import GameState.IntroState1;
import GameState.CreditState;
import GameState.HowToPlayState;
import GameState.HowToPlayState2;
import GameState.MenuState;
import GameState.PauseState;
import GameState.PlayState;


public class GameStateManager {
	
	private boolean paused;
	private PauseState pauseState;
	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	public static final int NUM_STATES = 8;
	public static final int INTRO = 0;
	public static final int INTRO1 = 1;
	public static final int MENU = 2;
	public static final int PLAY = 3;
	public static final int CREDIT = 4;
	public static final int HOWTOPLAY = 5;
	public static final int HOWTOPLAY2 = 6;
	public static final int GAMEOVER = 7;
	
	public GameStateManager() {
		
		JukeBox.init();
		
		paused = false;
		pauseState = new PauseState(this);
		
		gameStates = new GameState[NUM_STATES];
		setState(INTRO);
		
	}
	
	public void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if(i == INTRO) {
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		}
		if(i == INTRO1) {
			gameStates[i] = new IntroState1(this);
			gameStates[i].init();
		}
		else if(i == MENU) {
			gameStates[i] = new MenuState(this);
			gameStates[i].init();
		}
		else if(i == PLAY) {
			gameStates[i] = new PlayState(this);
			gameStates[i].init();
		}
		else if(i == CREDIT) {
			gameStates[i] = new CreditState(this);
			gameStates[i].init();
		}
		else if(i == HOWTOPLAY) {
			gameStates[i] = new HowToPlayState(this);
			gameStates[i].init();
		}
		else if(i == HOWTOPLAY2) {
			gameStates[i] = new HowToPlayState2(this);
			gameStates[i].init();
		}
		else if(i == GAMEOVER) {
			gameStates[i] = new GameOverState(this);
			gameStates[i].init();
		}
	}
	
	public void unloadState(int i) {
		gameStates[i] = null;
	}
	
	public void setPaused(boolean b) {
		paused = b;
	}
	
	public void update() {
		if(paused) {
			pauseState.update();
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
	}
	
	public void draw(Graphics2D g) {
		if(paused) {
			pauseState.draw(g);
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		}
	}
	
}
