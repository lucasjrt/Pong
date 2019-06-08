package br.ufu.facom.pong;

import br.ufu.facom.framework.objetos.FConstantes;
import br.ufu.facom.framework.objetos.FMediador;

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
		if ((bola.getEsquerda() <= jogador.getDireita()
				&& bola.getDireita() >= jogador.getEsquerda() && jogador.getIdJogador() == 0)) { // Verificação horizontal
			if (bola.getInferior() >= jogador.getTopo()
					&& bola.getTopo() <= jogador.getInferior()) { // Verificação vertical
				bola.setPosicao(jogador.getDireita() + (FConstantes.TAMANHO_BOLA >> 1),
						bola.getY());
				return jogador;
			} // Fim verificação vertical
		} // Fim verificação horizontal
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
	public void mover() {
		int aumVy = 0;
		boolean colide = false;
		if (colide(bola, jogadores[0]) != null) {
			colide = true;
			bola.vx = -bola.vx;
			aumVy = (Constantes.VELOCIDADE_MAX_VERT_BOLA * (bola.getY() - jogadores[0].getY())) / jogo.tamanhoBloco.height;  
		} else if (colide(bola, jogadores[1]) != null) {
			colide = true;
			bola.vx = -bola.vx;
			aumVy = (Constantes.VELOCIDADE_MAX_VERT_BOLA * (bola.getY() - jogadores[1].getY())) / jogo.tamanhoBloco.height;
		}
		if (colide) {
			if (bola.vy + aumVy <= Constantes.VELOCIDADE_MAX_VERT_BOLA
					&& bola.vy + aumVy >= -Constantes.VELOCIDADE_MAX_VERT_BOLA)
				bola.vy += aumVy;
			else if (bola.vy + aumVy <= Constantes.VELOCIDADE_MAX_VERT_BOLA)
				bola.vy = -Constantes.VELOCIDADE_MAX_VERT_BOLA;
			else if (bola.vy + aumVy >= -Constantes.VELOCIDADE_MAX_VERT_BOLA)
				bola.vy = Constantes.VELOCIDADE_MAX_VERT_BOLA;
		}
	}

	public int getX(int idJogador) {
		return jogadores[idJogador].getX();
	}

	public int getY(int idJogador) {
		return jogadores[idJogador].getY();
	}
}
