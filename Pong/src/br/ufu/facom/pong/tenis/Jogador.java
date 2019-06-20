package br.ufu.facom.pong.tenis;

import java.awt.Rectangle;

import br.ufu.facom.framework.objetos.FJogador;

public class Jogador extends FJogador {
	public Jogador(Tenis jogo, int idJogador, Rectangle tamanho, Mediador med) {
		super(jogo, idJogador, tamanho, med);
		this.pontuacao = new Pontuacao(jogo, idJogador);
	}
}