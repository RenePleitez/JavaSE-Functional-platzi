package com.platzi.functional._04_functional;

import java.util.function.Function;
import java.util.function.Predicate;

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
            //Método 'apply' en la función 'squareFunction'
            public Integer apply(Integer x) {
                //Queremos obtener el número al cuadrado al llamar esta función (x^2)
                return x * x;
            }
            //Recordar que se debe poner punto y coma ';' tras la definición de la función
        };
        //Ya con la función definida, la invocamos de la siguiente manera:
                                                                //función.método_en_la_función(parámetro)
        System.out.println("Resulatdo función squareFunction: " + squareFunction.apply(5));
        //Podemos replicar la ejecución de la función las veces que queramos:
        System.out.println("Resultado función squareFunction: " + squareFunction.apply(25));
        //Sigue parte 2 =>>>

        //=>>> Parte 3: Invocamos el método square y su construcción es muy similar a la de la función squareFunction (porque un método también
        // puede ser una función), pero una función actúa como tipo de dato, entonces podemos involucrarlas en variables y pasarlas como parámetros
        // o recibirlas como retorno de una ejecución.
        System.out.println("Resultado método square: " + square(5));
        System.out.println("Resultado método square: " + square(25));

        //Clase 12 - Revisando el paquete java.util.function: Predicate
        //Ya entendimos que hay interfaces nuevas dentro del paquete Functional que nos permiten hacer PF de manera tradicional, tomamos la
        // definición de nuestra interfaz y generamos una instancia con el operador new. Pero esta sintaxis no aporta mucho porque hace que el
        // código sea menos legible. Podemos definir funciones de una manera más simple y más legible:

        //Función que recibe int y devuelve boolean (si el número es impar la variable será igual a true)
        Function<Integer, Boolean> isOdd = x -> x % 2 == 1;
        //Esta versión para definir funciones es mucho más corta y simple. Ya no tenemos que ver las anotaciones de sobreescritura ni utilizar un
        // operador para crear un objeto. Con esta nueva sintaxis pasemos a otra interfaz en Functions, Predicate. Un predicate es una especie de
        // función que trabaja sobre un tipo, pero genera un boolean (testea si algo es válido).
        // Vamos a crear un Predicate sobre enteros (Integers) para testear si un número es par:
        Predicate<Integer> isEven = x -> x % 2 == 0;
        //Para poder ejecutar este tipo de funciones específicas conocidas como predicados, solo tenemos que invocarlas con un método diferente al
        // apply (usado para la ejecución del tipo de funciones Function), que en este caso es 'test'. Estamos revisando si un predicado es
        // verdad y bastará con pasarle un parámetro para obtener un resultado:
        System.out.println(isEven.test(4));;// True , es par (método .test() por ser de tipo Predicate)
        System.out.println(isOdd.apply(9));// True , es impar (método .apply() por ser de tipo Function)
        //Creemos un predicado que reciba un objeto de tipo Student y nos diga si aprobó:
        Predicate<Student> isApproved = student -> student.getCalification() >= 6.0; // La calificación debe ser mayor o igual a 6 para que sea true
        //Creamos un objeto de tipo Student a partir de la clase molde con el mismo nombre:
        Student rene = new Student(5.9);
        //Imprimimos el resultado de aplicar el predicado isApproved sobre el objeto rene.
        System.out.println(isApproved.test(rene)); // false , reprobó
        //Con los predicados podemos generar validaciones rápidas sobre las mismas funciones, métodos que ya tenemos. Nos puede beneficiar mucho
        // al momento de estar filtrando elementos o corroborar que algo tenga datos.
    }
    /* =>>> Parte 2: Método square (clase 11)
    * Hasta ahora esto no tiene nada de diferente con crear métodos:
    * El método square se define fuera del método 'main', a diferencia de la función squareFunction.*/
    static int square (int x){
            return x * x;
        }
    /*Sigue parte 3 =>>>*/

    static class Student{
        //Método constructor de la clase Student, que recibe un tipo de dato double obligatoriamente para la creación de un objeto de este tipo
        // y asigna su valor al atributo calification.
        public Student(double calification) {
            this.calification = calification;
        }
        //Getter del atributo de la clase Student
        public double getCalification() {
            return calification;
        }
        //Atributo de la clase Student
        private double calification;
    }
}

