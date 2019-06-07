package br.facom.ufu.poo.objetos;

import java.awt.Graphics;
import java.util.ArrayList;

import br.facom.ufu.poo.FPong;

public abstract class FPontuacao {
	private int idJogador;
	private int pontos;

	private final int ESPACAMENTO_NUMERO = 20;
	private int x, y; // Canto superior esquerdo do numero mais a direita

	private ArrayList<FDisplay> numeros;

	public FPontuacao(FPong jogo, FJogador jogador) {
		this.idJogador = jogador.idJogador;
		this.x = (jogo.LARGURA_TELA >> 1) + (idJogador == 0 ? -(jogo.LARGURA_TELA >> 3) : jogo.LARGURA_TELA >> 3);
		this.y = jogo.ALTURA_TELA >> 3;
		this.numeros = new ArrayList<FDisplay>();
		numeros.add(new FDisplay(pontos, x, y));
		if(idJogador == 1) {
			this.x -= numeros.get(0).LARGURA_NUMERO;
			numeros.get(0).setPosicao(x, y);
		}
	}

	public void desenhar(Graphics g) {
		for(FDisplay d : numeros) {
			d.desenhar(g);
		}
	}

	private int contaDigitos(int n) {
		if (n == 0)
			return 1;
		n = Math.abs(n);
		int contagem = 0;
		while (n > 0) {
			n /= 10;
			contagem++;
		}
		return contagem;
	}

	public void setPontos(int pontos) {
		if (idJogador == 1)
			this.x += (contaDigitos(pontos) - contaDigitos(this.pontos))
					* (numeros.get(0).LARGURA_NUMERO + ESPACAMENTO_NUMERO);
		this.pontos = pontos;
		atualizaNumeros();
	}
	
	public int getPontos() {
		return pontos;
	}

	private void atualizaNumeros() {
		int n = contaDigitos(pontos);
		int temp = pontos;
		while (numeros.size() < n)
			numeros.add(new FDisplay(0));
		while (numeros.size() > n) 
			numeros.remove(numeros.size());
		for (int i = 0; i < n; i++) {
			numeros.get(i).setValor(temp % 10);
			temp /= 10;
			numeros.get(i).setPosicao(x - (numeros.get(i).LARGURA_NUMERO + ESPACAMENTO_NUMERO) * i, y);
		}
	}
}