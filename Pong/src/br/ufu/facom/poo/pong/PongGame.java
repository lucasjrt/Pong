package br.ufu.facom.poo.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import br.facom.ufu.poo.FPong;

public class PongGame extends FPong implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private final int UPDATE_RATE = 60;

	// Remover
	private final int ESPACAMENTO_TRACEJADO = 25;
	
	private Thread thread;
	private Image img;
	private Graphics g;
	
	
	// Objetos do jogo
	private Mediador med;
	public Jogador[] jogadores;
	private Bola bola;
	
	//Configuracoes do jogo
	public int tamanhoBloco;
	
	public void inicializar() {
		img = createImage(LARGURA_TELA, ALTURA_TELA);
		g = img.getGraphics();
		tamanhoBloco = BLOCO_MEDIO.height;
		jogadores = new Jogador[2];
		jogadores[0] = new Jogador(this, 0, BLOCO_MEDIO, med);
		jogadores[1] = new Jogador(this, 1, BLOCO_MEDIO, med);
		bola = new Bola(this);
		med = new Mediador(this, jogadores, bola);
		bola.setMediador(med);
		bola.setVelocidade(bola.VELOCIDADE_ALTA);
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
				desenhaJogadores(jogadores, g);
				bola.desenhar(g);
				bola.mover();
				jogadores[0].desenharPontuacao(g);
				jogadores[1].desenharPontuacao(g);
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

