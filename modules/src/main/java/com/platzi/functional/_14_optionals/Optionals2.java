package com.platzi.functional._14_optionals;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
/* Clase 22 - La clase Optional

*  Hasta ahora hemos visto Functions, Predicates, Métodos, Consumers y Supplier (conjunto de elementos que hacen más sencilla la programación
* funcional); pero, hay un elemento que surgió en Java 8 que también ayuda a una mejor programación funcional, de manera que nos evita problemas con
* valores inexistentes (es decir NullPointerException). Este elemento es la clase Optional y su intención es que a partir de los diferentes tipos de
* instancias de funciones que se pueden crear, no tengamos que preocuparnos por el valor de retorno directamente.*/
public class Optionals2 {
    public static void main(String[] args) {
        //Vamos a tener que revisar el resultado de la ejecución del método getNames(), a través de la variable names, para corroborar la presencia
        // de datos en la lista retornada por dicho método
        List<String> names = getNames();
        //Primeramente tenemos que revisar que names no sea nulo
        if (names != null){
            //Aquí podríamos empezar a operar con nombres de names, pero van a haber casos como el del método mostValuablePlayer, que retorna Strings,
            // donde nos encontraremos que la condición de que la variable a través de la cual se está revisando la ejecución del método sea diferente
            // a null (que el String no sea nulo) no se cumple; ya que, el String no es nulo pero es un Sting vacío. Entonces tendríamos que empezar a
            // agregar condiciones y condiciones y condiciones..... No tenemos una manera concreta de definir cuando sí y cuando no hay un dato.
        }
        // La clase Optional nos va a ayudar a meter un dato y operar sobre el mismo cuando esté presente o cuando no, con esto podemos generar valores
        // por default de acuerdo a nuestras necesidades en cada iteración

        //Para operar sobre la lista de nombres del método/función 'getOptionalNames()' hay que obtener el Optional del tipo de la función/método
        Optional<List<String>> optionalNames = getOptionalNames();
        if (optionalNames.isPresent()){ //Una vez creado este Optional podemos operar sobre este
            //Operar con nombres de optionalNames en caso de que se cumpla la condición determinada por el método 'isPresent()'
        }//Si nos damos cuenta este código es lo mismo que comparar contra null (revisión del resultado de la ejecución del método getNames() es muy
        // similar a la revisión del resultado de la ejecución del método getOptionalNames()), en dicho caso no hay ninguna diferencia
        //Hagámoslo más funcional
                                                //namesValue solamente va a existir cuando hayan valores presentes en el Optional, cuando no hayan valores
                                                //presentes no ejecutaremos esta lambda
        optionalNames.ifPresent(namesValue -> namesValue.forEach(System.out::println));//Al utilizar el método 'ifPresent' el IDE nos sugiere utilizar
                                                                                        // un Consumer (tipo de función que va a tomar un dato y no va a retornar ningún resultado), en este caso utilizamos una lambda que simula el
                                                                                        // comportamiento de un Consumer (recibe un String y devuelve void) y permite la impresión de los nombres en la lista del método/función
                                                                                        // 'getOptionalNames()' (uso de lambdas y método de referencia para generar una función que simula a un Consumer)
        //Métodos para operar las instancias Optional<>:
        //optionalNames.flatMap() - Si sabemos que el Optional va a devolvernos una lista, podemos generar un iterador a partir de los elementos de
                                 // la lista. flatMap() recibe como parámetros Function y/ó Optional, en la Function vamos a recibir el elemento y
                                // generar unnuevo valor que se va a utilizar; por otra parte, el Optional es por si no llegásemos a encontrar un dato
        //optionalNames.map() - Podemos hacer conversión de datos. map() es una función a partir de la cual vamos a operar el dato cuando esté
                            // presente para convertirlo en otro dato. Nos va a generar, como nos sugiere el IDE, un Optional de un nuevo tipo; es
                            // decir, podemos tomar un dato que esté o no presente y generar un resultado que esté o no presente, dependiendo de si
                            // el Optional contiene un dato o no. En programación funciona esto es lo más cercano a lo que se conoce como el concepto
                            // mona (pequeña caja que nos va a prevenir encontrarnos con datos vacíos)

        //Vamos a almacenar el resultado de ejecutar la función/método 'optionalValuablePlayer()' en una variable de tipo Optional<tipo de dato>
        Optional<String> valuablePlayer = optionalValuablePlayer();
        //Para poder obtener el dato de quién es el jugador más valioso (nombre), determinado a partir de la lógica de búsqueda en la sección de código
        // del método 'optionalValuablePlayer()' (que puede ser un nombre o un objeto Optional vacío), podemos utilizar el método orElseGet(), al que
        // hay que pasarle como parámetro un Supplier (función que nos va a generar un nuevo dato, esto nos da la posibilidad de almacenar el resultado
        // en una variable independiente) cuyo resultado sea retornado si no hay un valor presente (objeto Optional vacío)
        String valuablePlayerName = valuablePlayer.orElseGet(() -> "No player found");
                                                           // Lambda definida para agregar un mensaje en caso de que el resultado de ejecutar la función/método 'optionalValuablePlayer()' sea un objeto de tipo Optional vacío (no se encontró un jugador más valioso en la base de datos o archivos), de esta manera tenemos un dato o tenemos un dato. No tenemos que lidiar con nulls, con datos vacíos y tenemos la lógica reservada en la parte en la que la necesitamos, sin tener que preocuparnos por lo que el método va a hacwer par alos 80 casos de nuestro proteycto

        //Podemos utilizar Optionals para generar datos sin tener que preocuparnos por la presencia real de datos, por como hacer accesos o como
        // generar diferentes tipos de defaults para cubrir todos los casos posibles. En la mayoría de los casos terminaremos utilizando solo un
        // Optional.empty cuando no encontremos casos y si desconocemos el valor de una variable podemos utilizar Optional.ifNullable.

        //La clase Optional es una manera de almacenar un dato del que no sabemos sus valores y si está o no presente, para poder acceder al mismo
        // mediante operadores (Functions, Consumers, Suppliers) que cuando esté presente el dato, Optional va a invocar a estas funciones
        // pasándoles el dato. En caso de que Optional no tenga un dato (null), no se va a invocar a dichas funciones.
    }
    //Tenemos un método 'getNames()' que debería retornar una lista de algo
    static List<String> getNames (){
        List<String> list = new LinkedList<>(); //Lo ideal al retornar una lista es tener una lista inicial y agregar elementos sobre la misma,
                                                // para eventualmente retornar dicha lista (evitamos retornar un nulo).
        //return list;
        //Pero hay ocasiones en que no tenemos elementos y preferimos retornar un Collections.emptyList(), que lo que hace es retornar una lista que
        // no tiene elementos
        return Collections.emptyList();
    }
    //Es una buena práctica el retornar una colección sin elementos, pero que pasa en casos donde debemos retornar un String ¿Es buena idea retornar
    // un String vacío?
    static String mostValuablePlayer (){
        //return "";
        //¿Sería preferible retornar un null en vez de un String vacío?
        return null;
    }
    //Si el método retorna un Integer, ¿qué tal si retornar un 0 está mal?
    static Integer mostExpensiveItem(){
        return -1;
    } // ¿Retornar -1 es una buena solución?
    //Las reglas van a variar dependiendo el caso e incluso van a haber casos en que retornar un null tenga mucha validéz, pero el problema empieza
    // cuando empezamos a retornar nulls porque por cada retorno tenemos que hacer una validación de los datos que recibimos (corroborar que un dato
    // esté presente o no)


