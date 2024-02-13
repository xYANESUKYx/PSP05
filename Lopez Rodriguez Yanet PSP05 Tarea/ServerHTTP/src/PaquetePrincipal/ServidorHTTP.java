package PaquetePrincipal;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @Yanet Lopez Rodriguez
 * Ejercicio 1.
 * Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) para que incluya la cabecera Date.
 * Ejercicio 2.
 * Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) para que implemente multihilo, y pueda gestionar la concurrencia de manera eficiente.
 */

class ServidorHTTP {

  public static void main(String[] args) {

    try {
      //CREAMOS UN OBJETO ServerSocket EN EL PUERTO 8066 PARA ESCUCHAR LAS PETICIONES ENTRANTES
      ServerSocket socServidor = new ServerSocket(8066);
      
      //DECLARAMOS UNA NUEVA VARIABLE Socket PARA ALMACENAR EL SOCKET DEL CLIENTE QUE REALIZA LA PETICIÓN
      Socket cliente;
      
      //LLAMAMOS A LA FUNCIÓN imprimeDisponible() PARA IMPRIMIR EN LA CONSOLA EL MANSAJE DE INICIO HOMOSEXUAL
      imprimeDisponible();

      //INICIAMOS UN BUCLE INFINITO PARA ESPERAR Y PROCESAR CONTINUAMENTE LAS PETICIONES ENTRANTES
      while (true) {
        //ESPERAMOS Y ACEPTAMOS LA CONEXIÓN ENTRANTE DEL CLIENTE, Y ALMACENAMOS EL SOCKET EN LA VARIABLE "cliente"
        cliente = socServidor.accept();
        
        //MOSTRAMOS POR PANTALLA QUE ESTAMOS ATENDIENDO A EL CLIENTE
        System.out.println("\nAtendiendo al cliente");
        
        //CREAMOS UNA INSTANCIA DE LA CLASE MULTIHILO (LA CLASE PARA MANEJAR MÚLTIPLES SOLICITUDES DE CLIENTES 
        //AL MISMO TIEMPO) Y LE PASAMOS EL SOCKET DEL CLIENTE COMO ARGUMENTO
        MultiHilo hilo = new MultiHilo(cliente);
        //INICIAMOS EL HILO PARA ATENDER AL CLIENTE.
        hilo.start();
          
        //MOSTRAMOS POR PANTALLA QUE YA SE HA ATENDIDO A EL CLIENTE
        System.out.println("Cliente atendido");
        
      }
      //CAPTURAMIS CUALQUIER EXCEPCIÓN
    } catch (IOException ex) {
      Logger.getLogger(ServidorHTTP.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  // MOSTRAMOS EL MANSAJE DE INICIO HOMOSEXUAL
  private static void imprimeDisponible() {
    System.out.println("El Servidor WEB se está ejecutando y permanece a la "
        + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
        + "de tu explorador preferido:\n\nhttp://localhost:8066\npara "
        + "solicitar la página de bienvenida\n\nhttp://localhost:8066/"
        + "quijote\n para solicitar una página del Quijote,\n\nhttp://"
        + "localhost:8066/q\n para simular un error");
  }
}

