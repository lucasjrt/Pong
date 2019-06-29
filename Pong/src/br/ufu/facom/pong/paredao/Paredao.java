package br.ufu.facom.pong.paredao;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.framework.objetos.FConstantes;

public class Paredao extends FPong implements Runnable {
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private BufferedImage img;
	private Graphics g;

	public final int DIREITA_CAMPO = LARGURA_TELA - TOPO_CAMPO;
	
	//Objetos do jogo
	private Jogador jogador;
	private Bola bola;
	
	private Mediador med;

	public Paredao(int velocidadeJogo, Rectangle tamanhoBloco) {
		super(velocidadeJogo);
		this.tamanhoBloco = tamanhoBloco;
		inicializar();
		iniciar();
	}

	@Override
	protected void inicializar() {
		img = new BufferedImage(LARGURA_TELA, ALTURA_TELA, BufferedImage.TYPE_INT_RGB);
		g = img.getGraphics();
		jogador = new Jogador(this, FConstantes.TAMANHO_BLOCO_MEDIO, med);
		jogador.setCor(Color.green);
		bola = new Bola(this, jogador);
		med = new Mediador(this, jogador, bola);
		bola.setMediador(med);
		bola.comecarMover();
		addKeyListener(new Teclado(jogador));
	}

	@Override
	protected void iniciar() {
		thread = new Thread(this, "Paredao");
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
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
	}
	
	private void atualizar() {
		bola.mover();
		jogador.atualizar();
	}
	
	private void renderizar() {
		g = img.getGraphics();
		desenhaCampo(g);
		jogador.desenhar(g);
		bola.desenhar(g);
		jogador.desenharPontuacao(g);
		g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, null);
		bs.show();
	}

	private void desenhaCampo(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.setColor(Color.white);
		g.fillRect(0, 0, LARGURA_TELA, TOPO_CAMPO);
		g.fillRect(0, INFERIOR_CAMPO, LARGURA_TELA, TOPO_CAMPO);
		g.fillRect(DIREITA_CAMPO, 0, TOPO_CAMPO, ALTURA_TELA);
	}
}
