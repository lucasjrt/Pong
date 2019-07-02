package br.ufu.facom.pong.paredao;

import java.awt.Graphics;
import java.awt.Rectangle;

import br.ufu.facom.framework.objetos.FJogador;

public class Jogador extends FJogador {
	Pontuacao pontuacao2;
	public Jogador(Paredao jogo, Rectangle tamanho, Mediador med) {
		super(jogo, 0, tamanho, med);
		this.pontuacao = new Pontuacao(jogo, 0);
		this.pontuacao2 = new Pontuacao(jogo, 1);
	}
	
	public void setPontuacao(int pontos, int idJogador) {
		if(idJogador == 0)
			pontuacao.setPontos(pontos);
		else
			pontuacao2.setPontos(pontos);
	}
	
	public void setIdJogador(int idJogador) {
		this.idJogador = idJogador;
	}
	
	public int getPontuacao() {
		if (idJogador == 0)
			return super.getPontuacao();
		else
			return pontuacao2.getPontos();
	}
	
	public void desenharPontuacao(Graphics g) {
		pontuacao.desenhar(g);
		pontuacao2.desenhar(g);
	}
	
}
