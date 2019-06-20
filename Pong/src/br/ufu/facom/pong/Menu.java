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
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	private final int LARGURA_TELA;
	private final int ALTURA_TELA;
	
	private JFrame frame;
	
	private Image img;
	private Graphics g;
	
	private String[] titulos = {"Estilo de jogo", "Modo de jogo", "Velocidade", "Tamanho do bloco"}; 
	public String[][] conteudos = 
			{{"Tênis", "Futebol", "Paredão"},
			{"Jogo", "Treino"},
			{"Lento", "Médio", "Rápido", "Crescente"},
			{"Pequeno", "Médio", "Grande"}};
	
	public int[] selecionado = {0, 0, 1, 1}; // Posição do vetor de cada opção que está selecionada
	public int atual = 0; // Opção do menu selecionada para ser modificada
	
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
		addKeyListener(new TecladoMenu(this));
		atualizaMenu();
	}
	
	private void inicializar() {
		frame = new JFrame("Pong");
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
		Font f = new Font("monospace", Font.PLAIN, 256);
		FontRenderContext frc = new FontRenderContext(null,true, true);
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.setColor(Color.white);
		desenhaTitulo(g, f, frc);
		f = new Font("monospace", Font.PLAIN, 64);
		g.setFont(f);
		desenhaCelulas(g, f, frc);
	}
	
	private void desenhaTitulo(Graphics g,Font f, FontRenderContext frc) {
		Rectangle2D r = f.getStringBounds("Pong", frc);
		int x = (int) (getWidth() - r.getWidth()) >> 1;
		int y = 3 * (((int) (getHeight() + r.getHeight())) >> 4);
		g.setFont(f);
		g.drawString("Pong", x, y);
	}
	
	private void desenhaCelulas(Graphics g, Font f, FontRenderContext frc) {
		int espacamento = calculaEspacamento(titulos, f, frc);
		Rectangle2D r;
		int y = 3 * ((int) (getHeight()) >> 2);
		int x, temp, acumulado = espacamento;
		for(int i = 0; i < titulos.length; i++) {
			r = f.getStringBounds(titulos[i], frc);
			x = acumulado;
			Color c = g.getColor();
			if(i == atual)
				g.setColor(Color.blue);
			else
				g.setColor(Color.black);
			g.fillRect(x, y + (int)r.getY(), (int)r.getWidth() + 5, 3 * (int)r.getHeight());
			g.setColor(c);
			g.drawString(titulos[i], x, y);
			g.drawRect(x, y + (int) r.getY(), (int)r.getWidth() + 5, 3 * (int)r.getHeight());
			temp = (int) f.getStringBounds(conteudos[i][selecionado[i]], frc).getWidth();
			x = ((int) r.getWidth() >> 1) - (temp >> 1) + acumulado;
			g.drawString(conteudos[i][selecionado[i]], x, y + 100);
			acumulado += r.getWidth() + espacamento;
		}
	}
	
	public void atualizaMenu() {
		desenhaMenu(g);
		repaint();
	}
	
	private int calculaEspacamento(String[] vetor, Font f, FontRenderContext frc) {
		int espacamento = 0, i;
		for(i = 0; i < vetor.length; i++)
			espacamento += f.getStringBounds(vetor[i], frc).getWidth();
		espacamento = getWidth() - espacamento;
		espacamento = espacamento / (i + 1);
		return espacamento;
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
