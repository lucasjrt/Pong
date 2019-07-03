package br.ufu.facom.pong.jogos.tenis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Bola;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Jogador;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Mediador;
import br.ufu.facom.pong.listeners.jogos.tenis.singleplayer.TecladoJogo;
import br.ufu.facom.pong.listeners.jogos.tenis.singleplayer.TecladoTreino;
import br.ufu.facom.pong.utilitarios.ModoJogo;

public class Tenis extends FPong implements Runnable {

	private static final long serialVersionUID = 1L;

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
		jogadores = new Jogador[2];
		jogadores[0] = new Jogador(this, 0, tamanhoBloco, med);
		jogadores[1] = new Jogador(this, 1, tamanhoBloco, med);
		bola = new Bola(this);
		med = new Mediador(this, jogadores, bola);
		bola.setMediador(med);
		bola.comecarMover();
		if (modoJogo == ModoJogo.JOGO)
			addKeyListener(new TecladoJogo(jogadores));
		if(modoJogo == ModoJogo.TREINO)
			addKeyListener(new TecladoTreino(jogadores));
	}

	protected void iniciar() {
		thread = new Thread(this, "Tenis");
		thread.start();
	}

	public void parar() {
		thread.interrupt();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				atualizar();
				delta--;
			}
			renderizar();
		}
	}
	
	private void atualizar() {
		bola.mover();
		jogadores[0].atualizar();
		jogadores[1].atualizar();

	}
	
	private void renderizar() {
		g = img.getGraphics();
		desenhaCampo(g);
		jogadores[0].desenharPontuacao(g);
		jogadores[1].desenharPontuacao(g);
		desenhaJogadores(jogadores, g);
		bola.desenhar(g);
		g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, null);
		bs.show();
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
