package br.ufu.facom.pong.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.ufu.facom.pong.utilitarios.ModoJogo;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	protected final int LARGURA_TELA;
	protected final int ALTURA_TELA;

	protected JFrame frame;
	private TemplateMenu menuAtual;
	
	public int velocidadeJogo;
	public Rectangle tamanhoBloco;
	public ModoJogo modoJogo;
	public boolean multiplayer;
	
	public Menu() {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//System.out.printf("Tolkit width: %d\nTolkit height: %d\nDevice width: %d\nDevice Height %d\n", device.getDisplayMode().getWidth(),device.getDisplayMode().getHeight(), (int) screenSize.getWidth(), (int) screenSize.getHeight());
		LARGURA_TELA = (int) screenSize.getWidth();
		ALTURA_TELA = (int) screenSize.getHeight();
		inicializarFrame();
		menuAtual = new MenuJogo(this);
		menuAtual.habilitar();
		device.setFullScreenWindow(frame);
		requestFocus();
		repaint();
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
		menuAtual.desenhaMenu();
		repaint();
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		menuAtual.desenhaMenu();
		g.drawImage(menuAtual.getImage(), 0, 0, LARGURA_TELA, ALTURA_TELA, this);
	}

	public void update(Graphics g) {
		paint(g);
	}
	
	public TemplateMenu getMenuAtual() {
		return menuAtual;
	}
	
	public void setMenuAtual(TemplateMenu menu) {
		menuAtual.desabilitar();
		menuAtual = menu;
		menu.habilitar();
	}
	
	public static void main(String[] args) {
		new Menu();
	}
}
