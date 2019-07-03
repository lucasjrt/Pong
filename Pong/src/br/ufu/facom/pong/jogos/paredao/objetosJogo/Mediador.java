package br.ufu.facom.pong.jogos.paredao.objetosJogo;

import java.awt.Color;

import br.ufu.facom.framework.objetos.FMediador;
import br.ufu.facom.framework.utilitarios.FConstantes;
import br.ufu.facom.pong.jogos.paredao.Paredao;

public class Mediador extends FMediador {
	private Paredao jogo;
	private Bola bola;
	private Jogador jogador;

	public Mediador(Paredao jogo, Jogador jogador, Bola bola) {
		super(jogador);
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
			} // Fim verificacao vertical
		} // Fim verificacao horizontal
		return null;
	}

	@Override
	public void atualizar() {
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
		if (colide) {
			if (bola.getVy() + aumVy <= jogo.VELOCIDADE_JOGO
					&& bola.getVy() + aumVy >= -jogo.VELOCIDADE_JOGO)
				bola.setVy(bola.getVy() + aumVy);
			else if (bola.getVy() + aumVy <= jogo.VELOCIDADE_JOGO)
				bola.setVy(-jogo.VELOCIDADE_JOGO);
			else if (bola.getVy() + aumVy >= -jogo.VELOCIDADE_JOGO)
				bola.setVy(jogo.VELOCIDADE_JOGO);
		}
	}

	@Override
	public void pontua(int arg0) {
		jogador.setPontuacao(jogador.getPontuacao() + 1, jogador.getIdJogador());
	}

}
