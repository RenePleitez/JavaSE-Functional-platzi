package com.platzi.functional._04_functional;

import java.util.function.Consumer;
import java.util.function.Supplier;
//Clase 13 - Revisando el paquete java.util.function: Consumer y Supplier
//Con la clase CLIArguments creada, ahora creamos unas utilidades para esta en CLIArgumentsUtils
public class CLIArgumentsUtils {
    /*Primero que nada creamos una función que nos muestre el manual únicamente cuando la propiedad isHelp está presente. Entonces utilizamos las palabras clave static y void porque queremos que la función tenga un scope mayor al de esta clase y no retornará ningún valor debido a que se encarga de mostrar un contenido o no.
    * La nueva función se nombra showHelp(CLIArguments), que recibe un objeto de tipo CLIArguments como parámetro.*/
    static void showHelp(CLIArguments cliArguments){
        //Internamente definiremos un Consumer<T> que es un tipo de función específica (interfaz genérica) que trabaja sobre un tipo de dato (T), en este caso trabajaremos sobre nuestra clase CLIArguments.
        //Le llamaremos 'consumerHelper' e internamente indicamos un condicional para determinar si el objeto de tipo CLIArguments cuenta con la variable isHelp == true y así determinar si es necesario desplegar el manual o no.
        Consumer<CLIArguments> consumerHelper = cliArguments1 -> { //Hay que dar un nuevo nombre a la variable que contiene el valor del isHelp del objeto de tipo CLIArguments, diferente al utilizado para definirlo como parámetro del método showHelp().
            if(cliArguments1.isHelp()){//Si el valor de isHelp == true:
                System.out.println("El manual ha sido solicitado");//Por ahora, mostraremos el mensaje de que ha sido solicitado
            }else{
                System.out.println("El manual no ha sido solicitado");
            }
        //Recordar que se debe poner punto y coma ';' tras la definición de la función
        };
        //Para invocar a nuestro nuevo Consumer<T> recién creado, simplemente hay que llamar a nuestro 'consumerHelper', seguido de la sintaxis punto "." y accept(). En vez de apply() y test() que corresponden a los métodos utilizados en la sintaxis para ejecutar las funciones específicas Function y Predicate, respectivamente.
        //Dentro del método accept(), pasamos el objeto de tipo CLIArguments con el que estamos trabajando. Un uso práctico del Consumer<> es realizar operaciones sobre un tipo de dato, tenemos un listado de datos y vamos consumiendo/operando sobre cada dato en específico. Por ejemplo borrar archivos, recibes una lista de archivos
        // y vas borrando los archivos que vaya recibiendo el Consumer, si estos cumplen con una condición determinada.
        consumerHelper.accept(cliArguments);
    }
    /*
    * Ahora crearemos una función rápida que nos provea de objetos tipo CLIArguments, por medio de un Supplier, que es otra función específica (interfaz genérica) cuya función es generar objetos de cierto tipo. El único método en su definición es el método '.get()' (recibe un objeto de tipo T y devuelve un objeto de tipo T)*/
    static CLIArguments generateCLI(){
        //Este Supplier<T> trabajará sobre objetos de tipo CLIArguments y posteriormente creamos un generator, que no recibe ningún dato denotado por los paréntesis '()' y lo que hacemos es generar un nuevo (new) CLIArguments()
        Supplier<CLIArguments> generator = () -> new CLIArguments();
        //Retornamos el resultado del Supplier<T> 'generator'
        return generator.get();
    }
    //Las utilidades de las interfaces Supplier<T> pueden ser en la generación de configuraciónes o creación de archivos bajo demanda, donde ya no tienes que proveer una configuración/archivo completo y solo crear una forma de obtener el siguiente resultado. Eso lo veremos en el módulo de streams donde generaremos datos de manera
    //infinita a partir de un supplier.
}