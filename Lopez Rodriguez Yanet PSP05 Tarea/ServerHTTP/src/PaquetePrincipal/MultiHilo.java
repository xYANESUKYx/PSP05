package PaquetePrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @Yanet Lopez Rodriguez
 * Ejercicio 1.
 * Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) para que incluya la cabecera Date.
 * Ejercicio 2.
 * Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) para que implemente multihilo, y pueda gestionar la concurrencia de manera eficiente.
 */

public class MultiHilo extends Thread{
    
  //DECLARAMOS UN NUEVO Socket Y LO MARCAMOS COMO FINAL
  private final Socket socketCliente;

  //CREAMOS EL CONSTRUCTOR DE LA CLASE MULTIHILO 
  public MultiHilo(Socket socketCliente) {
    this.socketCliente = socketCliente;
  }

  //CREAMOS EL MÉTODO run() EL CUAL SE EJECUTARÁ CUANDO SE INICIE EL SUBPROCESO DE EJECUCIÓN DE MultiHilo
  @Override
  public void run() {
    //DECLARAMOS UNA NUEVA VARIABLE DE TIPO InputStreamReader Y LA INICIALIZAMOS
    InputStreamReader InputStream = null;
    try {
      //DECLARAMOS DOS NUEVAS VARIABLES DE TIPO String
      String peticion;
      String html;
      //CREAMOS UN OBJETO InputStreamReader A PARTIR DEL FLUJO DE ENTRADA DEL socketCliente PARA LEER LA PETICIÓN DEL CLIENTE
      InputStream = new InputStreamReader(socketCliente.getInputStream());
      //CREAMOS UN OBJETO BufferedReader A PARTIR DEL InputStreamReader PARA PODER LEER LÍNEAS DE TEXTO DE LA PETICIÓN
      BufferedReader bufLeer = new BufferedReader(InputStream);
      //CREAMOS UN OBJETO PrintWriter A PARTIR DEL FLUJO DE SALIDA DEL socketCliente PARA ESCRIBIR LA RESPUESTA QUE SE 
      //ENVIARÁ AL CLIENTE
      PrintWriter printWriter = new PrintWriter(socketCliente.getOutputStream(), true);
      //LEEMOS LA PRIMERA LÍNEA DE LA PETICIÓN DEL CLIENTE Y LA ASIGNAMOS A LA VARIABLE "peticion"
      peticion = bufLeer.readLine();
      //ELIMINAMOS TODOS LOS ESPACIOS EN BLANCO DE LA VARIABLE "peticion" PARA FACILITAR SU ANÁLISIS
      peticion = peticion.replaceAll(" ", "");
     
      //SI LA PETICIÓN DEL CLIENTE COMIENZA CON "GET"
      if (peticion.startsWith("GET")) {
        //EXTRAEMOS LA SUBCADENA DE "peticion" (ENTRE 'GET' Y 'HTTP/1.1') 
        //QUE CONTIENE LA URL SOLICITADA POR EL CLIENTE
        peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));

        //SI LA URL SOLICITADA ESTÁ VACÍA O IGUAL A "/"
        if (peticion.length() == 0 || peticion.equals("/")) {
          //ASIGNAMOS EL CONTENIDO DE LA PÁGINA "html_index" A LA VARIABLE “html”
          html = Paginas.html_index;
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA LÍNEA INICIAL DE UNA RESPUESTA EXITOSA
          printWriter.println(Mensajes.lineaInicial_OK);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA PRIMERA CABECERA
          printWriter.println(Paginas.primeraCabecera);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA FECHA ACTUAL
          printWriter.println("Date:" + Paginas.Date);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA LONGITUD DEL CONTENIDO
          printWriter.println("Content-Length: " + html.length() + 1);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA UNA LÍNEA EN BLANCO PARA INDICAR EL FIN DE LAS CABECERAS
          printWriter.println("\n");
          //ESCRIBIMOS EN EL FLUJO DE SALIDA EL CONTENIDO DE LA PÁGINA SOLICITADA
          printWriter.println(html);
        } 
        //SI LA URL SOLICITADA ES IGUAL A "/quijote"
        else if (peticion.equals("/quijote")) {
          //ASIGNAMOS EL CONTENIDO DE LA PÁGINA "html_quijote" A LA VARIABLE “html”
          html = Paginas.html_quijote;
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA LÍNEA INICIAL DE UNA RESPUESTA EXITOSA
          printWriter.println(Mensajes.lineaInicial_OK);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA PRIMERA CABECERA
          printWriter.println(Paginas.primeraCabecera);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA FECHA ACTUAL
          printWriter.println("Date:" + Paginas.Date);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA LONGITUD DEL CONTENIDO
          printWriter.println("Content-Length: " + html.length() + 1);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA UNA LÍNEA EN BLANCO PARA INDICAR EL FIN DE LAS CABECERAS
          printWriter.println("\n");
          //ESCRIBIMOS EN EL FLUJO DE SALIDA EL CONTENIDO DE LA PÁGINA SOLICITADA
          printWriter.println(html);
        } 
        //SI LA URL SOLICITADA NO ESTÁ VACÍA NI ES IGUAL A "/" NI A "/quijote"
        else {
          //ASIGNAMOS EL CONTENIDO DE LA PÁGINA "html_noEncontrado" A LA VARIABLE “html”
          html = Paginas.html_noEncontrado;
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA LÍNEA INICIAL DE NotFound
          printWriter.println(Mensajes.lineaInicial_NotFound);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA PRIMERA CABECERA
          printWriter.println(Paginas.primeraCabecera);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA FECHA ACTUAL
          printWriter.println("Date:" + Paginas.Date);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA LA LONGITUD DEL CONTENIDO
          printWriter.println("Content-Length: " + html.length() + 1);
          //ESCRIBIMOS EN EL FLUJO DE SALIDA UNA LÍNEA EN BLANCO PARA INDICAR EL FIN DE LAS CABECERAS
          printWriter.println("\n");
          //ESCRIBIMOS EN EL FLUJO DE SALIDA EL CONTENIDO DE LA PÁGINA SOLICITADA
          printWriter.println(html);
        }

      }
    //CAPTURAMOS CUALQUIER EXCEPCIÓN DE TIPO IOException QUE PUEDA OCURRIR
    } catch (IOException ex) {
      Logger.getLogger(ServidorHTTP.class.getName()).log(Level.SEVERE, null, ex);
    //INDEPENDIENTEMENTE DE SI SE PRODUCE UNA EXCEPCIÓN O NO
    } finally {
      try {
        //CERRAMOS EL InputStreamReader Y EL Socket DEL CLIENTE PARA LIBERAR RECURSOS
        socketCliente.close();
        InputStream.close();
      //CAPTURAMOS CUALQUIER EXCEPCIÓN DE TIPO IOException QUE PUEDA OCURRIR
      } catch (IOException ex) {
        Logger.getLogger(ServidorHTTP.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}

