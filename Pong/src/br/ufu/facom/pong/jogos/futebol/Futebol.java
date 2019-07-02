package br.ufu.facom.pong.jogos.futebol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.pong.jogos.futebol.objetosJogo.Bola;
import br.ufu.facom.pong.jogos.futebol.objetosJogo.Jogador;
import br.ufu.facom.pong.jogos.futebol.objetosJogo.Mediador;
import br.ufu.facom.pong.jogos.futebol.objetosJogo.Obstaculos;
import br.ufu.facom.pong.listeners.jogos.futebol.TecladoJogo;
import br.ufu.facom.pong.listeners.jogos.futebol.TecladoTreino;
import br.ufu.facom.pong.utilitarios.ModoJogo;

public class Futebol extends FPong implements Runnable {

	private static final long serialVersionUID = 1L;

	private final int UPDATE_RATE = 100;

	private Thread thread;
	private Image img;
	private Graphics g;

	// Objetos do jogo
	private Mediador med;
	public Jogador[] jogadores;
	private Bola bola;
	private Obstaculos obstaculo;

	private ModoJogo modoJogo;

	public Futebol(int velocidadeJogo, ModoJogo modoJogo, Rectangle tamanhoBloco) {
		super(velocidadeJogo);
		this.tamanhoBloco = tamanhoBloco;
		this.modoJogo = modoJogo;
		inicializar();
		iniciar();
	}

	protected void inicializar() {
		img = createImage(LARGURA_TELA, ALTURA_TELA);
		g = img.getGraphics();
		jogadores = new Jogador[2];
		jogadores[0] = new Jogador(this, 0, tamanhoBloco, med);
		jogadores[1] = new Jogador(this, 1, tamanhoBloco, med);
		bola = new Bola(this);
		obstaculo = new Obstaculos(this);
		med = new Mediador(this, jogadores, bola, this.obstaculo);
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
			obstaculo.desenhar(g);
			obstaculo.mover();
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
	}
}
