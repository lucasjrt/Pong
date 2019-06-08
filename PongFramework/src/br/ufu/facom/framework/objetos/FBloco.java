package br.ufu.facom.framework.objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FBloco {
	private Rectangle bloco;			// O que vai ser renderizado
	protected int x, y;				// Centro do bloco
	public boolean colide;
	
	protected FMediador med;
	
	public FBloco(Rectangle bloco, int x, int y, FMediador med) {
		this.x = x;
		this.y = y;
		this.bloco = bloco;
		//this.med = med;
		this.bloco.x = x - (bloco.x + ((bloco.width - bloco.x) >> 1));
		this.bloco.y = y - (bloco.y + ((bloco.height - bloco.y) >> 1));
	}
	
	public void desenhar(Graphics g) {
		Color temp = g.getColor();
		g.setColor(Color.white);
		g.fillRect(bloco.x, bloco.y, bloco.width, bloco.height);
		g.setColor(temp);
	}
	
	public void setPosicao(int x, int y) {
		this.x = x;
		this.y = y;
		this.bloco.x = x - (bloco.width >> 1);
		this.bloco.y = y - (bloco.height>> 1);		
	}
	
	public int getLargura() {
		return (int) bloco.getWidth();
	}
	
	public int getAltura() {
		return (int) bloco.getHeight();
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
}
