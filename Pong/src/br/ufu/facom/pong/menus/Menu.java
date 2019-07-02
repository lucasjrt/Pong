package br.ufu.facom.pong.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	protected final int LARGURA_TELA;
	protected final int ALTURA_TELA;

	protected JFrame frame;

	private BufferedImage img;
	private Graphics g;

	public int[] selecionado = { 0, 0, 1, 1 }; // Posicao do vetor de cada opcao que esta selecionada
	public int atual = 0; // Opcao do menu selecionada para ser modificada

	public Menu() {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		inicializarFrame();
		device.setFullScreenWindow(frame);
		LARGURA_TELA = frame.getWidth();
		ALTURA_TELA = frame.getHeight();
		inicializar();
		requestFocus();
	}

	private void inicializar() {
		img = new BufferedImage(LARGURA_TELA, ALTURA_TELA, BufferedImage.TYPE_INT_BGR);
		g = img.getGraphics();
	}

	private void inicializarFrame() {
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

	public int calculaEspacamento(String[] vetor, Font f, FontRenderContext frc) {
		int espacamento = 0, i;
		for (i = 0; i < vetor.length; i++)
			espacamento += f.getStringBounds(vetor[i], frc).getWidth();
		espacamento = getWidth() - espacamento;
		espacamento = espacamento / (i + 1);
		return espacamento;
	}

	public void atualizaMenu() {
		desenhaMenu(g);
		repaint();
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public abstract void submeter();

	protected abstract void desenhaMenu(Graphics g);
}
