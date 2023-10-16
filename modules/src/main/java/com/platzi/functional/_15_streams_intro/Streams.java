package com.platzi.functional._15_streams_intro;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
/* Clase 23 - Entendiendo los Streams
*
* Ahora entendemos que la clase Optional es una manera en la que podemos funcionar o generar datos, cuidando que los datos sean seguros al ser
* procesados. Optional es una clase muy interesante, pero ¿qué pasa cuando queremos operar múltiples datos? (no siempre vamos a tener Optionals de
* listas).
*
* En Java 8 LTS se agregó la clase Stream (flujo de datos a través del cual aparecen datos y estos se van consumiendo), que es una especie de lista
* con elementos que se pueden iterar. La diferencia esencial entre listas o colecciones y los Streams, es que un Stream es auto-iterable (cuando
* creamos una lista debemos decidir como ir haciendo las operaciones con cada uno de los elementos en la misma, es muy común crear una lista y a
* continuación definir un ciclo for cuyo cuerpo contenga los comportamientos que queremos).
* */
public class Streams {
    public static void main(String[] args) {
        // Lo más común es crear una lista, que en este caso será a través del método 'getList()' creado en el archivo NombresUtils y contendrá Strings
        // asociados a nombres de distintos cursos de programación:
        List<String> courseList = NombresUtils.getList(
                "Python",
                "Frontend",
                "Backend",
                "FullStack");
        //Ya teniendo nuestra lista de datos, definimos un ciclo for para operar con los mismos
        for (String course : courseList){ //Decimos que en el ciclo se trabajará con datos de tipo String (que representarán a cada curso de
                                          // programación en la lista), tomados de la lista 'courseList' creada anteriormente:
            String newCourseName = course.replace("Python", "Java");//Se pueden realizar operaciones como hacer un reemplazo de
                                                                                     // elementos y almacenar la nueva lista (habiendo reemplazado un
                                                                                     // elemento) en una variable independiente
            System.out.println("Platzi: " + newCourseName);//Agregamos un prefijo a la impresión por pantalla de cada curso en la lista de interés
                                                           // tras haber reemplazado un elemento por otro, cabe destacar que se realizará la
                                                           // impresión de la lista guardada en la variable 'newCourseName'.

            //course.toLowerCase - esta operación nos permite convertir los Strings de la lista a minúsculas

        }//Este tipo de operaciones constituyen la forma más común de trabajar sobre Collections, Listas, Sets y Arreglos.
        System.out.println("___________________________________");

        //El trabajo sobre un Stream es algo diferente, sobre estos hay que definir su tipo (en este caso String), nombre del Stream y utilizamos el
        // método 'of()' (manera más sencilla de generar un Stream) para generarlo a partir de datos predefinidos como parámetros del mismo.
        Stream<String> coursesStream = Stream.of("Java",
                "Frontend",
                "Backend",
                "FullStack");
        // Para poder consumir estos datos (decirle al Stream que se itere), podemos agregar operaciones dentro del Stream:
        // Stream<Integer> courseLengthStream = courseStream.map(course -> course.length()); - Vamos a convertir los Strings en el Stream
        // 'coursesStream' en otros Strings diferentes que compondrán al nuevo Stream 'courseLengthStream' (map nos va a devolver un nuevo Stream como
        // resultado de ejecutar la función lambda para los elementos de un Stream utilizado como base o parámetro). En este caso el Stream nuevo
        // estará compuesto de datos de tipo Integer que corresponden a los valores de longitud de cada String en el Stream utilizado como base.

        //Optional<Integer> longest = courseLengthStream.max((x , y) -> y - x); - En este ejemplo buscamos el nombre más largo que tenemos en el Stream
        // 'courseLengthStream' con el método 'max()' que recibe un Comparator como parámetro (especie de función que toma dos elementos y decide cuál
        // es el más grande). En la lambda definida dentro del cuerpo del método 'max()', de tipo Comparator (porque imita a esta función),
        // determinamos como parámetros un valor 'x' y un valor 'y' para retornar la resta (y - x), con esto haremos la comparativa entre los valores
        // Integer en el Stream 'courseLengthStream'. En este caso, al trabajar con Integers cabe la posibilidad de que el Stream esté vacío y de esta
        // manera no habría un máximo como tal. Es por ello que definimos la variable a la que se va a asignar el resultado de esta
        // operación/comportamiento como un Optional de tipo Integer. Con esto podemos obtener el número de elementos del curso con el nombre más
        // largo.

        //Otra operación que podemos realizar es agregar un énfasis al nombre de cada curso en el Stream coursesStream:
        //Stream<String> emphasisCourses = coursesStream.map(course -> "¡" + course + "!"); - Vamos a convertir los Strings en el Stream
        // 'coursesStream' en otros Strings diferentes que compondrán al nuevo Stream 'emphasisCourses' (map nos va a devolver un nuevo Stream como
        // resultado de ejecutar la función lambda para los elementos de un Stream utilizado como base o parámetro). En este caso el Stream nuevo
        // estará compuesto de datos de tipo String, que se diferencían de los valores de tipo String del Stream utilizado como base en que ahora los
        // nombres de los cursos cuentan con signos de exclamación (¡!). Para agregar el énfasis a los Strings originales es necesario generar nuevos
        // Strings, porque los Strings son inmutables. La función map() nos genera un nuevo Stream que no necesariamente debe ser del mismo tipo de
        // dato que el Stream utilizado como parámetro, esto se debe a que recibe como parámetro una Function<T, R> (recibe un parámetro tipo T y
        // puede generar un tipo R).

        //Stream<String> justJavaCourses = coursesStream.filter(course -> course.contains("Java")); - Podemos generar otro Stream nuevo, también
        // resultante del Stream 'coursesStream', que estará compuesto por los nombres de cursos que contengan el String "Java". Para realizar el
        // filtrado de datos utilizamos el método 'filter()', que recibe como parámetro un Predicate que nos servirá como la lógica para determinar
        // cuando sí y cuando no incluir un curso en el nuevo Stream. En la lambda de tipo Predicate establecida en el cuerpo del método 'filter()',
        // determinamos que se trabajará con un parámetro 'course' (Java infiere que es de tipo String) para retornar únicamente aquellos Strings que
        // contengan la palabra "Java" (para indicar este comportamiento en el valor de retorno se utilizó el nombre asignado a los parámetros de
        // entrada de la función lambda (course), seguido de la sintaxis punto y el método 'contains("String")').

        //emphasisCourses.forEach(System.out::println); - Consumimos el Stream 'emphasisCourses' (le decimos que se itere) al llamarlo con su nombre,
        //                                                seguido del método forEach() en cuyo cuerpo determinamos que queremos imprimir en pantalla
        //                                                los elementos del Stream (aplicando el operador de referencia).
        //justJavaCourses.forEach(System.out::println);

        // Es importante mencionar que muchas de las operaciones de Streams hacen transformación a un nuevo tipo de String (map(), filter(), etc.).
        // Además, cabe resaltar que únicamente se puede consumir 1 vez un Stream creado; por ejemplo, 'coursesStream' está siendo consumido en la
        // línea 42 y 43 de este código, pero tras la línea 42 ya no puede volverse a consumir para operar sobre sus elementos (no se podría hacer el
        // filtrado con filter()) y en caso de correr el programa nos aparecería una excepción (Un Stream se vuelve inútil tras haber sido consumido).
        // Cuando se realiza una operación con un Stream se genera un nuevo Stream y este último es el que debe consumirse posteriormente (el
        // original ya no puede operarse/consumirse). Para poder ejecutar nuestro programa y que no hayan excepciones, vamos a mantener una secuencia
        // de consumo dentro de los Streams:
        Stream<String> emphasisCourses = coursesStream.map(course -> "¡" + course + "!"); //Se genera el Stream 'emphasisCourses' a partir del
                                                                                          // consumo del Stream 'coursesStream'
        Stream<String> justJavaCourses = emphasisCourses.filter(course -> course.contains("Java"));//Se genera el Stream 'justJavaCourses' a partir
                                                                                                   // del consumo del Stream 'emphasisCourses'
        justJavaCourses.forEach(System.out::println);//Se consume el Stream 'justJavaCourses' de manera que se quiere imprimir sus elementos en
        // pantalla. En pantalla veremos que tras las operaciones con los Streams generados, solo hay un curso que incluye el texto "Java" y
        // aparecerá en pantalla al ejecutar el código (con sus respectivos signos de exclamación porque 'justJavaCourses' se genera a partir del
        // consumo de 'emphasisCourses').

    }// Ya no se generan nuevos Streams a partir de un solo Streams base, como anteriormente se buscaba generar 2 Stream nuevos y diferentes a
    // partir del Stream base 'coursesStream', sino que se mantiene una secuencia de consumo.

}
