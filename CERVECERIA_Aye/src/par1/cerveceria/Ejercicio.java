package par1.cerveceria;

import par1.cerveceria.entidades.Cervecera;
import par1.cerveceria.entidades.Sabor;
import par1.cerveceria.entidades.EnvaseCerveza;
import par1.cerveceria.entidades.Medida;

public class Ejercicio {

	public static void main(String[] args) {
		Cervecera miCervecera = new Cervecera();
		
		System.out.println("----Agrego los barriles----");
		agregarBarril(miCervecera,Sabor.BOCK);
		agregarBarril(miCervecera,Sabor.GOLDEN);
		agregarBarril(miCervecera,Sabor.LAGER);
		agregarBarril(miCervecera,Sabor.STOUT);
		System.out.println("----Agrego los cajones----");
		agregarCajon(miCervecera,Sabor.GOLDEN);
		agregarCajon(miCervecera,Sabor.IPA);
		agregarCajon(miCervecera,Sabor.LAGER);
		agregarCajon(miCervecera,Sabor.BOCK);
		agregarCajon(miCervecera,Sabor.STOUT);
		agregarCajon(miCervecera,Sabor.HONEY);
		
		System.out.println("El precio por litro es " + miCervecera.getPrecioLitro());
		System.out.println("El precio por unidad es " + miCervecera.getPrecioUnidad());
		
		miCervecera.listarDisponibles();

		System.out.println("** VENTAS **");
		EnvaseCerveza cerveza = miCervecera.vender(Sabor.STOUT);
		mostrarCompra(cerveza);
		cerveza = miCervecera.vender(Medida.CHICA, Sabor.BOCK);
		mostrarCompra(cerveza);
		cerveza = miCervecera.vender(Medida.MEDIANA, Sabor.LAGER);
		mostrarCompra(cerveza);
		cerveza = miCervecera.vender(Medida.GRANDE, Sabor.IPA);
		mostrarCompra(cerveza);
		cerveza = miCervecera.vender(Medida.GRANDE, Sabor.GOLDEN);
		mostrarCompra(cerveza);

		miCervecera.cerrarDia();
	}

	private static void mostrarCompra(EnvaseCerveza cerveza) {
		if (cerveza == null) {
			System.out.println("No habia cerveza del sabor pedido");
		} else {
			System.out.printf("%s de %s - %5.2f$\n", cerveza.getTipo(), cerveza.getSabor(), cerveza.getPrecioVenta());
		}
	}
	private static void agregarBarril(Cervecera miCervecera, Sabor sabor) {
		try {
			miCervecera.agregarBarril(sabor);
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	private static void agregarCajon(Cervecera miCervecera, Sabor sabor) {
		try {
			miCervecera.agregarCajon(sabor);
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	

}
