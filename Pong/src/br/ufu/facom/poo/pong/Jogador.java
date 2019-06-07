package br.ufu.facom.poo.pong;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.facom.ufu.poo.objetos.FJogador;

public class Jogador extends FJogador{
	private int velocidadeMovimento = 15;
	private PongGame jogo;
	
	private final int CIMA = 0;
	private final int BAIXO = 1;
	
	public int movendoP1 = -1;
	public int movendoP2 = -1;
	
	private Pontuacao pontuacao;
	
	public Jogador(PongGame jogo, int idJogador, Rectangle tamanho, Mediador med) {
		super(jogo, idJogador, tamanho, med);
		this.jogo = jogo;
		this.pontuacao = new Pontuacao(jogo, this);
		adicionarControles();
		
	}
	
	private void adicionarControles() {

		if(idJogador == 0) {
			jogo.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					movendoP1 = -1;
				}
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_W)
						movendoP1 = CIMA;
					else if(e.getKeyCode() == KeyEvent.VK_S)
						movendoP1 = BAIXO;
				}
			});
		} else if (idJogador == 1) {
			jogo.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
					movendoP2 = -1;
				}
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_UP)
						movendoP2 = CIMA;
					else if(e.getKeyCode() == KeyEvent.VK_DOWN)
						movendoP2 = BAIXO;
				}
			});
		}
	}
	
	public void mover(int direcao) {
		if(direcao == CIMA) {
			if(getY() - (jogo.tamanhoBloco >> 1) >= 0) {
				if(getY() - (jogo.tamanhoBloco >> 1) - velocidadeMovimento < 0) {
					bloco.setPosicao(getX(), (jogo.tamanhoBloco >> 1));
				}
				else {
					bloco.setPosicao(getX(), getY() - velocidadeMovimento);
				}
			}
		} else if(direcao == BAIXO) {
			if(getY() < jogo.ALTURA_TELA - (jogo.tamanhoBloco >> 1)) {
				if(getY() + (jogo.tamanhoBloco >> 1) + velocidadeMovimento > jogo.ALTURA_TELA) {
					bloco.setPosicao(getX(), jogo.ALTURA_TELA - (jogo.tamanhoBloco >> 1));
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
		if(idJogador == 0) {
			if(movendoP1 == CIMA)
				mover(CIMA);
			else if(movendoP1 == BAIXO)
				mover(BAIXO);
		} else {
			if(movendoP2 == CIMA)
				mover(CIMA);
			else if(movendoP2 == BAIXO)
				mover(BAIXO);
		}
	}
	
	public void setPontuacao(int pontos) {
		pontuacao.setPontos(pontos);
	}
	
	public int getPontuacao() {
		return pontuacao.getPontos();
	}
}
 