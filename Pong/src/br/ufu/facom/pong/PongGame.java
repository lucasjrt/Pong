package br.ufu.facom.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.framework.objetos.FConstantes;

public class PongGame extends FPong implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private final int UPDATE_RATE = 100;

	// Remover
	private final int ESPACAMENTO_TRACEJADO = 25;
	
	private Thread thread;
	private Image img;
	private Graphics g;
	
	private Teclado teclado;
	
	// Objetos do jogo
	private Mediador med;
	public Jogador[] jogadores;
	private Bola bola;
	
	public Rectangle tamanhoBloco = FConstantes.TAMANHO_BLOCO_MEDIO;
	
	public void inicializar() {
		img = createImage(LARGURA_TELA, ALTURA_TELA);
		g = img.getGraphics();
		jogadores = new Jogador[2];
		jogadores[0] = new Jogador(this, 0, tamanhoBloco, med);
		jogadores[1] = new Jogador(this, 1, tamanhoBloco, med);
		teclado = new Teclado(jogadores);
		this.addKeyListener(teclado);
		bola = new Bola(this);
		med = new Mediador(this, jogadores, bola);
		bola.setMediador(med);
		bola.setVelocidade(Constantes.VELOCIDADE_JOGO);
		bola.comecarMover();
	}

	public void iniciar() {
		thread = new Thread(this, "Game");
		thread.start();
	}

	public void parar() {
		thread.interrupt();
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void run() {
		try {
			while (true) {
				desenhaCampo(g);
				jogadores[0].desenharPontuacao(g);
				jogadores[1].desenharPontuacao(g);
				desenhaJogadores(jogadores, g);
				bola.desenhar(g);
				bola.mover();
				jogadores[0].atualizar();
				jogadores[1].atualizar();
				repaint();
				Thread.sleep((int) (1000 / UPDATE_RATE));
			}
		} catch (InterruptedException ie) {
			System.err.print("Interrompido!\n" + ie);
		}
	}
	
	private void desenhaJogadores(Jogador[] jogadores, Graphics g) {
		for(Jogador j: jogadores) {
			j.desenhar(g);
		}
	}
	
	private void desenhaCampo(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.setColor(Color.white);
		int aux = 0;
		for(int i = 0; i < ALTURA_TELA - 2 * ESPACAMENTO_TRACEJADO; i++) {
			g.drawLine((LARGURA_TELA >> 1) - 1, aux, (LARGURA_TELA >> 1) - 1, aux + ESPACAMENTO_TRACEJADO);
			g.drawLine(LARGURA_TELA >> 1, aux, LARGURA_TELA >> 1, aux + ESPACAMENTO_TRACEJADO);
			g.drawLine((LARGURA_TELA >> 1) + 1, aux, (LARGURA_TELA >> 1) + 1, aux + ESPACAMENTO_TRACEJADO);
			aux += 2 * ESPACAMENTO_TRACEJADO;
		}
	}
}

