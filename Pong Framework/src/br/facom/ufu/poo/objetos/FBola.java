package br.facom.ufu.poo.objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class FBola {
	public final int VELOCIDADE_CRESCENTE = 0;
	public final int VELOCIDADE_BAIXA = 1;
	public final int VELOCIDADE_MEDIA = 2;
	public final int VELOCIDADE_ALTA = 3;
	
	private final int TAMANHO_BOLA = 10;
	
	private Rectangle bordas;			// O circulo e renderizado como uma elipse, portanto precisa de um retangulo
//	private int velocidade = 0;		// O quanto a bola anda a cada quadro
	private int x, y;
//	private int ax = 0, ay = 0;		// Vetor de direcao
//	private Mediador med;
	
	public FBola(Mediador med) {
//		this.med = med;
		bordas = new Rectangle(TAMANHO_BOLA, TAMANHO_BOLA);
		bordas.x = x - (TAMANHO_BOLA >> 1);
		bordas.y = y - (TAMANHO_BOLA >> 1);
	}
	
	public void setPosicao(int x, int y) {
		this.x = x;
		this.y = y;
		bordas.x = x - (TAMANHO_BOLA >> 1);
		bordas.y = y - (TAMANHO_BOLA >> 1);
	}
	
	public void setVelocidade(int velocidade) {
//		this.velocidade = velocidade;
	}
	
	public void desenhar(Graphics g) {
		Color temp = g.getColor();
		g.setColor(Color.white);
		g.fillRect(bordas.x, bordas.y, bordas.width, bordas.height);
		g.setColor(temp);
	}
	
	public void moverBola() {
		
	}
}
