package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.ngoclt.guiandpanel.GUI;

public class Monster extends DadParticipate {
	public static final int WIDTH = 75;
	public static final int HEIGHT = 59;
	
	public static final int WIDTH_IMG = 45;
	public static final int HEIGHT_IMG = 45;
	
	private Image imaArr[] = new Image[4];
	private ImageIcon imageIconMonster;

	public Monster(int x, int y, int size, int orient, int speed) {
		super(x, y, size, orient, speed);
	}

	public static int getWidthImg() {
		return WIDTH_IMG;
	}

	public static int getHeightImg() {
		return HEIGHT_IMG;
	}

	public void draw(Graphics2D g2d) {
		if (getOrient() == LEFT) {
			imageIconMonster = new ImageIcon(getClass().getResource(
					"/image/monster_left.png"));
			imaArr[0] = imageIconMonster.getImage();
			g2d.drawImage(imaArr[0], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
					null);
		}
		if (getOrient() == RIGHT) {
			imageIconMonster = new ImageIcon(getClass().getResource(
					"/image/monster_right.png"));
			imaArr[1] = imageIconMonster.getImage();
			g2d.drawImage(imaArr[1], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
					null);
		}
		if (getOrient() == UP) {
			imageIconMonster = new ImageIcon(getClass().getResource(
					"/image/monster_up.png"));
			imaArr[2] = imageIconMonster.getImage();
			g2d.drawImage(imaArr[2], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
					null);
		}
		if (getOrient() == DOWN) {
			imageIconMonster = new ImageIcon(getClass().getResource(
					"/image/monster_down.png"));
			imaArr[3] = imageIconMonster.getImage();
			g2d.drawImage(imaArr[3], getX(), getY(), WIDTH_IMG, HEIGHT_IMG,
					null);
		}
		
	}

	public void changeOrient(int newOrient) {
		setOrient(newOrient);
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

	public boolean checkCollisionWithComponents(ComponentMap comp) {
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
		Rectangle rectComp = new Rectangle(comp.getX(), comp.getY(),
				ComponentMap.getSize(), ComponentMap.getSize());
		return rectMonster.intersects(rectComp);
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
