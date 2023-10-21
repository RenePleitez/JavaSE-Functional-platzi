package com.platzi.functional._15_streams_intro;

import java.util.stream.IntStream;
/* Clase 26 - Streams de tipo específico y Paralelismo
*  Stream por sí misma es una interfaz y una clase, hay clases como AbstractStream o AbstractImplementationStream que Java genera internamente para
* proveernos de estos Streams. Pero hay veces que necesitamos Streams de un tipo específico, es posible extender o implementar de la clase Stream y
* también utilizar Streams predefinidos.*/
public class TypeStream {
    public static void main(String[] args) {
        //Algo muy común es trabajar con datos primitivos como los int. Vamos a crear un 'IntStream' que se caracteriza por ser un Stream que emite
        // números enteros.
        IntStream infiniteStream = IntStream.iterate(0, x -> x + 1); //Nuestro IntStream 'infiniteStream' va a estar generando datos de manera
        // infinita debido a que utilizamos el método 'iterate()' para crearlo. Dentro del cuerpo del método definimos que comience a iterarse
        // empezando con cero y posteriormente definimos un IntUnitaryOperator (función/operador que recibe un int como parámetro y retorna un int).
        // Con esto vamos a empezar generando el número 0, luego el 1, luego el 2, luego el 3 y así sucesivamente hasta que le digamos al Stream que
        // pare.

        //¿Cómo le decimos a un Stream infinito que pare? - Limitándolo
        infiniteStream.limit(1000)//En este caso limitamos nuestro String para que genere 1000 números. Como definimos que nuestra numeración
                // se genere de 1 en 1, el Stream generará una numeración del 0 al 999.

                .parallel()//Cuando utilizamos el método 'parallel()', Stream se va a encargar de distribuir los datos en todos los procesadores
                // o cores de tu procesador sin necesidad de crear un hilo o mantener una referencia hacia los distintos procesos. Al correr el
                // programa tras agregar este método, en la terminal, podemos ver que se hace exactamente lo mismo que cuando no estaba añadido, solo
                // que de forma concurrente. La numeración que se genera no es continua (no es una numeración que muestra ordenadamente los números
                // pares del 0 al 999), vemos que se empieza por los 800 y tantos a los que le siguen los 100 y tantos. Esto es porque el procesador
                // está ejecutando las tareas y conforme va terminando con diferentes tareas, va devolviendo los resultados.

                .filter(x -> x % 2 == 0)//Generamos un nuevo Stream a partir de los datos de nuestro IntStream 'infiniteStream', que incluye únicamente
                // a los datos que son pares.

                //.allMatch(x -> x % 2 == 0) - Método 'allMatch(Predicate)' nos devuelve un valor booleano (true o false) acorde a si todos los elementos
                // de un Stream cumplen o no la condición planteada en un Predicate que recibe como parámetro. En este caso sería redundante agregarlo
                // para confirmar que todos los elementos del Stream sean pares, debido a que una línea antes se realiza el filtrado (el valor booleano
                // retornado siempre sería true). Es una forma de validar que todos los empleados de un lugar tengan un ID o que todas las personas estén
                // afiliadas a un cierto programa o incluso validar que todos los teléfonos de nuestra agenda tengan una sintaxis válida.

                .forEach(System.out::println);//Imprimimos cada elemento del Stream generado tras el filtrado de los datos generados por nuestro
                // 'infiniteStream' con el método 'forEach()' y utilizando nuestro operador de referencia.

        //Es posible que estas operaciones sean grandes, pesadas y hayan muchos datos por procesar; además, Java es famoso por el manejo de concurrencia
        // (manejo de múltiples hilos, threads y procesadores) y el soporte que tiene para ello. Por esto, tenemos un método que nos ayuda a que nuestro
        // Stream y las operaciones sobre el mismo, puedan ejecutarse de manera concurrente; es decir, si nuestro procesador tiene cuatro núcleos se van
        // a ejecutar cuatro operaciones a la vez (cada una con su respectivo dato) y al final Stream va a recolectar los datos en un solo elemento. Este
        // es el método 'parallel()'.

    }//Es importante recalcar que una vez que creamos un Stream de tipo específico, todas las funciones, operadores y métodos dentro este Stream van a
    // seguir trabajando sobre ese tipo. Esto nos ayuda a agregar comportamientos sin tener que estar validando el tipo de dato o las propiedades de
    // un objeto.

//Streams paralelos es muy poderoso, pero tiene una condición. Cuando se tengan pocos datos, Stream trabaja mejor sobre un solo procesador; por otra
// parte, si se tienen muchos datos (cientos de miles o millones) es recomendable utilizar 'parallel()'. También, cabe resaltar que si es importante
// el orden de los datos, NO deberíamos usar 'parallel()'.
}
