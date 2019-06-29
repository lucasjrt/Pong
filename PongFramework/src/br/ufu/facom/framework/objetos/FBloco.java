package br.ufu.facom.framework.objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FBloco {
	private Rectangle bloco;			// O que vai ser renderizado
	private int x, y;
	public boolean colide;
	
	protected FMediador med;
	
	private Color cor;
	
	public FBloco(Rectangle bloco, int x, int y) {
		this.bloco = bloco;
		this.x = x;
		this.y = y;
		this.bloco.x = x - (bloco.width >> 1);
		this.bloco.y = y - (bloco.height >> 1);
		cor = Color.white;
	}
	
	public void desenhar(Graphics g) {
		Color temp = g.getColor();
		g.setColor(cor);
		g.fillRect(bloco.x, bloco.y, bloco.width, bloco.height);
		g.setColor(temp);
	}
	
	public void setPosicao(int x, int y) {
		this.x = x;
		this.y = y;
		this.bloco.x = x - (bloco.width >> 1);
		this.bloco.y = y - (bloco.height >> 1);		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLargura() {
		return bloco.width;
	}
	
	public int getAltura() {
		return bloco.height;
	}
	
	public int getTopo() {
		return bloco.y;
	}
	
	public int getInferior() {
		return  bloco.y + bloco.height;
	}
	
	public int getEsquerda() {
		return bloco.x;
	}
	
	public int getDireita() {
		return bloco.x + bloco.width;
	}
	
	public Color getCor() {
		return cor;
	}
	
	public void setCor(Color cor) {
		this.cor = cor;
	}
}
