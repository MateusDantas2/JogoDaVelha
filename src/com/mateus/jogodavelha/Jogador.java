package com.mateus.jogodavelha;

import com.mateus.jogodavelha.io.Console;

/**
 * Representa um jogador do jogo da velha
 * @author Mateus Dantas
 *
 */
public class Jogador {
	
	private String nome;
	private char simbolo;
	
	public Jogador(String nome, char simbolo) {
		this.nome = nome;
		this.simbolo = simbolo;
	}

	public String getNome() {
		return nome;
	}
	
	public char getSimbolo() {
		return simbolo;
	}
	
	public Jogada obterJogada() throws JogadaInvalidaException {
		String str = Console.readString();
		return new Jogada(str);
	}
	
}
