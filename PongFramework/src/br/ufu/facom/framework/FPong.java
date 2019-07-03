package br.ufu.facom.framework;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public abstract class FPong extends Canvas {
	private static final long serialVersionUID = 1L;
	
	protected Thread thread;
	protected BufferedImage img;
	protected BufferStrategy bs;
	protected Graphics g;
	
	protected JFrame frame;
	
	public final int LARGURA_TELA;
	public final int ALTURA_TELA;
	
	public int VELOCIDADE_JOGO;
	
	public final int TOPO_CAMPO;
	public final int INFERIOR_CAMPO;
	
	public Rectangle tamanhoBloco;
	
	public FPong(int velocidadeJogo) {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		inicializarFrame();
		device.setFullScreenWindow(frame);
		LARGURA_TELA = frame.getWidth();
		ALTURA_TELA = frame.getHeight();
		VELOCIDADE_JOGO = velocidadeJogo;
		TOPO_CAMPO = ALTURA_TELA >> 5;
		INFERIOR_CAMPO = ALTURA_TELA - TOPO_CAMPO;
		setSize(LARGURA_TELA, ALTURA_TELA);
		preInicializar();
		requestFocus();
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
					System.exit(0);
				}
			}
			public void keyPressed(KeyEvent e) {}
		});
	}
	
	private synchronized void inicializarFrame() {
		frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(LARGURA_TELA, ALTURA_TELA);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void preInicializar() {
		img = new BufferedImage(LARGURA_TELA, ALTURA_TELA, BufferedImage.TYPE_INT_RGB);
		if(bs == null)
			createBufferStrategy(2);
		bs = getBufferStrategy();
		g = img.getGraphics();
	}
	
	protected abstract void iniciar();
	protected abstract void inicializar();
}
