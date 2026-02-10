package buscaminas;

public class JuegoBuscaMInas {
	private Casilla[][] tablero;
	private int[][] minas;
	
	public JuegoBuscaMInas(int dimensiones, int numMinas) {
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
	}
	public void imprimirTablero() {
		System.out.print(" ");
		for (int i = 1; i < this.tablero.length+1; i++) {
			System.out.print(" "+i);		
		}
		
		for (int i = 1; i < this.tablero.length+1; i++) {
			System.out.println();
			System.out.print(i);
			for (int j = 0; j < this.tablero.length; j++) {
				if (this.tablero[i][j].getEstaMarcada()) {
					System.out.print(" "+"*");
				} else if(this.tablero[i][j].getEstaOculta()) {
				System.out.print(" "+"-");
				}else {
					System.out.print(" "+this.tablero[]);
				}
			}
			
		}
		
	}

}
