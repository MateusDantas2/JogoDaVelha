package com.mateus.jogodavelha.pontuacao;

/**
 * 
 * @author Mateus Dantas
 * Interface para gerenciar a pontua��o. As classes que implementam essa interface
 * devem implementar a l�gica de como gravar e ler a pontua��o.
 */
public interface GerenciadorPontuacao {
	
	public Integer getPontuacao(String nome);
	
	public void gravarPontuacao(String nome) throws PontuacaoException;
}
