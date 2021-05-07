package servidor;

public class LauncherServidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestionFila gestionFila = new GestionFila();
		Comunicacion comunicacion = new Comunicacion(gestionFila);

	}

}
