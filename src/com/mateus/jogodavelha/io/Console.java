package com.mateus.jogodavelha.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Classe utilitária para ler dados do console
 * @author Mateus Dantas
 *
 */
public class Console {
	
	public static String readString() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler o dado do teclado.");
		}
	}
	
	public static int readInt() {
		String str = readString();
		
		try {
			return Integer.parseInt(str);
		} catch(NumberFormatException e) {
			throw new RuntimeException(str + "não é um int válido.");
		}
	}
	
	public static double readDouble() {
		String str = readString();
		
		try {
			return Double.parseDouble(str);
		} catch(NumberFormatException e) {
			throw new RuntimeException(str + "não é um double válido.");
		}
	}
}
