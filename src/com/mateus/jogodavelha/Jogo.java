package com.mateus.jogodavelha;

import java.util.ArrayList;
import java.util.List;

import com.mateus.jogodavelha.io.Console;
import com.mateus.jogodavelha.pontuacao.GerenciadorPontuacao;
import com.mateus.jogodavelha.pontuacao.GerenciadorPontuacaoArquivo;

/**
 * L�gica do jogo
 * @author Mateus Dantas
 *
 */
public class Jogo {
	
	private Tabuleiro tabuleiro = new Tabuleiro();
	
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	
	private GerenciadorPontuacao gerenciadorPontuacao;
	
	/**
	 * Inicia o jogo
	 * @throws JogoDaVelhaException � lan�ada se ocorrer algum problema durante o jogo
	 */
	public void jogar() throws JogoDaVelhaException {
		
		System.out.println("=================");
		System.out.println("| JOGO DA VELHA |");
		System.out.println("=================");
		System.out.println();
		
		gerenciadorPontuacao = new GerenciadorPontuacaoArquivo();
		
		System.out.println("Nome do Jogador 1: ");
		String nome = Console.readString();
		jogadores.add(new Jogador(nome, 'X'));
		
		// Se o jogador com este nome j� tiver uma pontua��o gravada, mostra o n�mero de vit�rias.
		Integer pontuacao = gerenciadorPontuacao.getPontuacao(nome);
	
		String msg = "O jogador %s j� possui %d %s!\n";
		
		if (pontuacao != null) {
			System.out.format(msg, nome, pontuacao, pontuacao == 1 ? "vit�ria" : "vit�rias");
		}
		
		System.out.println("Nome do Jogador 2: ");
		nome = Console.readString();
		jogadores.add(new Jogador(nome, 'O'));
		
		// Se o jogador com este nome j� tiver uma pontua��o gravada, mostra o n�mero de vit�rias.
		pontuacao = gerenciadorPontuacao.getPontuacao(nome);
		if (pontuacao != null) {
			System.out.format(msg, nome, pontuacao, pontuacao == 1 ? "vit�ria" : "vit�rias");
		}
		
		//Controla se o jogo est� finalizado ou n�o
		boolean finalizado = false;
		
		// �ndice 0 para jogador 1 e 1 para o jogador 2.
		int indexJogadorAtual = 0;
		
		// Armazena o jogador que venceu, se for null o jogo empatou.
		Jogador vencedor = null;
		
		while (!finalizado) {
			
			tabuleiro.imprimir();
			
			Jogador jogador = jogadores.get(indexJogadorAtual);
			
			System.out.println("Jogador '" + jogador.getNome() + "' => ");
			
			boolean sequenciaEncontrada;
			
			try {
				Jogada jogada = jogador.obterJogada();
				
				sequenciaEncontrada = tabuleiro.efetuarJogada(jogada, jogador.getSimbolo());
			} catch (JogadaInvalidaException e) {
				System.out.println("Erro: " + e.getMessage() + " Por favor, jogue dois n�meros entre 1 e 0 seguidos de v�rgula. Ex: 0,0");
				continue;
			}
			
			if (sequenciaEncontrada) {
				vencedor = jogador;
				finalizado = true;
			} else if (tabuleiro.isCompleto()) {
				finalizado = true;
			}
			
			indexJogadorAtual = (indexJogadorAtual + 1) % jogadores.size();
		}
		
		System.out.println();
		
		if (vencedor == null) {
			System.out.println("EMPATE!");
		} else {
			System.out.println("O jogador '" + vencedor.getNome() + "' venceu o jogo!");
			
			gerenciadorPontuacao.gravarPontuacao(vencedor.getNome());
		}
		
		tabuleiro.imprimir();
	}
}
