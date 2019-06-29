package br.ufu.facom.pong.futebol;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
	private Jogador jogador;
	
	public Teclado(Jogador jogador) {
		this.jogador = jogador;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
			jogador.movendo = jogador.CIMA;
		if(e.getKeyCode() == KeyEvent.VK_S)
			jogador.movendo = jogador.BAIXO;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		jogador.movendo = -1;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
