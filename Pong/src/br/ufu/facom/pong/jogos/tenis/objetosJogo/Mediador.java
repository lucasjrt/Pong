package br.ufu.facom.pong.jogos.tenis.objetosJogo;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.framework.objetos.FMediador;
import br.ufu.facom.framework.utilitarios.FConstantes;

public class Mediador extends FMediador {
	private Jogador[] jogadores;
	private Bola bola;
	private FPong jogo;

	public Mediador(FPong jogo, Jogador[] jogadores, Bola bola) {
		super(jogadores);
		this.jogo = jogo;
		this.jogadores = jogadores;
		this.bola = bola;
	}

	private Jogador colide(Bola bola, Jogador jogador) {
		if ((bola.getEsquerda() <= jogador.getDireita()
				&& bola.getDireita() >= jogador.getEsquerda() && jogador.getIdJogador() == 0)) { // Verificacao horizontal
			if (bola.getInferior() >= jogador.getTopo()
					&& bola.getTopo() <= jogador.getInferior()) { // Verificacao vertical
				bola.setPosicao(jogador.getDireita() + (FConstantes.TAMANHO_BOLA >> 1),
						bola.getY());
				return jogador;
			} // Fim verificacao vertical
		} // Fim verificacao horizontal
		if ((bola.getDireita() >= jogador.getEsquerda()
				&& bola.getEsquerda() <= jogador.getDireita() && jogador.getIdJogador() == 1))
			if (bola.getY() >= jogador.getY() - (jogo.tamanhoBloco.height >> 1)
					&& bola.getY() <= jogador.getY() + (jogo.tamanhoBloco.height >> 1)) {
				bola.setPosicao(jogador.getEsquerda() - (FConstantes.TAMANHO_BOLA >> 1),
						bola.getY());
				return jogador;
			}
		return null;
	}

	public void pontua(int idJogador) {
		jogadores[idJogador].setPontuacao(jogadores[idJogador].getPontuacao() + 1);
	}

	@Override
	public void atualizar() {
		int aumVy = 0;
		boolean colide = false;
		if (colide(bola, jogadores[0]) != null) {
			colide = true;
			bola.setVx(-bola.getVx());
			aumVy = (jogo.VELOCIDADE_JOGO * (bola.getY() - jogadores[0].getY()))
					/ jogo.tamanhoBloco.height;
		} else if (colide(bola, jogadores[1]) != null) {
			colide = true;
			bola.setVx(-bola.getVx());
			aumVy = (jogo.VELOCIDADE_JOGO * (bola.getY() - jogadores[1].getY()))
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
}
