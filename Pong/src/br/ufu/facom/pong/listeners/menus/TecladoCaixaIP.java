package br.ufu.facom.pong.listeners.menus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.ufu.facom.pong.menus.MenuCliente;

public class TecladoCaixaIP implements KeyListener {
	MenuCliente menu;

	public TecladoCaixaIP(MenuCliente menu) {
		this.menu = menu;
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			menu.submeter();
		else if (Character.getType(e.getKeyChar()) != 0) {
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && menu.ip.length() > 0)
				menu.ip = menu.ip.substring(0, menu.ip.length() - 1);
			else if (menu.ip.length() <= 14)
				menu.ip += e.getKeyChar();
			menu.getPai().atualizaMenu();
		} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
