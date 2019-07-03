package br.ufu.facom.pong.jogos.tenis.multiplayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
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
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Bola;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Jogador;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Mediador;
import br.ufu.facom.pong.listeners.jogos.tenis.multiplayer.TecladoMultiplayerServer;

public class TenisMultiplayerServer extends FPong implements Runnable{
	private static final long serialVersionUID = 1L;

	private final int PORTA_TCP_SERVIDOR = 34130;
	private final int PORTA_UDP_CLIENTE = 51582;
	private final int PORTA_UDP_SERVIDOR = 51583;
	
	private boolean conexaoEstabelecida = false;

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
		System.out.println("Aguardando conexão:");
		Thread t = new Thread(new Runnable() {
			public void run() {
				while(!conexaoEstabelecida) {
					desenhaMensagemEspera();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		aguardarConexao();
		conexaoEstabelecida = true;
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				atualizar();
				delta--;
			}
			renderizar();
		}
	}
	
	private void atualizar() {
		bola.mover();
		jogadores[0].atualizar();
		jogadores[1].atualizar();
		try {
			enviarDados();
		} catch (IOException e) {
			e.printStackTrace();
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
	
	private void desenhaMensagemEspera() {
		Font f = new Font("monospace", Font.PLAIN, 90);;
		FontRenderContext frc = new FontRenderContext(null, true, true);
		String mensagem = "Aguardando jogador 2";
		Color temp = g.getColor();
		Rectangle2D r = f.getStringBounds(mensagem, frc);
		int x = (LARGURA_TELA >> 1) - ((int) r.getWidth() >> 1);
		int y = (ALTURA_TELA >> 1);
		g.setFont(f);
		g.drawString(mensagem, x, y + (ALTURA_TELA >> 3));
		g.setColor(temp);
		g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, null);
		bs.show();
	}
}
