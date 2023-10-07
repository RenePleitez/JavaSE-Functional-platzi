package com.platzi.functional._10_chaining;

import com.platzi.functional._10_chaining.builder.BuilderNoChaining;
import com.platzi.functional._10_chaining.builder.BuilderWithChaining;
import com.platzi.functional._10_chaining.data.Account;

import java.util.function.Function;
//Clase 20 - Dándole nombre a un viejo amigo: Chaining
public class Chaining {

    /**
     * Aunque el chaining no es algo exclusivo o específicamente de la programación funcional,
     * vale la pena mencionarlo, puesto que puede ser de ayuda al usar funciones de java o
     * en temas futuros…
     * <p>
     * Chaining es la estrategia de retornar siempre un objeto, tal que puedas invocar
     * métodos con cada invocación.
     *
     * Chaining es encadenar el resultado de una ejecución con respecto a otra ejecución
     */
    static void examplesOfChaining() {
        //Cuando no usas chaining tu codigo se ve aburrido y repetitivo
        BuilderNoChaining builderNoChaining = new BuilderNoChaining("");
        builderNoChaining.withBalance(100.00);
        builderNoChaining.withFirstName("Sinuhe");
        builderNoChaining.withLastName("Jaime");
        builderNoChaining.withPhone("+52155555555");

        Account sinuheAccount = builderNoChaining.buildAccount();

        //Pero con un buen chaining puedes hacer las cosas más simples.
        Account anotherAccount =
                new BuilderWithChaining("1")
                        .withBalance(100.00)
                        .withFirstName("Sinuhe")
                        .withLastName("Jaime")
                        .withPhone("+52155555555")
                        .buildAccount();

        //Es relativamente comun verlo en Strings:
        char[] saludo = "Bye Platzi!".replaceAll("Bye", "Hola")
                .toLowerCase()
                .toCharArray();

        //O como en el primer ejemplo, con builders. StringBuilder es una clase con la que podemos crear un String:
        //Crearlo es tan sencillo como:
        StringBuilder builderJava = new StringBuilder();
        //Sobre el mismo elemento StringBuilder podemos empezar a operar y añadir texto
        builderJava.append("E") //En este caso podríamos haber omitido el punto y coma ';' tras instanciar el elemento StringBuilder, para llamar
                                // directamente al método append() sin necesidad de escribir primero el nombre del elemento creado (builderJava)
        //Después podemos mandar llamar directamente el método append()
                .append("s")
                .append("t")
                .append("o")
                .append("y")
                .append(" ")
                .append("A")
                .append("p")
                .append("r")
                .append("e")
                .append("n")
                .append("d")
                .append("i")
                .append("e")
                .append("n")
                .append("d")
                .append("o")
                .append(" Java ")
        //Con esto estamos haciendo un encadenamiento de llamadas. Al terminar el encadenamiento (chaining) hay que agregar punto y coma ';' al
        // final del último append().
                .append(8);

        System.out.println(builderJava.toString());


        //La relevancia en este curso, de esta estrategia, viene al crear funciones más complejas
        //o que se crean a partir de otras funciones. Posteriormente, veremos que es de mucha utilidad
        //con ciertos tipos de datos. Veremos que la ejecución de una función nos devuelve un resultado
        // y ese resultado lo utilizamos para pasarlo a otra nueva función.

        //Por ejemplo, creamos una funcion que reemplace las "f" por asteriscos
        Function<String, String> replacer = s -> s.replaceAll("f", "*");

        
        Function<Function<String, String>, Function<String, String>> replacerPlus =
                f -> f.andThen(s -> s.replaceAll("a", "/"));

        System.out.println(
                replacerPlus
                        .apply(replacer)
                        .andThen(s -> s.replaceFirst("Ho", "Mo"))
                        .apply("Hablando de chaining, esto Hola, fucho")
        );
    }

    public static void main(String[] args) {
        //Chaining a partir de un stringBuilder: (Alternativas)
        //'String nombre_del_elemento =' en vez de 'StringBuilder nombre_del_elemento = new StringBuilder();'
        String stringBuilder = "Hola. " + //Concatenación en vez de utilizar el método .append()
                "Este " +
                "mensaje " +
                "está " +
                "encadenado (chaining).";//Punto y coma tras el último texto concatenado
        //Imprimimos el mensaje encadenado
        System.out.println(stringBuilder);

        //Instanciamos un objeto de tipo Chainer
        Chainer chainer = new Chainer();
        //Chaining de métodos para decir hola y adiós de un tiro
        chainer.sayHi()
                .sayBye(); //También podría escribirse con la estructura => 'chainer.sayHi().sayBye();'
        //A esto se le llama "chaining" y nos da la ventaja o beneficio de no tener que almacenar los resultados
        //Si quisieramos almacenar los resultados es totalmente válido:
        //Podemos generar una instancia chainer2 que contenga el resultado de invocar a la instancia creada chainer con sayHi()
        Chainer chainer2 = chainer.sayHi();
        //Podemos generar un chainer3 que contenga el resultado de invocar a la instancia creada chainer (o chainer2) con sayBye()
        Chainer chainer3 = chainer2.sayBye();
        System.out.println(chainer2 + "from chainer2 = chainer.sayHi()");
        System.out.println(chainer3 + "from chainer3 = chainer.sayBye()");
        //Esto tendrá mucho sentido en temas posteriores cuando empecemos a hacer encadenamiento o chaining de operaciones sobre un solo tipo de
        // dato y le vayamos diciendo al programa que haga un filtrado o una conversión de datos, pudiendo ir agregando funciones sin necesidad de
        // almacenar los resultados en diferentes variables.
    }
    //Hagamos una clase que haga "chaining", que lo único que va a hacer es devolver instancias de la misma con diferentes características:
    static class Chainer{
        //Instancia de Chainer que internamente imprime el mensaje "Hola"
        public Chainer sayHi(){
            System.out.println("Hello");
            return this; //Se utiliza "this" porque al tratar de retornar la impresión del mensaje como tal, el IDE nos indica que en la definición
            // del método estamos especificando que el valor de retorno es un elemento de tipo Chainer (no void).
            //La palabra "this" hace que el método retorne esta misma instancia.
        }
        //Instancia de Chainer que internamente imprime el mensaje "Bye"
        public Chainer sayBye(){
            System.out.println("Bye");
            return this;//Se utiliza "this" porque al tratar de retornar la impresión del mensaje como tal, el IDE nos indica que en la definición
            // del método estamos especificando que el valor de retorno es un elemento de tipo Chainer (no void).
            //La palabra "this" hace que el método retorne esta misma instancia.
        }
    }
}
