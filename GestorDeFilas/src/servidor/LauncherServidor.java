package servidor;

public class LauncherServidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestionFila gestionFila = new GestionFila();
		Comunicacion comunicacion = new Comunicacion(gestionFila);
		
		DeshabilitadorBox hilo_1; // hilo para deshabilitar box's
		RecibidorLlamados hilo_2; // hilo para recibir pedidos de llamados
		
		hilo_1 = new DeshabilitadorBox(comunicacion);
		hilo_2 = new RecibidorLlamados(comunicacion);
		hilo_1.start();
		System.out.println("ESTAMOS POR EJECUTAR EL SEGUNDO HILO !!!");
		hilo_2.start();
		System.out.println("lo ejecutamos !!!");

	}

}
