package com.mateus.jogodavelha.pontuacao;

/**
 * 
 * @author Mateus Dantas
 * Interface para gerenciar a pontuação. As classes que implementam essa interface
 * devem implementar a lógica de como gravar e ler a pontuação.
 */
public interface GerenciadorPontuacao {
	
	public Integer getPontuacao(String nome);
	
	public void gravarPontuacao(String nome) throws PontuacaoException;
}
