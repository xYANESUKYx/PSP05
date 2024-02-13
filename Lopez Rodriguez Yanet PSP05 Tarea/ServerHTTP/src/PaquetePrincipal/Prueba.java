package PaquetePrincipal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** @Yanet Lopez Rodriguez
 * Ejercicio 1.
 * Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) para que incluya la cabecera Date.
 * Ejercicio 2.
 * Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) para que implemente multihilo, y pueda gestionar la concurrencia de manera eficiente.
 */

public class Prueba {
    
    public static void main(String[] args) {
        //CREAMOS UN ExecutorService CON UN NÚMERO DETERMINADO DE HILOS
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //REALIZAMOS LLAMADAS SIMULTÁNEAS AL SERVIDOR CON DIFERENTES HILOS
        for (int i = 0; i < 10; i++) {
            Runnable worker = new LlamadaHTTP();
            executor.execute(worker);
        }
        //FINALIZAMOS EL ExecutorService
        executor.shutdown();
    }
    
    public static class LlamadaHTTP implements Runnable {
        @Override
        public void run() {
            try {
                //REALIZAMOS LA LLAMADA HTTP AL SERVIDOR
                URL url = new URL("http://localhost:8066/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                
                //OBTENEMOS EL CODIGO DE RESPUESTA
                int responseCode = connection.getResponseCode();
                System.out.println("Código de respuesta: " + responseCode);
                
                //CERRAMOS LA CONEXIÓN
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

