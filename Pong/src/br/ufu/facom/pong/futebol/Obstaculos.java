package br.ufu.facom.pong.futebol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import br.ufu.facom.framework.objetos.FConstantes;


public class Obstaculos{
	
	Futebol jogo;
	ArrayList<Bloco> bloco;
	boolean Obstaculo1Subindo;
	boolean Obstaculo2Subindo;
	
	public Obstaculos(Futebol jogo) {
		this.jogo = jogo;
		Obstaculo1Subindo = false;
		Obstaculo2Subindo = true;
		bloco = new ArrayList<Bloco>();
		System.out.println(jogo.ALTURA_TELA/6);
		int meio = (jogo.INFERIOR_CAMPO-jogo.TOPO_CAMPO)/2+jogo.TOPO_CAMPO;
		bloco.add(new Bloco(new Rectangle(FConstantes.TAMANHO_BLOCO_PEQUENO), 3*jogo.LARGURA_TELA/10, (meio-FConstantes.TAMANHO_BLOCO_PEQUENO.height/2-jogo.TOPO_CAMPO)/2+jogo.TOPO_CAMPO));
		bloco.add(new Bloco(new Rectangle(FConstantes.TAMANHO_BLOCO_PEQUENO), 3*jogo.LARGURA_TELA/10, meio));
		bloco.add(new Bloco(new Rectangle(FConstantes.TAMANHO_BLOCO_PEQUENO), 3*jogo.LARGURA_TELA/10,(jogo.INFERIOR_CAMPO+ (meio+FConstantes.TAMANHO_BLOCO_PEQUENO.height/2))/2));
		bloco.add(new Bloco(new Rectangle(FConstantes.TAMANHO_BLOCO_PEQUENO), 7*jogo.LARGURA_TELA/10, (meio-FConstantes.TAMANHO_BLOCO_PEQUENO.height/2-jogo.TOPO_CAMPO)/2+jogo.TOPO_CAMPO));
		bloco.add(new Bloco(new Rectangle(FConstantes.TAMANHO_BLOCO_PEQUENO), 7*jogo.LARGURA_TELA/10, meio));
		bloco.add(new Bloco(new Rectangle(FConstantes.TAMANHO_BLOCO_PEQUENO), 7*jogo.LARGURA_TELA/10,(jogo.INFERIOR_CAMPO+ (meio+FConstantes.TAMANHO_BLOCO_PEQUENO.height/2))/2));
	}
	
	public void desenhar(Graphics g) {
		for(int i=0; i<6; i++) {
			bloco.get(i).desenhar(g);
		}
	}
	
	public void mover() {
		if(Obstaculo1Subindo) {
			for(int i=0; i<3; i++) {
				bloco.get(i).setPosicao(bloco.get(i).getX(),bloco.get(i).getY()-1);
			}
			if(bloco.get(0).getY()-FConstantes.TAMANHO_BLOCO_PEQUENO.height/2 - jogo.TOPO_CAMPO < 1*(jogo.INFERIOR_CAMPO-jogo.TOPO_CAMPO)/10)
				Obstaculo1Subindo = false;
		}
		else {
			for(int i=0; i<3; i++) {
				bloco.get(i).setPosicao(bloco.get(i).getX(),bloco.get(i).getY()+1);
			}
			if(bloco.get(2).getY()+FConstantes.TAMANHO_BLOCO_PEQUENO.height/2 - jogo.TOPO_CAMPO > 9*(jogo.INFERIOR_CAMPO-jogo.TOPO_CAMPO)/10)
				Obstaculo1Subindo = true;
		}
		if(Obstaculo2Subindo) {
			for(int i=3; i<6; i++) {
				bloco.get(i).setPosicao(bloco.get(i).getX(),bloco.get(i).getY()-1);
			}
			if(bloco.get(3).getY()-FConstantes.TAMANHO_BLOCO_PEQUENO.height/2 - jogo.TOPO_CAMPO < 1*(jogo.INFERIOR_CAMPO-jogo.TOPO_CAMPO)/10)
				Obstaculo2Subindo = false;
		}
		else {
			for(int i=3; i<6; i++) {
				bloco.get(i).setPosicao(bloco.get(i).getX(),bloco.get(i).getY()+1);
			}
			if(bloco.get(5).getY() + FConstantes.TAMANHO_BLOCO_PEQUENO.height/2 - jogo.TOPO_CAMPO > 9*(jogo.INFERIOR_CAMPO-jogo.TOPO_CAMPO)/10)
				Obstaculo2Subindo = true;
		}
	}
}
