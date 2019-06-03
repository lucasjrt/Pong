package br.ufu.facom.poo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import br.facom.ufu.poo.FPong;

public class PongGame extends FPong implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private final int FPS = 40;
	
	private final int ESPACAMENTO_TRACEJADO = 25;
	
	private Thread thread;
	private Image img;
	private Graphics g;

	
	public void inicializar() {
		img = createImage(LARGURA_TELA, ALTURA_TELA);
		g = img.getGraphics();
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
				repaint();
				Thread.sleep((int) (1000 / FPS));
				// bloco.move(bloco.getX() + 1, bloco.getY() + 1);
			}
		} catch (InterruptedException ie) {
			System.err.print("Interrompido!\n" + ie);
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
