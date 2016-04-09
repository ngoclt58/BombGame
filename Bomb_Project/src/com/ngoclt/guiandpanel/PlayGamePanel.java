package com.ngoclt.guiandpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ngoclt.characterparticipate.BombPlayer;
import com.ngoclt.characterparticipate.DadParticipate;
import com.ngoclt.characterparticipate.Manager;

public class PlayGamePanel extends JPanel {

	/** */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Tahoma", Font.BOLD, 20);
	private JLabel labelMenuPlayer1, labelMenuPlayer2, labelImgPlayer1,
			labelImgPlayer2, labelPlayer1, labelPlayer2, labelImgHeartPlayer1,
			labelImgHeartPlayer2, labelNumHeartPlayer1, labelNumHeartPlayer2,
			labelPlayer1GameOver, labelPlayer2GameOver;
	private ImageIcon imgMenuPlayer, imgPlayer1, imgPlayer2, imgHeart;
	private Manager manager;
	private BitSet mKeysValue;
	private int timeGame = 0;
	private int numberPlayers = 1;

	public PlayGamePanel(int numberPlayer) {
		this.numberPlayers = numberPlayer;
		initPlayGamePanel();
		initComps();
		addComps();
		createThread();
	}

	public int getNumberPlayers() {
		return numberPlayers;
	}

	public void setNumberPlayers(int numberPlayers) {
		this.numberPlayers = numberPlayers;
	}

	private void handlePressKeyBoard() {
		if (manager.getNumberHeartPlayer1() > 0) {
			if (mKeysValue.get(KeyEvent.VK_UP)) {
				manager.changeOrientBombPlayer1(DadParticipate.UP);
				manager.moveBombPlayer1(timeGame);
			}
			if (mKeysValue.get(KeyEvent.VK_DOWN)) {
				manager.changeOrientBombPlayer1(DadParticipate.DOWN);
				manager.moveBombPlayer1(timeGame);
			}
			if (mKeysValue.get(KeyEvent.VK_LEFT)) {
				manager.changeOrientBombPlayer1(DadParticipate.LEFT);
				manager.moveBombPlayer1(timeGame);
			}
			if (mKeysValue.get(KeyEvent.VK_RIGHT)) {
				manager.changeOrientBombPlayer1(DadParticipate.RIGHT);
				manager.moveBombPlayer1(timeGame);
			}
			if (mKeysValue.get(KeyEvent.VK_NUMPAD0)) {
				manager.bombPlayer1Fire();
			}
		}
		if (numberPlayers == 2 && manager.getNumberHeartPlayer2() > 0) {
			if (mKeysValue.get(KeyEvent.VK_W)) {
				manager.changeOrientBombPlayer2(DadParticipate.UP);
				manager.moveBombPlayer2(timeGame);
			}
			if (mKeysValue.get(KeyEvent.VK_S)) {
				manager.changeOrientBombPlayer2(DadParticipate.DOWN);
				manager.moveBombPlayer2(timeGame);
			}
			if (mKeysValue.get(KeyEvent.VK_A)) {
				manager.changeOrientBombPlayer2(DadParticipate.LEFT);
				manager.moveBombPlayer2(timeGame);
			}
			if (mKeysValue.get(KeyEvent.VK_D)) {
				manager.changeOrientBombPlayer2(DadParticipate.RIGHT);
				manager.moveBombPlayer2(timeGame);
			}
			if (mKeysValue.get(KeyEvent.VK_SPACE)) {
				manager.bombPlayer2Fire();
			}
		}
	}

	private void initComps() {
		labelMenuPlayer1 = new JLabel();
		imgMenuPlayer = new ImageIcon(getClass().getResource(
				"/image/background_Info.png"));
		labelMenuPlayer1.setIcon(imgMenuPlayer);
		labelMenuPlayer1.setBounds(700, 0, imgMenuPlayer.getIconWidth(),
				imgMenuPlayer.getIconHeight());

		labelImgPlayer1 = new JLabel();
		imgPlayer1 = new ImageIcon(getClass().getResource(
				"/image/bomber_down.png"));
		labelImgPlayer1.setIcon(imgPlayer1);
		labelImgPlayer1.setBounds(715, 30, imgPlayer1.getIconWidth(),
				imgPlayer1.getIconHeight());

		labelPlayer1 = new JLabel("Player 1");
		labelPlayer1.setFont(font);
		labelPlayer1.setForeground(Color.RED);
		labelPlayer1.setBounds(780, 40, 100, 30);

		labelImgHeartPlayer1 = new JLabel();
		imgHeart = new ImageIcon(getClass().getResource("/image/heart.png"));
		labelImgHeartPlayer1.setIcon(imgHeart);
		labelImgHeartPlayer1.setBounds(740, 130, imgHeart.getIconWidth(),
				imgHeart.getIconHeight());

		labelNumHeartPlayer1 = new JLabel("x "
				+ manager.getBombPlayer1().getNumsHeart());
		labelNumHeartPlayer1.setFont(font);
		labelNumHeartPlayer1.setForeground(Color.RED);
		labelNumHeartPlayer1.setBounds(780, 125, 100, 30);

		labelPlayer1GameOver = new JLabel("<Game Over>");
		labelPlayer1GameOver.setFont(font);
		labelPlayer1GameOver.setForeground(Color.RED);
		labelPlayer1GameOver.setBounds(720, 170, 150, 30);
		labelPlayer1GameOver.setVisible(false);

		labelMenuPlayer2 = new JLabel();
		imgMenuPlayer = new ImageIcon(getClass().getResource(
				"/image/background_Info.png"));
		labelMenuPlayer2.setIcon(imgMenuPlayer);
		labelMenuPlayer2.setBounds(700, imgMenuPlayer.getIconHeight(),
				imgMenuPlayer.getIconWidth(), imgMenuPlayer.getIconHeight());

		if (numberPlayers == 2) {
			labelImgPlayer2 = new JLabel();
			imgPlayer2 = new ImageIcon(getClass().getResource(
					"/image/bombplayer2_down.png"));
			labelImgPlayer2.setIcon(imgPlayer2);
			labelImgPlayer2.setBounds(715, 350, imgPlayer2.getIconWidth(),
					imgPlayer2.getIconHeight());

			labelPlayer2 = new JLabel("Player 2");
			labelPlayer2.setFont(font);
			labelPlayer2.setForeground(Color.RED);
			labelPlayer2.setBounds(780, 360, 100, 30);

			labelImgHeartPlayer2 = new JLabel();
			imgHeart = new ImageIcon(getClass().getResource("/image/heart.png"));
			labelImgHeartPlayer2.setIcon(imgHeart);
			labelImgHeartPlayer2.setBounds(740, 440, imgHeart.getIconWidth(),
					imgHeart.getIconHeight());

			labelNumHeartPlayer2 = new JLabel("x "
					+ manager.getBombPlayer2().getNumsHeart());
			labelNumHeartPlayer2.setFont(font);
			labelNumHeartPlayer2.setForeground(Color.RED);
			labelNumHeartPlayer2.setBounds(780, 435, 100, 30);

			labelPlayer2GameOver = new JLabel("<Game Over>");
			labelPlayer2GameOver.setFont(font);
			labelPlayer2GameOver.setForeground(Color.RED);
			labelPlayer2GameOver.setBounds(720, 500, 150, 30);
			labelPlayer2GameOver.setVisible(false);
		}
		mKeysValue = new BitSet(256);
		setFocusable(true);
	}

