package br.ufu.facom.pong.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import br.ufu.facom.framework.utilitarios.FConstantes;
import br.ufu.facom.pong.jogos.futebol.Futebol;
import br.ufu.facom.pong.jogos.paredao.Paredao;
import br.ufu.facom.pong.jogos.tenis.Tenis;
import br.ufu.facom.pong.listeners.menus.TecladoMenuJogo;
import br.ufu.facom.pong.utilitarios.ModoJogo;

public class MenuJogo extends TemplateMenu {
	private String[] titulos = { "Estilo de jogo", "Modo de jogo", "Velocidade", "Tamanho do bloco" };

	public String[][] conteudos = { { "Tenis", "Futebol", "Paredao" }, { "Jogo", "Treino", "Multiplayer" },
			{ "Lento", "Medio", "Rapido", "Crescente" }, { "Pequeno", "Medio", "Grande" } };

	public Celula[] selecionado = new Celula[4]; // Posição do vetor de cada opção que está selecionada
	public int atual = 0; // Opção do menu selecionada para ser modificada
	private KeyListener teclado;

	public MenuJogo(Menu menu) {
		super(menu);
		teclado = new TecladoMenuJogo(this);
		selecionado[0] = new Celula(3, 0);
		selecionado[1] = new Celula(3, 2);
		selecionado[2] = new Celula(4, 1);
		selecionado[3] = new Celula(3, 1);
	}

	@Override
	public void desenhaMenu() {
		Font f = new Font("monospace", Font.PLAIN, 256);
		FontRenderContext frc = new FontRenderContext(null, true, true);
		g.setColor(Color.black);
		g.fillRect(0, 0, menu.LARGURA_TELA, menu.getHeight());
		g.setColor(Color.white);
		desenhaTitulo(g, f, frc);
		f = new Font("monospace", Font.PLAIN, 64);
		g.setFont(f);
		desenhaCelulas(g, f, frc);
	}

	private void desenhaCelulas(Graphics g, Font f, FontRenderContext frc) {
		int espacamento = menu.calculaEspacamento(titulos, f, frc);
		Rectangle2D r;
		int y = 3 * ((int) (menu.ALTURA_TELA) >> 2);
		int x, temp, acumulado = espacamento;
		for (int i = 0; i < titulos.length; i++) {
			r = f.getStringBounds(titulos[i], frc);
			x = acumulado;
			Color c = g.getColor();
			if (i == atual)
				g.setColor(Color.blue);
			else
				g.setColor(Color.black);
			g.fillRect(x, y + (int) r.getY(), (int) r.getWidth() + 5, 3 * (int) r.getHeight());
			g.setColor(c);
			g.drawString(titulos[i], x, y);
			g.drawRect(x, y + (int) r.getY(), (int) r.getWidth() + 5, 3 * (int) r.getHeight());
			temp = (int) f.getStringBounds(conteudos[i][selecionado[i].getAtual()], frc).getWidth();
			x = ((int) r.getWidth() >> 1) - (temp >> 1) + acumulado;
			g.drawString(conteudos[i][selecionado[i].getAtual()], x, y + 100);
			acumulado += r.getWidth() + espacamento;
		}
	}

	public void submeter() {
		switch (selecionado[2].getAtual()) {
		case 0:
			menu.velocidadeJogo = FConstantes.BOLA_VELOCIDADE_BAIXA;
			break;
		case 2:
			menu.velocidadeJogo = FConstantes.BOLA_VELOCIDADE_ALTA;
			break;
		case 3:
			menu.velocidadeJogo = FConstantes.BOLA_VELOCIDADE_CRESCENTE;
			break;
		default:
			menu.velocidadeJogo = FConstantes.BOLA_VELOCIDADE_MEDIA;
		}

		switch (selecionado[3].getAtual()) {
		case 0:
			menu.tamanhoBloco = FConstantes.TAMANHO_BLOCO_PEQUENO;
			break;
		case 2:
			menu.tamanhoBloco = FConstantes.TAMANHO_BLOCO_GRANDE;
			break;
		default:
			menu.tamanhoBloco = FConstantes.TAMANHO_BLOCO_MEDIO;
		}

		switch (selecionado[1].getAtual()) {
		case 1:
			menu.modoJogo = ModoJogo.TREINO;
			break;
		case 2:
			menu.multiplayer = true;
			menu.setMenuAtual(new MenuMultiplayer(menu));
			return;
		default:
			menu.modoJogo = ModoJogo.JOGO;
		}

		switch (selecionado[0].getAtual()) {
		case 0:
			new Tenis(menu.velocidadeJogo, menu.modoJogo, menu.tamanhoBloco);
			break;
		case 1:
			new Futebol(menu.velocidadeJogo, menu.modoJogo, menu.tamanhoBloco);
			break;
		case 2:
			new Paredao(menu.velocidadeJogo, menu.tamanhoBloco);
			break;
		}
	}

	@Override
	public void habilitar() {
		menu.addKeyListener(teclado);
	}

	@Override
	public void desabilitar() {
		menu.removeKeyListener(teclado);
	}

	public KeyListener getKeyListener() {
		return teclado;
	}

	public void prox() {
		selecionado[atual].prox();
	}

	public void ant() {
		selecionado[atual].ant();
	}
}

class Celula {
	int maxPos;
	int atual;

	public Celula(int maxPos, int atual) {
		this.maxPos = maxPos;
		this.atual = atual;
	}

	public void prox() {
		atual = (atual + maxPos + 1) % maxPos;
	}

	public void ant() {
		atual = (atual + maxPos - 1) % maxPos;
	}

	public int getAtual() {
		return atual;
	}

	public int getMaxPos() {
		return maxPos;
	}
}
