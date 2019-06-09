package br.ufu.facom.framework.objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import br.ufu.facom.framework.FPong;

public abstract class FBola {
	protected Rectangle bloco;			// Area de renderizaca da bola
	protected  int x, y;
	protected int vx, vy;
	protected int velocidade;
	private FPong jogo;
	protected FMediador med;
	
	public FBola(FPong jogo) {
		this.jogo = jogo;
		bloco = new Rectangle(FConstantes.TAMANHO_BOLA, FConstantes.TAMANHO_BOLA);
		bloco.x = x - (FConstantes.TAMANHO_BOLA >> 1);
		bloco.y = y - (FConstantes.TAMANHO_BOLA >> 1);
	}
	
	public void setPosicao(int x, int y) {
		this.x = x;
		this.y = y;
		bloco.x = x - (FConstantes.TAMANHO_BOLA >> 1);
		bloco.y = y - (FConstantes.TAMANHO_BOLA >> 1);
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
	
	public void comecarMover() {
		if (Math.random() > 0.5) {
			vx = velocidade;
		} else {
			vx = -velocidade;
		}
	}
	
	public void mover() {
		setPosicao(getX() + vx, getY() + vy);
		if (getTopo() <= jogo.TOPO_CAMPO || getInferior() >= jogo.INFERIOR_CAMPO)
			vy = -vy;
		
		if (getX() <= 0) {
			med.pontua(1);
			setPosicao(med.getX(1), med.getY(1) - jogo.VELOCIDADE_JOGO);
			vy = (int) (Math.random() * (jogo.VELOCIDADE_JOGO >> 1));
			if(Math.random() > 0.5)
				vy = -vy;
		} else if (getX() >= jogo.LARGURA_TELA) {
			med.pontua(0);
			setPosicao(med.getX(0), med.getY(0) + jogo.VELOCIDADE_JOGO);	
			vy = (int) (Math.random() * (jogo.VELOCIDADE_JOGO >> 1));
			if(Math.random() > 0.5)
				vy = -vy;
		}

		med.mover();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getVx() {
		return vx;
	}
	
	public int getVy() {
		return vy;
	}
	
	public void setVx(int vx) {
		this.vx = vx;
	}
	
	public void setVy(int vy) {
		this.vy = vy;
	}
	
	public int getTopo() {
		return bloco.y;
	}
	
	public int getInferior() {
		return bloco.y + bloco.height;
	}
	
	public int getEsquerda() {
		return bloco.x;
	}
	
	public int getDireita() {
		return bloco.x + bloco.width;
	}
	
	public void setMediador(FMediador med) {
		this.med = med;
	}
}
