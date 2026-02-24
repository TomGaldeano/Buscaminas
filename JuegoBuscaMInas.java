package buscaminas;

import java.util.Random;

public class JuegoBuscaMInas {
	private Casilla[][] tablero;
	private int numMinas = 0,numMarcadas;
	private boolean exploto, marcoMal;
	
	public JuegoBuscaMInas(int dimensiones, int numMinas) {
		this.numMinas = numMinas;
		this.iniciarTablero(new Casilla[dimensiones][dimensiones]);
		this.exploto=false;
		this.marcoMal = false;
		this.numMarcadas = 0;
		
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
		/* Calcula el número de minas que rodean a cada casilla. */
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
							posiciones[k][0]>-1 && posiciones[k][1]>-1) {
						if (tablero[posiciones[k][0]][posiciones[k][1]].getTieneMina()) {
							minas++;
						}						
					}
				}
				this.tablero[i][j].setNumMinasCercanas(minas);
				minas = 0;				
			}			
		}
	}
	public void imprimirTablero() {
		System.out.print("    ");
		for (int i = 1; i < this.tablero.length+1; i++) {
			if (i<10) {
				System.out.print("\u001B[32m"+" 0"+i+"\u001B[0m");
			}else{
				System.out.print("\u001B[32m"+" "+i+"\u001B[0m");
			}					
		}
		
		for (int i = 1; i < this.tablero.length+1; i++) {
			System.out.println();
			if (i<10) {
				System.out.print("\u001B[32m"+" 0"+i+"\u001B[0m");
			}else{
				System.out.print("\u001B[32m"+" "+i+"\u001B[0m");
			}
			for (int j = 0; j < this.tablero.length; j++) {
				if (this.tablero[i-1][j].getEstaMarcada()) {
					System.out.print("  ^");
				}/* else if (this.tablero[i-1][j].getTieneMina()) {
					System.out.print("  *"); // para debug
				}*/ else if(this.tablero[i-1][j].getEstaOculta()) {
					System.out.print("  -");
				}else {
					System.out.print("  "+this.tablero[i-1][j].getNumMinasCercanas());
				}
			}			
		}
		System.out.println();		
	}
	
	public boolean descubrirCasilla(int fila, int columna){
		/*  Se le pasa por parámetro la fila y columna a descubrir. Devuelve false en
		caso que ya estuviese descubierta o marcada previamente. También si excede de los límites
		del tablero.*/
		if (fila>this.tablero.length || fila< 1 || columna>this.tablero.length || columna<1) {
			//System.out.println("Se han excedido los limites del tablero");
			return false;
		}else if (!this.tablero[fila-1][columna-1].getEstaOculta()) {
			//System.out.println("casilla ya Descubierta");
			return false;
		}else if (this.tablero[fila-1][columna-1].getEstaMarcada()) {
			//System.out.println("Casilla ya marcada");
			return false;
		}
		if(this.tablero[fila-1][columna-1].getTieneMina()) {
			this.exploto = true;
		}
		this.tablero[fila-1][columna-1].setEstaOculta(false);
		// recursion
		if (this.tablero[fila-1][columna-1].getNumMinasCercanas()==0) {
			int[][] posiciones = {
				    {fila-1, columna-1}, // arriba-izquierda
				    {fila-1, columna},   // arriba
				    {fila-1, columna+1}, // arriba-derecha
				    {fila,   columna-1}, // izquierda
				    {fila,   columna+1}, // derecha
				    {fila+1, columna-1}, // abajo-izquierda
				    {fila+1, columna},   // abajo
				    {fila+1, columna+1}  // abajo-derecha
				};
			for(int i=0;i<posiciones.length;i++) {
				this.descubrirCasilla(posiciones[i][0], posiciones[i][1]);
			}
		}
		
		return true;
	}

	public boolean marcarCasilla(int fila, int columna){
		/*Se le pasa por parámetro la fila y columna a marcar. Devuelve false en
		caso que ya estuviese descubierta o marcada previamente. También si excede de los límites
		del tablero. */ 
		if (fila>this.tablero.length || fila<1 || columna>this.tablero.length || columna<1) {
			//System.out.println("Se han excedido los limites del tablero");
			return false;
		}else if (!this.tablero[fila-1][columna-1].getEstaOculta()) {
			//System.out.println("casilla ya Descubierta");
			return false;
		}else if (this.tablero[fila-1][columna-1].getEstaMarcada()) {
			//System.out.println("Casilla ya marcada");
		}
		if(!this.tablero[fila-1][columna-1].getTieneMina()) {
			this.marcoMal = true;
		}
		this.tablero[fila-1][columna-1].setEstaMarcada(true);
		this.numMarcadas++;
		return true;
	}

 	public int causaTerminacionJuego(){
		/* Devuelve un entero con los siguientes valores: 1 (el jugador ha
		marcado todas las minas y ha ganado), 2 (el jugador ha perdido porque ha descubierto una
		casilla con una mina), 3 (el jugador ha perdido porque ha marcado una casilla sin
		mina), 0 (la partida continúa) */
		if (this.numMarcadas==this.numMinas) {
			return 1;
		}
		else if (this.exploto) {
			return 2;
		}else if (this.marcoMal) {
			return 3;
		}
		return 0;
	}
		

}
