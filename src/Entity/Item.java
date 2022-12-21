package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import TileMap.TileMap;

public class Item extends Entity{
	
	private BufferedImage sprite;
	private int type;
	public static final int PLANK = 0;
	public static final int PLANK1 = 1;
	public static final int BLACKKEY = 2;
	public static final int BLUEKEY = 3;
	public static final int GRAYKEY = 4;
	public static final int GRAYKEY1 = 5;
	public static final int GRAYKEY2 = 6;
	public static final int GRAYKEY3 = 7;
	public static final int GREENKEY = 8;
	public static final int ORANGEKEY = 9;
	public static final int PURPLEKEY = 10;
	public static final int GRAYKEY4 = 11;
	
	public Item(TileMap tm) {
		super(tm);
		type = -1;
		width = height = 16;
		cwidth = cheight = 12;
	}
	
	public void setType(int i) {
		type = i;
		if(type == PLANK) {
			sprite = Content.PLANK[0][0];
		}
		else if(type == PLANK1) {
			sprite = Content.PLANK[0][0];
		}
		else if(type == BLACKKEY) {
			sprite = Content.BLACKKEY[0][0];
		}
		else if(type == BLUEKEY) {
			sprite = Content.BLUEKEY[0][0];
		}
		else if(type == GRAYKEY) {
			sprite = Content.GRAYKEY[0][0];
		}
		else if(type == GRAYKEY1) {
			sprite = Content.GRAYKEY[0][0];
		}
		else if(type == GRAYKEY2) {
			sprite = Content.GRAYKEY[0][0];
		}
		else if(type == GRAYKEY3) {
			sprite = Content.GRAYKEY[0][0];
		}
		else if(type == GREENKEY) {
			sprite = Content.GREENKEY[0][0];
		}
		else if(type == ORANGEKEY) {
			sprite = Content.ORANGEKEY[0][0];
		}
		else if(type == PURPLEKEY) {
			sprite = Content.PURPLEKEY[0][0];
		}
		else if(type == GRAYKEY4) {
			sprite = Content.GRAYKEY[0][0];
		}
	}
	
	public void collected(Player p) {
		if(type == BLACKKEY) {
			p.gotBlackKey();
		}
		if(type == BLUEKEY) {
			p.gotBlueKey();
		}
		if(type == GRAYKEY){
			p.gotGrayKey();
		}
		if(type == GRAYKEY1){
			p.gotGrayKey1();
		}
		if(type == GRAYKEY2){
			p.gotGrayKey2();
		}
		if(type == GRAYKEY3){
			p.gotGrayKey3();
		}
		if(type == GREENKEY){
			p.gotGreenKey();
		}
		if(type == ORANGEKEY){
			p.gotOrangeKey();
		}
		if(type == PURPLEKEY){
			p.gotPurpleKey();
		}
		if(type == PLANK){
			p.gotPlank();
		}
		if(type == PLANK1){
			p.gotPlank1();
		}
		if(type == GRAYKEY4){
			p.gotGrayKey4();
		}
	}
	
	public void draw(Graphics2D g) {
		setMapPosition();
		g.drawImage(sprite, x + xmap - width / 2, y + ymap - height / 2, null);
	}
	
}
