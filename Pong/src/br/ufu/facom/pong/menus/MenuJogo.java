package br.ufu.facom.pong.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.net.DatagramSocket;

import br.ufu.facom.framework.utilitarios.FConstantes;
import br.ufu.facom.pong.jogos.futebol.Futebol;
import br.ufu.facom.pong.jogos.paredao.Paredao;
import br.ufu.facom.pong.jogos.tenis.Tenis;
import br.ufu.facom.pong.listeners.menus.TecladoMenu;
import br.ufu.facom.pong.utilitarios.ModoJogo;

public class MenuJogo extends Menu{
	private static final long serialVersionUID = 1L;
	private String[] titulos = {"Estilo de jogo", "Modo de jogo", "Velocidade", "Tamanho do bloco"};
	
	public String[][] conteudos = 
		{{"Tênis", "Futebol", "Paredão"},
		{"Jogo", "Treino", "Multiplayer"},
		{"Lento", "Médio", "Rápido", "Crescente"},
		{"Pequeno", "Médio", "Grande"}};
	
	DatagramSocket ds;

	public int[] selecionado = {0, 0, 1, 1}; // Posição do vetor de cada opção que está selecionada
	public int atual = 0; // Opção do menu selecionada para ser modificada
	private boolean multiplayer = false;
	
	public MenuJogo() {  
		atualizaMenu();
		addKeyListener(new TecladoMenu(this));
	}

	protected void desenhaMenu(Graphics g) {
		Font f = new Font("monospace", Font.PLAIN, 256);
		FontRenderContext frc = new FontRenderContext(null,true, true);
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.setColor(Color.white);
		desenhaTitulo(g, f, frc);
		f = new Font("monospace", Font.PLAIN, 64);
		g.setFont(f);
		desenhaCelulas(g, f, frc);
	}
	
	private void desenhaTitulo(Graphics g,Font f, FontRenderContext frc) {
		Rectangle2D r = f.getStringBounds("Pong", frc);
		int x = (int) (getWidth() - r.getWidth()) >> 1;
		int y = 3 * (((int) (getHeight() + r.getHeight())) >> 4);
		g.setFont(f);
		g.drawString("Pong", x, y);
	}
	
	private void desenhaCelulas(Graphics g, Font f, FontRenderContext frc) {
		int espacamento = calculaEspacamento(titulos, f, frc);
		Rectangle2D r;
		int y = 3 * ((int) (getHeight()) >> 2);
		int x, temp, acumulado = espacamento;
		for(int i = 0; i < titulos.length; i++) {
			r = f.getStringBounds(titulos[i], frc);
			x = acumulado;
			Color c = g.getColor();
			if(i == atual)
				g.setColor(Color.blue);
			else
				g.setColor(Color.black);
			g.fillRect(x, y + (int)r.getY(), (int)r.getWidth() + 5, 3 * (int)r.getHeight());
			g.setColor(c);
			g.drawString(titulos[i], x, y);
			g.drawRect(x, y + (int) r.getY(), (int)r.getWidth() + 5, 3 * (int)r.getHeight());
			temp = (int) f.getStringBounds(conteudos[i][selecionado[i]], frc).getWidth();
			x = ((int) r.getWidth() >> 1) - (temp >> 1) + acumulado;
			g.drawString(conteudos[i][selecionado[i]], x, y + 100);
			acumulado += r.getWidth() + espacamento;
		}
	}
		
	public void submeter() {
		ModoJogo modoJogo;
		int velocidade;
		Rectangle tamanhoBloco;
		frame.setVisible(false);
		switch (selecionado[1]) {
			case 1:
				modoJogo = ModoJogo.TREINO;
				break;
			case 2:
				multiplayer = true;
				modoJogo = ModoJogo.MULTIPLAYER;
//				MenuMultiplayer mm = new MenuMultiplayer(this);
				break;
			default:
				modoJogo = ModoJogo.JOGO;
		}
		
		switch(selecionado[2]) {
			case 0:
				velocidade = FConstantes.BOLA_VELOCIDADE_BAIXA;
				break;
			case 2:
				velocidade = FConstantes.BOLA_VELOCIDADE_ALTA;
				break;
			case 3:
				velocidade = FConstantes.BOLA_VELOCIDADE_CRESCENTE;
				break;
			default:
				velocidade = FConstantes.BOLA_VELOCIDADE_MEDIA;
		}
		
		switch(selecionado[3]) {
			case 0:
				tamanhoBloco = FConstantes.TAMANHO_BLOCO_PEQUENO;
				break;
			case 2: 
				tamanhoBloco = FConstantes.TAMANHO_BLOCO_GRANDE;
				break;
			default:
				tamanhoBloco = FConstantes.TAMANHO_BLOCO_MEDIO;
		}
		
		switch(selecionado[0]) {
			case 0:
				if(!multiplayer) // TODO: inverter condição
					new Tenis(velocidade, modoJogo, tamanhoBloco);
					//new Paredao(velocidade, tamanhoBloco);
					// TODO: Descer esse construtor pro else e adicionar o de multiplayer aqui
				//else
				break;
			case 1:
				if(multiplayer)
					// TODO: Construtor futebol multiplayer
					System.out.println();
				else
					new Futebol(velocidade, modoJogo, tamanhoBloco);
				// Futebol
				break;
			case 2:
				if(!multiplayer) // TODO: Inverter condição
					new Paredao(velocidade, tamanhoBloco);
					// TODO: Descer construtor pro else e adicionar o de multiplayer aqui
				else
				break;
		}
	}
		
	public static void main(String[] args) {
//		new Paredao(FConstantes.BOLA_VELOCIDADE_ALTA, FConstantes.TAMANHO_BLOCO_MEDIO);
//		Imprime o endereço da máquina
//		try {
//			System.out.println(Inet4Address.getLocalHost().getHostAddress()); 
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
		new MenuJogo();
//		new MenuMultiplayer();
	}
}
