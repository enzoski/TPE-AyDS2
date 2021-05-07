package atencion;

import atencion.vista_atencion.VistaAtencionInicio;
import atencion.vista_atencion.VistaAtencionLlamarCliente;

public class LauncherAtencion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VistaAtencionInicio vistaInicio = new VistaAtencionInicio();
		VistaAtencionLlamarCliente vistaLlamarCliente = new VistaAtencionLlamarCliente();
		ControladorAtencion controlador = new ControladorAtencion(vistaInicio, vistaLlamarCliente);

	}

}
