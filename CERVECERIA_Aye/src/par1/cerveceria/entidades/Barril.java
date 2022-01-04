package par1.cerveceria.entidades;

public class Barril extends Contenedor implements Stockeable{
	private double litros;
	private static double LTS = 30; 
	private static double MED_MEDIANA = 1.5;
	
	public Barril(Sabor sabor) {
		super(sabor);
		this.setLitros(LTS);
	}

	@Override
	protected EnvaseCerveza extraer() {
		EnvaseCerveza garrafa = new Garrafa(super.getSabor(),Medida.MEDIANA,MED_MEDIANA); 
		return garrafa;
	}

	@Override
	public boolean estaVacio() {
		boolean vacio = false;
		if(this.litros < 0) {
			vacio = true;
		}
		return vacio;
	}

	public double getLitros() {
		return litros;
	}
	
	private void setLitros(double litros) {
		this.litros = litros;
		//System.out.println(this.litros);
	}

	@Override
	public String toString() {
		return "Barril [sabor = " + super.getSabor() + " litros=" + litros + "]";
	}
	
	
	
}
