package registro;

public class LauncherRegistro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VistaRegistro vistaRegistro = new VistaRegistro();
		VistaRegistroConfirmacion vistaConfirmacion = new VistaRegistroConfirmacion();
		ControladorRegistro controlador = new ControladorRegistro(vistaRegistro, vistaConfirmacion);
	}

}
