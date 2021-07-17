# TPE-AyDS2
**Trabajo Práctico Especial**: Análisis y Diseño de Sistemas II - 2021<br />
Facultad de Ingeniería - Universidad Nacional de Mar del Plata

**Integrantes**: Valentina Fernández - Enzo Nogueira Barria

**Objetivo**: Diseño e implementación de un sistema de gestión digital de filas.

## Componentes del Sistema:
* *Registro* (dispositivo táctil donde los clientes ingresan su DNI)
* *Atencion* (puesto de trabajo de los empleados que llaman a clientes)
* *Llamado* (televisor donde se muestran los llamados a clientes)
* *Servidor* (maneja la comunicación entre componentes y gestiona la fila de clientes; se cuenta con 2 servidores para mejorar la disponibilidad del sistema)
* *Monitor* (controla el estado de todos los componentes del sistema, informando si alguno deja de funcionar)

Además, se cuenta con un repositorio de clientes (en XML) con el cuál el servidor valida los intentos de registro; y a medida que se registren clientes o se realicen llamados, se irá generando un historial de cada uno (en XML).

## Instalación y Configuración:
1. Ejecutar cada uno de los [*.jar*](./JARs_ejecutables) desde la consola de Windows/Linux con el siguiente comando (estando en la ruta correspondiente):
   > *java -jar \<aplicacion>\.jar*
2. Ingresar las direcciones IP o datos que se soliciten.

Nota: *se debe tener instalada la [máquina virtual de java](https://www.java.com/)*.

## Interfaces Gráficas:
![GUIs](https://user-images.githubusercontent.com/74929931/126049365-d0c63730-28a7-4cf4-b57b-2aa438e2264e.png)

