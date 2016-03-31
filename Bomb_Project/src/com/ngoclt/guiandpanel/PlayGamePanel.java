package com.ngoclt.guiandpanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.ngoclt.characterparticipate.DadParticipate;
import com.ngoclt.characterparticipate.Manager;

public class PlayGamePanel extends JPanel {

	/** */
	private static final long serialVersionUID = 1L;
	private Manager manager;
	private BitSet mKeysValue;
	private int timeGame = 0;

	public PlayGamePanel() {
		initPlayGamePanel();
		initComps();
		createThread();
	}

	private void handlePressKeyBoard() {
		if (mKeysValue.get(KeyEvent.VK_UP)) {
			manager.changeOrientBombPlayer(DadParticipate.UP);
			manager.moveBombPlayer(timeGame);
		}
		if (mKeysValue.get(KeyEvent.VK_DOWN)) {
			manager.changeOrientBombPlayer(DadParticipate.DOWN);
			manager.moveBombPlayer(timeGame);
		}
		if (mKeysValue.get(KeyEvent.VK_LEFT)) {
			manager.changeOrientBombPlayer(DadParticipate.LEFT);
			manager.moveBombPlayer(timeGame);
		}
		if (mKeysValue.get(KeyEvent.VK_RIGHT)) {
			manager.changeOrientBombPlayer(DadParticipate.RIGHT);
			manager.moveBombPlayer(timeGame);
		}
		if (mKeysValue.get(KeyEvent.VK_SPACE)) {
			manager.bombPlayerFire();
		}
	}

	private void addComps() {

	}

	private void initComps() {
		mKeysValue = new BitSet(256);
		setFocusable(true);
	}

	private void initPlayGamePanel() {
		manager = new Manager();
		manager.initObjects();
		manager.readFileToCreateItem("/map/item2.txt");
		manager.readFileToCreateMap("/map/map2.txt");

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				timeGame++;
				manager.downTimeOutToBum(50);
				manager.downTimeOutToRemoveBomb(50);
				manager.removeComponentsAndMonsterAfterBombBang();
				manager.eatItemByBombPlayer();
				manager.moveMonster(timeGame);
				manager.checkCollisionBombPlayerAndBomb();
				manager.checkCollisionBombPlayerAndMonster();
				manager.explosionBombToAnotherBomb();
				repaint();
			}
		}
	};

}