	private void addComps() {
		add(labelPlayer1);
		add(labelImgPlayer1);
		add(labelImgHeartPlayer1);
		add(labelNumHeartPlayer1);
		add(labelPlayer1GameOver);
		if (numberPlayers == 2) {
			add(labelPlayer2);
			add(labelImgPlayer2);
			add(labelImgHeartPlayer2);
			add(labelNumHeartPlayer2);
			add(labelPlayer2GameOver);
		}
		add(labelMenuPlayer1);
		add(labelMenuPlayer2);
	}

	private void initPlayGamePanel() {
		if (numberPlayers == 1) {
			manager = new Manager(1);
		} else {
			manager = new Manager(2);
		}
		manager.initObjects();
		manager.readFileToCreateItem("/map2/item2.txt");
		manager.readFileToCreateMap("/map2/map2.txt");
		manager.readFileToCreateMonster("/map2/monster2.txt");
		setLayout(null);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				mKeysValue.set(e.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				mKeysValue.clear(arg0.getKeyCode());
			}
		});
	}

	public void changeMap() {
		if (manager.changeMap()) {
			manager.deleteMap();
			manager.getBombPlayer1().setX(BombPlayer.START_X_PLAYER1);
			manager.getBombPlayer1().setY(BombPlayer.START_Y_PLAYER1);
			manager.readFileToCreateItem("/map3/item3.txt");
			manager.readFileToCreateMap("/map3/map3.txt");
			manager.readFileToCreateMonster("/map3/monster3.txt");
		}
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D g2d = (Graphics2D) arg0;
		ImageIcon imgBackGround = new ImageIcon(getClass().getResource(
				"/image/background_Play.png"));
		Image img = imgBackGround.getImage();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(img, 0, 0, GUI.WIDTH, GUI.HEIGHT, this);
		manager.draw(g2d);
	}

	public void createThread() {
		Thread thread = new Thread(runnable);
		thread.start();
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			while (true) {
				handlePressKeyBoard();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				timeGame++;
				manager.downTimeOutToBum(50);
				manager.downTimeOutToRemoveBombPlayer1(50);
				manager.downTimeOutToRemoveBombOfBoss(50);
				manager.removeComponentsAndMonsterAfterBombBang();
				manager.eatItemByBombPlayer1();
				manager.moveMonster(timeGame);
				manager.checkCollisionBombPlayer1AndBomb();
				manager.checkCollisionBombPlayer1AndMonster();
				manager.checkCollisionBombPlayer1AndBombOfBoss();
				manager.removeBossAfterBombBang();
				if (manager.getBombPlayer1().getBombPlayerIsCollisionWithBomb()) {
					manager.setTimeOutToPlayer1Dead(50);
				}
				if (manager.getBombPlayer1().getNumsHeart() == 0) {
					labelPlayer1GameOver.setVisible(true);
				}

				if (numberPlayers == 2) {
					manager.checkCollisionBombPlayer2AndMonster();
					manager.downTimeOutToRemoveBombPlayer2(50);
					manager.eatItemByBombPlayer2();
					manager.checkCollisionBombPlayer2AndBomb();
					manager.checkCollisionBombPlayer2AndBombOfBoss();
					labelNumHeartPlayer2.setText("x "
							+ manager.getBombPlayer2().getNumsHeart());
					if (manager.getBombPlayer2()
							.getBombPlayerIsCollisionWithBomb()) {
						manager.setTimeOutToPlayer2Dead(50);
					}
					if (manager.getBombPlayer2().getNumsHeart() == 0) {
						labelPlayer2GameOver.setVisible(true);
					}
				}
				manager.explosionBombToAnotherBomb();
				manager.createBombOfBoss(timeGame);
				manager.moveBombOfBoss();
				manager.removeComponentsAfterBombOfBossBang();
				manager.setLocationForBossAfterDead();
				changeMap();
				manager.playMusic();
				repaint();
				labelNumHeartPlayer1.setText("x "
						+ manager.getBombPlayer1().getNumsHeart());
			}
		}
	};

}
