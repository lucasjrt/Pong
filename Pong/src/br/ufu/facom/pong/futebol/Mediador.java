package br.ufu.facom.pong.futebol;

import java.awt.Color;
import java.util.ArrayList;

import br.ufu.facom.framework.objetos.FConstantes;
import br.ufu.facom.framework.objetos.FMediador;

public class Mediador extends FMediador {
	private Futebol jogo;
	private Bola bola;
	private Jogador jogador;
	private Obstaculos obstaculo;

	public Mediador(Futebol jogo, Jogador jogador, Bola bola, Obstaculos obstaculos) {
		super(jogador);
		this.obstaculo = obstaculos;
		this.jogo = jogo;
		this.bola = bola;
		this.jogador = jogador;
	}

	private Jogador colide(Bola bola, Jogador jogador) {
		if ((bola.getEsquerda() <= jogador.getDireita()
				&& bola.getDireita() >= jogador.getEsquerda())) { // Verificação horizontal
			if (bola.getInferior() >= jogador.getTopo()
					&& bola.getTopo() <= jogador.getInferior()) { // Verificação vertical
				bola.setPosicao(jogador.getDireita() + (FConstantes.TAMANHO_BOLA >> 1),
						bola.getY());
				return jogador;
			} // Fim verificação vertical
		} // Fim verificação horizontal
		return null;
	}

	@Override
	public void mover() {
		int aumVy = 0;
		boolean colide = false;
		if (colide(bola, jogador) != null) {
			if(jogador.getIdJogador() == 0) {
				jogador.setCor(Color.blue);
				jogador.setIdJogador(1);
			} else {
				jogador.setCor(Color.green);
				jogador.setIdJogador(0);
			}
			colide = true;
			bola.setVx(-bola.getVx());
			aumVy = (jogo.VELOCIDADE_JOGO * (bola.getY() - jogador.getY()))
					/ jogo.tamanhoBloco.height;
		}
		
		Bloco bloco = colisaoBloco();
		if(bloco != null) {
			bola.setVx(-bola.getVx());
			aumVy = (jogo.VELOCIDADE_JOGO * (bola.getY() - bloco.getY()))
					/ jogo.tamanhoBloco.height;
		}
		
		
		
		if (colide || bloco!=null) {
			if (bola.getVy() + aumVy <= jogo.VELOCIDADE_JOGO
					&& bola.getVy() + aumVy >= -jogo.VELOCIDADE_JOGO)
				bola.setVy(bola.getVy() + aumVy);
			else if (bola.getVy() + aumVy <= jogo.VELOCIDADE_JOGO)
				bola.setVy(-jogo.VELOCIDADE_JOGO);
			else if (bola.getVy() + aumVy >= -jogo.VELOCIDADE_JOGO)
				bola.setVy(jogo.VELOCIDADE_JOGO);
		}
	}
	
	private Bloco colisaoBloco() {
		for(int i=0; i<6; i++) {
			if(bola.getVx()>0) {
				if(bola.getDireita() >= obstaculo.bloco.get(i).getEsquerda()
						&& bola.getEsquerda() < obstaculo.bloco.get(i).getDireita()
						&& bola.getInferior() >= obstaculo.bloco.get(i).getTopo()
						&& bola.getTopo() <= obstaculo.bloco.get(i).getInferior()) {
					return obstaculo.bloco.get(i);
				}
			}
			if(bola.getEsquerda() <= obstaculo.bloco.get(i).getDireita()
					&& bola.getDireita() > obstaculo.bloco.get(i).getEsquerda()
					&& bola.getInferior() >= obstaculo.bloco.get(i).getTopo()
					&& bola.getTopo() <= obstaculo.bloco.get(i).getInferior()) {
				return obstaculo.bloco.get(i);
			}
		}
		return null;
	}

	@Override
	public void pontua(int arg0) {
		jogador.setPontuacao(jogador.getPontuacao() + 1, jogador.getIdJogador());
	}

}
