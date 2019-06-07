package br.ufu.facom.poo.pong;

import br.facom.ufu.poo.objetos.FBola;

public class Bola extends FBola {
	public int vx, vy;
	private Mediador med;

	private PongGame jogo;

	public Bola(PongGame jogo) {
		this.jogo = jogo;
		setPosicao(jogo.LARGURA_TELA >> 1, jogo.ALTURA_TELA >> 1);
	}

	public void comecarMover() {
		if (Math.random() > 0.5) {
			vx = velocidade;
		} else {
			vx = -velocidade;
		}
	}

	public void mover() {
		setPosicao(getX() + vx, getY() + vy);
		if (getY() <= 0 || getY() >= jogo.ALTURA_TELA) {
			vy = -vy;
		}
		if (getX() <= 0) {
			med.pontua(1);
			setPosicao(jogo.LARGURA_TELA >> 1, jogo.ALTURA_TELA >> 1);
		} else if (getX() >= jogo.LARGURA_TELA) {
			med.pontua(0);
			setPosicao(jogo.LARGURA_TELA >> 1, jogo.ALTURA_TELA >> 1);
		}

		med.mover();
	}

	public void setMediador(Mediador med) {
		this.med = med;
	}
}
