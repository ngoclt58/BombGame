package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.ngoclt.guiandpanel.GUI;

public class BombPlayer extends DadParticipate {

	public static final int WIDTH_IMG = 45;
	public static final int HEIGHT_IMG = 45;
	public static final int WIDTH_CHARACTER = 45;
	public static final int HEIGHT_CHARACTER = 55;
	
	public static final int START_X_PLAYER1 = 650;
	public static final int START_Y_PLAYER1 = 0;
	public static final int START_X_PLAYER2 = 0;
	public static final int START_Y_PLAYER2 = 0;

	public static final int PLAYER1 = 1;
	public static final int PLAYER2 = 2;

	public static final int TIME_OUT_TO_DEAD = 3000;

	public Image imaArr[] = new Image[4];
	private boolean maxSpeed = false;
	private int numBombCanPut = 1;
	private int typePlayer;
	private boolean bombPlayerIsCollisionWithBomb = false;
	private boolean bombPlayerIsCollisionWithMonster = false;
	private ImageIcon imageIconBombPlayer, imageIconDeath;
	private int timeOutToDead = TIME_OUT_TO_DEAD;
	private int numsHeart = 3;

	public BombPlayer(int x, int y, int size, int orient, int speed,
			int speedFire, int typePlayer) {
		super(x, y, size, orient, speed);
		this.typePlayer = typePlayer;
	}

	public static int getWidthImg() {
		return WIDTH_IMG;
	}

	public static int getHeightImg() {
		return HEIGHT_IMG;
	}

	public static int getStartXPlayer1() {
		return START_X_PLAYER1;
	}

	public static int getStartYPlayer1() {
		return START_Y_PLAYER1;
	}

	public static int getStartXPlayer2() {
		return START_X_PLAYER2;
	}

	public static int getStartYPlayer2() {
		return START_Y_PLAYER2;
	}

	public boolean isBombPlayerIsCollisionWithMonster() {
		return bombPlayerIsCollisionWithMonster;
	}

	public ImageIcon getImageIconBombPlayer() {
		return imageIconBombPlayer;
	}

	public ImageIcon getImageIconDeath() {
		return imageIconDeath;
	}

	public int getNumsHeart() {
		return numsHeart;
	}

	public void setNumsHeart(int numsHeart) {
		this.numsHeart = numsHeart;
	}

	public int getTypePlayer() {
		return typePlayer;
	}

	public boolean getMaxSpeed() {
		return maxSpeed;
	}

	public int getNumBombCanPut() {
		return numBombCanPut;
	}

	public void setBombPlayerIsCollisionWithMonster(
			boolean bombPlayerIsCollisionWithMonster) {
		this.bombPlayerIsCollisionWithMonster = bombPlayerIsCollisionWithMonster;
	}

	public boolean getBombPlayerIsCollisionWithBomb() {
		return bombPlayerIsCollisionWithBomb;
	}

	public boolean getBombPlayerIsDead() {
		return bombPlayerIsCollisionWithBomb;
	}

	public void setBombPlayerIsDead(boolean bombPlayerIsDead) {
		this.bombPlayerIsCollisionWithBomb = bombPlayerIsDead;
	}

	public void setNumBombCanPut(int numBombCanPut) {
		this.numBombCanPut = numBombCanPut;
	}

