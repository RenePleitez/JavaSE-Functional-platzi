package com.platzi.functional._11_composition;

import java.util.function.Function;
/* Clase 21 - Entendiendo la composición de funciones
*
* En clases anteriores hemos visto que una HOF toma como parámetro o devuelve (retorna) como resultado una función, o bien la combinación de ambas
* características (tomar como parámetro y retornar una función). Con esto podemos generar composición de funciones y en esta clase veremos dos casos
* concretos de métodos con los que podemos crear composición de funciones.
*
* Creamos la clase MathOperations2 donde nuestras funciones realizarán operaciones matemáticas.
* */
public class MathOperations2 {
    public static void main(String[] args) {
        //Generamos una función que trabaja con un Integer y genera un Integer
        Function<Integer, Integer> multiplyBy3 = x -> {//Tomaremos un número x
            System.out.println("Multiplicando " + x + " por 3");
            return x * 3;//Retornamos el resultado de multiplicar x * 3
        };

        // MÉTODO COMPOSE(lambda)
        //Si quisiéramos que primero sumara 1 y después multiplicara por 3, podemos generar una nueva función a partir de 'multiplyBy3' con el método
        // compose(); por ejemplo, creamos una función que trabaja o toma como parámetro un dato de tipo Integer y genera un Integer:
        Function<Integer, Integer> add1MultiplyBy3 =
                multiplyBy3.compose(y -> {//Para extender el cuerpo de la lambda agregamos llaves {}
                    System.out.println("Le agregaré 1 a " + y);//Añadimos una impresión en pantalla dentro de nuestra lambda para saber el orden de
                    // ejecución de la composición de funciones (orden de llamadas de nuestras funciones)
                    return y + 1;//Retornamos el resultado de sumar y + 1
                });

        // MÉTODO ANDTHEN(lambda)
        //Si quisiéramos agregar un paso a la función compuesta 'add1MultiplyBy3', debemos definir una nueva función con una lambda que ejecute el
        // comportamiento que queremos agregar
        Function<Integer, Integer> andSquare =
                add1MultiplyBy3.andThen(x -> {//En esta función vamos a invocar al método ANDTHEN() y le pasaremos una lambda en la que determinamos
                    // que se tomará un valor x para generar un valor igual a x * x (al cuadrado)
                    System.out.println("Estoy elevando " + x + " al cuadrado");//Añadimos una impresión en pantalla dentro de nuestra lambda para
                    // saber el orden de ejecución de la composición de funciones (orden de llamadas de nuestras funciones)
                    return x * x;//Retornamos el resultado de multiplicar x por sí mismo
                });
        //Para imprimir el resultado de ejecutar la función 'add1MultiplyBy3' usamos un println y el método apply() con el parámetro de tipo Integer
        // con que se trabajará
        System.out.println(
                add1MultiplyBy3.apply(5)
        );
        //En pantalla veremos que con el método COMPOSE() primero se ejecutará la suma de 1 al valor indicado como parámetro (add1MultiplyBy3) y
        // posteriormente la multiplicación del resultado de dicha operación por 3 (multiplyBy3). De fondo, al invocar a la función 'add1MultiplyBy3'
        // se está generando una función intermedia a partir del método COMPOSE() (compose() toma como parámetro una función lambda que ejecuta
        // primeramente y después ejecuta la función sobre la cual se mandó llamar el método, en este caso primero la función add1MultiplyBy3 y
        // después multiplyBy3)

        //Acabamos de cercar una función compuesta basada en dos funciones y tenemos una manera de agregar pasos antes de la ejecución de una función,
        // pero ¿también podemos agregar pasos después de la ejecución de una función?

        //Para imprimir el resultado de ejecutar la función 'andSquare' usamos un println y el método apply() con el parámetro de tipo Integer con
        // que se trabajará
        System.out.println(
                andSquare.apply(3)
        );
        //En pantalla veremos que con el método ANDTHEN() primero se ejecutará la suma de 1 al valor indicado como parámetro (add1MultiplyBy3),
        // posteriormente la multiplicación del resultado anterior por 3 (multiplyBy3) (comportamiento de composición de funciones anterior) y
        // finalmente la multiplicación de este último resultado por sí mismo. De fondo, al invocar a la función 'andSquare' se está generando una
        // función adicional a partir del método ANDTHEN() (andThen() toma como parámetro una función lambda que ejecuta tras la ejecución de la
        // función sobre la cual se mandó llamar el método, en este caso primero se ejecuta la función compuesta 'add1MultiplyBy3' y después
        // 'andSquare')

        //Acabamos de cercar una función compuesta basada en tres funciones y ahora tenemos una manera de agregar pasos tanto antes como después de
        // la ejecución de una función. Con esto vemos que al recibir una función podemos generar nuevas funciones que tengan un orden de precedencia
        // o que puedan ejecutar otros pasos adicionales antes de ejecutarse a sí mismas. Esta es una manera de crear funciones más complejas a partir
        // de lógica o comportamientos de alguien más (podemos asegurar crear un archivo, que un equipo tenga internet, revisar o generar una conexión
        // antes de hacer una consulta de nuestro lado).
    }
}
