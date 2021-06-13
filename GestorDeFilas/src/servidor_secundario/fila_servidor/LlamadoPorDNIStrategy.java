package servidor_secundario.fila_servidor;

import java.util.Iterator;
import java.util.Queue;

public class LlamadoPorDNIStrategy implements I_LlamadoStrategy {
	
	private Queue<String> clientes;
	
	public LlamadoPorDNIStrategy(Queue<String> clientes) { // de forma ascendente (primero los DNI menores, o sea, la gente mas grande)
		this.clientes = clientes;
	}

	@Override
	public String proximoCliente() {
		String minDNI = this.minDNI();
		if(minDNI != null)
			this.clientes.remove(minDNI);
		return minDNI;
	}
	
	private String minDNI() {  
        String minDNI = "zzzzzzzzz";
        String dniActual = "";
        Iterator<String> it = this.clientes.iterator();
        while(it.hasNext()){
        	dniActual = it.next();
        	if(minDNI.compareTo(dniActual) > 0) // si minDNI > dniActual
                     minDNI = dniActual;
        }
        if(minDNI.equals("zzzzzzzzz"))
        	minDNI = null;
        else{
			this.clientes.remove(minDNI);
		}
        return minDNI;
    }

}
