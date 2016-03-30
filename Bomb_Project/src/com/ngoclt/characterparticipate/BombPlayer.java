package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.ngoclt.guiandpanel.GUI;

public class BombPlayer extends DadParticipate {

	public static final int WIDTH_IMG = 45;
	public static final int HEIGHT_IMG = 45;

	public static final int START_X = 250;
	public static final int START_Y = 200;
	
	public static final int TIME_OUT_TO_FIRE = 1000;

	public Image imaArr[] = new Image[4];
	private int speedFire;
	private int timeOut = 0;
	private boolean maxSpeed = false;
	private int numBombCanPut = 1;
	private boolean bombPlayerIsCollisionWithBomb = false;
	private boolean bombPlayerIsCollisionWithMonster = false;

	public BombPlayer(int x, int y, int size, int orient, int speed,
			int speedFire) {
		super(x, y, size, orient, speed);
		this.speedFire = speedFire;
	}

	public int getSpeedFire() {
		return speedFire;
	}

	public boolean getMaxSpeed() {
		return maxSpeed;
	}

	public int getNumBombCanPut() {
		return numBombCanPut;
	}

	public boolean getbombPlayerIsCollisionWithMonster() {
		return bombPlayerIsCollisionWithMonster;
	}

	public void setBombPlayerIsCollisionWithMonster(
			boolean bombPlayerIsCollisionWithMonster) {
		this.bombPlayerIsCollisionWithMonster = bombPlayerIsCollisionWithMonster;
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
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/bomber_left.png"));
			imaArr[0] = imageIcon.getImage();
			g2d.drawImage(imaArr[0], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
					null);
		}
		if (getOrient() == RIGHT) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/bomber_right.png"));
			imaArr[1] = imageIcon.getImage();
			g2d.drawImage(imaArr[1], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
					null);
		}
		if (getOrient() == UP) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/bomber_up.png"));
			imaArr[2] = imageIcon.getImage();
			g2d.drawImage(imaArr[2], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
					null);
		}
		if (getOrient() == DOWN) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/bomber_down.png"));
			imaArr[3] = imageIcon.getImage();
			g2d.drawImage(imaArr[3], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
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
		if (timeOut <= 0 && numBombCanPut > 0) {
			return true;
		} else
			return false;
	}

	public void changeOrient(int newOrient) {
		setOrient(newOrient);
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
		Rectangle rectComp = new Rectangle(comp.getX(), comp.getY(), comp.SIZE,
				comp.SIZE);
		return rectBomer.intersects(rectComp);
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
		Rectangle rectBomb = new Rectangle(bomb.getX(), bomb.getY(), bomb.WIDTH_IMG,
				bomb.HEIGHT_IMG);
		return rectBomer.intersects(rectBomb);
	}
	
	public void drawDeadByCollisionWithBomb(Graphics2D g2d) {
		if (bombPlayerIsCollisionWithBomb == true) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/bomber_dead.png"));
			Image imgDead = imageIcon.getImage();
			g2d.drawImage(imgDead, getX(), getY(), WIDTH_IMG, HEIGHT_IMG, null);
		}
	}
	
	public void drawDeadByCollisionWithMonster(Graphics2D g2d) {
		if(bombPlayerIsCollisionWithMonster == true) {
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/image/ghost.png"));
			Image imgGhost = imageIcon.getImage();
			g2d.drawImage(imgGhost, getX(), getY(), WIDTH_IMG, HEIGHT_IMG, null);
		}
	}

	public boolean checkCollisionWithItem(ItemSupport item) {
		Rectangle rectBomer = new Rectangle(getX(), getY(), WIDTH_IMG,
				HEIGHT_IMG);
		Rectangle rectItem = new Rectangle(item.getX(), item.getY(), item.SIZE,
				item.SIZE);
		return rectBomer.intersects(rectItem);
	}

	public boolean checkCollisionWithMonster(Monster monster) {
		Rectangle rectBomer = new Rectangle(getX(), getY(), WIDTH_IMG,
				HEIGHT_IMG);
		Rectangle rectMonster = new Rectangle(monster.getX(), monster.getY(),
				monster.WIDTH_IMG, monster.HEIGHT_IMG);
		return rectBomer.intersects(rectMonster);
	}
}
