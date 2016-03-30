package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bomb extends DadParticipate {
	public static final int WIDTH_IMG = 50;
	public static final int HEIGHT_IMG = 50;
	private static final int WIDTH_BOMBBANG = 150;
	private static final int HEIGHT_BOMBBANG = 150;
	private static final int WIDTH_BOMBBANG_DAMAGE2 = 250;
	private static final int HEIGHT_BOMBBANG_DAMAGE2 = 250;
	
	public static final int TIME_OUT_TO_BUM = 3000;
	public static final int TIME_OUT_TO_REMOVE_BOMB = 1000;

	private int timeOutToBum = TIME_OUT_TO_BUM;
	private int timeOutToRemoveBomb = TIME_OUT_TO_REMOVE_BOMB;
	private int damge;

	public Bomb(int x, int y, int size, int orient, int speed, int damge) {
		super(x, y, size, orient, speed);
		this.damge = damge;
	}

	public void setDamage(int damage) {
		this.damge = damage;
	}
	public int getDamge() {
		return damge;
	}

	public int getTimeOutToRemoveBomb() {
		return timeOutToRemoveBomb;
	}

	public int getTimeOutToBum() {
		return timeOutToBum;
	}

	public void draw(Graphics2D g2d) {
		if (timeOutToBum > 0) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/bomb.png"));
			Image imgBomb = imageIcon.getImage();
			g2d.drawImage(imgBomb, getX(), getY(), WIDTH_IMG, HEIGHT_IMG, null);
		}
		if (timeOutToBum <= 0 && timeOutToRemoveBomb > 0 && damge==1) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/bombbang.png"));
			Image imgBomb = imageIcon.getImage();
			g2d.drawImage(imgBomb, getX() - WIDTH_IMG, getY() - HEIGHT_IMG,
					WIDTH_BOMBBANG, HEIGHT_BOMBBANG, null);
		}
		if (timeOutToBum <= 0 && timeOutToRemoveBomb > 0 && damge>=2) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/bombbang.png"));
			Image imgBomb = imageIcon.getImage();
			g2d.drawImage(imgBomb, getX() - WIDTH_IMG, getY() - HEIGHT_IMG,
					WIDTH_BOMBBANG, HEIGHT_BOMBBANG, null);
		}
	}

	public boolean removeBomb() {
		if (timeOutToBum <= 0) {
			return true;
		} else
			return false;
	}

	public void downTimeOutToBum(int time) {
		timeOutToBum -= time;
	}

	public void downTimeOutToRemoveBomb(int time) {
		timeOutToRemoveBomb -= time;
	}

	public boolean checkCollisionWithComponents(ComponentMap comps) {
		Rectangle recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG,
				WIDTH_IMG, HEIGHT_BOMBBANG);
		Rectangle recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),
				WIDTH_BOMBBANG, HEIGHT_IMG);
		Rectangle recComp = new Rectangle(comps.getX(), comps.getY(),
				comps.SIZE, comps.SIZE);
		if (recBombHorizontal.intersects(recComp)
				|| recBombVertical.intersects(recComp)) {
			// System.out.println(comps.getX()+"-" + comps.getY());
			return true;
		} else
			return false;
	}
	
	public boolean checkCollisionWithMonster(Monster monster) {
		Rectangle recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG,
				WIDTH_IMG, HEIGHT_BOMBBANG);
		Rectangle recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),
				WIDTH_BOMBBANG, HEIGHT_IMG);
		Rectangle recMonster = new Rectangle(monster.getX(), monster.getY(),
				monster.WIDTH_IMG, monster.WIDTH_IMG);
		if (recBombHorizontal.intersects(recMonster)
				|| recBombVertical.intersects(recMonster)) {
			return true;
		} else
			return false;
	}

	public boolean checkCollisionWithBombPlayer(BombPlayer bombPlayer) {
		Rectangle recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG,
				WIDTH_IMG, HEIGHT_BOMBBANG);
		Rectangle recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),
				WIDTH_BOMBBANG, HEIGHT_IMG);
		Rectangle recBombPlayer = new Rectangle(bombPlayer.getX(), bombPlayer.getY(),
				bombPlayer.WIDTH_IMG, bombPlayer.WIDTH_IMG);
		if (recBombHorizontal.intersects(recBombPlayer)
				|| recBombVertical.intersects(recBombPlayer)) {
			return true;
		} else
			return false;
	}
	
}
