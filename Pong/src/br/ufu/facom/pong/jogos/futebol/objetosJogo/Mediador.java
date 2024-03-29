package br.ufu.facom.pong.jogos.futebol.objetosJogo;

import br.ufu.facom.framework.objetos.FMediador;
import br.ufu.facom.framework.utilitarios.FConstantes;
import br.ufu.facom.pong.jogos.futebol.Futebol;

public class Mediador extends FMediador {
	private Jogador[] jogadores;
	private Bola bola;
	private Futebol jogo;
	private Obstaculos obstaculo;

	public Mediador(Futebol jogo, Jogador[] jogadores, Bola bola, Obstaculos obstaculo) {
		super(jogadores);
		this.obstaculo = obstaculo;
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
		
		Bloco bloco = colisaoBloco();
		if(bloco != null) {
			bola.setVx(-bola.getVx());
			aumVy = (jogo.VELOCIDADE_JOGO * (bola.getY() - bloco.getY()))
					/ jogo.tamanhoBloco.height;
		}
		
		if (colide || bloco != null) {
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
}
