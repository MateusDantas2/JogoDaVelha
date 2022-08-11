package com.mateus.jogodavelha;

/**
 * Exceção que representa uma jogada inválida realizada pelo jogador
 * @author Mateus Dantas
 */
public class JogadaInvalidaException extends JogoDaVelhaException {
	
	public JogadaInvalidaException(String message) {
		super(message);
	}
}
