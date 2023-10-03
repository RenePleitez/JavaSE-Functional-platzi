package com.platzi.functional._04_functional;

import java.util.function.Function;
public class MathFunctions {
    //Clase 11 - Revisando el paquete java.util.function: Function
    //Procedamos a ver lo que tiene Java para poder hacer PF, lo primero que haremos será crear una función.
    //No vamos a estar creando objetos de esto, por lo que no vale la pena agregar un constructor, getters y setters, etc.
    public static void main(String[] args) {
        System.out.println("Hola desde MathFunctions.");
        //Estrcutura => Function<Tipo de datos de entrada, tipo de datos de salida> nombre_de_función = new Function<Tipo de datos de entrada, tipo de datos de salida>
        Function<Integer, Integer> squareFunction = new Function<Integer, Integer>() {
            //El IDE proporciona la siguiente estructura:
            @Override
            public Integer apply(Integer x) {
                //Queremos obtener el número al cuadrado al llamar esta función (x^2)
                return x * x;
            }
            //Recordar que se debe poner punto y coma ';' tras la definición de la función
        };
        //Ya con la función definida, la invocamos de la siguiente manera:
        //Ejecutamos la función.método_en_la_función(parámetro)
        System.out.println("Resulatdo función squareFunction: " + squareFunction.apply(5));
        //Podemos replicar la ejecución de la función las veces que queramos:
        System.out.println("Resultado función squareFunction: " + squareFunction.apply(25));
        //Sigue parte 2 =>>>



        //=>>> Parte 3: Invocamos el método square y su construcción es muy similar a la de la función squareFunction (porque un método también
        // puede ser una función), pero una función actúa como tipo de dato, entonces podemos involucrarlas en variables y pasarlas como parámetros
        // o recibirlas como retorno de una ejecución.
        System.out.println("Resultado método square: " + square(5));
        System.out.println("Resultado método square: " + square(25));
        /*
         * =>>> Parte 2: hasta ahora esto no tiene nada de diferente con crear métodos: */
    }
    //El método square se define fuera del método 'main', a diferencia de la función squareFunction.
        static int square (int x){
            return x * x;
        }
    // Sigue parte 3 =>>>
}

