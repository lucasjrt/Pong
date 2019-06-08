package br.ufu.facom.pong;

import java.awt.Graphics;
import java.awt.Rectangle;

import br.ufu.facom.framework.objetos.FJogador;

public class Jogador extends FJogador {
	//	private int velocidadeMovimento = 10;
	private int velocidadeMovimento = 2 * Constantes.VELOCIDADE_JOGO / 3;
	private PongGame jogo;

	public final int CIMA = 0;
	public final int BAIXO = 1;

	public int movendo = -1;

	private Pontuacao pontuacao;

	public Jogador(PongGame jogo, int idJogador, Rectangle tamanho, Mediador med) {
		super(jogo, idJogador, tamanho, med);
		this.jogo = jogo;
		this.pontuacao = new Pontuacao(jogo, this);
	}

	public void mover(int direcao) {
		if (direcao == CIMA) {
			if (getY() - (jogo.tamanhoBloco.height >> 1) >= 0) {
				if (getY() - (jogo.tamanhoBloco.height >> 1) - velocidadeMovimento < 0) {
					bloco.setPosicao(getX(), (jogo.tamanhoBloco.height >> 1));
				} else {
					bloco.setPosicao(getX(), getY() - velocidadeMovimento);
				}
			}
		} else if (direcao == BAIXO) {
			if (getY() < jogo.ALTURA_TELA - (jogo.tamanhoBloco.height >> 1)) {
				if (getY() + (jogo.tamanhoBloco.height >> 1)
						+ velocidadeMovimento > jogo.ALTURA_TELA) {
					bloco.setPosicao(getX(), jogo.ALTURA_TELA - (jogo.tamanhoBloco.height >> 1));
				} else {
					bloco.setPosicao(getX(), getY() + velocidadeMovimento);
				}
			}
		}
	}

	public void desenharPontuacao(Graphics g) {
		pontuacao.desenhar(g);
	}

	public void atualizar() {
		if (movendo == CIMA)
			mover(CIMA);
		else if (movendo == BAIXO)
			mover(BAIXO);
	}

	public void setPontuacao(int pontos) {
		pontuacao.setPontos(pontos);
	}

	public int getPontuacao() {
		return pontuacao.getPontos();
	}

	public void setMovendo() {

	}
}
