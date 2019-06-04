package br.facom.ufu.poo.objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Similar a um display de sete segmentos posicoes do vetor:
 *  
 *   0 
 * 1   2
 *   3 
 * 4   5 
 *   6
 * 
 * @author lucasjrt
 *
 */
public class FDisplay {
	private Rectangle[] controle;
	private Rectangle[] segmentos;
	private int x, y;

	public final int LARGURA_NUMERO = 150;
	public final int ALTURA_NUMERO = 210;
	public final int EXPESSURA = 15;

	/**
	 * Similar a um display de 7 segmentos
	 * 
	 * @param valor numero que o display ira representar
	 */
	public FDisplay(int valor) {
		controle = new Rectangle[7];
		segmentos = new Rectangle[7];
		atualizar(controle, 8);
		atualizar(segmentos, valor);
	}

	public FDisplay(int valor, int x, int y) {
		controle = new Rectangle[7];
		segmentos = new Rectangle[7];
		atualizar(controle, 8);
		atualizar(segmentos, valor);
		this.x = x;
		this.y = y;
	}
	
	public void desenhar(Graphics g) {
		Color temp = g.getColor();
		
		g.setColor(Color.black);
		for(int i = 0; i < segmentos.length; i++)
			g.fillRect(controle[i].x + x, controle[i].y + y, controle[i].width, controle[i].height);
		
		g.setColor(Color.white);
		for(int i = 0; i < segmentos.length; i++)
			if(segmentos[i] != null) 
				g.fillRect(segmentos[i].x + x, segmentos[i].y + y, segmentos[i].width, segmentos[i].height);
		
		g.setColor(temp);
		
	}

	private void atualizar(Rectangle[] vetor,int valor) {
		switch (valor) {
		case 0:
			for (int i = 0; i < 7; i++)
				if (i == 3)
					vetor[i] = null;
				else if (vetor[i] == null)
					vetor[i] = calculaRetangulo(i);
			break;
		case 1:
			for (int i = 0; i < 7; i++)
				if (i == 2 || i == 5) {
					if (vetor[i] == null)
						vetor[i] = calculaRetangulo(i);
				} else
					vetor[i] = null;
			break;
		case 2:
			for (int i = 0; i < 7; i++)
				if (i == 1 || i == 5)
					vetor[i] = null;
				else if (vetor[i] == null)
					vetor[i] = calculaRetangulo(i);
			break;
		case 3:
			for (int i = 0; i < 7; i++)
				if (i == 1 || i == 4)
					vetor[i] = null;
				else if (vetor[i] == null)
					vetor[i] = calculaRetangulo(i);
			break;
		case 4:
			for (int i = 0; i < 7; i++)
				if (i == 0 || i == 4 || i == 6)
					vetor[i] = null;
				else if (vetor[i] == null)
					vetor[i] = calculaRetangulo(i);
			break;
		case 5:
			for (int i = 0; i < 7; i++)
				if (i == 2 || i == 4)
					vetor[i] = null;
				else if (vetor[i] == null)
					vetor[i] = calculaRetangulo(i);
			break;
		case 6:
			for (int i = 0; i < 7; i++)
				if (i == 2)
					vetor[i] = null;
				else if (vetor[i] == null)
					vetor[i] = calculaRetangulo(i);
			break;
		case 7:
			for (int i = 0; i < 7; i++)
				if (i == 0 || i == 2 || i == 5) {
					if (vetor[i] == null)
						vetor[i] = calculaRetangulo(i);
				} else
					vetor[i] = null;
			break;
		case 8:
			for (int i = 0; i < 7; i++) {
				vetor[i] = calculaRetangulo(i);
			}
			break;
		case 9:
			for (int i = 0; i < 7; i++)
				if (i == 4)
					vetor[i] = null;
				else if (vetor[i] == null)
					vetor[i] = calculaRetangulo(i);
			break;
		}
	}

	private Rectangle calculaRetangulo(int posicaoVetor) {
		switch (posicaoVetor) {
		case 0:
			return new Rectangle(LARGURA_NUMERO, EXPESSURA);
		case 1:
			return new Rectangle(EXPESSURA, ALTURA_NUMERO >> 1);
		case 2:
			return new Rectangle(LARGURA_NUMERO - EXPESSURA, 0, EXPESSURA, ALTURA_NUMERO >> 1);
		case 3:
			return new Rectangle(0, (ALTURA_NUMERO >> 1) - (EXPESSURA >> 1), LARGURA_NUMERO, EXPESSURA);
		case 4:
			return new Rectangle(0, ALTURA_NUMERO >> 1, EXPESSURA, ALTURA_NUMERO >> 1);
		case 5:
			return new Rectangle(LARGURA_NUMERO - EXPESSURA, ALTURA_NUMERO >> 1, EXPESSURA, ALTURA_NUMERO >> 1);
		case 6:
			return new Rectangle(0, ALTURA_NUMERO - EXPESSURA, LARGURA_NUMERO, EXPESSURA);
		}
		return null;
	}

	public void setPosicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setValor(int valor) {
		atualizar(segmentos, valor);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
