package br.ufu.facom.pong.listeners.menus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.ufu.facom.pong.menus.MenuMultiplayer;

public class TecladoCaixaIP implements KeyListener {
	MenuMultiplayer menu;

	public TecladoCaixaIP(MenuMultiplayer menu) {
		this.menu = menu;
	}

	public void keyPressed(KeyEvent e) {
		if (menu.selecionado == 2 && Character.getType(e.getKeyChar()) != 0) {
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && menu.ipHost.length() > 0)
				menu.ipHost = menu.ipHost.substring(0, menu.ipHost.length() - 1);
			else if (menu.ipHost.length() <= 14)
				menu.ipHost += e.getKeyChar();
			menu.atualizaMenu();
		}
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
}
