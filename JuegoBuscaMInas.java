package buscaminas;

import java.util.Random;

public class JuegoBuscaMInas {
	private Casilla[][] tablero;
	private int numMinas = 0;
	private int numMarcadas = 0;
	
	
	public JuegoBuscaMInas(int dimensiones, int numMinas) {
		this.numMinas = numMinas;
		this.iniciarTablero(new Casilla[dimensiones][dimensiones]);
		
	}

	public Casilla[][] getTablero() {
		return tablero;
	}

	public void iniciarTablero(Casilla[][] tablero) {
		this.tablero = tablero;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				this.tablero[i][j] = new Casilla(false, 0);
			}
			
		}
		this.minar();
		this.calcularMinasCercanas();
		
	}
	private void minar() {
		Random rand = new Random();
		int x = 0, y = 0;
		for (int i = 0; i < this.numMinas; i++) {
			do {
				x = rand.nextInt(this.tablero.length);
				y = rand.nextInt(this.tablero.length);
			} while (this.tablero[x][y].getTieneMina());
			this.tablero[x][y].setTieneMina(true);
		}
	}
	private void calcularMinasCercanas() {
		int minas = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				int[][] posiciones = {
					    {i-1, j-1}, // arriba-izquierda
					    {i-1, j},   // arriba
					    {i-1, j+1}, // arriba-derecha
					    {i,   j-1}, // izquierda
					    {i,   j+1}, // derecha
					    {i+1, j-1}, // abajo-izquierda
					    {i+1, j},   // abajo
					    {i+1, j+1}  // abajo-derecha
					};
				for (int k = 0; k < posiciones.length; k++) {
					if (posiciones[k][0]<this.tablero.length && posiciones[k][1]<this.tablero.length &&
							posiciones[k][0]>0 && posiciones[k][1]>0) {
						if (tablero[posiciones[k][0]][posiciones[k][1]].getTieneMina()) {
							minas++;
						}
						
					}

				}
				this.tablero[i][j].setNumMinasCercanas(minas);;
				minas = 0;
				
			}
			
		}
	}
	public void imprimirTablero() {
		System.out.print(" ");
		for (int i = 1; i < this.tablero.length+1; i++) {
			System.out.print("\u001B[32m"+" "+i+"\u001B[0m");		
		}
		
		for (int i = 1; i < this.tablero.length+1; i++) {
			System.out.println();
			System.out.print("\u001B[32m"+i+"\u001B[0m");
			for (int j = 0; j < this.tablero.length; j++) {
				if (this.tablero[i-1][j].getTieneMina()) {
					System.out.print(" "+"*");
				} else if(this.tablero[i-1][j].getEstaOculta()) {
					System.out.print(" "+this.tablero[i-1][j].getNumMinasCercanas());
				}else {
					System.out.print(" "+this.tablero[i-1][j].getNumMinasCercanas());
				}
			}
			
		}
		
	}

	public int getNumMarcadas() {
		return numMarcadas;
	}

}
