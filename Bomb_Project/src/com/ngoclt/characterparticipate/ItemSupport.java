package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ItemSupport extends ComponentMap{
	public static final int NOTHING =0;
	public static final int BOMB_POWER = 1;
	public static final int BOMB_MORE =2;
	public static final int SPEED_UP = 3;

	
	private Image img1,img2, img3;
	
	public ItemSupport(int x, int y, int type) {
		super(x, y, type);
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(getType() == BOMB_POWER) {
			ImageIcon imgIcon = new ImageIcon(getClass().getResource("/image/item_bombsize.png"));
			img1 = imgIcon.getImage();
			g2d.drawImage(img1, getX(), getY(), SIZE, SIZE, null);
		}
		if(getType() == BOMB_MORE) {
			ImageIcon imgIcon = new ImageIcon(getClass().getResource("/image/item_bomb.png"));
			img2 = imgIcon.getImage();
			g2d.drawImage(img2, getX(), getY(), SIZE, SIZE, null);
		}
		if(getType() == SPEED_UP) {
			ImageIcon imgIcon = new ImageIcon(getClass().getResource("/image/item_shoe.png"));
			img3 = imgIcon.getImage();
			g2d.drawImage(img3, getX(), getY(), SIZE, SIZE, null);
		}
		
	}
	
	
	
	
}
