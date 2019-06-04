package br.facom.ufu.poo.objetos;

import java.awt.Graphics;
import java.awt.Rectangle;

import br.facom.ufu.poo.FPong;

public abstract class FJogador {
	protected int idJogador;
	protected FBloco bloco;
	
	protected FMediador med;
	protected FPong jogo;
//	private int pontos;

	/**
	 * 
	 * @param idJogador Maximo dois jogadores
	 */
	public FJogador(FPong jogo, int idJogador, Rectangle tamanho, FMediador med) {
		this.jogo = jogo;
		this.idJogador = idJogador;
		int xPos = idJogador * jogo.LARGURA_TELA + (idJogador == 0 ? (tamanho.width >> 1) + 20: -(tamanho.width >> 1) - 20);
		int yPos = (jogo.ALTURA_TELA >> 1);
		this.bloco = new FBloco(new Rectangle(tamanho.width, tamanho.height), xPos, yPos, med);
	}

	public void desenhar(Graphics g) {
		bloco.desenhar(g);
	}
	
	public void setMediador(FMediador med) {
		this.med = med;
	}
	
	public int getX() {
		return bloco.x;
	}
	
	public int getY() {
		return bloco.y;
	}
	
	public int getIdJogador() {
		return idJogador;
	}
	
	public int getTopo() {
		return bloco.x - (bloco.getAltura() >> 1);
	}
	
	public int getInferior() {
		return bloco.x + (bloco.getAltura() >> 1);
	}
	
	public int getDireita() {
		return bloco.x + (bloco.getLargura() >> 1);
	}
	
	public int getEsquerda() {
		return bloco.x - (bloco.getLargura() >> 1);
	}
}
