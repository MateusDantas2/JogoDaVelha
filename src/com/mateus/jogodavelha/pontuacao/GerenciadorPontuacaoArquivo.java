package com.mateus.jogodavelha.pontuacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Interface utilizada para gerenciar a pontuação. As classes que implementam essa interface
 * devem implementar a lógica de como gravar e ler a pontuação.
 * @author Mateus Dantas
 *
 */
public class GerenciadorPontuacaoArquivo implements GerenciadorPontuacao {
	
	private static final String ARQUIVO_PONTUACAO = "pontuacao.txt";
	
	/**
	 * Mapa que armazena em memória a pontuação dos jogadores
	 */
	private Map<String, Integer> pontuacaoMap = new HashMap<String, Integer>();

	/**
	 * Construtor
	 * @throws PontuacaoException
	 */
	public GerenciadorPontuacaoArquivo() throws PontuacaoException {
		inicializar();
	}
	
	/**
	 * Inicializa o gerenciador de pontuação
	 * @throws PontuacaoException
	 */
	private void inicializar() throws PontuacaoException {
		File arquivo = new File(ARQUIVO_PONTUACAO);
		if (!arquivo.exists()) {
			try {
				arquivo.createNewFile();
			} catch (IOException e) {
				throw new PontuacaoException(e.getMessage());
			}
		}
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(arquivo));
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split("\\|");
				
				pontuacaoMap.put(tokens[0].toUpperCase(), Integer.valueOf(tokens[1]));
			}
		} catch (IOException e) {
			throw new PontuacaoException(e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new PontuacaoException(e.getMessage());
				}
			}
		}
	}
	
	@Override
	public Integer getPontuacao(String nome) {
		return pontuacaoMap.get(nome.toUpperCase());
	}
	
	public void gravarPontuacao(String nome) throws PontuacaoException {
		Integer pontuacao = getPontuacao(nome);
		
		if (pontuacao == null) {
			pontuacao = 0;
		}
		
		pontuacaoMap.put(nome.toUpperCase(), pontuacao + 1);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PONTUACAO))) {
			for (Map.Entry<String, Integer> entry : pontuacaoMap.entrySet()) {
				writer.write(entry.getKey() + "|" + entry.getValue());
				
				writer.newLine();
			}
		} catch (IOException e) {
			throw new PontuacaoException(e.getMessage());
		}
	}
}
