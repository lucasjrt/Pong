package br.ufu.facom.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TecladoMenu implements KeyListener{
	protected MenuJogo menu;
	protected boolean shiftPressionado= false;
	
	public TecladoMenu(MenuJogo menu) {
		this.menu = menu;
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			menu.selecionado[menu.atual] = (menu.selecionado[menu.atual] + 1) % menu.conteudos[menu.atual].length;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			menu.selecionado[menu.atual] = (menu.selecionado[menu.atual] + menu.conteudos[menu.atual].length - 1) % menu.conteudos[menu.atual].length;
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
		menu.atualizaMenu();	
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			shiftPressionado = false;
	}
}
