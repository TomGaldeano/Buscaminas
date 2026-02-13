package buscaminas;

import java.util.Scanner;

public class Main {
	static Scanner in = new Scanner(System.in);
	public static JuegoBuscaMInas dificultad() {
		int bomba = 128163;
		String dificultad = ' ';
		System.out.println("\u001B[36m"+"====================\n"
				+ "BUSCAMINAS\n"
				+ "====================\n"
				+ "Introduzca una fila (1-8) y una columna (1-8) separadas por una\n"
				+ "coma para revelar una casilla. Introduzca \"m\" seguido de una fila\n"
				+ "y una columna separadas por una coma para marcar una casilla con\n"
				+ "una bandera."+"\u001B[0m");
		do {
			
		} while (dificultad!='1'&&dificultad!='2'&&dificultad!='3');
		return new JuegoBuscaMInas(8, 10);
	}
	public static void main(String[] args) {
		System.out.println("Vienvendido al buscaminas");
		JuegoBuscaMInas juego = dificultad();
		
		juego.imprimirTablero();

	}

}
