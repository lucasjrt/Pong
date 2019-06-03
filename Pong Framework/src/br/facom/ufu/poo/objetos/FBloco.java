package br.facom.ufu.poo.objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FBloco {
	private Rectangle bloco;		// O que vai ser renderizado
	private int x, y;				// Centro do bloco
	
	//private Mediador med;
	
	protected FBloco(Rectangle bloco, int x, int y, Mediador med) {
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
		this.bloco.x = x - (bloco.x + ((bloco.width - bloco.x) >> 1));
		this.bloco.y = y - (bloco.y + ((bloco.height - bloco.y) >> 1));		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
