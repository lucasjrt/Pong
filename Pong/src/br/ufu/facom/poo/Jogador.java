package br.ufu.facom.poo;

import java.awt.Rectangle;

import br.facom.ufu.poo.FPong;
import br.facom.ufu.poo.objetos.FJogador;
import br.facom.ufu.poo.objetos.Mediador;

public class Jogador extends FJogador{

	public Jogador(FPong jogo, int idJogador, Rectangle tamanho, Mediador med) {
		super(jogo, idJogador, tamanho, med);
		
	}
	
	public void controle() {
		if(idJogador == 0) {
			
		}
	}
}
