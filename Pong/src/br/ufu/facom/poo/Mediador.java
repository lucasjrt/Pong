package br.ufu.facom.poo;

import br.facom.ufu.poo.objetos.FMediador;

public class Mediador extends FMediador {
	private Jogador[] jogadores;
	private Bola bola;
	private PongGame jogo;

	public Mediador(PongGame jogo, Jogador[] jogadores, Bola bola) {
		this.jogo = jogo;
		this.jogadores = jogadores;
		this.bola = bola;
	}

	private Jogador colide(Bola bola, Jogador jogador) {
		if ((bola.getX() - (bola.TAMANHO_BOLA >> 1) <= jogador.getX() + 5 && jogador.getIdJogador() == 0))
			if (bola.getY() >= jogador.getY() - (jogo.tamanhoBloco >> 1)
					&& bola.getY() <= jogador.getY() + (jogo.tamanhoBloco >> 1)) {
				bola.setPosicao(jogador.getDireita() + (bola.TAMANHO_BOLA >> 1), bola.getY());
				return jogador;
			}
		if ((bola.getX() + (bola.TAMANHO_BOLA >> 1) >= jogador.getX() - 5 && jogador.getIdJogador() == 1))
			if (bola.getY() >= jogador.getY() - (jogo.tamanhoBloco >> 1)
					&& bola.getY() <= jogador.getY() + (jogo.tamanhoBloco >> 1)) {
				bola.setPosicao(jogador.getEsquerda() - (bola.TAMANHO_BOLA >> 1), bola.getY());
				return jogador;
			}
		return null;
	}

	public void pontua(int idJogador) {
		jogadores[idJogador].setPontuacao(jogadores[idJogador].getPontuacao() + 1);
	}
	
	@Override
	public void mover() {
		if (colide(bola, jogadores[0]) != null) {
			bola.vx = -bola.vx;
			bola.vy += (bola.getY() - jogadores[0].getY()) / 4;
		} else if (colide(bola, jogadores[1]) != null) {
			bola.vx = -bola.vx;
			bola.vy += (bola.getY() - jogadores[1].getY()) / 4;
		}
	}
}
