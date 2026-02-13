package buscaminas;

public class Casilla {
	private boolean tieneMina = false, estaMarcada = false, estaOculta = true;
	private int numMinasCercanas = 0;
	
	public Casilla(boolean tieneMina, int numMinas) {
		this.tieneMina = tieneMina;
		this.numMinasCercanas = numMinas;
		
	}
	
	public int getNumMinasCercanas() {
		return numMinasCercanas;
	}
	public void setNumMinasCercanas(int numMinasCercanas) {
		this.numMinasCercanas = numMinasCercanas;
	}
	public boolean getTieneMina() {
		return tieneMina;
	}
	public void setTieneMina(boolean tieneMina) {
		this.tieneMina = tieneMina;
	}
	public boolean getEstaMarcada() {
		return estaMarcada;
	}
	public void setEstaMarcada(boolean estaMarcada) {
		this.estaMarcada = estaMarcada;
	}
	public boolean getEstaOculta() {
		return estaOculta;
	}
	public void setEstaOculta(boolean estaOculta) {
		this.estaOculta = estaOculta;
	}
}
