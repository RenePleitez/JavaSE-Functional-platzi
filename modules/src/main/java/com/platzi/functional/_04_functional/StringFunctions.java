package com.platzi.functional._04_functional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

//Clase 14 - Revisando el paquete java.util.function: Operators y BiFunction
public class StringFunctions {
    /*Hemos estado utilizando métodos estáticos, debido a que es una manera más sencilla de tener funciones sin necesidad de crear objetos bajo demanda.
    * */
    public static void main(String[] args) {
        //Usaremos una nueva función que trabaja sobre un cierto tipo definido de dato, pero si vamos a la definición podemos ver que la interfaz trabaja sobre una función que trabaja sobre un tipo de dato y genera un dato del mismo tipo (entonces no tenemos que definir explícitamente el tipo de elemento de entrada y de salida).
        //En este caso tomaremos un texto y le agregaremos comillas (cita textual).
        UnaryOperator<String> quote = text -> "\"" + text + "\"";
        //Podemos hacer esto para añadir un énfasis
        UnaryOperator<String> addMark = text -> text + "!";

        /*La manera de utilizar un UnaryOperator es exactamente la misma que tenemos para Function, con el método apply().*/
        System.out.println(quote.apply("Hola Estudiante de platzi!"));//Se añaden comillas en la impresión del texto determinado
        System.out.println(addMark.apply("Hola"));//Se añade un signo de exclamación al final del texto determinado

        //Interfaz bifunction:
        //Este es un tipo de función que va a tomar dos tipos de dato y generará otro tercer tipo de dato:
        BiFunction<Integer, Integer, Integer> multiplication = (x, y) -> x * y;
        //Para ejecutar este tipo de funciones se utiliza el método .apply(), como para Function y UnaryOperator
        multiplication.apply(5, 4);// 20

        //Así como existe UnaryOperator para un solo parámetro, Function para un solo parámetro y BiFunction para dos parámetros, también existe BinaryOperator. En lugar de definir la función 'multiplication' con tres tipo de dato podemos hacerlo con un solo tipo de dato: (recibe un tipo y los dos argumentos más el resultado van a ser del mismo tipo)
                                      //(2 args) -> 1 resultado todos los datos son Integer (Wrap function)
        BinaryOperator<Integer> mult = (x, y) -> x * y;
        mult.apply(5, 4);// 20

        //Hagamos un ejemplo con BiFunction para agregar espacios a un String
                                                                                   //Indicamos que queremos que el nuevo text tenga un total de caracteres igual a number (utilizando "%" + number...), añadiendo espacios a la izquierda del text original, y que formateé con espacios el texto que queremos (utilizando ... + "s", text)
        BiFunction<String, Integer, String> leftPad = (text, number) -> String.format("%" + number + "s", text);
        //De acuerdo a nuestro formato, si el texto es más grande en caracteres que la cantidad de espacios determinada, no se va a añadir ningún espacio adicional (nos va a devolver el texto que ya tenemos porque el formato indica que, al text original se le añaden espacios antes de manera que el text final cuente con un número de caracteres igual al valor de numbers, empezando por los espacios agregados y terminando con el texto original).
        System.out.println(leftPad.apply("Javaaaaaaaa", 10)); //Si el text original "t" cuenta con 11 caracteres y se determina un valor de numbers "u" igual a 10, el texto original ya ocupa la cantidad de espacios que queremos que se ocupen; por lo que, no se añadirán espacios antes (se nos imprimirá el text original)
        System.out.println(leftPad.apply("Java", 10));//Si el text original "t" cuenta con 4 caracteres y se determina un valor de numbers "u" igual a 10, Se añadirán 6 espacios antes de la impresión del texto original para que ahora se ocupen 10 espacios determinados con numbers
        //Un ejemplo práctico para poder utilizar una BiFunction sería un formateador de texto, tienes un programa que se encarga de leer un archivo y le vas agregando todos los formatos conforme lo vas necesitando con una serie de funciones.
        //Podemos tener una lista de BiFunctions que pudieran hacer el tipo de formateo respecto a lo que necesitamos:
        List<BiFunction<String, Integer, String>> formateadores;
        //La intención de esto es que podemos empezar a pasar este tipo de lógica entre diferentes funciones y métodos, para compartir alguna manera de hacer las cosas sin necesidad de tener esta lógica regada en múltiples clases o metodos (mejor tenerlas definidas únicamente como variables y estar compartiéndolas mutuamente).
    }
}
