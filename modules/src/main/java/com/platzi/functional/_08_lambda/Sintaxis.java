package com.platzi.functional._08_lambda;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
/* Clase 18 - Comprendiendo la sintaxis de las funciones lambda
* Las lambdas son funciones anónimas, ¿por qué querríamos utilizar lambdas?*/
public class Sintaxis {
    public static void main(String[] args) {
        //Creamos una lista a partir del método getList() en NombresUtils:
        List<String> cursos = NombresUtils.getList("Java", "Functional");
        //Trabajemos sobre la lista e imprimamos por pantalla el valor de cada elemento en ella:
        cursos.forEach(curso -> System.out.println(curso));//Para esto podemos utilizar el operador de referencia, pero buscamos ejemplificar que
        // esta función definida dentro del método forEach() es una lambda, no está almacenada en ningún lugar y si tratáramos de volver a usarla
        // no podríamos porque no está almacenada en ninguna parte del código (únicamente se usa en este fragmento de código, caso único que
        // requiere de lógica muy simple)
        BiFunction<Integer,Integer, Integer> s = (x, y) -> x * y; //La sintaxis al crear una BiFunction es muy parecida a la de las funciones lambda,
        // ¿en qué difieren?

        //Sintaxis específica de lambda de tipo ZeroArgumentos, generada para crear un elemento de tipo ZeroArgumentos (interfaz funcional creada
        // por nosotros). Esta es una lambda que no recibe argumentos/parámetros porque el único método dentro de la interfaz funcional no recibe
        // ninguno tampoco.
        usarZero(() -> 2);//Como el valor de retorno es directo (solo hay una acción definida tras la flecha ->, en este caso asignar un valor de 2),
        // no necesitamos poner la palabra return porque Java infiere que el valor del comportamiento / acción definida tras la flecha -> es el
        // valor que se va a retornar.
        //La función lambda anterior no recibe ningún parámetro y su valor de retorno es de tipo int (como el método get() dentro de ZeroArgumentos,
        // por eso puede reemplazarlo al crear un elemento de este tipo).

        usarPredicado(text -> text.isEmpty());//Creando lambda de tipo Predicate directamente sin tener que definir el tipo de dato que recibe o el
        // tipo de dato que retorna. Es una lambda que ya está definida por un tipo de dato y función (Predicate); por ello, se utiliza también el
        // método isEmpty() que retorna un valor booleano (los Predicates<T> devuelven un booleano).

        usarBiFunction((x, y) -> x * y);//Creando lambda de tipo BiFunction, por medio de la sintaxis adecuada al tener más de un parámetro
        // (primero pasamos los dos primeros parámetros entre paréntesis, seguido de nuestra flecha y directamente retornar un valor dado por la
        // multiplicación x * y)

        //En caso de que necesite hacer más cosas dentro de mi función:
        usarBiFunction((x, y) -> {//Añadimos llaves o braces con los que podemos generar un cuerpo o body más grande
            System.out.println("X: " + x + ", Y: " + y);//1. El primer valor resultado es la impresión de los dos parámetros del BiFunction, con
                                                        // los que se obtendrá un resultado
            return (x - y);//2. El segundo valor resultado es la operación de resta de los parámetros indicados en la lambda e indicamos
                            // explícitamente que queremos retornar el valor resultado de esta operación
        });
        //Puede haber casos en los que tengamos una función que no tenga ningún tipo de parámetro o retorno
        usarNada(() -> {//No tenemos parámetros en la lambda '()'
            System.out.println("Hola a todos desde clase Sintaxis");//La función 'println' recibe un parámetro pero no retorna nada (void)
            //No tengo que retornar ningún valor (void), solo se imprime un mensaje en la consola
        });
        //En este caso la función lambda anterior no recibe ningún parámetro '()' y no cuenta con valor de retorno (void)(como el método nada()
        // dentro de OperarNada, por eso puede reemplazarlo al crear un elemento de este tipo).

        //Si quieres hacer tu código más legible utilices los tipos de datos explícitamente, no es común en el día a día o encontrarlo en proyectos,
        // pero es una posibilidad. En muchas ocasiones lo vamos a omitir porque muchas veces el código habla por sí mismo, pero en otras ocasiones
        // es necesario entender cuál es el tipo de dato con el que se está operando:
        usarBiFunction((Integer x, Integer y) -> x * y);
        //Puede ser que en algunos casos estemos trabajando sobre una clase que es subclase (hereda) de otra superclase y esto lo encontraremos
        // mucho al operar con listas. Al operar con listas y utilizar el método forEach, el IDE automáticamente nos sugiere utilizar un Consumer<T>
        // de un tipo de dato que extienda de String. Va a pasar mucho que podamos generar funciones que trabajen con subtipos, siempre y cuando
        // nosotros tengamos una lambda definida para el tipo tal cual.
    }
    //Los métodos estáticos planteados en esta sección (tanto para crear elementos a partir de interfaces funcionales ya existentes (Predictae,
    // BiFunction) o propias(ZeroArgumentos, OperarNada)) nos sirven para poder pasarles funciones anónimas como parámetros, que actúen como
    // elementos de las diferentes interfaces funcionales ya existentes o propias. Son HOF que toman funciones anónimas y operan con ellas:

    //En el caso de los BiFunction, podemos crear un método llamado usarBiFunction() que tome un BiFunction<T,U,V> como parámetro (en este caso
    // con todos los parámetros Integer). Esto, porque podemos pasar una lambda que actúe como un elemento de tipo BiFunction<T,U,V> a este método
    // y así crearlo a partir de la función anónima.
    static void usarBiFunction (BiFunction<Integer, Integer, Integer> operacion){}

    //En el caso de los Predicates, podemos crear un método llamado usarPredicado() que tome un Predicate<T> como parámetro (en este caso de tipo
    // String). Esto, porque podemos pasar una lambda que actúe como un elemento de tipo Predicate<T> a este método y así crearlo a partir de la
    // función anónima.
    static void usarPredicado (Predicate<String> predicado){}

    //Generamos un método estático para poder crear un elemento de tipo OperarNada. Podemos pasar una lambda que actúe como un elemento de tipo
    // OperarNada a este método y así crearlo a partir de la función anónima.
    static void usarNada (OperarNada operarNada){}

    //Generamos un método estático para poder crear un elemento de tipo ZeroArgumentos. Podemos pasar una lambda que actúe como un elemento de
    // tipo ZeroArgumentos a este método y así crearlo a partir de la función anónima.
    static void usarZero (ZeroArgumentos zeroArgumentos){
    }
    //Interfaz funcional propia denominada ZeroArgumentos porque el método que contiene no recibe ningún argumento.
    @FunctionalInterface
    interface ZeroArgumentos {
        int get(); //Metodo en el cuerpo de la interfaz, no recibe ningún parámetro y su valor de retorno es de tipo int
    }
    //Interfaz funcional propia denominada OperarNada porque el método que contiene no recibe ningún argumento ni valor de retorno.
    @FunctionalInterface
    interface OperarNada {
        void nada();//No recibe ningún parámetro y no genera ningún resultado (void).
    }
}
