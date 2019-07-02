package br.ufu.facom.pong.tenis;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.framework.objetos.FBola;
import br.ufu.facom.framework.objetos.FConstantes;

public class Bola extends FBola {
	
	private FPong jogo;
	public Bola(FPong jogo) {
		super(jogo);
		this.jogo = jogo;
		setPosicao(jogo.LARGURA_TELA >> 1, jogo.ALTURA_TELA >> 1);
		setVelocidade(jogo.VELOCIDADE_JOGO);
	}
	
	public void mover() {
		setPosicao(getX() + vx, getY() + vy);
		if (getTopo() <= this.jogo.TOPO_CAMPO || getInferior() >= jogo.INFERIOR_CAMPO)
			vy = -vy;
		
		if (getX() <= 0) {
			med.pontua(1);
			setPosicao(med.getX(1), med.getY(1) - jogo.VELOCIDADE_JOGO);
			vy = (int) (Math.random() * (jogo.VELOCIDADE_JOGO >> 1));
			if(Math.random() > 0.5)
				vy = -vy;
		} else if (getX() >= jogo.LARGURA_TELA) {
			med.pontua(0);
			setPosicao(med.getX(0), med.getY(0) + jogo.VELOCIDADE_JOGO);	
			vy = (int) (Math.random() * (jogo.VELOCIDADE_JOGO >> 1));
			if(Math.random() > 0.5)
				vy = -vy;
		}
		med.mover();
	}
}
