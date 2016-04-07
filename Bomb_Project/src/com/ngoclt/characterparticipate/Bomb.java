package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bomb extends BombFather {

	public static final int WIDTH_IMG = 50;
	public static final int HEIGHT_IMG = 50;
	public static final int WIDTH_BOMBBANG = 140;
	public static final int HEIGHT_BOMBBANG = 140;
	public static final int WIDTH_BOMBBANG_DAMAGE2 = 240;
	public static final int HEIGHT_BOMBBANG_DAMAGE2 = 240;

	public static final int TIME_OUT_TO_BUM = 3000;
	public static final int TIME_OUT_TO_REMOVE_BOMB = 1000;

	public static final int DAMAGE_ONE = 1;
	public static final int DAMAGE_TWO = 2;
	
	public static final int PLAYER1_OWN_BOMB = 1;
	public static final int PLAYER2_OWN_BOMB = 2;
	private int timeOutToBum = TIME_OUT_TO_BUM;
	private int timeOutToRemoveBomb = TIME_OUT_TO_REMOVE_BOMB;
	private int damge;
	private ImageIcon imageIconBomb, imageIconBombVertical,
			imageIconBombHorizontal;
	private Image imgBomb;
	private Rectangle recBombVertical, recBombHorizontal;
	private int playerOwnBomb;

	public Bomb(int x, int y, int size, int orient, int damge,
			int playerOwnBomb ) {
		super(x, y, size, orient);
		this.damge = damge;
		this.playerOwnBomb = playerOwnBomb;
	}

	public int getPlayerOwnBomb() {
		return playerOwnBomb;
	}

	public void setDamage(int damage) {
		this.damge = damage;
	}

	public static int getWidthImg() {
		return WIDTH_IMG;
	}

	public static int getHeightImg() {
		return HEIGHT_IMG;
	}
	
	public static int getPlayer1OwnBomb() {
		return PLAYER1_OWN_BOMB;
	}

	public static int getPlayer2OwnBomb() {
		return PLAYER2_OWN_BOMB;
	}

	public int getDamge() {
		return damge;
	}

	public int getTimeOutToRemoveBomb() {
		return timeOutToRemoveBomb;
	}

	public void setTimeOutToBum(int timeOutToBum) {
		this.timeOutToBum = timeOutToBum;
	}

	public int getTimeOutToBum() {
		return timeOutToBum;
	}

	private int timeChangeImg = 0;

	public void draw(Graphics2D g2d) {
		if (timeOutToBum > 0) {
			if (playerOwnBomb ==PLAYER1_OWN_BOMB) {
				timeChangeImg++;
				int i = ((timeChangeImg % 21) / 3) + 1;
				imageIconBomb = new ImageIcon(getClass().getResource("/imagebomb/bomb" + i + ".png"));
			}
			if (playerOwnBomb==PLAYER2_OWN_BOMB) {
				timeChangeImg++;
				int i = ((timeChangeImg % 10) / 5) + 1;
				imageIconBomb = new ImageIcon(getClass().getResource("/imagebomb/bombPlayer2_" + i +".png"));
			}
			imgBomb = imageIconBomb.getImage();
			g2d.drawImage(imgBomb, getX(), getY(), WIDTH_IMG, HEIGHT_IMG, null);
		}
		if (timeOutToBum <= 0 && timeOutToRemoveBomb > 0 && damge == DAMAGE_ONE) {
			imageIconBombHorizontal = new ImageIcon(getClass().getResource("/image/bombang_horizontal.png"));
			imgBomb = imageIconBombHorizontal.getImage();
			g2d.drawImage(imgBomb, getX() - WIDTH_IMG, getY(), WIDTH_BOMBBANG,HEIGHT_IMG, null);

			imageIconBombVertical = new ImageIcon(getClass().getResource("/image/bombang_vertical.png"));
			Image imgBomb2 = imageIconBombVertical.getImage();
			g2d.drawImage(imgBomb2, getX(), getY() - HEIGHT_IMG, WIDTH_IMG,HEIGHT_BOMBBANG, null);

		}
		if (timeOutToBum <= 0 && timeOutToRemoveBomb > 0 && damge >= DAMAGE_TWO) {
			imageIconBombHorizontal = new ImageIcon(getClass().getResource("/image/bombang_horizontal.png"));
			imgBomb = imageIconBombHorizontal.getImage();
			g2d.drawImage(imgBomb, getX() - DAMAGE_TWO * WIDTH_IMG, getY(),WIDTH_BOMBBANG_DAMAGE2, HEIGHT_IMG, null);

			imageIconBombVertical = new ImageIcon(getClass().getResource("/image/bombang_vertical.png"));
			Image imgBomb2 = imageIconBombVertical.getImage();
			g2d.drawImage(imgBomb2, getX(), getY() - DAMAGE_TWO * HEIGHT_IMG, WIDTH_IMG,HEIGHT_BOMBBANG_DAMAGE2, null);
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
		if (getDamge() == DAMAGE_ONE) {
			recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG, WIDTH_IMG, HEIGHT_BOMBBANG);
			recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),WIDTH_BOMBBANG, HEIGHT_IMG);
		}
		if (getDamge() >= DAMAGE_TWO) {
			recBombVertical = new Rectangle(getX(), getY() - DAMAGE_TWO * HEIGHT_IMG,WIDTH_IMG, HEIGHT_BOMBBANG_DAMAGE2);
			recBombHorizontal = new Rectangle(getX() - DAMAGE_TWO * WIDTH_IMG, getY(),WIDTH_BOMBBANG_DAMAGE2, HEIGHT_IMG);
		}
		Rectangle recComp = new Rectangle(comps.getX(), comps.getY(),ComponentMap.getSize(), ComponentMap.getSize());
		if (recBombHorizontal.intersects(recComp)|| recBombVertical.intersects(recComp)) {
			return true;
		} else
			return false;
	}

	public boolean checkCollisionWithMonster(Monster monster) {
		if (getDamge() == DAMAGE_ONE) {
			recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG, WIDTH_IMG, HEIGHT_BOMBBANG);
			recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),WIDTH_BOMBBANG, HEIGHT_IMG);
		}
		if (getDamge() >= DAMAGE_TWO) {
			recBombVertical = new Rectangle(getX(), getY() - 2 * HEIGHT_IMG,WIDTH_IMG, HEIGHT_BOMBBANG_DAMAGE2);
			recBombHorizontal = new Rectangle(getX() - 2*WIDTH_IMG, getY(),WIDTH_BOMBBANG_DAMAGE2, HEIGHT_IMG);

		}
		Rectangle recMonster = new Rectangle(monster.getX(), monster.getY(), Monster.getWidthImg(), Monster.getHeightImg());
		if (recBombHorizontal.intersects(recMonster)|| recBombVertical.intersects(recMonster)) {
			return true;
		} else
			return false;
	}

	public boolean checkCollisionWithBombPlayer(BombPlayer bombPlayer) {
		if (getDamge() == DAMAGE_ONE) {
			recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG,WIDTH_IMG, HEIGHT_BOMBBANG);
			recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),WIDTH_BOMBBANG, HEIGHT_IMG);
		}
		if (getDamge() >= DAMAGE_TWO) {
			recBombVertical = new Rectangle(getX(), getY() - 2 * HEIGHT_IMG,WIDTH_IMG, HEIGHT_BOMBBANG_DAMAGE2);
			recBombHorizontal = new Rectangle(getX() - 2 * WIDTH_IMG, getY(),WIDTH_BOMBBANG_DAMAGE2, HEIGHT_IMG);
		}
		Rectangle recBombPlayer = new Rectangle(bombPlayer.getX(),bombPlayer.getY(), BombPlayer.getWidthImg(), BombPlayer.getHeightImg());
		if (recBombHorizontal.intersects(recBombPlayer)|| recBombVertical.intersects(recBombPlayer)) {
			return true;
		} else
			return false;
	}

	public boolean checkCollisionWithAnotherBomb(Bomb anotherBomb) {
		if (getDamge() == DAMAGE_ONE) {
			recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG,WIDTH_IMG, HEIGHT_BOMBBANG);
			recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),WIDTH_BOMBBANG, HEIGHT_IMG);
		}
		if (getDamge() >= DAMAGE_TWO) {
			recBombVertical = new Rectangle(getX(), getY() - 2 * HEIGHT_IMG,WIDTH_IMG, HEIGHT_BOMBBANG_DAMAGE2);
			recBombHorizontal = new Rectangle(getX() - 2 * WIDTH_IMG, getY(),WIDTH_BOMBBANG_DAMAGE2, HEIGHT_IMG);
		}
		Rectangle recAnotherBomb = new Rectangle(anotherBomb.getX(),anotherBomb.getY(), Bomb.getWidthImg(),Bomb.HEIGHT_IMG);
		if (recBombHorizontal.intersects(recAnotherBomb)|| recBombVertical.intersects(recAnotherBomb)) {
			return true;
		} else
			return false;
	}

	public boolean checkCollisionWithBoss(Boss boss) {
		if (getDamge() == DAMAGE_ONE) {
			recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG,WIDTH_IMG, HEIGHT_BOMBBANG);
			recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),WIDTH_BOMBBANG, HEIGHT_IMG);
		}
		if (getDamge() >= DAMAGE_TWO) {
			recBombVertical = new Rectangle(getX(), getY() - 2 * HEIGHT_IMG,WIDTH_IMG, HEIGHT_BOMBBANG_DAMAGE2);
			recBombHorizontal = new Rectangle(getX() - 2 * WIDTH_IMG, getY(),WIDTH_BOMBBANG_DAMAGE2, HEIGHT_IMG);
		}
		Rectangle recBoss = new Rectangle(boss.getX(),boss.getY(), Boss.getWidthImg(),Boss.HEIGHT_IMG);
		if (recBombHorizontal.intersects(recBoss)|| recBombVertical.intersects(recBoss)) {
			return true;
		} else
			return false;
	}
}
