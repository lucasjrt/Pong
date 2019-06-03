package br.facom.ufu.poo.objetos;

import java.awt.Graphics;
import java.awt.Rectangle;

import br.facom.ufu.poo.FPong;

public abstract class FJogador {
	protected int idJogador;
	protected FBloco jogador;
//	private int pontos;

	/**
	 * 
	 * @param idJogador Maximo dois jogadores
	 */
	public FJogador(FPong jogo, int idJogador, Rectangle tamanho, Mediador med) {
//		this.idJogador = idJogador;
		int xPos = idJogador * jogo.LARGURA_TELA + (idJogador == 0 ? tamanho.width >> 1 : -tamanho.width >> 1);
		int yPos = (jogo.ALTURA_TELA >> 1);
		this.jogador = new FBloco(new Rectangle(tamanho.width, tamanho.height), xPos, yPos, med);
	}

	public void desenhar(Graphics g) {
		jogador.desenhar(g);
	}

	public abstract void controle();
}
