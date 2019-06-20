package br.ufu.facom.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	private final int LARGURA_TELA;
	private final int ALTURA_TELA;
	
	private JFrame frame;
	
	private Image img;
	private Graphics g;
	
	public Menu() {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		inicializarFrame();
		device.setFullScreenWindow(frame);
		LARGURA_TELA = frame.getWidth();
		ALTURA_TELA = frame.getHeight();
		inicializar();
		requestFocus();
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(0);
			}
			public void keyPressed(KeyEvent e) {}
		});
		desenhaMenu(g);
	}
	
	private void inicializar() {
		frame = new JFrame("Pong");
		System.out.println(LARGURA_TELA + ", " + ALTURA_TELA);
		img = createImage(LARGURA_TELA, ALTURA_TELA);
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
	
	private void desenhaMenu(Graphics g) {
		Font f = new Font("monospace", Font.PLAIN, 128);
		FontRenderContext frc = new FontRenderContext(null,true, true);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		desenhaTitulo(g, f, frc);
	}
	
	private void desenhaTitulo(Graphics g,Font f, FontRenderContext frc) {
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, this);
	}
		
	public void update(Graphics g) {
		paint(g);		
	}
		
	public static void main(String[] args) {
//		FPong p = new Paredao(FConstantes.BOLA_VELOCIDADE_ALTA, FConstantes.TAMANHO_BLOCO_MEDIO);
//		p.inicializar();
//		p.iniciar();
		new Menu();
	}
}
