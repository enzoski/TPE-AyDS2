package registro;

import java.awt.event.ActionListener;

public interface I_VistaRegistro {
	
	// Action Commands [VistaRegistroInicio]
	// (definen los strings que se obtendran al producirse un evento, en este caso al pulsar los botones)
	public static final String AC_REGISTRAR = "registrarDNI";
	public static final String AC_NUM_0 = "0";
	public static final String AC_NUM_1 = "1";
	public static final String AC_NUM_2 = "2";
	public static final String AC_NUM_3 = "3";
	public static final String AC_NUM_4 = "4";
	public static final String AC_NUM_5 = "5";
	public static final String AC_NUM_6 = "6";
	public static final String AC_NUM_7 = "7";
	public static final String AC_NUM_8 = "8";
	public static final String AC_NUM_9 = "9";
	public static final String AC_BORRAR = "borrarUltDig";
	
	// Action Commands [VistaRegistroConfirmacion]
	public static final String AC_MODIFICAR = "modificarDNI";
	public static final String AC_CONFIRMAR = "confirmarDNI";
	
	// Métodos comunes a implementar
	public void abrirVentana();
	public void cerrarVentana();
	public void setControlador(ActionListener c);

}
