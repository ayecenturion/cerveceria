package par1.cerveceria.entidades;

import java.util.ArrayList;

public class Cajon extends Contenedor implements Stockeable{
	private ArrayList<Botella> botellas;
	private static int CANT_BOTELLAS = 12;
	
	public Cajon(Sabor sabor) {
		super(sabor);
		this.botellas = new ArrayList<>();
		agregarBotellas();
	}
	
	private void agregarBotellas() {
		for(int i = 0; i<CANT_BOTELLAS;i++) {
			Botella b = new Botella(super.getSabor());
			this.botellas.add(b);
		}
	}

	@Override
	protected EnvaseCerveza extraer() {
		return this.botellas.remove(0);
	}

	@Override
	public boolean estaVacio() {
		boolean vacio = true;
		if(this.botellas.size() > 0) {
			vacio = false;
		}
		return vacio;
	}

	@Override
	public String toString() {
		return "Cajon [botellas=" + botellas.size() + ", getSabor()=" + getSabor() + "]";
	}
	
	
}
