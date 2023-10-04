package com.platzi.functional._06_reference_operator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/* Clase 16 - Operador de Referencia
* ¿Cómo puedo empezar a meter programación funcional en un proyecto donde ya tengo definidos métodos, funciones y clases que tienen una manera de
* trabajar entre ellas?
* Java pensó en esto y lo que hizo fue proveer una manera de hacer referencia a eso que ya tenemos definido.*/
public class NombresUtils {
    //A partir del método main vamos a hacer nuestros experimentos para poder utilizar elementos de referencia
    public static void main(String[] args) {
        //Creamos una lista de nombres (String) a partir del método getList() definido al inicio de la clase
        List<String> professors = getList("Nicolás","Juan", "Zulema");
        //Las listas incluyen un método para poder proveerle a un consumer, este es el foreach() y la misma estructura del método (al escribirlo) nos
        // dice que requiere de un consumer que reciba cada elemento de la lista.
        //Creamos un Consumer<T> de tipo String porque es el tipo al que corresponde la lista de nombres, que se llame printer y se encargue de
        // recibir un texto e imprimirlo individualmente en la pantalla
        Consumer<String> printer = text -> System.out.println(text); //Utilizamos el Consumer<T> como tipo de función que toma un tipo de dato String
                                                                    // y devuelve void (nada)
        //Podemos pasar este consumer y la lista va a iterar cada uno de los elementos y va a ejecutar el Consumer para cada uno. Esto es algo
        // redundante porque podríamos haberlo hecho de otra forma, con un operador que nos permite hacer referencia a métodos o funciones que ya
        // están definidas en una clase u objeto.
        professors.forEach(printer);
        System.out.println("/////////////////////////");
        //En lugar de crear un Consumer<T> podemos utilizar el operador de referencia (::) para indicar que queremos buscar la output data (System.out)
        // del método getList() con el que se creó este objeto (Array) professors y que se ejecute la impresión en la terminal de cada elemento de la
        // misma (println).
        professors.forEach(System.out::println);
        //Ya no tenemos la necesidad de crear nuevas funciones u objetos si ya tenemos métodos estáticos o métodos en objetos con los cuales trabajar.
        // Esto facilita entonces el poder referenciar directamente un método que haga una creación de archivo o un parseo o uno que pueda tomar una
        // decisión por nosotros y que ya está en nuestro proyecto y que no tenemos que convertir en una función. De una u otra manera, los métodos
        // también son funciones que están ligados a un objeto o clase y para poder utilizarlos a partir del operador de referencia, el método debe
        // cumplir con las mismas características que la función que deberíamos escribir (en este caso concreto el método out de System devuelve datos
        // de tipo String, tomados por el método println que en cada caso devuelve void; esto, cumple con las mismas características que al crear la
        // función Consumer<T> y ejecutar 'professors.forEach(Consumer<>)').
    }

    //En este ejemplo vamos a definir un método que nos va a generar una lista, esta es de tipo T para que sea genérica y pueda utilizarse para crear
    // listas de distintos tipos; además, se establece como parámetro que puede recibir una cantidad indefinida de elementos T y agregamos un return
    // donde indicamos que queremos que se genere una lista a partir de los mismos.
    //Clase 17 - Hacemos public la lista para poder importarla desde otro paquete
    public static <T> List<T> getList(T... elements){
        return Arrays.asList(elements); //Utilizamos la clase Arrays de Utils y el método asList() para crear la lista de los elementos indicados como
                                        // parámetros del método getList(elements).
    }

}
