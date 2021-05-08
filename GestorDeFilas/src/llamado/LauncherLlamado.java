package llamado;

public class LauncherLlamado {

	public static void main(String[] args) {
		VistaLlamadoTV vistaLlamados = new VistaLlamadoTV();
		ControladorLlamado controlador = new ControladorLlamado(vistaLlamados);
		
		AtendedorLlamados hilo_1; // hilo para hacer llamados
		EliminadorLlamados hilo_2; // hilo para eliminar un box que se desconectó
		
		hilo_1 = new AtendedorLlamados(controlador);
		hilo_2 = new EliminadorLlamados(controlador);
		hilo_1.start();
		hilo_2.start();

	}

}
