package br.ufu.facom.pong.listeners.menus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.ufu.facom.pong.menus.MenuMultiplayer;

public class TecladoMenuMultiplayer implements KeyListener{
	MenuMultiplayer menu;
	
	public TecladoMenuMultiplayer(MenuMultiplayer menu) {
		this.menu = menu;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			menu.selecionado = (menu.selecionado + 1) % 2;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			menu.selecionado = (menu.selecionado + 1) % 2;
		else if(e.getKeyCode() == KeyEvent.VK_ENTER)
			menu.submeter();
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		menu.getPai().atualizaMenu();
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}
