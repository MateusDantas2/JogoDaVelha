package com.mateus.jogodavelha;

/**
 * 
 * @author Mateus Dantas
 * Representa uma jogada feita pelo jogador.
 */
public class Jogada {
	
	private int i;
	private int j;

	public Jogada(String jogada) throws JogadaInvalidaException {
		parseString(jogada);
	}
	
	/**
	 * @param jogada Faz o parse da string jogada.
	 * @throws JogadaInvalidaException é lançada se a jogada foi inválida
	 */
	private void parseString(String jogada) throws JogadaInvalidaException {
		try {
			String[] tokens = jogada.split(",");
			
			this.i = Integer.parseInt(tokens[0].trim());
			this.j = Integer.parseInt(tokens[1].trim());
		} catch (Exception e) {
			throw new JogadaInvalidaException("A jogada é inválida.");
		}
	}

	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
}
