package atencion.vista_atencion;

import java.awt.event.ActionListener;

public interface I_VistaAtencion {
	
	// Action Commands [VistaAtencionInicio]
	public static final String AC_HABILITAR = "habilitarBox";
		
	// Action Commands [VistaAtencionLlamarCliente]
	public static final String AC_LLAMAR = "llamarCliente";
	public static final String AC_DESCONECTAR = "desconectarBox";
		
	// Métodos comunes a implementar
	public void abrirVentana();
	public void cerrarVentana();
	public void setControlador(ActionListener c);

}
