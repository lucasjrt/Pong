package br.ufu.facom.pong.jogos.tenis.objetosJogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import br.ufu.facom.framework.FPong;

public class TenisMultiplayerServer extends FPong implements Runnable{

	private static final long serialVersionUID = 1L;

	private final int UPDATE_RATE = 100;
//	private final int PORTA_TCP_CLIENTE = 34129;
	private final int PORTA_TCP_SERVIDOR = 34130;
	private final int PORTA_UDP_CLIENTE = 51582;
	private final int PORTA_UDP_SERVIDOR = 51583;

	private Thread thread;
	private Image img;
	private Graphics g;

	// Objetos do jogo
	private Mediador med;
	public Jogador[] jogadores;
	private Bola bola;
	
	//Dados multiplayer
	byte[] receiveData = new byte[1024];
	private DatagramSocket serverSocket = null;
	MultiplayerData data = null;
	InetAddress endereco;
	ObjectInputStream objetoEntrada = null;
	ByteArrayInputStream streamEntrada = null;

	public TenisMultiplayerServer(int velocidadeJogo, Rectangle tamanhoBloco) {
		super(velocidadeJogo);
		this.tamanhoBloco = tamanhoBloco;
		inicializar();
		iniciar();
	}

	protected void inicializar() {
		img = createImage(LARGURA_TELA, ALTURA_TELA);
		g = img.getGraphics();
		jogadores = new Jogador[2];
		jogadores[0] = new Jogador(this, 0, tamanhoBloco, med);
		jogadores[1] = new Jogador(this, 1, tamanhoBloco, med);
		bola = new Bola(this);
		med = new Mediador(this, jogadores, bola);
		bola.setMediador(med);
		bola.comecarMover();
		data = new MultiplayerData();
		try {
			serverSocket = new DatagramSocket(PORTA_UDP_SERVIDOR);
		} catch (SocketException e2) {
			e2.printStackTrace();
		}
		addKeyListener(new TecladoMultiplayerServer(jogadores, serverSocket));
	}

	protected void iniciar() {
		thread = new Thread(this, "Tenis");
		thread.start();
	}

	public void parar() {
		thread.interrupt();
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	private void aguardarConexao() { 
		ServerSocket serverSocketTCP;
		Socket socket;
		try {
			serverSocketTCP = new ServerSocket(PORTA_TCP_SERVIDOR);
			socket = serverSocketTCP.accept();
			endereco = socket.getInetAddress();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			enviarDados();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		aguardarConexao();
		while (true) {
			desenhaCampo(g);
			jogadores[0].desenharPontuacao(g);
			jogadores[1].desenharPontuacao(g);
			desenhaJogadores(jogadores, g);
			bola.desenhar(g);
			bola.mover();
			jogadores[0].atualizar();
			jogadores[1].atualizar();
			try {
				enviarDados();
			} catch (IOException e) {
				e.printStackTrace();
			}
			repaint();
			try {
				Thread.sleep((int) (1000 / UPDATE_RATE));
			} catch (InterruptedException ie) {
				System.err.print("Interrompido!\n" + ie);
			}
		}
	}
	
	private void enviarDados() throws IOException {
		data.bolaX = bola.getX();
		data.bolaY = bola.getY();
		data.jogador1X = jogadores[0].getX();
		data.jogador1Y = jogadores[0].getY();
		data.jogador2X = jogadores[1].getX();
		data.jogador2Y = jogadores[1].getY();
		data.pontuacaoJogador1 = jogadores[0].getPontuacao();
		data.pontuacaoJogador2 = jogadores[1].getPontuacao();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);
		ObjectOutputStream streamSaida = new ObjectOutputStream(new BufferedOutputStream(byteStream));
		streamSaida.flush();
		streamSaida.writeObject(data);
		streamSaida.flush();
		byte[] sendBuf = byteStream.toByteArray();
		DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, endereco, PORTA_UDP_CLIENTE);
		serverSocket.send(packet);
	}
	
	private void desenhaJogadores(Jogador[] jogadores, Graphics g) {
		for (Jogador j : jogadores) {
			j.desenhar(g);
		}
	}

	private void desenhaCampo(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.setColor(Color.white);
		g.fillRect(0, 0, LARGURA_TELA, TOPO_CAMPO);
		g.fillRect(0, INFERIOR_CAMPO, LARGURA_TELA, TOPO_CAMPO);
		g.fillRect((LARGURA_TELA >> 1) - (LARGURA_TELA >> 7), 0, LARGURA_TELA >> 7, ALTURA_TELA);
	}
}
