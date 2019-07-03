package br.ufu.facom.pong.jogos.tenis.multiplayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MultiplayerData implements Serializable{

	private static final long serialVersionUID = 1L;

	//Parte interessante para jogador 1
	public int teclaCimaJogador2;
	public int teclaCimaSoltadaJogador2;
	public int teclaBaixoJogador2;
	public int teclaBaixoSoltadaJogador2;
	
	//Parte ineressante para o jogador 2
	public int bolaX, bolaY;
	public int pontuacaoJogador1;
	public int pontuacaoJogador2;
	
	public int jogador1X;
	public int jogador1Y;
	public int jogador2X;
	public int jogador2Y;
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
	}
}

