package br.facom.ufu.poo.objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class FBola {
	public final int VELOCIDADE_CRESCENTE = 0;
	public final int VELOCIDADE_BAIXA = 5;
	public final int VELOCIDADE_MEDIA = 15;
	public final int VELOCIDADE_ALTA = 25;
	
	public final int TAMANHO_BOLA = 20;
	
	protected Rectangle bloco;			// Area de renderizaca da bola
	private  int x, y;
	protected int velocidade;
	
	public FBola() {
		bloco = new Rectangle(TAMANHO_BOLA, TAMANHO_BOLA);
		bloco.x = x - (TAMANHO_BOLA >> 1);
		bloco.y = y - (TAMANHO_BOLA >> 1);
	}
	
	public void setPosicao(int x, int y) {
		this.x = x;
		this.y = y;
		bloco.x = x - (TAMANHO_BOLA >> 1);
		bloco.y = y - (TAMANHO_BOLA >> 1);
	}
	
	public void desenhar(Graphics g) {
		Color temp = g.getColor();
		g.setColor(Color.white);
		g.fillRect(bloco.x, bloco.y, bloco.width, bloco.height);
		g.setColor(temp);
	}
	
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
