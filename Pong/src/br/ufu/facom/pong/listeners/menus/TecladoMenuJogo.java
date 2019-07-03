package br.ufu.facom.pong.listeners.menus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.ufu.facom.pong.menus.MenuJogo;

public class TecladoMenuJogo implements KeyListener{
	private boolean shiftPressionado= false;

	private MenuJogo menu;
	
	public TecladoMenuJogo(MenuJogo menu) {
		this.menu = menu;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			menu.prox();
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			menu.ant();
		else if (e.getKeyCode() == KeyEvent.VK_SHIFT) 
			shiftPressionado = true;
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(shiftPressionado)
				menu.atual = (menu.atual + 3) % 4;
			else
				menu.atual = (menu.atual + 1) % 4;
		} else if(e.getKeyCode() == KeyEvent.VK_ENTER)
			menu.submeter();
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		menu.getPai().atualizaMenu();	
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			shiftPressionado = false;
	}
	public void keyTyped(KeyEvent e) {}

}
