package par1.cerveceria.entidades;

public class Botella extends EnvaseCerveza {
	private Sabor sabor;
	private static double MEDIDA_BOTELLA = 0.350; 
	private static double PR_BOT = 80;
	
	public Botella(Sabor sabor) {
		super(sabor);
		setPrecioVenta(PR_BOT);
	}

	@Override
	protected void setPrecioVenta(double precio) {
		this.precioVenta = precio;
		
	}

	@Override
	public String getTipo() {
		
		return "Botella";
	}

	@Override
	public String toString() {
		return "Botella [sabor=" + sabor + "]";
	}
	
	

}
