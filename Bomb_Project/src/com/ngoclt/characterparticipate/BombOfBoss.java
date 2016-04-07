package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class BombOfBoss extends BombFather {
	private static final int WIDTH_IMG = 50;
	private static final int HEIGHT_IMG = 50;
	private static final int WIDTH_BOMBBANG = 140;
	private static final int HEIGHT_BOMBBANG = 140;
	
	public static final int TIME_OUT_TO_CREATE_BOMB = 31;
	private int speed;
	private Image imgBomb;
	private ImageIcon imgIconImg;
	private boolean canMove = true;
	private ImageIcon imageIconBombVertical,imageIconBombHorizontal;
	
	public BombOfBoss(int x, int y, int size, int orient, int speed) {
		super(x, y, size, orient);
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public static int getTimeOutToCreateBomb() {
		return TIME_OUT_TO_CREATE_BOMB;
	}

	public void draw(Graphics2D g2d) {
		if (timeOutToBum > 0) {
			imgIconImg = new ImageIcon(getClass().getResource("/image/bomb.png"));
			imgBomb = imgIconImg.getImage();
			g2d.drawImage(imgBomb, getX(), getY(), WIDTH_IMG, HEIGHT_IMG, null);
		}
		if (timeOutToBum < 0) {
			canMove = false;
			imageIconBombHorizontal = new ImageIcon(getClass().getResource("/image/bombang_horizontal.png"));
			imgBomb = imageIconBombHorizontal.getImage();
			g2d.drawImage(imgBomb, getX() - WIDTH_IMG, getY(), WIDTH_BOMBBANG,HEIGHT_IMG, null);

			imageIconBombVertical = new ImageIcon(getClass().getResource("/image/bombang_vertical.png"));
			Image imgBomb2 = imageIconBombVertical.getImage();
			g2d.drawImage(imgBomb2, getX(), getY() - HEIGHT_IMG, WIDTH_IMG,HEIGHT_BOMBBANG, null);
		}
	}

	public void move() {
		if (canMove) {
			if (getOrient() == LEFT) {
				x -= speed;
			}
			if (getOrient() == RIGHT) {
				x += speed;
			}
			if (getOrient() == UP) {
				y -= speed;
			}
			if (getOrient() == DOWN) {
				y += speed;
			}
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
		Rectangle rectBomb = new Rectangle(newX, newY, WIDTH_IMG, HEIGHT_IMG);
		Rectangle rectComp = new Rectangle(comp.getX(), comp.getY(),
				ComponentMap.getSize(), ComponentMap.getSize());
		return rectBomb.intersects(rectComp);
	}
	
	public boolean checkCollisionAfterBombBangWithComponents(ComponentMap comps) {
		Rectangle recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG, WIDTH_IMG, HEIGHT_BOMBBANG);
		Rectangle recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),WIDTH_BOMBBANG, HEIGHT_IMG);
		Rectangle recComp = new Rectangle(comps.getX(), comps.getY(),ComponentMap.getSize(), ComponentMap.getSize());
		if (recBombHorizontal.intersects(recComp)|| recBombVertical.intersects(recComp)) {
			return true;
		} else
			return false;
	}

	public boolean checkCollisionWithBombPlayer(BombPlayer bombPlayer) {
		Rectangle recBombVertical = new Rectangle(getX(), getY() - HEIGHT_IMG,WIDTH_IMG, HEIGHT_BOMBBANG);
		Rectangle recBombHorizontal = new Rectangle(getX() - WIDTH_IMG, getY(),WIDTH_BOMBBANG, HEIGHT_IMG);
		Rectangle recBombPlayer = new Rectangle(bombPlayer.getX(),bombPlayer.getY(), BombPlayer.getWidthImg(), BombPlayer.getHeightImg());
		if (recBombHorizontal.intersects(recBombPlayer)|| recBombVertical.intersects(recBombPlayer)) {
			return true;
		} else
			return false;
	}
	
}
