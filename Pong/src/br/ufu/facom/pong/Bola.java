package br.ufu.facom.pong;

import br.ufu.facom.framework.objetos.FBola;

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
		if (getTopo() <= 0 || getInferior() >= jogo.ALTURA_TELA) {
			vy = -vy;
		}
		if (getX() <= 0) {
			med.pontua(1);
			setPosicao(med.getX(1), med.getY(1) - Constantes.DISTANCIA_JOGADOR_REINICIO);
			vy = (int) (Math.random() * (Constantes.VELOCIDADE_MAX_VERT_BOLA >> 1));
			if(Math.random() > 0.5)
				vy = -vy;
		} else if (getX() >= jogo.LARGURA_TELA) {
			med.pontua(0);
			setPosicao(med.getX(0), med.getY(0) + Constantes.DISTANCIA_JOGADOR_REINICIO);	
			vy = (int) (Math.random() * (Constantes.VELOCIDADE_MAX_VERT_BOLA >> 1));
			if(Math.random() > 0.5)
				vy = -vy;
		}

		med.mover();
	}

	public void setMediador(Mediador med) {
		this.med = med;
	}
}
