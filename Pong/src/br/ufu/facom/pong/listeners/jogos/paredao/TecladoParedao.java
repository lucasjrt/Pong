package br.ufu.facom.pong.listeners.jogos.paredao;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.ufu.facom.pong.jogos.paredao.objetosJogo.Jogador;

public class TecladoParedao implements KeyListener{
	private Jogador jogador;
	
	public TecladoParedao(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
			jogador.movendo = jogador.CIMA;
		if(e.getKeyCode() == KeyEvent.VK_S)
			jogador.movendo = jogador.BAIXO;
	}

	public void keyReleased(KeyEvent e) {
		jogador.movendo = -1;
	}

	public void keyTyped(KeyEvent e) {}

}
