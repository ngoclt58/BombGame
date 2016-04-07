package com.ngoclt.guiandpanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ngoclt.playsound.Sound;

public class GUI extends JFrame {
	private PlayGamePanel playGamePanel;
	private MenuPanel menuPanel;
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 700;
	public static final int WIDTH = 700;
	public static final int WIDTH_SCREEN = 900;

	public GUI() {
		initGUI();
		initComps();
		addComps();
		clickButton();
		music();
	}

	private void music() {
		Sound sound = new Sound("D:/menu.wav");
		sound.start();
	}

	private void clickButton() {
		menuPanel.getButtonPlay1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuPanel.setVisible(false);
				playGamePanel = new PlayGamePanel(1);
				GUI.this.add(playGamePanel);
				playGamePanel.setVisible(true);
				playGamePanel.requestFocusInWindow();
			}
		});

		menuPanel.getButtonPlay2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuPanel.setVisible(false);
				playGamePanel = new PlayGamePanel(2);
				GUI.this.add(playGamePanel);
				playGamePanel.setVisible(true);
				playGamePanel.requestFocusInWindow();
			}
		});

		menuPanel.getBtIntroduction().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane
						.showMessageDialog(
								null,
								"Player 1 dùng các phím lên, xuống , trái , phải để điều khiển và phím 0 để đặt bomb"
										+ "\n"
										+ "Player 2 dùng các phím a, w, s, d để điều khiển và phím space để đặt bomb"
										+ "\n"
										+ "Chúc các bạn trẻ chơi game vui vẻ! ");
			}
		});

		menuPanel.getBtAuthor().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Author: Lê Thanh Ngọc"
						+ "\n"
						+ "Trường: Đại học Công Nghệ- Đại học Quốc gia Hà Nội"
						+ "\n" + "Email: ngoclt_58@vnu.edu.vn");
			}
		});
	}

	private void addComps() {
		add(menuPanel);
		// add(playGamePanel);
	}

	private void initComps() {
		menuPanel = new MenuPanel();
		// playGamePanel = new PlayGamePanel();
		// playGamePanel.setVisible(false);
	}

	private void initGUI() {
		setTitle("Bomb version Ngọc Lê");
		setSize(WIDTH_SCREEN, HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new CardLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
