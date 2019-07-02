package br.ufu.facom.pong.jogos.tenis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Bola;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Jogador;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Mediador;
import br.ufu.facom.pong.listeners.jogos.tenis.TecladoJogo;
import br.ufu.facom.pong.listeners.jogos.tenis.TecladoTreino;
import br.ufu.facom.pong.utilitarios.ModoJogo;

public class Tenis extends FPong implements Runnable {

	private static final long serialVersionUID = 1L;

	private final int UPDATE_RATE = 100;

	private Thread thread;
	private BufferedImage img;
	private BufferStrategy bs;
	private Graphics g;

	// Objetos do jogo
	private Mediador med;
	public Jogador[] jogadores;
	private Bola bola;

	private ModoJogo modoJogo;

	public Tenis(int velocidadeJogo, ModoJogo modoJogo, Rectangle tamanhoBloco) {
		super(velocidadeJogo);
		this.tamanhoBloco = tamanhoBloco;
		this.modoJogo = modoJogo;
		inicializar();
		iniciar();
	}

	protected void inicializar() {
		img = new BufferedImage(LARGURA_TELA, ALTURA_TELA, BufferedImage.TYPE_INT_RGB);
		if(bs == null)
			bs = create
		g = img.getGraphics();
		jogadores = new Jogador[2];
		jogadores[0] = new Jogador(this, 0, tamanhoBloco, med);
		jogadores[1] = new Jogador(this, 1, tamanhoBloco, med);
		bola = new Bola(this);
		med = new Mediador(this, jogadores, bola);
		bola.setMediador(med);
		bola.comecarMover();
		if (modoJogo == ModoJogo.JOGO)
			addKeyListener(new TecladoJogo(jogadores));
		else
			addKeyListener(new TecladoTreino(jogadores));
	}

	protected void iniciar() {
		thread = new Thread(this, "Tenis");
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
			try {
				Thread.sleep((int) (1000 / UPDATE_RATE));
			} catch (InterruptedException ie) {
				System.err.print("Interrompido!\n" + ie);
			}
		}
	}

	private void desenhaJogadores(Jogador[] jogadores, Graphics g) {
		for (Jogador j : jogadores) {
			j.desenhar(g);
		}
	}

	private void desenhaCampo(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.setColor(Color.white);
		g.fillRect(0, 0, LARGURA_TELA, TOPO_CAMPO);
		g.fillRect(0, INFERIOR_CAMPO, LARGURA_TELA, TOPO_CAMPO);
		g.fillRect((LARGURA_TELA >> 1) - (LARGURA_TELA >> 7), 0, LARGURA_TELA >> 7, ALTURA_TELA);
	}
}
