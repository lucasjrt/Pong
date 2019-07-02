package br.ufu.facom.framework;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class FPong extends JPanel {
	private static final long serialVersionUID = 1L;
	
	protected JFrame frame;
	
	public final int LARGURA_TELA;
	public final int ALTURA_TELA;
	
	public final int VELOCIDADE_JOGO;
	
	public final int TOPO_CAMPO;
	public final int INFERIOR_CAMPO;
	
	public Rectangle tamanhoBloco;
	
	public FPong(int velocidadeJogo) {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		
			
		inicializarFrame();
		LARGURA_TELA = frame.getWidth();
		ALTURA_TELA = frame.getHeight(); 
		
		device.setFullScreenWindow(frame);
		VELOCIDADE_JOGO = velocidadeJogo;
		TOPO_CAMPO = ALTURA_TELA >> 5;
		INFERIOR_CAMPO = ALTURA_TELA - TOPO_CAMPO;
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
	
	protected abstract void iniciar();
	protected abstract void inicializar();
}
