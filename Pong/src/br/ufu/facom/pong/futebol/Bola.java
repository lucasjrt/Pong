package br.ufu.facom.pong.futebol;

import br.ufu.facom.framework.objetos.FBola;
import br.ufu.facom.framework.objetos.FConstantes;

public class Bola extends FBola{
	private Futebol jogo;
	private Jogador jogador;
	private Obstaculos obstaculo;
	
	public Bola(Futebol jogo, Jogador jogador) {
		super(jogo);
		
		this.jogo = jogo;
		this.jogador = jogador;
		setPosicao(jogador.getX(), jogador.getY() + jogo.VELOCIDADE_JOGO);
		setVelocidade(jogo.VELOCIDADE_JOGO);
	}
	
	public void comecarMover() {
		vx = velocidade;
	}
	
	public void mover() {
		setPosicao(getX() + vx, getY() + vy);
		if (getTopo() <= jogo.TOPO_CAMPO || getInferior() >= jogo.INFERIOR_CAMPO)
			vy = -vy;
		
		if (getX() <= 0) {
			med.pontua(jogador.getIdJogador());
			setPosicao(jogador.getX(), jogador.getY() + jogo.VELOCIDADE_JOGO);
			vx = velocidade;
			vy = (int) (Math.random() * (jogo.VELOCIDADE_JOGO >> 2));
			if(Math.random() > 0.5)
				vy = -vy;
		} else if (getX() >= jogo.DIREITA_CAMPO) {
			setPosicao(jogo.DIREITA_CAMPO - (FConstantes.TAMANHO_BOLA >> 1), y);	
			vx = -vx;
		}
		
		med.mover();
	}

}
