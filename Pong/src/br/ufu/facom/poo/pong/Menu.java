package br.ufu.facom.poo.pong;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	private final int LARGURA_TELA;
	private final int ALTURA_TELA;
	
	private JFrame frame;
	
	private Image img;
	
	public Menu() {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.requestFocus();
		LARGURA_TELA = frame.getWidth();
		ALTURA_TELA = frame.getHeight();
		device.setFullScreenWindow(frame);
	}
	
	private void inicializar() {
		img = createImage(LARGURA_TELA, ALTURA_TELA);		
	}
	
	private void iniciar() {
		
	}
	
	public static void main(String[] args) {
		PongGame p = new PongGame();
		p.inicializar();
		p.iniciar();
		//Menu m = new Menu();
//		m.inicializar();
//		m.iniciar();
	}
}
