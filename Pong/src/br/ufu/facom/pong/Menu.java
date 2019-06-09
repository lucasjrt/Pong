package br.ufu.facom.pong;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.framework.objetos.FConstantes;
import br.ufu.facom.pong.paredao.Paredao;

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
		FPong p = new Paredao(FConstantes.BOLA_VELOCIDADE_ALTA, FConstantes.TAMANHO_BLOCO_MEDIO);
		p.inicializar();
		p.iniciar();
		//Menu m = new Menu();
//		m.inicializar();
//		m.iniciar();
	}
}
