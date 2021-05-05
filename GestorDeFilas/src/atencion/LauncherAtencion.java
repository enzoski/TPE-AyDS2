package atencion;

public class LauncherAtencion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VistaAtencionInicio vistaInicio = new VistaAtencionInicio();
		VistaAtencionLlamarCliente vistaLlamarCliente = new VistaAtencionLlamarCliente();
		ControladorAtencion controlador = new ControladorAtencion(vistaInicio, vistaLlamarCliente);

	}

}
