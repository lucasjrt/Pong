package br.ufu.facom.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MenuMultiplayer extends Menu {
	private static final long serialVersionUID = 1L;
	public int selecionado = 0; // 0 Botão voltar, 1 Botão para conectar com o ip, 2 Caixa de ip
	private String[] titulos = { "Voltar", "Conectar" };
	private String ipHost = "192.168.0.1"; // caso essa instância for se conectar em outra máquina, este valor deixa
											// de ser null

	DatagramSocket ds;

	public MenuMultiplayer() {
		atualizaMenu();
		addKeyListener(new TecladoMenuMultiplayer(this));
	}

	@Override
	public void submeter() {
		if (selecionado == 1 || selecionado == 2) 
			try {
				ds = new DatagramSocket(1234, InetAddress.getByName(ipHost));
			} catch (UnknownHostException | SocketException e) {
				e.printStackTrace();
			}
		frame.setVisible(false);
		frame.dispose();
	}

	@Override
	protected void desenhaMenu(Graphics g) {
		Font f = new Font("monospace", Font.PLAIN, 256);
		FontRenderContext frc = new FontRenderContext(null, true, true);
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.setColor(Color.white);
		desenhaTitulo(g, f, frc);
		f = new Font("monospace", Font.PLAIN, 90);
		g.setFont(f);
		desenhaPainelIP(g, f, frc);
		desenhaBotoes(g, f, frc);
	}

	private void desenhaTitulo(Graphics g, Font f, FontRenderContext frc) {
		Rectangle2D r = f.getStringBounds("Pong", frc);
		int x = (int) (getWidth() - r.getWidth()) >> 1;
		int y = 3 * (((int) (getHeight() + r.getHeight())) >> 4);
		g.setFont(f);
		g.drawString("Pong", x, y);
	}

	private void desenhaPainelIP(Graphics g, Font f, FontRenderContext frc) {
		Color temp = g.getColor();
		Rectangle2D r = f.getStringBounds("000.000.000.000", frc);
		int x = (getWidth() >> 1) - ((int) r.getWidth() >> 1);
		int y = (frame.getHeight() >> 1) - ((int) r.getHeight() >> 1);
		g.setColor(Color.white);
		g.drawRect(x, y, (int) r.getWidth(), (int) r.getHeight());
		g.drawString(ipHost, x, y + ((int) r.getHeight()) - ((int) r.getHeight() >> 2));
		r = f.getStringBounds("IP: ", frc);
		g.drawString("IP: ", x - (int) r.getWidth(), y + ((int) r.getHeight()) - ((int) r.getHeight() >> 2));
		g.setColor(temp);
	}

	private void desenhaBotoes(Graphics g, Font f, FontRenderContext frc) {
		Color temp = g.getColor();
		Rectangle2D r;
		int espacamento = calculaEspacamento(titulos, f, frc);
		int acumulado = espacamento;
		int x, y = getHeight() - ((int) (getHeight()) >> 2);
		for (int i = 0; i < titulos.length; i++) {
			r = f.getStringBounds(titulos[i], frc);
			x = acumulado;
			if (i == selecionado)
				g.setColor(Color.blue);
			else
				g.setColor(Color.black);
			g.fillRect(x + (int) r.getX(), y + (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
			g.setColor(Color.white);
			g.drawString(titulos[i], x, y);
			g.drawRect(x + (int) r.getX(), y + (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
			acumulado += espacamento + r.getWidth();
		}
		g.setColor(temp);
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LARGURA_TELA, ALTURA_TELA);
		g.drawImage(img, 0, 0, LARGURA_TELA, ALTURA_TELA, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public DatagramSocket getDatagramSocket() {
		return ds;
	}
}
