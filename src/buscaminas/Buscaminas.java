package buscaminas;

import java.util.Scanner;

public class Buscaminas {
	static Scanner in = new Scanner(System.in);
	public static JuegoBuscaMInas dificultad() {
		char dificultad = ' ';
		int dimensiones = 0;
		do {
			System.out.println("Elije una dificultad una dificultad\n"
				+"1: principiante con 8X8 filas (con 10 minas)\n"
				+"2: mateur con 12x12 filas (con 30 minas)\n"
				+"3: avanzado con 16x16 filas (60 minas)"
			);
			dificultad = in.nextLine().charAt(0);
		} while (dificultad!='1'&&dificultad!='2'&&dificultad!='3');
		switch (dificultad) {
			case '1':
				dimensiones=8;
				return new JuegoBuscaMInas(8, 10);
			case '2':
				dimensiones=12;
				return new JuegoBuscaMInas(12, 30);
			case '3':
				dimensiones=16;
				return new JuegoBuscaMInas(16, 60);
			default:
				break;
		}
		System.out.println("\u001B[36m"+"====================\n"
				+ "BUSCAMINAS\n"
				+ "====================\n"
				+ "Introduzca una \"d\", una fila (1-"+dimensiones+") y una columna (1-"+dimensiones+") separadas por una\n"
				+ "coma para revelar una casilla. Introduzca \"m\", una fila\n"
				+ "y una columna separadas por una coma para marcar una casilla con\n"
				+ "una bandera. ej (m,12,2), (d,3,3)"+"\u001B[0m");
		return new JuegoBuscaMInas(8, 10);
	}

	public static void main(String[] args) {
		String[] orden;
		System.out.println("Vienvendido al buscaminas");
		JuegoBuscaMInas juego = dificultad();
		do {
			juego.imprimirTablero();
			System.out.print("Seleccione una casilla:");
			orden = in.nextLine().split(",");
			if (orden[0].equals("d")) {
				juego.descubrirCasilla(Integer.parseInt(orden[1]), Integer.parseInt(orden[2]));
			}else if (orden[0].equals("m")) {
				juego.marcarCasilla(Integer.parseInt(orden[1]), Integer.parseInt(orden[2]));
			}else{
				System.out.println("Instruccion no valida");
			}
		} while (juego.causaTerminacionJuego()==0);
		if (juego.causaTerminacionJuego()==1) {
			System.out.println("Ha ganado");
		}else if(juego.causaTerminacionJuego()==2){
			System.out.println("Exploto la mina");
		}else {
			System.out.println("Marco mal");
		}
		

	}

}
