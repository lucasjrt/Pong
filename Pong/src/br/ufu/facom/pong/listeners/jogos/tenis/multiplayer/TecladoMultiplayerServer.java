package br.ufu.facom.pong.listeners.jogos.tenis.multiplayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import br.ufu.facom.pong.jogos.tenis.multiplayer.MultiplayerData;
import br.ufu.facom.pong.jogos.tenis.objetosJogo.Jogador;


public class TecladoMultiplayerServer implements Runnable, KeyListener{
	private Jogador[] jogadores;
	private DatagramSocket serverSocket;
	Thread thread;
	
	public TecladoMultiplayerServer (Jogador[] jogadores, DatagramSocket serverSocket) {
		this.jogadores = jogadores;
		this.serverSocket = serverSocket;
		thread = new Thread(this, "Tenis");
		thread.start();
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			jogadores[0].movendo = jogadores[0].CIMA;
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			jogadores[0].movendo = jogadores[0].BAIXO;
			return;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			jogadores[0].movendo = -1;
			return;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			jogadores[0].movendo = -1;
			return;
		}
			
	}

	public void keyTyped(KeyEvent e) {}

	@Override
	public void run() {
        byte[] receiveData = new byte[1024];
        MultiplayerData data = null;
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		ObjectInputStream objetoEntrada;
		ByteArrayInputStream streamEntrada;
		while(true) {
			try {
				serverSocket.receive(receivePacket);
				streamEntrada = new ByteArrayInputStream(receiveData);
				objetoEntrada = new ObjectInputStream(new BufferedInputStream(streamEntrada));
				data = (MultiplayerData) objetoEntrada.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			if(data.teclaCimaJogador2 == 1)
				jogadores[1].movendo = jogadores[1].CIMA;
			else if(data.teclaBaixoJogador2 == 1)
				jogadores[1].movendo = jogadores[1].BAIXO;
			else if(data.teclaCimaSoltadaJogador2 == 1 || data.teclaBaixoSoltadaJogador2 == 1)
				jogadores[1].movendo = -1;
		}
	}
}
