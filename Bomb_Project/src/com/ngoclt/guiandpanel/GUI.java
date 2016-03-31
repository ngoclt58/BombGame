package com.ngoclt.guiandpanel;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class GUI extends JFrame {
	private PlayGamePanel playGamePanel;
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 700;
	public static final int WIDTH = 700;

	public GUI() {
		initGUI();
		initComps();
		addComps();
		music();
	}

	private void music() {
		// Sound sound = new Sound("D:/nova.wav");
		// sound.start();
	}

	private void addComps() {
		add(playGamePanel);
	}

	private void initComps() {
		playGamePanel = new PlayGamePanel();
	}

	private void initGUI() {
		setTitle("Bomb version Ngọc Lê");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new CardLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
