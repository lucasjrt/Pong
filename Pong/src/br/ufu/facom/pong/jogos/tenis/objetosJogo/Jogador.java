package br.ufu.facom.pong.jogos.tenis.objetosJogo;

import java.awt.Rectangle;

import br.ufu.facom.framework.objetos.abstratos.FJogador;
import br.ufu.facom.pong.jogos.tenis.Tenis;

public class Jogador extends FJogador {
	public Jogador(Tenis jogo, int idJogador, Rectangle tamanho, Mediador med) {
		super(jogo, idJogador, tamanho, med);
		this.pontuacao = new Pontuacao(jogo, idJogador);
	}
}
