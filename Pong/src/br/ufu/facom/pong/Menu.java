package br.ufu.facom.pong;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public abstract class Menu extends Canvas{
	private static final long serialVersionUID = 1L;

	protected final int LARGURA_TELA, ALTURA_TELA;
	
	protected JFrame frame;
	protected BufferedImage img;
	protected Graphics g;
	
	BufferStrategy bs;
	
	public Menu() {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		inicializarFrame();
		device.setFullScreenWindow(frame);
		LARGURA_TELA = frame.getWidth();
		ALTURA_TELA = frame.getHeight();
		inicializar();
		requestFocus();
		if(bs == null) {
			createBufferStrategy(2);
		}
		bs = getBufferStrategy();
		g = bs.getDrawGraphics();
	}
	
	private void inicializar() {
		img = new BufferedImage(LARGURA_TELA, ALTURA_TELA, BufferedImage.TYPE_INT_BGR);
//		g = img.getGraphics();
	}
	
	private void inicializarFrame() {
		frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.add(this);
	}
	
	protected int calculaEspacamento(String[] vetor, Font f, FontRenderContext frc) {
		int espacamento = 0, i;
		for(i = 0; i < vetor.length; i++)
			espacamento += f.getStringBounds(vetor[i], frc).getWidth();
		espacamento = getWidth() - espacamento;
		espacamento = espacamento / (i + 1);
		return espacamento;
	}
	
	public abstract void submeter();
	protected abstract void desenhaMenu(Graphics g);
	
	public void atualizaMenu() {
		if(frame.isEnabled()) {
			desenhaMenu(g);
			bs.show();
		}
	}
}
