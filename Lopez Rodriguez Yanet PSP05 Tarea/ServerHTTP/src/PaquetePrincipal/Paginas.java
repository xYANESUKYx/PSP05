package PaquetePrincipal;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** @Yanet Lopez Rodriguez
 * Ejercicio 1.
 * Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) para que incluya la cabecera Date.
 * Ejercicio 2.
 * Modifica el ejemplo del servidor HTTP (Proyecto java ServerHTTP, apartado 5.1 de los contenidos) para que implemente multihilo, y pueda gestionar la concurrencia de manera eficiente.
 */

 public class Paginas {
  
  public static final String primeraCabecera ="Content-Type:text/html;charset=UTF-8";
  
  //ALMACENAMOS EN EL String "Date" la fecha y hora actual
  public static final String Date = fechaHora();
  
  public static final String html_index = "<html>"
          + "<head><title>index</title></head>"
          + "<body>"
          + "<h1>¡Enhorabuena!</h1>"
          + "<p>Tu servidor HTTP mínimo funciona correctamente</p>"
          + "</body>"
          + "</html>";
  public static final String html_quijote = "<html>"
          + "<head><title>quijote</title></head>"
          + "<body>"
          + "<h1>Así comienza el Quijote</h1>"
          + "<p>En un lugar de la Mancha, de cuyo nombre no quiero "
          + "acordarme, no ha mucho tiempo que vivía un hidalgo de los "
          + "de lanza en astillero, adarga antigua, rocín flaco y galgo "
          + "corredor. Una olla de algo más vaca que carnero, salpicón "
          + "las más noches, duelos y quebrantos (huevos con tocino) los "
          + "sábados, lentejas los viernes, algún palomino de añadidura "
          + "los domingos, consumían las tres partes de su hacienda. El "
          + "resto della concluían sayo de velarte (traje de paño fino), "
          + "calzas de velludo (terciopelo) para las fiestas con sus "
          + "pantuflos de lo mismo, y los días de entresemana se honraba "
          + "con su vellorí (pardo de paño) de lo más fino. Tenía en su "
          + "casa una ama que pasaba de los cuarenta, y una sobrina que "
          + "no llegaba a los veinte, y un mozo de campo y plaza, que "
          + "así ensillaba el rocín como tomaba la podadera...</p>"
          + "</body>"
          + "</html>";
  public static final String html_noEncontrado = "<html>"
          + "<head><title>noEncontrado</title></head>"
          + "<body>"
          + "<h1>¡ERROR! Página no encontrada</h1>"
          + "<p>La página que solicitaste no existe en nuestro "
          + "servidor</p>"
          + "</body>"
          + "</html>";
  
  //CREAMOS UNA NUEVA FUNCIÓN QUE RETORNE UN String CON LA FECHA ACTUAL 
  private static String fechaHora(){
    return ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
  }
  
}