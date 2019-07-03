package br.ufu.facom.pong.jogos.tenis.multiplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import br.ufu.facom.framework.FPong;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Bola;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Jogador;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Mediador;
import br.ufu.facom.pong.listeners.jogos.tenis.multiplayer.TecladoMultiplayerClient;

public class TenisMultiplayerClient extends FPong implements Runnable {

	private static final long serialVersionUID = 1L;
	private final int PORTA_TCP_SERVIDOR = 34130;
	private final int PORTA_UDP_CLIENTE = 51582;
	private final int PORTA_UDP_SERVIDOR = 51583;

	// Objetos do jogo
	private Mediador med;
	public Jogador[] jogadores;
	private Bola bola;
	
	//Dados multiplayer
	private DatagramSocket serverSocket = null;
	private MultiplayerData data;
	InetAddress endereco;
	byte[] receiveData;
	DatagramPacket receivePacket;
	ByteArrayOutputStream byteStream;
	ObjectOutputStream os;
	ObjectInputStream objetoEntrada = null;
	ByteArrayInputStream streamEntrada = null;


	public TenisMultiplayerClient(int velocidadeJogo, Rectangle tamanhoBloco, String endereco) {
		super(velocidadeJogo);
		try {
			this.endereco = InetAddress.getByName(endereco);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.tamanhoBloco = tamanhoBloco;
		receiveData = new byte[1024];
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		byteStream = new ByteArrayOutputStream(1024);
		try {
			os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			serverSocket = new DatagramSocket(PORTA_UDP_CLIENTE);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		inicializar();
		iniciar();
	}

	protected void inicializar() {
		try {
			estabelecerConexao();
		} catch (IOException e) {
			e.printStackTrace();
		}
		jogadores = new Jogador[2];
		jogadores[0] = new Jogador(this, 0, tamanhoBloco, med);
		jogadores[1] = new Jogador(this, 1, tamanhoBloco, med);
		bola = new Bola(this);
		med = new Mediador(this, jogadores, bola);
		bola.setMediador(med);
		bola.comecarMover();
		addKeyListener(new TecladoMultiplayerClient(jogadores, serverSocket, endereco, PORTA_UDP_SERVIDOR));
	}
	
	protected void estabelecerConexao() throws IOException {
		Socket socketTCP = new Socket(endereco.getHostAddress(),PORTA_TCP_SERVIDOR);
	    socketTCP.close();
	}

	protected void iniciar() {
		thread = new Thread(this, "Tenis");
		thread.start();
		new Thread(
	            new Runnable() {
	                public void run() {
	                    runMovimenta();
	                }
	            }
	        ).start();
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

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1)
				delta--;
			renderizar();
		}
	}
	
	private void renderizar() {
		g = img.getGraphics();
		desenhaCampo(g);
		jogadores[0].desenharPontuacao(g);
		jogadores[1].desenharPontuacao(g);
		desenhaJogadores(jogadores, g);
		bola.desenhar(g);
		g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, null);
		bs.show();
	}
	
	public void runMovimenta() {
		while(true) {
			try {
				serverSocket.receive(receivePacket);
				streamEntrada = new ByteArrayInputStream(receiveData);
				objetoEntrada = new ObjectInputStream(new BufferedInputStream(streamEntrada));
				data = (MultiplayerData) objetoEntrada.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			bola.setPosicao(data.bolaX, data.bolaY);
			jogadores[0].setPosicao(data.jogador1X, data.jogador1Y);
			jogadores[1].setPosicao(data.jogador2X, data.jogador2Y);
			jogadores[0].setPontuacao(data.pontuacaoJogador1);
			jogadores[1].setPontuacao(data.pontuacaoJogador2);
		}
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
