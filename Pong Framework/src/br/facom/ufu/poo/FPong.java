package br.facom.ufu.poo;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class FPong extends JPanel {
	private static final long serialVersionUID = 1L;

	// Constantes
	public static final Rectangle BLOCO_PEQUENO = new Rectangle(20, 50);
	public static final Rectangle BLOCO_MEDIO = new Rectangle(20, 100);
	public static final Rectangle BLOCO_GRANDE = new Rectangle(20, 150);

	public final int LARGURA_TELA;
	public final int ALTURA_TELA;
	
	private JFrame frame;
	
	public FPong() {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		inicializarFrame();
		device.setFullScreenWindow(frame);
		LARGURA_TELA = frame.getWidth();
		ALTURA_TELA = frame.getHeight();
		setSize(LARGURA_TELA, ALTURA_TELA);
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
}
