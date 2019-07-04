package br.ufu.facom.pong.menus;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class TemplateMenu {
	protected BufferedImage img;
	protected Graphics g;
	protected Menu menu;
	
	public TemplateMenu(Menu menu) {
		this.menu = menu;
		inicializar();
	}
	
	private void inicializar() {
		img = new BufferedImage(menu.LARGURA_TELA, menu.ALTURA_TELA, BufferedImage.TYPE_INT_BGR);
		g = img.getGraphics();
	}
	
	protected void desenhaTitulo(Graphics g,Font f, FontRenderContext frc) {
		Rectangle2D r = f.getStringBounds("Pong", frc);
		int x = (int) (menu.LARGURA_TELA - r.getWidth()) >> 1;
		int y = 3 * (((int) (menu.ALTURA_TELA + r.getHeight())) >> 4);
		g.setFont(f);
		g.drawString("Pong", x, y);
	}
	
	
	public abstract void desenhaMenu();
	public abstract void habilitar();
	public abstract void desabilitar();
	
	public BufferedImage getImage() {
		return img;
	}
	
	public Menu getPai() {
		return menu;
	}
}
