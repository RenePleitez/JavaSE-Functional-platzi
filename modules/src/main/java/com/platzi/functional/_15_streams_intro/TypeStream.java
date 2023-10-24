package com.platzi.functional._15_streams_intro;

import java.util.List;
import java.util.stream.Collectors;
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

        //Clase 29 - Vamos a almacenar la List obtenida tras la operación terminal collect() en la variable 'numbersList', definida como una
        // List<Integer> en este punto porque aquí se realiza la primera operación con el IntStream 'infiniteStream', que terminará convirtiéndose a
        // una List<Integer> gracias al método boxed() seguido del método collect(tipo de collector que convierte de Stream a List), que es una
        // operación terminal (ya no tenemos un Stream nuevo tras esta operación).
        //Con esto estamos convirtiendo un Stream de datos con el que se realizan operaciones, a una lista que ya es estática y tiene un número
        // definido de elementos (ya no vamos a poder agregar operaciones después de esto). Lo único que nos faltaría sería convertir estos números
        // al tipo de dato que devolveremos en nuestro servicio. El caso más común es tomar un Stream y convertirlo a una lista, pero puede que
        // nuestras necesidades sean diferentes. collect() como tal (la función) tiene diferentes formas, podemos tener una invocación a una función
        // a la que le demos un Supplier en el que se va a crear una colección a través de la que se van a agregar los elementos y tenemos un
        // BiConsumer que estará recibiendo dos elementos y generando un resultado final a partir de ellos (a esto se le conoce como Acumulator).
        // Tendremos un BiConsumer en caso de que nuestro Stream esté corriendo en paralelo (Stream tratará de recopilar todos los datos en uno solo)
        //Pero como nos quedaremos con la forma de collect() que nos permite convertir un Stream a una estructura de datos, veamos que estructuras de
        // datos existen. Entre los métodos que tiene Collectors podemos ver que tiene algunas conversiones, los importantes por ahora será convertir
        // un Stream de datos a una colección de datos sin tener un tipo específico, a un mapa concurrente que podríamos utilizar para paralelismo o
        // concurrencia, a una lista (que es el ejemplo de esta clase), a un mapa donde tenemos una llave y un valor partiendo de los datos
        // (necesitamos proveer una función con la que se generará la llave para un cierto dato), a un set (la ventaja de esta estructura es que
        // podemos tener elementos únicos, sin repetirse en el set)
        List<Integer> numbersList = infiniteStream.limit(1000)//En este caso limitamos nuestro String para que genere 1000 números. Como definimos que nuestra numeración
                // se genere de 1 en 1, el Stream generará una numeración del 0 al 999.

                //Clase 29 - Quitamos el parallel porque 1000 elementos representa pocos datos (quitamos el paralelismo). Mantenemos que
                // 'infiniteStream' sea un IntStream, pero ya no es paralelo.
                //.parallel()//Cuando utilizamos el método 'parallel()', Stream se va a encargar de distribuir los datos en todos los procesadores
                // o cores de tu procesador sin necesidad de crear un hilo o mantener una referencia hacia los distintos procesos. Al correr el
                // programa tras agregar este método, en la terminal, podemos ver que se hace exactamente lo mismo que cuando no estaba añadido, solo
                // que de forma concurrente. La numeración que se genera no es continua (no es una numeración que muestra ordenadamente los números
                // pares del 0 al 999), vemos que se empieza por los 800 y tantos a los que le siguen los 100 y tantos. Esto es porque el procesador
                // está ejecutando las tareas y conforme va terminando con diferentes tareas, va devolviendo los resultados.

                .filter(x -> x % 2 == 0)//Generamos un nuevo Stream a partir de los datos de nuestro IntStream 'infiniteStream', que incluye únicamente
                // a los datos que son pares.

                //Clase 29 - Collectors
                //Streams o iteradores automáticos, lo que hacen es aplicar operaciones intermedias(mediante su invocación) a los datos o elementos
                // provistos por el Stream, hasta que lleguemos a una operación terminal. Hasta ahora lo que hemos hecho es imprimir por pantalla,
                // pero en el mundo real tenemos servidores web, peticiones web, bases de datos, archivos, procesamiento de datos, etc. Entonces,
                // necesitamos generar un resultado de operar sobre un Stream y muchas veces quien consuma nuestros servicios en Java no va a estar
                // trabajando sobre Java; por ejemplo, puede ser un programa en Python, una petición web en JavaScript u otro servicio escrito en C
                // (necesitamos buscar la manera de transformar nuestro Stream en algo que se pueda operar). Generalmente, si estás trabajando con
                // web se hará una conversión a XML o Jason y si se trabaja con sistemas de escritorio se hará una conversión a bytes.
                //Convirtamos nuestro IntStream a una lista de Integers (los números los podemos procesar fácilmente). Lo que haremos es tomar
                // nuestro 'infiniteStream' y convertirlo a una lista de Integers
                //Tras quitar el paralelismo, convertimos nuestro IntStream obtenido de filter() a algo boxed(). Esto quiere decir que convertimos
                // el IntStream con el que venimos operando a otro tipo de Stream que trabaja con datos específicos (Stream<Integer>)
                .boxed()
                //Con collect() recopilamos los datos de un Stream en una sola estructura de datos. Es una manera de convertir un Stream de datos a
                // una estructura que ya no tiene operadores (tiene datos concretos que son resultado de aplicar múltiples operaciones a un Stream).
                // Es muy común utilizar los Collectors que ya están definidos en la clase que lleva este nombre en Java (tenemos Collectors para
                // convertir a lista, mapa o arreglo, simplemente al elegir uno o crear uno propio). En este caso utilizaremos el Collector que
                // convierte a lista (este método nos devolverá una List<Integer>)
                .collect(Collectors.toList());

                //.allMatch(x -> x % 2 == 0) - Método 'allMatch(Predicate)' nos devuelve un valor booleano (true o false) acorde a si todos los elementos
                // de un Stream cumplen o no la condición planteada en un Predicate que recibe como parámetro. En este caso sería redundante agregarlo
                // para confirmar que todos los elementos del Stream sean pares, debido a que una línea antes se realiza el filtrado (el valor booleano
                // retornado siempre sería true). Es una forma de validar que todos los empleados de un lugar tengan un ID o que todas las personas estén
                // afiliadas a un cierto programa o incluso validar que todos los teléfonos de nuestra agenda tengan una sintaxis válida.

                //Clase 29 - quitamos el forEach() para utilizar otra operación muy común en los Streams
                //.forEach(System.out::println);//Imprimimos cada elemento del Stream generado tras el filtrado de los datos generados por nuestro
                // 'infiniteStream' con el método 'forEach()' y utilizando nuestro operador de referencia.

        //Es posible que estas operaciones sean grandes, pesadas y hayan muchos datos por procesar; además, Java es famoso por el manejo de concurrencia
        // (manejo de múltiples hilos, threads y procesadores) y el soporte que tiene para ello. Por esto, tenemos un método que nos ayuda a que nuestro
        // Stream y las operaciones sobre el mismo, puedan ejecutarse de manera concurrente; es decir, si nuestro procesador tiene cuatro núcleos se van
        // a ejecutar cuatro operaciones a la vez (cada una con su respectivo dato) y al final Stream va a recolectar los datos en un solo elemento. Este
        // es el método 'parallel()'.

        //Clase 29 - Imprimimos la lista generada a partir del IntStream
        System.out.println(numbersList);
    }//Es importante recalcar que una vez que creamos un Stream de tipo específico, todas las funciones, operadores y métodos dentro este Stream van a
    // seguir trabajando sobre ese tipo. Esto nos ayuda a agregar comportamientos sin tener que estar validando el tipo de dato o las propiedades de
    // un objeto.

//Streams paralelos es muy poderoso, pero tiene una condición. Cuando se tengan pocos datos, Stream trabaja mejor sobre un solo procesador; por otra
// parte, si se tienen muchos datos (cientos de miles o millones) es recomendable utilizar 'parallel()'. También, cabe resaltar que si es importante
// el orden de los datos, NO deberíamos usar 'parallel()'.
}
/* Clase 30 - job-search: Un proyecto para encontrar trabajo
* Construyamos un proyecto que nos ayude a encontrar trabajo utilizando la API de trabajos de GitHub, que es una API restfull, a través de la cual
* podemos hacerle peticiones para que nos devuelva un listado de trabajos. Esta API no es muy complicada, tiene el consumo a través de gets para
* poder encontrar los trabajos basados en palabras clave (internamente las conoce como descriptions), lo que haremos será enviar un Jason o URL de
* petición y se nos devolverá por resultados un listado de trabajos con ese keyword o descripción específica. Podemos enviar keywords en relación a
* la descripción del trabajo que buscamos, ubicaciones, si el trabajo que buscamos es de medio o tiempo completo.*/