	public void setMaxSpeed(boolean maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void draw(Graphics2D g2d) {
		if (getOrient() == LEFT) {
			if (typePlayer == PLAYER1) {
				imageIconBombPlayer = new ImageIcon(getClass().getResource(
						"/image/bomber_left.png"));
			} else {
				imageIconBombPlayer = new ImageIcon(getClass().getResource(
						"/image/bombplayer2_left.png"));
			}
			imaArr[0] = imageIconBombPlayer.getImage();
			g2d.drawImage(imaArr[0], getX(), getY(), WIDTH_CHARACTER, HEIGHT_CHARACTER,
					null);
		}
		if (getOrient() == RIGHT) {
			if (typePlayer == PLAYER1) {
				imageIconBombPlayer = new ImageIcon(getClass().getResource(
						"/image/bomber_right.png"));
			} else {
				imageIconBombPlayer = new ImageIcon(getClass().getResource(
						"/image/bombplayer2_right.png"));
			}
			imaArr[1] = imageIconBombPlayer.getImage();
			g2d.drawImage(imaArr[1], getX(), getY(), WIDTH_CHARACTER, HEIGHT_CHARACTER,
					null);
		}
		if (getOrient() == UP) {
			if (typePlayer == PLAYER1) {
				imageIconBombPlayer = new ImageIcon(getClass().getResource(
						"/image/bomber_up.png"));
			} else {
				imageIconBombPlayer = new ImageIcon(getClass().getResource(
						"/image/bombplayer2_up.png"));
			}
			imaArr[2] = imageIconBombPlayer.getImage();
			g2d.drawImage(imaArr[2], getX(), getY(), WIDTH_CHARACTER, HEIGHT_CHARACTER,
					null);
		}
		if (getOrient() == DOWN) {
			if (typePlayer == PLAYER1) {
				imageIconBombPlayer = new ImageIcon(getClass().getResource(
						"/image/bomber_down.png"));
			} else {
				imageIconBombPlayer = new ImageIcon(getClass().getResource(
						"/image/bombplayer2_down.png"));
			}
			imaArr[3] = imageIconBombPlayer.getImage();
			g2d.drawImage(imaArr[3], getX(), getY(), WIDTH_CHARACTER, HEIGHT_CHARACTER,
					null);
		}

	}

	public void move(int time) {
		if (getOrient() == LEFT && getX() >= 0) {
			setX(getX() - getSpeed());
		}
		if (getOrient() == RIGHT && getX() <= GUI.WIDTH - WIDTH_IMG) {
			setX(getX() + getSpeed());
		}
		if (getOrient() == UP && getY() >= 0) {
			setY(getY() - getSpeed());
		}
		if (getOrient() == DOWN && getY() <= GUI.HEIGHT - HEIGHT_IMG) {
			setY(getY() + getSpeed());
		}
	}

	public boolean isFire() {
		if (numBombCanPut > 0) {
			return true;
		} else
			return false;
	}

	public void changeOrient(int newOrient) {
		setOrient(newOrient);
	}

	public void setTimeOutToDead(int time) {
		timeOutToDead -= time;
		if (timeOutToDead <= 0) {
			timeOutToDead = TIME_OUT_TO_DEAD;
			if (typePlayer == 1) {
				setX(START_X_PLAYER1);
				setY(START_Y_PLAYER1);
			}
			if (typePlayer == 2) {
				setX(START_X_PLAYER2);
				setY(START_Y_PLAYER2);
			}
			bombPlayerIsCollisionWithBomb = false;
			numsHeart--;
		}
	}

	public boolean checkCollisionWithComponentsToMove(ComponentMap comp) {
		int newX = getX();
		int newY = getY();
		switch (getOrient()) {
		case LEFT:
			newX -= getSpeed();
			break;
		case RIGHT:
			newX += getSpeed();
			break;
		case UP:
			newY -= getSpeed();
			break;
		case DOWN:
			newY += getSpeed();
			break;
		default:
			break;
		}
		Rectangle rectBomer = new Rectangle(newX, newY, WIDTH_IMG, HEIGHT_IMG);
		Rectangle rectComp = new Rectangle(comp.getX(), comp.getY(), ComponentMap.getSize(),
				ComponentMap.getSize());
		return rectBomer.intersects(rectComp);
	}

	public boolean checkCollisionWithBossToMove(Boss boss) {
		int newX = getX();
		int newY = getY();
		switch (getOrient()) {
		case LEFT:
			newX -= getSpeed();
			break;
		case RIGHT:
			newX += getSpeed();
			break;
		case UP:
			newY -= getSpeed();
			break;
		case DOWN:
			newY += getSpeed();
			break;
		default:
			break;
		}
		Rectangle rectBomer = new Rectangle(newX, newY, WIDTH_IMG, HEIGHT_IMG);
		Rectangle recBoss = new Rectangle(boss.getX(), boss.getY(), Boss.getWidthImg(),
				Boss.getHeightImg());
		return rectBomer.intersects(recBoss);
	}
	
	public boolean checkCollisionWithBombToMove(Bomb bomb) {
		int newX = getX();
		int newY = getY();
		switch (getOrient()) {
		case LEFT:
			newX -= getSpeed();
			break;
		case RIGHT:
			newX += getSpeed();
			break;
		case UP:
			newY -= getSpeed();
			break;
		case DOWN:
			newY += getSpeed();
			break;
		default:
			break;
		}
		Rectangle rectBomer = new Rectangle(newX, newY, WIDTH_IMG, HEIGHT_IMG);
		Rectangle rectBomb = new Rectangle(bomb.getX(), bomb.getY(),
				Bomb.getWidthImg(), Bomb.getHeightImg());
		return rectBomer.intersects(rectBomb);
	}

	public void drawDeadByCollisionWithBomb(Graphics2D g2d) {
		if (bombPlayerIsCollisionWithBomb == true) {
			imageIconDeath = new ImageIcon(getClass().getResource(
					"/image/bomber_dead.png"));
			Image imgDead = imageIconDeath.getImage();
			g2d.drawImage(imgDead, getX(), getY(), WIDTH_IMG, HEIGHT_IMG, null);
		}
	}

	public void drawDeadByCollisionWithMonster(Graphics2D g2d) {
		if (bombPlayerIsCollisionWithMonster == true) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/ghost.png"));
			Image imgGhost = imageIcon.getImage();
			g2d.drawImage(imgGhost, getX(), getY(), WIDTH_IMG, HEIGHT_IMG, null);
		}
	}

	public boolean checkCollisionWithItem(ItemSupport item) {
		Rectangle rectBomer = new Rectangle(getX(), getY(), WIDTH_IMG,
				HEIGHT_IMG);
		Rectangle rectItem = new Rectangle(item.getX(), item.getY(), ComponentMap.getSize(),
				ComponentMap.getSize());
		return rectBomer.intersects(rectItem);
	}

	public boolean checkCollisionWithMonster(Monster monster) {
		Rectangle rectBomer = new Rectangle(getX(), getY(), WIDTH_IMG,
				HEIGHT_IMG);
		Rectangle rectMonster = new Rectangle(monster.getX(), monster.getY(),
				Monster.getWidthImg(), Monster.getHeightImg());
		return rectBomer.intersects(rectMonster);
	}
}
