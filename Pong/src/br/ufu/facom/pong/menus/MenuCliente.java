package br.ufu.facom.pong.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import br.ufu.facom.pong.jogos.tenis.multiplayer.TenisMultiplayerClient;
import br.ufu.facom.pong.listeners.menus.TecladoCaixaIP;

public class MenuCliente extends TemplateMenu{
	private KeyListener teclado;
	
	public String ip = "127.0.0.1"; 
	
	public MenuCliente(Menu menu) {
		super(menu);
		teclado = new TecladoCaixaIP(this);
	}
	
	public void submeter() {
		menu.frame.setVisible(false);
		menu.frame.setEnabled(false);
		new TenisMultiplayerClient(menu.velocidadeJogo, menu.tamanhoBloco, ip);
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
		desenhaPainelIP(g, f, frc);
	}
	
	private void desenhaPainelIP(Graphics g, Font f, FontRenderContext frc) {
	Color temp = g.getColor();
	Rectangle2D r = f.getStringBounds("000.000.000.000", frc);
	int x = (menu.LARGURA_TELA >> 1) - ((int) r.getWidth() >> 1);
	int y = (menu.ALTURA_TELA >> 1) - ((int) r.getHeight() >> 1);
	g.drawRect(x, y, (int) r.getWidth(), (int) r.getHeight());
	g.drawString(ip, x, y + ((int) r.getHeight()) - ((int) r.getHeight() >> 2));
	r = f.getStringBounds("IP: ", frc);
	g.drawString("IP: ", x - (int) r.getWidth(), y + ((int) r.getHeight()) - ((int) r.getHeight() >> 2));
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
