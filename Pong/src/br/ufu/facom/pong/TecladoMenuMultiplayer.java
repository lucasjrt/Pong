package br.ufu.facom.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TecladoMenuMultiplayer implements KeyListener{
	MenuMultiplayer menu;
	
	public TecladoMenuMultiplayer(MenuMultiplayer menu) {
		this.menu = menu;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			menu.selecionado = (menu.selecionado + 1) % 3;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			menu.selecionado = (menu.selecionado + 2) % 3;
		else if(e.getKeyCode() == KeyEvent.VK_UP)
			menu.selecionado = 4;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			menu.selecionado = 2;
		else if(e.getKeyCode() == KeyEvent.VK_ENTER)
			menu.submeter();
		menu.atualizaMenu();
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}