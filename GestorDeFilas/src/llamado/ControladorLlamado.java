package llamado;

public class ControladorLlamado {
	
	private VistaLlamadoTV vistaLlamados;
	
	public ControladorLlamado(VistaLlamadoTV vistaLlamados) {
		this.vistaLlamados = vistaLlamados;
		this.vistaLlamados.setVisible(true);
	}
	
	public void hacerLlamado() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			while (true) {
			Socket socket = serverSocket.accept();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new
			InputStreamReader(socket.getInputStream()));
			 String msg = in.readLine();
			 System.out.println(msg + "\n"); //ver como parsear esto, o si mandamos objetos.
			socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
