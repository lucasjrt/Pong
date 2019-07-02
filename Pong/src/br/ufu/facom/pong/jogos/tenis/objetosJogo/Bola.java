package br.ufu.facom.pong.jogos.tenis.objetosJogo;

import br.ufu.facom.framework.objetos.FBola;
import br.ufu.facom.pong.jogos.tenis.Tenis;

public class Bola extends FBola {
	public Bola(Tenis jogo) {
		super(jogo);
		setPosicao(jogo.LARGURA_TELA >> 1, jogo.ALTURA_TELA >> 1);
		setVelocidade(jogo.VELOCIDADE_JOGO);
	}
}
