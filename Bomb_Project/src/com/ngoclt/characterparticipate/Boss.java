package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Boss extends DadParticipate {
	public static final int WIDTH_IMG = 100;
	public static final int HEIGHT_IMG = 100;

	public static final int NUMBER_OF_BOSS = 4;
	
	public static final int LOCATIONX_AFTERDEAD = 1000;
	public static final int LOCATIONY_AFTERDEAD = 1000;
	
	private Image imaArr[] = new Image[4];
	private ImageIcon imageIconMonster;
	private boolean isAlive = true;

	public Boss(int x, int y, int size, int orient, int speed) {
		super(x, y, size, orient, speed);
	}

	public static int getWidthImg() {
		return WIDTH_IMG;
	}

	public static int getHeightImg() {
		return HEIGHT_IMG;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void draw(Graphics2D g2d) {
		if (isAlive) {
			if (getOrient() == LEFT) {
				imageIconMonster = new ImageIcon(getClass().getResource(
						"/image/boss_left.png"));
				imaArr[0] = imageIconMonster.getImage();
				g2d.drawImage(imaArr[0], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
						null);
			}
			if (getOrient() == RIGHT) {
				imageIconMonster = new ImageIcon(getClass().getResource(
						"/image/boss_right.png"));
				imaArr[1] = imageIconMonster.getImage();
				g2d.drawImage(imaArr[1], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
						null);
			}
			if (getOrient() == UP) {
				imageIconMonster = new ImageIcon(getClass().getResource(
						"/image/boss_up.png"));
				imaArr[2] = imageIconMonster.getImage();
				g2d.drawImage(imaArr[2], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
						null);
			}
			if (getOrient() == DOWN) {
				imageIconMonster = new ImageIcon(getClass().getResource(
						"/image/boss_down.png"));
				imaArr[3] = imageIconMonster.getImage();
				g2d.drawImage(imaArr[3], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
						null);
			}
		}
	}

	public void setLocationForBossAfterDead() {
		if(!isAlive) {
			x = 1000;
			y = 1000;
		}
	}
	
	public boolean checkCollisionWithBomb(Bomb bomb) {
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
		Rectangle rectMonster = new Rectangle(newX, newY, WIDTH_IMG, HEIGHT_IMG);
		Rectangle rectBomb = new Rectangle(bomb.getX(), bomb.getY(),
				Bomb.getWidthImg(), Bomb.getHeightImg());
		return rectMonster.intersects(rectBomb);
	}
}
