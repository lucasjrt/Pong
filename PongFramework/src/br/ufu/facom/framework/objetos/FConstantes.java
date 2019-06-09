package br.ufu.facom.framework.objetos;

import java.awt.Rectangle;

public class FConstantes {
	
	//Constantes bola
	public static final int BOLA_VELOCIDADE_BAIXA = 7;
	public static final int BOLA_VELOCIDADE_MEDIA = 14;
	public static final int BOLA_VELOCIDADE_ALTA = 28;
	public static final int BOLA_VELOCIDADE_CRESCENTE = 0;
	
	public static final int TAMANHO_BOLA = 20;
	
	//Constantes display
	public static final int DISPLAY_LARGURA_NUMERO = 150;
	public static final int DISPLAY_ALTURA_NUMERO = 210;
	public static final int DISPLAY_EXPESSURA = 15;
	public static final int ESPACAMENTO_NUMERO = 20;

	//Constantes bloco
	public static final Rectangle TAMANHO_BLOCO_PEQUENO = new Rectangle(20, 50);
	public static final Rectangle TAMANHO_BLOCO_MEDIO = new Rectangle(20, 100);
	public static final Rectangle TAMANHO_BLOCO_GRANDE = new Rectangle(20, 150);
}
