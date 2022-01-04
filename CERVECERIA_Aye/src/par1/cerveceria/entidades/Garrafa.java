package par1.cerveceria.entidades;

public class Garrafa extends EnvaseCerveza{
	private Sabor sabor;
	private static double PRECIO_LITRO = 160.0;
	private static double MED_GRANDE = 3;
	private static double MED_MEDIANA = 1.5;
	private static double MED_CHICA = 0.750;
	private static double PORC_MED = 0.10;
	private static double PORC_CH = 0.20;
	private Medida medida;
	private double ltsCapacidad;
	
	public Garrafa(Sabor sabor,Medida medida,double ltsCapacidad) {
		super(sabor);
		setMedida(medida);
		setLtsCapacidad(ltsCapacidad);
		setPrecioVenta(PRECIO_LITRO);
	}

	@Override
	protected void setPrecioVenta(double precio) {
		double extra = 0;
		double pr = precio * ltsCapacidad;
		
		if(this.ltsCapacidad == MED_CHICA) {
			
			extra = (pr * PORC_CH) + pr;
			
		}else if(ltsCapacidad == MED_MEDIANA) {
			extra = (pr * PORC_MED) + pr;
			
		}else {
			extra = precio * ltsCapacidad;
			
		}
		super.precioVenta = extra;
	}
	
	
	public Medida getMedida() {
		return medida;
	}

	private void setMedida(Medida medida) {
		this.medida = medida;
	}

	public double getLtsCapacidad() {
		return ltsCapacidad;
	}

	private void setLtsCapacidad(double ltsCapacidad) {
		this.ltsCapacidad = ltsCapacidad;
	}

	@Override
	public String getTipo() {
		return "Garrafa";
	}
	

}
