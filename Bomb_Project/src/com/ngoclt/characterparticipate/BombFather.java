package com.ngoclt.characterparticipate;

public class BombFather {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	public static final int TIME_OUT_TO_BUM = 3000;
	public static final int TIME_OUT_TO_REMOVE_BOMB = 1000;
	
	public static final int SIZE_BOMB = 50;
	
	protected int timeOutToBum = TIME_OUT_TO_BUM;
	protected int timeOutToRemoveBomb = TIME_OUT_TO_REMOVE_BOMB;
	
	protected int x,y;
	protected int size;
	protected int orient;
	
	public BombFather(int x, int y, int size, int orient) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.orient = orient;
	}
	
	public static int getSizeBomb() {
		return SIZE_BOMB;
	}

	public int getTimeOutToBum() {
		return timeOutToBum;
	}

	public void setTimeOutToBum(int timeOutToBum) {
		this.timeOutToBum = timeOutToBum;
	}

	public int getTimeOutToRemoveBomb() {
		return timeOutToRemoveBomb;
	}

	public void setTimeOutToRemoveBomb(int timeOutToRemoveBomb) {
		this.timeOutToRemoveBomb = timeOutToRemoveBomb;
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

	
	
}
