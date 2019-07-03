package br.ufu.facom.framework.objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.framework.objetos.ferramentas.FBloco;

public abstract class FJogador {
	protected int idJogador;
	protected FBloco bloco;

	protected FMediador med;
	protected FPong jogo;
	protected int velocidadeMovimento;
	protected FPontuacao pontuacao;

	public final int CIMA = 0;
	public final int BAIXO = 1;

	public int movendo = -1;

	/**
	 * 
	 * @param idJogador Maximo dois jogadores
	 */
	public FJogador(FPong jogo, int idJogador, Rectangle tamanho, FMediador med) {
		this.jogo = jogo;
		this.idJogador = idJogador;
		int largura = idJogador * jogo.LARGURA_TELA
				+ (idJogador == 0 ? (tamanho.width >> 1) + 20 : - (tamanho.width >> 1) - 20);
		int altura = (jogo.ALTURA_TELA >> 1);
		this.bloco = new FBloco(new Rectangle(tamanho.width, tamanho.height), largura, altura);
		velocidadeMovimento = 2 * jogo.VELOCIDADE_JOGO / 3;
	}

	public void mover(int direcao) {
		if (direcao == CIMA) {
			if (getTopo() > jogo.TOPO_CAMPO) {
				if (getTopo() - velocidadeMovimento <= jogo.TOPO_CAMPO) {
					bloco.setPosicao(getX(), jogo.TOPO_CAMPO + (jogo.tamanhoBloco.height >> 1));
				} else {
					bloco.setPosicao(getX(), getY() - velocidadeMovimento);
				}
			}
		} else if (direcao == BAIXO) {
			if (getInferior() < jogo.INFERIOR_CAMPO) {
				if (getInferior() + velocidadeMovimento > jogo.INFERIOR_CAMPO) {
					bloco.setPosicao(getX(), jogo.INFERIOR_CAMPO - (getAltura() >> 1));
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

	public void desenhar(Graphics g) {
		bloco.desenhar(g);
	}

	public void setPontuacao(int pontos) {
		pontuacao.setPontos(pontos);
	}

	public void setCor(Color cor) {
		bloco.setCor(cor);
	}

	public Color getCor() {
		return bloco.getCor();
	}

	public int getPontuacao() {
		return pontuacao.getPontos();
	}

	public void setMediador(FMediador med) {
		this.med = med;
	}
	
	public void setPosicao(int x, int y) {
		bloco.setPosicao(x, y);
	}

	public int getX() {
		return bloco.getX();
	}

	public int getY() {
		return bloco.getY();
	}

	public int getIdJogador() {
		return idJogador;
	}

	public int getTopo() {
		return bloco.getTopo();
	}

	public int getInferior() {
		return bloco.getInferior();
	}

	public int getDireita() {
		return bloco.getDireita();
	}

	public int getEsquerda() {
		return bloco.getEsquerda();
	}

	public int getAltura() {
		return bloco.getAltura();
	}

	public int getLargura() {
		return bloco.getLargura();
	}
}
