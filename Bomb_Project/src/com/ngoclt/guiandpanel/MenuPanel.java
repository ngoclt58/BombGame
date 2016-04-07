package com.ngoclt.guiandpanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	/** */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 30);
	private JLabel labelBackGround;
	private ImageIcon imgBackGround;
	private JButton btPlay1, btPlay2, btIntroduction, btAuthor;

	public MenuPanel() {
		initMenuPanel();
		initComps();
		addComps();
	}

	public JButton getButtonPlay1() {
		return btPlay1;
	}
	
	public JButton getButtonPlay2() {
		return btPlay2;
	}
	
	public JButton getBtIntroduction() {
		return btIntroduction;
	}

	public JButton getBtAuthor() {
		return btAuthor;
	}
	
	private void addComps() {
		add(btPlay1);
		add(btPlay2);
		add(btIntroduction);
		add(btAuthor);
		add(labelBackGround);
	}

	private void initComps() {
		labelBackGround = new JLabel();
		labelBackGround.setBounds(0,0,900,700);
		imgBackGround = new ImageIcon(getClass().getResource("/image/background_menu.png"));
		labelBackGround.setIcon(imgBackGround);
		
		btPlay1 = new JButton("1 player ");
		btPlay1.setBackground(Color.RED);
		btPlay1.setFont(font);
		btPlay1.setForeground(Color.YELLOW);
		btPlay1.setBounds(600, 300, 250, 40);
		
		btPlay2 = new JButton("2 player ");
		btPlay2.setBackground(Color.RED);
		btPlay2.setFont(font);
		btPlay2.setForeground(Color.YELLOW);
		btPlay2.setBounds(600, 370, 250, 40);
		
		btIntroduction = new JButton("Introduction ");
		btIntroduction.setBackground(Color.RED);
		btIntroduction.setFont(font);
		btIntroduction.setForeground(Color.YELLOW);
		btIntroduction.setBounds(600, 440, 250, 40);
		
		btAuthor = new JButton("Author ");
		btAuthor.setBackground(Color.RED);
		btAuthor.setFont(font);
		btAuthor.setForeground(Color.YELLOW);
		btAuthor.setBounds(600, 510, 250, 40);
	}

	private void initMenuPanel() {
		setLayout(null);
	}
	
}
