package br.ufu.facom.pong.futebol;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TecladoTreino implements KeyListener {

	private Jogador[] jogadores;
	public TecladoTreino(Jogador[] jogadores) {
		this.jogadores = jogadores;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			jogadores[0].movendo = jogadores[0].CIMA;
			jogadores[1].movendo = jogadores[1].CIMA;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			jogadores[0].movendo = jogadores[0].BAIXO;
			jogadores[1].movendo = jogadores[1].BAIXO;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			jogadores[0].movendo = jogadores[0].CIMA;
			jogadores[1].movendo = jogadores[1].CIMA;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			jogadores[0].movendo = jogadores[0].BAIXO;
			jogadores[1].movendo = jogadores[1].BAIXO;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
			jogadores[0].movendo = -1;
			jogadores[1].movendo = -1;
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
			jogadores[0].movendo = -1;
			jogadores[1].movendo = -1;
		}
	}

	public void keyTyped(KeyEvent e) {
	}
}
