package br.ufu.facom.pong.listeners.jogos.tenis.multiplayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.ufu.facom.pong.jogos.tenis.multiplayer.MultiplayerData;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Jogador;

public class TecladoMultiplayerClient implements KeyListener{
	private Jogador[] jogadores;
	private DatagramSocket serverSocket;
	MultiplayerData data;
	InetAddress endereco;
	int portaServidor;
	
	public TecladoMultiplayerClient (Jogador[] jogadores, DatagramSocket serverSocket, InetAddress endereco, int portaServidor) {
		this.jogadores = jogadores;
		this.endereco = endereco;
		this.portaServidor = portaServidor;
		this.serverSocket = serverSocket;
		data = new MultiplayerData();
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			jogadores[1].movendo = jogadores[1].CIMA;
			data.teclaCimaJogador2 = 1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			jogadores[1].movendo = jogadores[1].BAIXO;
			data.teclaBaixoJogador2 = 1;
		}
		enviarDados();
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
			data.teclaCimaSoltadaJogador2 = 1;
			data.teclaCimaJogador2 = 0;
			data.teclaBaixoJogador2 = 0;
			enviarDados();
			return;
		}
	}
	
	public void enviarDados() {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			os.flush();
			os.writeObject(data);
			os.flush();
			byte[] sendBuf = byteStream.toByteArray();
			DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, endereco, portaServidor);
			serverSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void keyTyped(KeyEvent e) {}
}

