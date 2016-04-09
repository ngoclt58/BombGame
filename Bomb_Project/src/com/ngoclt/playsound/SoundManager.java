package com.ngoclt.playsound;

public class SoundManager {
	private SourceEffect soundMenu, soundBombBang, soundBomberDie, soundItem,
			soundWin;

	public SoundManager() {
		soundMenu = new SourceEffect("menu.wav");
		soundBombBang = new SourceEffect("bomb_bang.wav");
		soundBomberDie = new SourceEffect("bomber_die.wav");
		soundItem = new SourceEffect("item.wav");
		soundWin = new SourceEffect("win.wav");
	}

	public SourceEffect getSoundBombBang() {
		return soundBombBang;
	}

	public SourceEffect getSoundBomberDie() {
		return soundBomberDie;
	}

	public SourceEffect getSoundItem() {
		return soundItem;
	}

	public SourceEffect getSoundWin() {
		return soundWin;
	}

	public SourceEffect getSoundMenu() {
		return soundMenu;
	}

}
