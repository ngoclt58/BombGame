package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ComponentMap {
	private static final int NOTHING = 0;
	private static final int BRICK = 1;
	private static final int BOX =2;
	private static final int ANOTHER_BOX = 3;
	public static final int SIZE = 50;
	
	private int x, y;
	private int type;
	private ImageIcon imgIcon;
	private Image img1, img2, img3;
	public ComponentMap(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getType() {
		return type;
	}
	
	public void draw(Graphics2D g2d) {
		if(type == BRICK) {
			imgIcon = new ImageIcon(getClass().getResource("/image/brick.png"));
			img1 = imgIcon.getImage();
			g2d.drawImage(img1, getX(), getY(), SIZE, SIZE, null);
		}
		if(type == BOX) {
			imgIcon = new ImageIcon(getClass().getResource("/image/box.png"));
			img2 = imgIcon.getImage();
			g2d.drawImage(img2, getX(), getY(), SIZE, SIZE, null);
		}
		if(type == ANOTHER_BOX) {
			imgIcon = new ImageIcon(getClass().getResource("/image/box2.png"));
			img3 = imgIcon.getImage();
			g2d.drawImage(img3, getX(), getY(), SIZE, SIZE, null);
		}
	}
	
}
