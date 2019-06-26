package br.ufu.facom.pong.futebol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.framework.objetos.FConstantes;

public class Futebol extends FPong implements Runnable {
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private Image img;
	private Graphics g;

	private final int UPDATE_RATE = 100;
	
	public final int DIREITA_CAMPO = LARGURA_TELA - TOPO_CAMPO;
	
	private Teclado teclado;
	
	//Objetos do jogo
	private Jogador jogador;
	private Bola bola;
	private Obstaculos obstaculo1;
	private Obstaculos obstaculo2;
	
	private Mediador med;

	public Futebol(int velocidadeJogo, Rectangle tamanhoBloco) {
		super(velocidadeJogo);
		this.tamanhoBloco = tamanhoBloco;
		//while(frame.getWidth() <= 5 || frame.getHeight() <= 5) {
			inicializar();
			iniciar();
		//}
	}

	@Override
	protected void inicializar() {
		img = createImage(LARGURA_TELA, ALTURA_TELA);
		g = img.getGraphics();
		jogador = new Jogador(this, FConstantes.TAMANHO_BLOCO_MEDIO, med);
		jogador.setCor(Color.green);
		teclado = new Teclado(jogador);
		obstaculo1 = new Obstaculos(this);
		obstaculo2 = new Obstaculos(this);
		bola = new Bola(this, jogador);
		med = new Mediador(this, jogador, bola);
		bola.setMediador(med);
		bola.comecarMover();
		addKeyListener(teclado);
	}

	@Override
	protected void iniciar() {
		thread = new Thread(this, "Paredao");
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			desenhaCampo(g);
			jogador.desenhar(g);
			bola.desenhar(g);
			bola.mover();
			obstaculo1.desenhar(g);
			obstaculo2.desenhar(g);
			obstaculo1.mover();
			obstaculo2.mover();
			jogador.atualizar();
			jogador.desenharPontuacao(g);
			obstaculo1.mover();
			obstaculo2.mover();
			obstaculo1.mover();
			obstaculo2.mover();
			repaint();
			try {
				Thread.sleep(1000 / UPDATE_RATE);
			} catch (InterruptedException ie) {
				System.err.print("Interrompido!\n" + ie);
			}
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	private void desenhaCampo(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.setColor(Color.white);
		g.fillRect(0, 0, LARGURA_TELA, TOPO_CAMPO);
		g.fillRect(0, INFERIOR_CAMPO, LARGURA_TELA, TOPO_CAMPO);
		g.fillRect(DIREITA_CAMPO, 0, TOPO_CAMPO, ALTURA_TELA);
	}
}
