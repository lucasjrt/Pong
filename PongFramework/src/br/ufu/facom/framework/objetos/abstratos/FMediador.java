package br.ufu.facom.framework.objetos.abstratos;

public abstract class FMediador {
	public FJogador[] jogadores;
	
	public FMediador(FJogador jogador) {
		jogadores = new FJogador[1];
		jogadores[0] = jogador;
	}
	
	public FMediador(FJogador[] jogadores) {
		this.jogadores = jogadores;
	}
	
	public abstract void mover();
	public abstract void pontua(int idJogador);
	
	public int getX(int idJogador) {
		return jogadores[idJogador].getX();
	}
	
	public int getY(int idJogador) {
		return jogadores[idJogador].getY();
	}
	
	public int getX() {
		return jogadores[0].getX();
	}
	
	public int getY() {
		return jogadores[0].getY();
	}
}
