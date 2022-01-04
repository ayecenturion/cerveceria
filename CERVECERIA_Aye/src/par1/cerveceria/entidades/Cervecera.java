package par1.cerveceria.entidades;

import java.util.ArrayList;
import java.util.List;

public class Cervecera {

	private static final double PRECIO_BOTELLITA = 80.0;
	private static final double PRECIO_LITRO = 160.0;
	private ArrayList<Contenedor> deposito;
	private ArrayList<Integer> saboresVendidos;

	private Refrigerador<Cajon> refrigeradorEnvasados;
	private Refrigerador<Barril> refrigeradorSueltos;
	
	private static double MED_GRANDE = 3;
	private static double MED_MEDIANA = 1.5;
	private static double MED_CHICA = 0.750;
	
	

	public Cervecera() {
		this.refrigeradorEnvasados = new Refrigerador<>();
		this.refrigeradorSueltos = new Refrigerador<>();
		this.deposito = new ArrayList<>();
		this.saboresVendidos = new ArrayList<>();
	}
	/**
	 * Guarda el barril en el refrigerador que corresponda.
	 * @param sabor
	 */
	public void agregarBarril(Sabor sabor) {
		if(sabor == null) {
			throw new IllegalArgumentException("El sabor indicado no existe");
		}else {
			Barril barril = new Barril(sabor);
			this.refrigeradorSueltos.add(barril);
			System.out.println("Barril de " + sabor + " agregado.");
		}
		
	}

	/**
	 * Guarda el cajon en el refrigerador que corresponda
	 * @param sabor
	 */
	public void agregarCajon(Sabor sabor) {
		if(sabor == null) {
			throw new IllegalArgumentException("El sabor indicado no existe");
		}else {
			Cajon cajon = new Cajon(sabor);
			this.refrigeradorEnvasados.add(cajon);
			System.out.println("Cajon de " + sabor + " agregado.");
		}
	}

	/**
	 * Se usa para vender botellitas. No olvides chequear el stock al terminar la venta.
	 * @param sabor
	 * @return
	 */
	public EnvaseCerveza vender(Sabor sabor) {
		EnvaseCerveza botellita = null;
		Cajon c = this.refrigeradorEnvasados.buscar(sabor);
		//System.out.println(c.toString());
		if(c != null && !c.estaVacio()) {
			botellita = c.extraer();
			sumarUnidadesVendidas(sabor);
			//System.out.println(c.toString());
		}
		
		if(c.estaVacio()) {
			this.deposito.add(c);
		}
		
		return botellita;
	}
	
	private void sumarUnidadesVendidas(Sabor sabor) {
		int suma = 0;
		int pos = sabor.ordinal();
		
		for(Sabor s: Sabor.values()) {
			this.saboresVendidos.add(0);
		}
		int valor = this.saboresVendidos.get(pos);
		suma = suma + 1;	
		this.saboresVendidos.set(pos, suma);
		
		
	}

	/**
	 * Se usa para vender cerveza suelta en garrafas. No olvides chequear el stock al terminar la venta.
	 * @param capacidad
	 * @param sabor
	 * @return
	 */
	public EnvaseCerveza vender(Medida capacidad, Sabor sabor) {
		EnvaseCerveza envase = null;
		Barril b = this.refrigeradorSueltos.buscar(sabor);
		double ltsCapacidad = determinarCapacidad(capacidad);
		calcularPrGarrafa(b,sabor, capacidad, ltsCapacidad);
		
		if(b != null && !b.estaVacio()) {
			if(b.getLitros() >= ltsCapacidad) {
				envase = calcularPrGarrafa(b,sabor, capacidad, ltsCapacidad);
				sumarUnidadesVendidas(sabor);
			}	
		}
		
		if(b != null && b.estaVacio()) {
			this.deposito.add(b);
		}
		return envase;
	}
	
	private double determinarCapacidad(Medida medida) {
		
		double capacidad = 0;
		
		if(medida == Medida.CHICA) {
			capacidad = MED_CHICA;
		}else if(medida == Medida.MEDIANA) {
			capacidad = MED_MEDIANA;
		}else {
			capacidad = MED_GRANDE;
		}

		return capacidad;
	}
	
	private Garrafa calcularPrGarrafa(Barril barril, Sabor sabor, Medida capacidad, double ltsCapacidad) {
		Garrafa garrafa = new Garrafa(sabor, capacidad,ltsCapacidad);
		
		return garrafa;
	}

	/**
	 * Lista los sabores disponibles de cerveza suelta o preenvasada
	 */
	public void listarDisponibles() {
		ArrayList<Cajon> cajones = this.refrigeradorEnvasados.getLista();
		
		System.out.println("Sabores disponibles de cerveza envasada: ");
		for(Cajon c : cajones) {
			System.out.println(c.getSabor());
		}
		ArrayList<Barril> barriles = this.refrigeradorSueltos.getLista();
		
		System.out.println("Sabores disponibles de cerveza suelta: ");
		for(Barril b : barriles) {
			System.out.println(b.getSabor());
		}
	}

	public double getPrecioLitro() {
		return PRECIO_LITRO;
	}

	public double getPrecioUnidad() {
		return PRECIO_BOTELLITA;
	}

	/**
	 * Lista la reposicion a partir de lo que haya vacio en deposito.
	 */
	private void listarReposicion() {
		System.out.println("-- Reposiciones --");
		if (deposito.isEmpty()) {
			System.out.println("No hay nada que reponer.");
		} else {
			for(Contenedor c : this.deposito) {
				if(c instanceof Cajon) {
					System.out.println("Cajon de " + c.getSabor());
				}else {
					System.out.println("Barril de " + c.getSabor());
				}
				
			}
		}
	}

	/**
	 * Lista la cantidad de unidades vendidas de cada sabor.
	 */
	private void listarUnidadesVendidasPorSabor() {
		System.out.println("-- Unidades vendidas por sabor --");
		for(Integer i : this.saboresVendidos) {
			
		}
		
		for(Sabor s: Sabor.values()) {
			String nombre = s.name();
			int pos = s.ordinal();
			int cant = this.saboresVendidos.get(pos);
			System.out.println(nombre + " = " + cant);
		}
	}

	public void cerrarDia() {
		System.out.println("** Cierre del dia **");
		listarReposicion();
		listarUnidadesVendidasPorSabor();
	}

}