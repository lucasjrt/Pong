package br.ufu.facom.pong.jogos.futebol.objetosJogo;

import br.ufu.facom.framework.objetos.FBola;
import br.ufu.facom.framework.utilitarios.FConstantes;
import br.ufu.facom.pong.jogos.futebol.Futebol;

public class Bola extends FBola {
	
	private Futebol jogo;
	public Bola(Futebol jogo) {
		super(jogo);
		this.jogo = jogo;
		setPosicao(jogo.LARGURA_TELA >> 1, jogo.ALTURA_TELA >> 1);
		setVelocidade(jogo.VELOCIDADE_JOGO);
	}
	
	public void mover() {
		setPosicao(getX() + vx, getY() + vy);
		if (getTopo() <= jogo.TOPO_CAMPO || getInferior() >= jogo.INFERIOR_CAMPO)
			vy = -vy;
		if (getDireita() + 2*FConstantes.TAMANHO_BOLA < 0) {
			med.pontua(1);
			setPosicao(med.getX(1), med.getY(1) - jogo.VELOCIDADE_JOGO);
			vy = (int) (Math.random() * (jogo.VELOCIDADE_JOGO >> 1));
			if(Math.random() > 0.5)
				vy = -vy;
			vx = velocidade;
		} else if (getEsquerda() - 2*FConstantes.TAMANHO_BOLA > jogo.LARGURA_TELA) {
			med.pontua(0);
			setPosicao(med.getX(0), med.getY(0) + jogo.VELOCIDADE_JOGO);	
			vy = (int) (Math.random() * (jogo.VELOCIDADE_JOGO >> 1));
			if(Math.random() > 0.5)
				vy = -vy;
			vx = velocidade;
		}
		med.atualizar();
	}
}
