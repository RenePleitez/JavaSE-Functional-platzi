package com.platzi.functional._05_sam;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;
//Clase 15 - Entendiendo dos jugadores clave: SAM y FunctionalInterface
/* Hasta ahora hemos visto que el paquete Functional en Java, nos proveé de interfaces con las que podemos escribir nuestras propias funciones. Podemos
* hacer evaluaciones con Predicate<>, evaluaciones con Function<> (un parámetro) y BiFunction<> (dos parámetros), si todos los datos de nuestra
* operación son del mismo tipo podemos usar UnaryOperator<> o BinaryOperator<>; además, si vamos a consumir algún dato (hacer una operación con un
* dato) tenemos Consumer<> y si necesitamos proveer de algún dato tenemos Supplier<>. Pero esto no llega hasta ahí, Java introduce una manera en que
* podemos empezar a definir nuestra propia estructura de funciones (sin extender de las interfaces funcionales vistas hasta el momento).*/
public class AgeUtils {
    /* En esta clase calcularemos la edad de alguien basándonos en su fecha de nacimiento (día/mes/año). Pero primero veamos que es una interfaz de
    * tipo SAM (Single Abstract Method), la cual se caracteriza por tener un solo método sin definir:

    interface BiConsumer{
        void accept();
    } Esto tal cual es una SAM

    * Podemos utilizar una notación incluida en Java8, para definir que esta interfaz se va a utilizar como una función:

    @FunctionalInterface (notación incluida en Java 8, con la que el lenguaje ya sabe que voy a poder utilizar la sintaxis con la flecha "->" para
                          hacer definiciones concretas)
    interface BiConsumer{
        void accept();
    }

    * Sin embargo, si añadimos otro método a la interfaz Java no va a permitir que se compile el programa porque no se cumple con la condición para ser
    * una interfaz SAM y no es posible utilizar la notación @FunctionalInterface para poder utilizar la sintaxis flecha "->" y crear funciones propias.

    @FunctionalInterface
    interface BiConsumer{
        void accept();

        int accept2();
    }
    * Esto nos arrojará un error por parte del IDE, indicándonos que hay más de un solo método no definido en la interfaz y esta no es de tipo SAM.*/
    public static void main(String[] args) {
        // Función para agregar ceros en casos necesarios para meses y días: esta nos ayuda a agregar ceros a los días y/o meses en caso de que cuenten
        // con un valor menor a 10, para evitar errores la función 'parseDate' que convierte tipos de dato Integer a LocalDate (convierte un Integer a
        // String).
                                                       //? utilizado para indicar que en caso de que x < 10 se nos devuelva "0" + x, : utilizado para
                                                       // indicar que de lo contrario se nos devuelva String.valueOf(x)
        Function<Integer, String> addCeros = x -> x < 10 ? "0" + x : String.valueOf(x);
        //Función para obtener una fecha parseando los datos: esta nos servirá para convertir 3 tipos de dato Integer a 1 tipo de dato LocalDate
        // (representación en objeto de una fecha en Java)
        TriFunction<Integer, Integer, Integer, LocalDate> parseDate = (day, month, year) -> LocalDate.parse(year + "-" + addCeros.apply(month) +
                "-" + addCeros.apply(day));
        //Cálculo de la edad a partir de dos funciones: esta recibe 3 parámetros Integer y posteriormente genera como resultado un Period (que es un
        //periodo de tiempo en Java), cuyo valor se determina a partir de invocar a la función 'parseDate', junto con su método apply() para ejecutarse
        // considerando los respectivos valores de (day, month, year) (valor correspondiente a la fecha de nacimiento de la persona de interés), y el
        // método now() de un objeto LocalDate (valor correspondiente a la fecha actual) dentro del método between del objeto Period.
        //Este método between es el encargado de determinar la diferencia en tiempo entre ambas fechas y requiere que el primer elemento introducido
        // como parámetro corresponda a la fecha más antigua y el segundo a la fecha más reciente o actual como en este caso, para mostrar un resultado
        // acorde a la operación matemática 'respuesta = fecha actual - fecha de nacimiento de persona de interés' (de lo contrario pueden presentarse
        // resultados negativos).
        //Tras haber definido el contenido del método between del objeto Period, para determinar la diferencia de tiempo entre las fechas, se utiliza a
        // su vez el método getYears() para obtener el resultado en términos de años; además, desde la definición de la función se determinó que el
        // resultado de esta sería de tipo Integer y sin el método getYears(), Period no representa un Integer y el IDE nos arroja un error por el
        // valor de retorno de la función.
        TriFunction<Integer, Integer, Integer, Integer> calculateAge = (day, month, year) -> Period.between(parseDate.apply(day, month, year),
                LocalDate.now()).getYears();
        // Obtenemos la edad de una persona que nació el 02/10/1992, a partir de que las funciones creadas se llamen entre sí (obtener el formato de
        // datos correctos para poder hacer la diferencia de fechas (Period) y agregar ceros a día o mes en caso de ser necesario para evitar errores
        // en "parseDate"):
        System.out.println("Al día de hoy, tienes " + calculateAge.apply(2, 10, 1992) + " años."); //31 años de 02/10/1992 a 03/10/2023 (hoy)
    }
    //Pasemos a definir nuestras propias funciones:
    //Podemos generar nuestras propias interfaces funcionales, podemos generar nuestras propias funciones definidas a partir de un cierto dato o de
    // una estructura.
    @FunctionalInterface
    interface TriFunction<T, U, V, R>{ //En Java no teníamos una interfaz funcional que aceptara 3 datos como parámetros y nosotros decidimos crearla,
                                       // podemos hacerlo para casos muy específicos que trabajen con tipos de datos o estructuras específicas.
        R apply(T t, U u, V v); //Generamos el método apply() para mantener masomenos la consistencia con respecto a las demás Functions, que se
                                // ejecutan mediante este método.
    }//Los datos de esta interfaz son genéricos (no conocemos el tipo de dato sobre el que va a trabajar la función), pero posteriormente se definirán
    // los tipos de dato de cada parámetro (al aplicarse según las necesidades del programa).
}
