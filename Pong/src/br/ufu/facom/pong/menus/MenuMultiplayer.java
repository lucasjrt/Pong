package br.ufu.facom.pong.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import br.ufu.facom.pong.jogos.tenis.multiplayer.TenisMultiplayerServer;
import br.ufu.facom.pong.listeners.menus.TecladoMenuMultiplayer;

public class MenuMultiplayer extends TemplateMenu {
	public int selecionado = 0; // 0 Jogador 1, 1 Jogador 2
	private String[] titulos = { "Jogador 1", "Jogador 2" };
	private KeyListener teclado;

	public MenuMultiplayer(Menu menu) {
		super(menu);
		teclado = new TecladoMenuMultiplayer(this);
	}

	public void submeter() {
		if(selecionado == 0) {
			menu.frame.setVisible(false);
			menu.frame.setEnabled(false);
			new TenisMultiplayerServer(menu.velocidadeJogo, menu.tamanhoBloco);
		}
		else
			menu.setMenuAtual(new MenuCliente(menu));
	}
	
	@Override
	public void desenhaMenu() {
		Font f = new Font("monospace", Font.PLAIN, 256);
		FontRenderContext frc = new FontRenderContext(null, true, true);
		g.setColor(Color.black);
		g.fillRect(0, 0, menu.LARGURA_TELA, menu.ALTURA_TELA);
		g.setColor(Color.white);
		desenhaTitulo(g, f, frc);
		f = new Font("monospace", Font.PLAIN, 90);
		g.setFont(f);
		desenhaBotoes(g, f, frc);
	}

	private void desenhaBotoes(Graphics g, Font f, FontRenderContext frc) {
		Color temp = g.getColor();
		Rectangle2D r;
		int espacamento = menu.calculaEspacamento(titulos, f, frc);
		int acumulado = espacamento;
		int x, y = menu.ALTURA_TELA - ((int) (menu.ALTURA_TELA) >> 2);
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

	@Override
	public void habilitar() {
		menu.addKeyListener(teclado);
	}

	@Override
	public void desabilitar() {
		menu.removeKeyListener(teclado);
	}
}
