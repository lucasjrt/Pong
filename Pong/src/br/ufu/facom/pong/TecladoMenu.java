package br.ufu.facom.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TecladoMenu implements KeyListener{
	private boolean shiftPressionado= false;

	private Menu menu;
	
	public TecladoMenu(Menu menu) {
		this.menu = menu;
	}
	
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
			menu.iniciarJogo();
		menu.atualizaMenu();	
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			shiftPressionado = false;
	}
	public void keyTyped(KeyEvent e) {}

}
