package com.ngoclt.characterparticipate;

import java.awt.Image;

public class DadParticipate {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;

	protected int x, y;
	protected int size;
	protected int orient;
	protected int speed;
	protected Image img;

	public DadParticipate(int x, int y, int size, int orient, int speed) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.orient = orient;
		this.speed = speed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setOrient(int orient) {
		this.orient = orient;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public int getOrient() {
		return orient;
	}

	public int getSpeed() {
		return speed;
	}

	public Image getImg() {
		return img;
	}

}
