package com.ngoclt.characterparticipate;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

import com.ngoclt.guiandpanel.GUI;

public class Manager {
	private BombPlayer bombPlayer1, bombPlayer2;
	private ArrayList<Bomb> arrBombs;
	private ArrayList<ComponentMap> arrComponentMaps;
	private ArrayList<ItemSupport> arrItemSupports;
	private ArrayList<Monster> arrMonsters;
	private ArrayList<Boss> arrBoss;
	private ArrayList<BombOfBoss> arrBombOfBosses;
	private RandomAccessFile randomAccessFile;
	private boolean[][] bombPutInMap;
	private int numRows, numCols;
	private int powerBomb = 0;
	private Random random = new Random();
	private boolean bombPlayer2isAppear = true;
	private int numberPlayers;
	private int timeCondition = 0;

	public Manager(int numberPlayers) {
		this.numberPlayers = numberPlayers;
	}

	public void initObjects() {
		bombPlayer1 = new BombPlayer(BombPlayer.START_X_PLAYER1,
				BombPlayer.START_Y_PLAYER1, 50, 0, 5, 5, BombPlayer.PLAYER1);
		if (numberPlayers == 2) {
			bombPlayer2 = new BombPlayer(BombPlayer.START_X_PLAYER2,
					BombPlayer.START_Y_PLAYER2, 50, 1, 5, 5, BombPlayer.PLAYER2);
		}
		arrBombs = new ArrayList<Bomb>();
		arrItemSupports = new ArrayList<ItemSupport>();
		arrComponentMaps = new ArrayList<ComponentMap>();
		arrMonsters = new ArrayList<Monster>();
		arrBombOfBosses = new ArrayList<BombOfBoss>();
		arrBoss = new ArrayList<Boss>();
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
		Boss boss3 = new Boss(600, 300, 50, 0, 5);
		arrBoss.add(boss3);
		Boss boss2 = new Boss(0, 300, 50, 1, 5);
		arrBoss.add(boss2);
		Boss boss4 = new Boss(275, 600, 50, 2, 5);
		arrBoss.add(boss4);
		Boss boss1 = new Boss(275, 0, 50, 3, 5);
		arrBoss.add(boss1);
		// Monster monster7 = new Monster(550, 400, ComponentMap.SIZE, 0, 3);
		// arrMonsters.add(monster7);
		// Monster monster8 = new Monster(0, 0, ComponentMap.SIZE, 0, 3);
		// arrMonsters.add(monster8);
		// Monster monster9 = new Monster(200, 400, ComponentMap.SIZE, 0, 3);
		// arrMonsters.add(monster9);
		// Monster monster10 = new Monster(200, 300, ComponentMap.SIZE, 0, 3);
		// arrMonsters.add(monster10);

		numCols = (GUI.WIDTH) / ComponentMap.SIZE;
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

	public BombPlayer getBombPlayer2() {
		return bombPlayer2;
	}

	public boolean isBombPlayer2isAppear() {
		return bombPlayer2isAppear;
	}

	public void setBombPlayer2isAppear(boolean bombPlayer2isAppear) {
		this.bombPlayer2isAppear = bombPlayer2isAppear;
	}

	public void draw(Graphics2D g2d) {
		if (bombPlayer1.getNumsHeart() > 0) {
			if (bombPlayer1.getBombPlayerIsDead()) {
				bombPlayer1.drawDeadByCollisionWithBomb(g2d);
			}
			if (!bombPlayer1.getBombPlayerIsDead()) {
				bombPlayer1.draw(g2d);
			}
		}
		if (numberPlayers == 2) {
			if (bombPlayer2.getNumsHeart() > 0) {
				if (bombPlayer2.getBombPlayerIsDead()) {
					bombPlayer2.drawDeadByCollisionWithBomb(g2d);
				}
				if (!bombPlayer2.getBombPlayerIsDead()) {
					bombPlayer2.draw(g2d);
				}
			}
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
		for (int i = 0; i < arrBoss.size(); i++) {
			arrBoss.get(i).draw(g2d);
		}
		for (int i = 0; i < arrBombOfBosses.size(); i++) {
			arrBombOfBosses.get(i).draw(g2d);
		}
	}

	public void moveBombPlayer1(int time) {
		for (int i = 0; i < arrComponentMaps.size(); i++) {
			if (bombPlayer1.checkCollisionWithComponentsToMove(arrComponentMaps
					.get(i)) && arrComponentMaps.get(i).getType() != 0) {
				return;
			}
		}

		for (int i = 0; i < arrBoss.size(); i++) {
			if (bombPlayer1.checkCollisionWithBossToMove(arrBoss.get(i))) {
				return;
			}
		}

		for (int i = 0; i < arrBombs.size(); i++) {
			if (bombPlayer1.checkCollisionWithBombToMove(arrBombs.get(i))
					&& ((bombPlayer1.getX() >= arrBombs.get(i).getX()
							+ BombPlayer.WIDTH_IMG)
							|| (bombPlayer1.getX() + BombPlayer.WIDTH_IMG <= arrBombs
									.get(i).getX())
							|| (bombPlayer1.getY() >= arrBombs.get(i).getY()
									+ BombPlayer.HEIGHT_IMG) || (bombPlayer1
							.getY() + BombPlayer.HEIGHT_IMG <= arrBombs.get(i)
							.getY()))) {
				return;
			}
		}
		bombPlayer1.move(time);
	}

	public void moveBombPlayer2(int time) {
		for (int i = 0; i < arrComponentMaps.size(); i++) {
			if (bombPlayer2.checkCollisionWithComponentsToMove(arrComponentMaps
					.get(i)) && arrComponentMaps.get(i).getType() != 0) {
				return;
			}
		}

		for (int i = 0; i < arrBoss.size(); i++) {
			if (bombPlayer2.checkCollisionWithBossToMove(arrBoss.get(i))) {
				return;
			}
		}

		for (int i = 0; i < arrBombs.size(); i++) {
			if (bombPlayer2.checkCollisionWithBombToMove(arrBombs.get(i))
					&& ((bombPlayer2.getX() >= arrBombs.get(i).getX()
							+ BombPlayer.WIDTH_IMG)
							|| (bombPlayer2.getX() + BombPlayer.WIDTH_IMG <= arrBombs
									.get(i).getX())
							|| (bombPlayer2.getY() >= arrBombs.get(i).getY()
									+ BombPlayer.HEIGHT_IMG) || (bombPlayer2
							.getY() + BombPlayer.HEIGHT_IMG <= arrBombs.get(i)
							.getY()))) {
				return;
			}
		}
		bombPlayer2.move(time);
	}

	public void eatItemByBombPlayer1() {
		for (int i = 0; i < arrItemSupports.size(); i++) {
			if (bombPlayer1.checkCollisionWithItem(arrItemSupports.get(i))
					&& arrItemSupports.get(i).getType() == ItemSupport.SPEED_UP) {
				if (bombPlayer1.getMaxSpeed() == false) {
					bombPlayer1.setX(bombPlayer1.getX()
							+ bombPlayer1.getSpeed());
					bombPlayer1.setSpeed(bombPlayer1.getSpeed()
							+ bombPlayer1.getSpeed());
					bombPlayer1.setMaxSpeed(true);
				}
				arrItemSupports.remove(arrItemSupports.get(i));
			}
			if (bombPlayer1.checkCollisionWithItem(arrItemSupports.get(i))
					&& arrItemSupports.get(i).getType() == ItemSupport.BOMB_MORE) {
				bombPlayer1
						.setNumBombCanPut(bombPlayer1.getNumBombCanPut() + 1);
				arrItemSupports.remove(arrItemSupports.get(i));
			}
			if (bombPlayer1.checkCollisionWithItem(arrItemSupports.get(i))
					&& arrItemSupports.get(i).getType() == ItemSupport.BOMB_POWER) {
				arrItemSupports.remove(arrItemSupports.get(i));
				powerBomb++;
			}
		}
	}

	public void eatItemByBombPlayer2() {
		for (int i = 0; i < arrItemSupports.size(); i++) {
			if (bombPlayer2.checkCollisionWithItem(arrItemSupports.get(i))
					&& arrItemSupports.get(i).getType() == ItemSupport.SPEED_UP) {
				if (bombPlayer2.getMaxSpeed() == false) {
					bombPlayer2.setX(bombPlayer2.getX()
							+ bombPlayer2.getSpeed());
					bombPlayer2.setSpeed(bombPlayer2.getSpeed()
							+ bombPlayer2.getSpeed());
					bombPlayer2.setMaxSpeed(true);
				}
				arrItemSupports.remove(arrItemSupports.get(i));
			}
			if (bombPlayer2.checkCollisionWithItem(arrItemSupports.get(i))
					&& arrItemSupports.get(i).getType() == ItemSupport.BOMB_MORE) {
				bombPlayer2
						.setNumBombCanPut(bombPlayer2.getNumBombCanPut() + 1);
				arrItemSupports.remove(arrItemSupports.get(i));
			}
			if (bombPlayer2.checkCollisionWithItem(arrItemSupports.get(i))
					&& arrItemSupports.get(i).getType() == ItemSupport.BOMB_POWER) {
				arrItemSupports.remove(arrItemSupports.get(i));
				powerBomb++;
			}
		}
	}

	public void bombPlayer1Fire() {
		if (bombPlayer1.isFire()) {
			int x = (bombPlayer1.getX() + ComponentMap.SIZE / 2)
					/ ComponentMap.SIZE;
			int y = (bombPlayer1.getY() + ComponentMap.SIZE / 2)
					/ ComponentMap.SIZE;
			int xDraw = x * ComponentMap.SIZE;
			int yDraw = y * ComponentMap.SIZE;
			if (bombPutInMap[x][y]) {
				Bomb bomb = new Bomb(xDraw, yDraw, ComponentMap.SIZE, 0, 1,
						Bomb.PLAYER1_OWN_BOMB);
				bomb.setDamage(bomb.getDamge() + powerBomb);
				arrBombs.add(bomb);
				bombPlayer1
						.setNumBombCanPut(bombPlayer1.getNumBombCanPut() - 1);
				bombPutInMap[x][y] = false;
			}
		}
	}

	public void bombPlayer2Fire() {
		if (bombPlayer2.isFire()) {
			int x = (bombPlayer2.getX() + ComponentMap.SIZE / 2)
					/ ComponentMap.SIZE;
			int y = (bombPlayer2.getY() + ComponentMap.SIZE / 2)
					/ ComponentMap.SIZE;
			int xDraw = x * ComponentMap.SIZE;
			int yDraw = y * ComponentMap.SIZE;
			if (bombPutInMap[x][y]) {
				Bomb bomb = new Bomb(xDraw, yDraw, ComponentMap.SIZE, 0, 1,
						Bomb.PLAYER2_OWN_BOMB);
				bomb.setDamage(bomb.getDamge() + powerBomb);
				arrBombs.add(bomb);
				bombPlayer2
						.setNumBombCanPut(bombPlayer2.getNumBombCanPut() - 1);
				bombPutInMap[x][y] = false;
			}
		}
	}

	public void changeOrientBombPlayer1(int newOrient) {
		bombPlayer1.changeOrient(newOrient);
	}

	public void changeOrientBombPlayer2(int newOrient) {
		bombPlayer2.changeOrient(newOrient);
	}

	public void downTimeOutToBum(int time) {
		for (int i = 0; i < arrBombs.size(); i++) {
			arrBombs.get(i).downTimeOutToBum(time);
		}
		for (int i = 0; i < arrBombOfBosses.size(); i++) {
			arrBombOfBosses.get(i).downTimeOutToBum(time);
		}
	}

	public void downTimeOutToRemoveBombOfBoss(int time) {
		for (int i = 0; i < arrBombOfBosses.size(); i++) {
			if (arrBombOfBosses.get(i).removeBomb()) {
				arrBombOfBosses.get(i).downTimeOutToRemoveBomb(time);
				if (arrBombOfBosses.get(i).getTimeOutToRemoveBomb() <= 0) {
					arrBombOfBosses.remove(arrBombOfBosses.get(i));
				}
			}
		}
	}

	public void downTimeOutToRemoveBombPlayer1(int time) {
		for (int i = 0; i < arrBombs.size(); i++) {
			if (arrBombs.get(i).removeBomb()
					&& arrBombs.get(i).getPlayerOwnBomb() == Bomb.PLAYER1_OWN_BOMB) {
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

	public void downTimeOutToRemoveBombPlayer2(int time) {
		for (int i = 0; i < arrBombs.size(); i++) {
			if (arrBombs.get(i).removeBomb()
					&& arrBombs.get(i).getPlayerOwnBomb() == Bomb.PLAYER2_OWN_BOMB) {
				arrBombs.get(i).downTimeOutToRemoveBomb(time);
				if (arrBombs.get(i).getTimeOutToRemoveBomb() <= 0) {
					bombPutInMap[arrBombs.get(i).getX() / ComponentMap.SIZE][arrBombs
							.get(i).getY() / ComponentMap.SIZE] = true;
					arrBombs.remove(arrBombs.get(i));
					bombPlayer2
							.setNumBombCanPut(bombPlayer2.getNumBombCanPut() + 1);
				}
			}
		}
	}

	public void removeBossAfterBombBang() {
		for (int i = 0; i < arrBombs.size(); i++) {
			for (int j = 0; j < arrBoss.size(); j++) {
				if (arrBombs.get(i).getTimeOutToBum() <= 0
						&& arrBombs.get(i).checkCollisionWithBoss(
								arrBoss.get(j)) == true) {
					arrBoss.get(j).setAlive(false);
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

	public void removeComponentsAfterBombOfBossBang() {
		for (int i = 0; i < arrBombOfBosses.size(); i++) {
			for (int j = 0; j < arrComponentMaps.size(); j++) {
				if (arrBombOfBosses.get(i).getTimeOutToBum() <= 0
						&& arrBombOfBosses.get(i)
								.checkCollisionAfterBombBangWithComponents(
										arrComponentMaps.get(j)) == true
						&& arrComponentMaps.get(j).getType() != 1) {
					arrComponentMaps.remove(arrComponentMaps.get(j));
				}
			}
		}
	}

	public void explosionBombToAnotherBomb() {
		for (int i = 0; i < arrBombs.size(); i++) {
			for (int j = i + 1; j < arrBombs.size(); j++) {
				if (arrBombs.get(i).getTimeOutToBum() <= 0
						&& arrBombs.get(i).checkCollisionWithAnotherBomb(
								arrBombs.get(j))) {
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
				if (arrMonsters.get(i).checkCollisionWithBomb(arrBombs.get(j))) {
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

	public void checkCollisionBombPlayer1AndBomb() {
		for (int i = 0; i < arrBombs.size(); i++) {
			if (arrBombs.get(i).getTimeOutToBum() <= 0
					&& arrBombs.get(i)
							.checkCollisionWithBombPlayer(bombPlayer1) == true) {
				bombPlayer1.setBombPlayerIsDead(true);
			}
		}
	}

	public void checkCollisionBombPlayer1AndBombOfBoss() {
		for (int i = 0; i < arrBombOfBosses.size(); i++) {
			if (arrBombOfBosses.get(i).getTimeOutToBum() <= 0
					&& arrBombOfBosses.get(i).checkCollisionWithBombPlayer(
							bombPlayer1) == true) {
				bombPlayer1.setBombPlayerIsDead(true);
			}
		}
	}

	public void checkCollisionBombPlayer2AndBombOfBoss() {
		for (int i = 0; i < arrBombOfBosses.size(); i++) {
			if (arrBombOfBosses.get(i).getTimeOutToBum() <= 0
					&& arrBombOfBosses.get(i).checkCollisionWithBombPlayer(
							bombPlayer2) == true) {
				bombPlayer2.setBombPlayerIsDead(true);
			}
		}
	}

	public void setTimeOutToPlayer1Dead(int time) {
		bombPlayer1.setTimeOutToDead(time);
	}

	public void setTimeOutToPlayer2Dead(int time) {
		bombPlayer2.setTimeOutToDead(time);
	}

	public void checkCollisionBombPlayer2AndBomb() {
		for (int i = 0; i < arrBombs.size(); i++) {
			if (arrBombs.get(i).getTimeOutToBum() <= 0
					&& arrBombs.get(i)
							.checkCollisionWithBombPlayer(bombPlayer2) == true) {
				bombPlayer2.setBombPlayerIsDead(true);
			}
		}
	}

	public void checkCollisionBombPlayer1AndMonster() {
		for (int i = 0; i < arrMonsters.size(); i++) {
			if (bombPlayer1.checkCollisionWithMonster(arrMonsters.get(i))) {
				bombPlayer1.setBombPlayerIsCollisionWithMonster(true);
				bombPlayer1.setX(BombPlayer.START_X_PLAYER1);
				bombPlayer1.setY(BombPlayer.START_Y_PLAYER1);
				bombPlayer1.setNumsHeart(bombPlayer1.getNumsHeart() - 1);
			}
		}

	}

	public int getNumberHeartPlayer1() {
		return bombPlayer1.getNumsHeart();
	}

	public int getNumberHeartPlayer2() {
		return bombPlayer2.getNumsHeart();
	}

	public void checkCollisionBombPlayer2AndMonster() {
		for (int i = 0; i < arrMonsters.size(); i++) {
			if (bombPlayer2.checkCollisionWithMonster(arrMonsters.get(i))) {
				bombPlayer2.setBombPlayerIsCollisionWithMonster(true);
				bombPlayer2.setX(BombPlayer.START_X_PLAYER2);
				bombPlayer2.setY(BombPlayer.START_Y_PLAYER2);
				bombPlayer2.setNumsHeart(bombPlayer2.getNumsHeart() - 1);
			}
		}
	}

	public void createBombOfBoss(int time) {
		if (time > timeCondition) {
			int i = (time % Boss.NUMBER_OF_BOSS);
			if (arrBoss.get(i).isAlive()) {
				if (i == 0) {
					BombOfBoss bomOfBoss = new BombOfBoss(
							arrBoss.get(i).getX(), arrBoss.get(i).getY() + 50,
							BombOfBoss.getSizeBomb(), i, 5);
					arrBombOfBosses.add(bomOfBoss);
				}
				if (i == 1) {
					BombOfBoss bomOfBoss = new BombOfBoss(
							arrBoss.get(i).getX() + 100,
							arrBoss.get(i).getY() + 50,
							BombOfBoss.getSizeBomb(), i, 5);
					arrBombOfBosses.add(bomOfBoss);
				}
				if (i == 2) {
					BombOfBoss bomOfBoss = new BombOfBoss(
							arrBoss.get(i).getX() + 25,
							arrBoss.get(i).getY() - 25,
							BombOfBoss.getSizeBomb(), i, 5);
					arrBombOfBosses.add(bomOfBoss);
				}
				if (i == 3) {
					BombOfBoss bomOfBoss = new BombOfBoss(
							arrBoss.get(i).getX() + 25,
							arrBoss.get(i).getY() + 100,
							BombOfBoss.getSizeBomb(), i, 5);
					arrBombOfBosses.add(bomOfBoss);
				}
			}
			timeCondition += BombOfBoss.getTimeOutToCreateBomb();
		}
	}

	public void moveBombOfBoss() {
		for (int i = 0; i < arrBombOfBosses.size(); i++) {
			for (int j = 0; j < arrComponentMaps.size(); j++) {
				if (arrBombOfBosses.get(i).checkCollisionWithComponents(
						arrComponentMaps.get(j))
						&& arrComponentMaps.get(j).getType() != 0) {
					arrBombOfBosses.get(i).setCanMove(false);
				}
			}
		}
		for (int i = 0; i < arrBombOfBosses.size(); i++) {
			arrBombOfBosses.get(i).move();
		}
	}

	public void setLocationForBossAfterDead() {
		for (int i = 0; i < arrBoss.size(); i++) {
			if(!arrBoss.get(i).isAlive()) {
				arrBoss.get(i).setLocationForBossAfterDead();
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