    //Aplicando la clase OPTIONAL
    //Tomemos los tres métodos/funciones anteriores y hagámoslas con Optional
    static Optional<List<String>> getOptionalNames(){ //Con este método/función vamos a estar tratando de obtener los nombres y en caso de no
        // encontrarlos, vamos a buscar la manera de operar sobre dichos nombres
        List<String> namesList = new LinkedList<>(); //Almacenamos lista en una variable para dar mejor visibilidad al código
        return Optional.of(namesList);//Obtención de nombres en namesList
    }
    //¿Cómo podemos retornar un dato cuando no está presente?
    // Cuando no tengamos un dato y lo único que tengamos es un null, tenemos la posibilidad de definir un Optional tal que:
    static Optional<String> optionalValuablePlayer (){
        //return Optional.ofNullable(); Con optional podemos simplemente retornar un Optional .ofNullable(). Método de la clase Optional con el que
        // indicamos que tenemos un dato si es null o no (Optional se encargará de hacer que esto esté escondido dentro de un objeto de tipo Optional
        // para no tener una nullPointerException, que es muy común en Java).

        //Pero tal vez no queremos lidiar con nulls y en lugar de retornar un Optional.ofNullable() podríamos:
        try {//Acceder a nuestros archivos o base de datos para hacer la búsqueda de un valor en específico
            return Optional.of("René");//En este punto utilizamos Optional para buscar el valor determinado como parámetro del método of()
                                            // dentro de la base de datos o archivos
        }catch (Exception e){// Si algo sale mal, detectar la excepción
            e.printStackTrace();
        }
        return Optional.empty();//Si no encontramos el valor indicado en la sección de código del try podemos retornar un Optional.empty() (objeto
                                // Optional vacío)
        //Con esto nos evitamos estar lidiando con nulls y podemos generar una manera en que podemos devolver un dato que existe (está presente) y que
        //puede ser utilizado de alguna manera.
    }
}
