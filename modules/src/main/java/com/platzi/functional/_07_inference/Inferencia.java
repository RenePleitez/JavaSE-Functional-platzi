package com.platzi.functional._07_inference;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.function.Function;
/* Clase 17 - Analizando la inferencia de tipos
* Hasta este punto hemos visto que nuestras funciones trabajan sobre un tipo de dato (T) y devuelven un resultado de otro tipo (T), o hemos creado
* funciones que trabajan sobre diferentes tipos. Pero al momento de definir las funciones no estamos definiendo explícitamente los tipos ¿qué hace
* Java por dentro?*/
public class Inferencia {
    public static void main(String[] args) {
        //Definimos una función que trabaja sobre un Integer y genera un String
        Function<Integer, String> funcionConvertidora = integer -> "Al doble" + (integer * 2);
                                                        //Java sabe que el dato 'integer' es un tipo de dato Integer
        //La inferencia de tipos hace referencia a que en el tiempo de compilación, Java se encarga de validar que los datos que están pasando a
        // través de nuestra función, sean del tipo que corresponde. Java adivina (basándose en la definición de la función) el tipo de dato que
        // entra a la función como el que se genera.
        //Invocamos o llamamos al método getList() de la clase NombresUtils para crear una lista: (en este caso de Strings)
        List<String> alumnos = NombresUtils.getList("Juan", "Hugo", "Paco");
        //Cuando llamamos al método forEach() no tenemos que definir un tipo, como se hace a continuación:
        alumnos.forEach((String name) -> System.out.println(name));
        //Gracias a la inferencia de tipos no es necesario escribir el tipo de dato de los elementos que se estarán iterando en el ciclo
                       // ya no es necesario especificar (String name) y se puede poner solo name
        alumnos.forEach(name -> System.out.println(name));
                      //String -> void
        //Más interesante aún, al utilizar el operador de referencia visto anteriormente estamos invocando a una función que Java ya sabe de qué tipo
        // es. Con esto cerramos la clase anterior, entender que para poder utilizar la referencia de un método necesitamos que este reciba el mismo
        // tipo de dato como parámetro y genere el mismo tipo de dato que la función que puede reemplazar. Java en tiempo de compilación va a inferir
        // estos datos de manera que no tengamos que ponerlos explícitamente como el la línea 18. Ya no tenemos que definir el tipo de dato estríctamente
        // al trabajar con funciones, java se va a encargar en el tiempo de compilación de corroborar que los tipos de datos correspondan, así no
        // tenderemos el problema de que al mandar la petición de un archivo recibamos una conexión a internet.
        alumnos.forEach(System.out::println);
                        //String :: void
    }
}