package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

import com.ngoclt.guiandpanel.GUI;

public class Manager {
	private BombPlayer bombPlayer1;
	private ArrayList<Bomb> arrBombs;
	private ArrayList<ComponentMap> arrComponentMaps;
	private ArrayList<ItemSupport> arrItemSupports;
	private ArrayList<Monster> arrMonsters;
	private RandomAccessFile randomAccessFile;
	private boolean[][] bombPutInMap;
	private int numRows, numCols;
	private int powerBomb = 0;
	private Random random = new Random();

	public void initObjects() {
		bombPlayer1 = new BombPlayer(BombPlayer.START_X, BombPlayer.START_Y, 50,
				1, 5, 5);
		arrBombs = new ArrayList<Bomb>();
		arrItemSupports = new ArrayList<ItemSupport>();
		arrComponentMaps = new ArrayList<ComponentMap>();
		arrMonsters = new ArrayList<Monster>();
		Monster monster1 = new Monster(450, 550, ComponentMap.SIZE, 0, 3);
		arrMonsters.add(monster1);
		Monster monster2 = new Monster(250, 0, ComponentMap.SIZE, 0, 3);
		arrMonsters.add(monster2);
		Monster monster3 = new Monster(100, 150, ComponentMap.SIZE, 0, 3);
		arrMonsters.add(monster3);
		Monster monster4 = new Monster(450, 400, ComponentMap.SIZE, 0, 3);
		arrMonsters.add(monster4);
		Monster monster5 = new Monster(400, 550, ComponentMap.SIZE, 0, 3);
		arrMonsters.add(monster5);
		Monster monster6 = new Monster(0, 400, ComponentMap.SIZE, 0, 3);
		arrMonsters.add(monster6);
//		Monster monster7 = new Monster(550, 400, ComponentMap.SIZE, 0, 3);
//		arrMonsters.add(monster7);
//		Monster monster8 = new Monster(0, 0, ComponentMap.SIZE, 0, 3);
//		arrMonsters.add(monster8);
//		Monster monster9 = new Monster(200, 400, ComponentMap.SIZE, 0, 3);
//		arrMonsters.add(monster9);
//		Monster monster10 = new Monster(200, 300, ComponentMap.SIZE, 0, 3);
//		arrMonsters.add(monster10);
		
		numCols = GUI.WIDTH / ComponentMap.SIZE;
		numRows = GUI.HEIGHT / ComponentMap.SIZE;
		bombPutInMap = new boolean[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				bombPutInMap[j][i] = true;
			}
		}
	}

	
	public BombPlayer getBombPlayer1() {
		return bombPlayer1;
	}

	public void draw(Graphics2D g2d) {
		if (bombPlayer1.getBombPlayerIsDead()) {
			bombPlayer1.drawDeadByCollisionWithBomb(g2d);
		}
		if (!bombPlayer1.getBombPlayerIsDead()) {
			bombPlayer1.draw(g2d);
		}
		for (int i = 0; i < arrBombs.size(); i++) {
			arrBombs.get(i).draw(g2d);
		}
		for (int i = 0; i < arrItemSupports.size(); i++) {
			arrItemSupports.get(i).draw(g2d);
		}
		for (int i = 0; i < arrComponentMaps.size(); i++) {
			arrComponentMaps.get(i).draw(g2d);
		}
		for (int i = 0; i < arrMonsters.size(); i++) {
			arrMonsters.get(i).draw(g2d);
		}
	}

	public void moveBombPlayer(int time) {
		for (int i = 0; i < arrComponentMaps.size(); i++) {
			if (bombPlayer1.checkCollisionWithComponentsToMove(arrComponentMaps
					.get(i)) && arrComponentMaps.get(i).getType() != 0) {
				return;
			}
		}
		// || (bombPlayer.getY() < arrBombs
		// .get(i).getX() + bombPlayer.HEIGHT_IMG)
		for (int i = 0; i < arrBombs.size(); i++) {
			if (bombPlayer1.checkCollisionWithBombToMove(arrBombs.get(i))
					&& ((bombPlayer1.getX() > arrBombs.get(i).getX()
							+ bombPlayer1.WIDTH_IMG)
							|| (bombPlayer1.getX() + bombPlayer1.WIDTH_IMG <= arrBombs
									.get(i).getX())
							|| (bombPlayer1.getY() > arrBombs.get(i).getY()
									+ bombPlayer1.HEIGHT_IMG) || (bombPlayer1
							.getY() + bombPlayer1.HEIGHT_IMG <= arrBombs.get(i)
							.getY()))) {
				return;
			}
		}
		bombPlayer1.move(time);
	}

	public void eatItemByBombPlayer() {
		for (int i = 0; i < arrItemSupports.size(); i++) {
			if (bombPlayer1.checkCollisionWithItem(arrItemSupports.get(i))
					&& arrItemSupports.get(i).getType() == ItemSupport.SPEED_UP) {
				if (bombPlayer1.getMaxSpeed() == false) {
					bombPlayer1.setX(bombPlayer1.getX() + bombPlayer1.getSpeed());
					bombPlayer1.setSpeed(bombPlayer1.getSpeed()
							+ bombPlayer1.getSpeed());
					bombPlayer1.setMaxSpeed(true);
				}
				arrItemSupports.remove(arrItemSupports.get(i));
			}
			if (bombPlayer1.checkCollisionWithItem(arrItemSupports.get(i))
					&& arrItemSupports.get(i).getType() == ItemSupport.BOMB_MORE) {
				bombPlayer1.setNumBombCanPut(bombPlayer1.getNumBombCanPut() + 1);
				arrItemSupports.remove(arrItemSupports.get(i));
			}
			if (bombPlayer1.checkCollisionWithItem(arrItemSupports.get(i))
					&& arrItemSupports.get(i).getType() == ItemSupport.BOMB_POWER) {
				arrItemSupports.remove(arrItemSupports.get(i));
				powerBomb++;
			}
		}
	}

	public void bombPlayerFire() {
		if (bombPlayer1.isFire()) {
			int x = (bombPlayer1.getX() + ComponentMap.SIZE / 2)
					/ ComponentMap.SIZE;
			int y = (bombPlayer1.getY() + ComponentMap.SIZE / 2)
					/ ComponentMap.SIZE;
			int xDraw = x * ComponentMap.SIZE;
			int yDraw = y * ComponentMap.SIZE;
			if (bombPutInMap[x][y]) {
				Bomb bomb = new Bomb(xDraw, yDraw, ComponentMap.SIZE, 0, 5, 1);
				bomb.setDamage(bomb.getDamge() + powerBomb);
				arrBombs.add(bomb);
				bombPlayer1.setNumBombCanPut(bombPlayer1.getNumBombCanPut() - 1);
				bombPutInMap[x][y] = false;
			}
		}
	}

	public void bombPlayerIsDead() {

	}

	public void changeOrientBombPlayer(int newOrient) {
		bombPlayer1.changeOrient(newOrient);
	}

	public void downTimeOutToBum(int time) {
		for (int i = 0; i < arrBombs.size(); i++) {
			arrBombs.get(i).downTimeOutToBum(time);
		}
	}

	public void downTimeOutToRemoveBomb(int time) {
		for (int i = 0; i < arrBombs.size(); i++) {
			if (arrBombs.get(i).removeBomb()) {
				arrBombs.get(i).downTimeOutToRemoveBomb(time);
				if (arrBombs.get(i).getTimeOutToRemoveBomb() <= 0) {
					bombPutInMap[arrBombs.get(i).getX() / ComponentMap.SIZE][arrBombs
							.get(i).getY() / ComponentMap.SIZE] = true;
					arrBombs.remove(arrBombs.get(i));
					bombPlayer1
							.setNumBombCanPut(bombPlayer1.getNumBombCanPut() + 1);
				}
			}
		}
	}

	public void removeComponentsAndMonsterAfterBombBang() {
		for (int i = 0; i < arrBombs.size(); i++) {
			for (int j = 0; j < arrComponentMaps.size(); j++) {
				if (arrBombs.get(i).getTimeOutToBum() <= 0
						&& arrBombs.get(i).checkCollisionWithComponents(
								arrComponentMaps.get(j)) == true
						&& arrComponentMaps.get(j).getType() != 1) {
					arrComponentMaps.remove(arrComponentMaps.get(j));
				}
			}
			for (int j = 0; j < arrMonsters.size(); j++) {
				if (arrBombs.get(i).getTimeOutToBum() <= 0
						&& arrBombs.get(i).checkCollisionWithMonster(
								arrMonsters.get(j)) == true) {
					arrMonsters.remove(arrMonsters.get(j));
				}
			}
		}
	}

	public void explosionBombToAnotherBomb() {
		for (int i = 0; i < arrBombs.size(); i++) {
			for (int j = i+1; j < arrBombs.size(); j++) {
				if(arrBombs.get(i).getTimeOutToBum() <= 0 && arrBombs.get(i).checkCollisionWithAnotherBomb(arrBombs.get(j))) {
					arrBombs.get(j).setTimeOutToBum(0);
				}
			}
		}
	}
	
	public void moveMonster(int time) {
		for (int i = 0; i < arrMonsters.size(); i++) {
			for (int j = 0; j < arrComponentMaps.size(); j++) {
				if (arrMonsters.get(i).checkCollisionWithComponents(
						arrComponentMaps.get(j))
						&& arrComponentMaps.get(j).getType() != 0) {
					int newOrient = random.nextInt(4);
					arrMonsters.get(i).changeOrient(newOrient);
					return;
				}
			}
		}
		
		for (int i = 0; i < arrMonsters.size(); i++) {
			for (int j = 0; j < arrBombs.size(); j++) {
				if(arrMonsters.get(i).checkCollisionWithBomb(arrBombs.get(j))) {
					int newOrient = random.nextInt(4);
					arrMonsters.get(i).changeOrient(newOrient);
					return;
				}
			}
		}
		
		for (int i = 0; i < arrMonsters.size(); i++) {
			arrMonsters.get(i).move(time);
			if (time % 50 == 0) {
				int newOrient = random.nextInt(4);
				arrMonsters.get(i).changeOrient(newOrient);
			}
		}
	}

	public void checkCollisionBombPlayerAndBomb() {
		for (int i = 0; i < arrBombs.size(); i++) {
			if (arrBombs.get(i).getTimeOutToBum() <= 0
					&& arrBombs.get(i).checkCollisionWithBombPlayer(bombPlayer1) == true) {
				bombPlayer1.setBombPlayerIsDead(true);
			}
		}
	}

	public void checkCollisionBombPlayerAndMonster() {
		for (int i = 0; i < arrMonsters.size(); i++) {
			if (bombPlayer1.checkCollisionWithMonster(arrMonsters.get(i))) {
				bombPlayer1.setBombPlayerIsCollisionWithMonster(true);
				bombPlayer1.setX(BombPlayer.START_X);
				bombPlayer1.setY(BombPlayer.START_Y);
			}
		}

	}

	public void readFileToCreateMap(String path) {
		File file = new File(getClass().getResource(path).getPath());
		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			String str = randomAccessFile.readLine();
			int locationY = 0;
			while (str != null) {
				int locationX = 0;
				for (int i = 0; i < str.length(); i++) {
					String s = str.substring(i, i + 1);
					int num = Integer.parseInt(s);
					ComponentMap componentMap = new ComponentMap(locationX
							* ComponentMap.SIZE, locationY * ComponentMap.SIZE,
							num);
					arrComponentMaps.add(componentMap);
					locationX++;
				}
				locationY++;

				str = randomAccessFile.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			randomAccessFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFileToCreateItem(String path) {
		File file = new File(getClass().getResource(path).getPath());
		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			String str = randomAccessFile.readLine();
			int locationY = 0;
			while (str != null) {
				int locationX = 0;
				for (int i = 0; i < str.length(); i++) {
					String s = str.substring(i, i + 1);
					int num = Integer.parseInt(s);
					ItemSupport itemSupport = new ItemSupport(locationX
							* ItemSupport.SIZE, locationY * ItemSupport.SIZE,
							num);
					arrItemSupports.add(itemSupport);
					locationX++;
				}
				locationY++;

				str = randomAccessFile.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			randomAccessFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
