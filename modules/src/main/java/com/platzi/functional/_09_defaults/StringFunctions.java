package com.platzi.functional._09_defaults;
//Clase 19 - Usando métodos default en nuestras interfaces (@FunctionalInterface)
/* En Java, a partir de la version JDK 8 LTS, es posible agregar métodos default a nuestras interfaces propias (para tener nuestro propio estilo de
* funciones). Para poder hacer interfaces funcionales definidas por nosotros, debemos tener un solo método sin definición en el cuerpo de la misma;
* pero, podemos agregar métodos que tengan una definición de como comportarse.*/
public class StringFunctions {
    //Creamos una nueva interfaz funcional, llamada StringOperation, donde definimos un método que no recibe parámetros y retorna un número entero int
    @FunctionalInterface
    interface StringOperation {
        int getAmount(); //Método abstracto sin definición
        default void operate(String text){ //Método default con una definición (default nos permite definir un cuerpo para un método dentro de una
            // interfaz funcional). Este método recibe un parámetro de tipo String y no retorna ningún valor
            int x = getAmount(); //Obtenemos una variable x de tipo int, a partir del método abstracto de la interfaz funcional
            while(x-- > 0){//Ciclo While donde se indica que el ciclo se ejecutará hasta que x sea menor que 0, restando 1 unidad al valor de la
                // variable por cada iteración
                System.out.println(text);//Imprimiremos el texto determinado como parámetro del método default, cada vez que se ejecute una iteración
                // del ciclo
            }
        }
    }

    public static void main(String[] args) {
        //Para utilizar nuestra interfaz funcional StringOperation, creamos un elemento de este tipo (hacemos una función de esta interfaz):
        StringOperation six = () -> 6;//La función lambda para crear el elemento imita el comportamiento del método abstracto definido en la interfaz,
                                      // no recibe parámetros y se retorna un valor numérico entero int (se asigna un valor int de retorno)
        six.operate("Alumno");//Podemos invocar la nueva función creada 'six' que ya cuenta con un valor int de retorno (6) establecido para el
                                    // método abstracto getAmount() y llamar al método default en la interfaz por medio de la sintaxis punto "."
                                    // Añadimos el parámetro tipo String requerido por el método default.
        //En este caso se imprime 6 veces el String "Alumno", debido a que se determinó que este sería el entero (int) que devuelve el primer método de
        // la interfaz funcional creada y se utiliza en el ciclo While del cuerpo del segundo método (default), de manera que se le resta 1 unidad a
        // dicho valor por cada iteración y este durará mientras el int sea > (mayor que) 0. (Si el int es = 6, el ciclo se ejecutará por 6 iteraciones
        // hasta que dicho valor sea igual (y no mayor que) 0)

        //Para utilizar nuestra interfaz funcional DoOperation, creamos un elemento de este tipo (hacemos una función de esta interfaz):
        DoOperation operateFive = System.out::println; //Uso de operador de referencia en vez de función anónima lambda
                // 'operateFive = text -> System.out.println(text)', para simular el comportamiento del método abstracto en la interfaz funcional
                // (el método println recibe un parámetro de tipo string (en este caso los outputs) y no retorna nada (void))
        operateFive.execute(5, "Platzi");//Podemos invocar la función creada 'operateFive' que ya cuenta con un comportamiento definido para
                                                // su método abstracto (imprimir el parámetro que recibe de tipo String en la terminal) y llamar al
                                                // método default en la interfaz por medio de la sintaxis punto "."
                                                //Añadimos los parámetros de tipo int y String, requeridos por el método default
        //En este caso se imprime 5 veces el String "Platzi", debido a que se determinó que este sería el entero (int) que se utiliza en el ciclo
        // While dentro del cuerpo del segundo método (default), de manera que se le resta 1 unidad a dicho valor por cada iteración y este durará
        // mientras el int sea > (mayor que) 0. (Si el int es = 5, el ciclo se ejecutará por 5 iteraciones hasta que dicho valor sea igual (y no mayor
        // que) 0).
        //Por otro lado, "Platzi" es el parámetro tipo String que recibe el método abstracto cada vez que este es llamado en una iteración del ciclo
        // While dentro del cuerpo del método default.
        //Esta segunda interfaz funcional nos permite imprimir el texto que queramos, las veces que lo indiquemos.

        //La particularidad de agregar métodos abstractos en nuestras interfaces funcionales (funciones propias) es que podemos tener una definición a
        // través de la cual yo utilice mi método abstracto, esto me da la versatilidad de poder crear una interfaz que haga querys a una base de
        // datos y simplemente tener un método default que haga la conexión (la parte que me tocaría implementar sería estrictamente la funcionalidad
        // de lo que se va a hacer con la conexión abierta). O hacer una comunicación web, en la que no tendríamos que saber directamente qué método
        // web se utiliza; sino, simplemente definir los parámetros que se van a pasar una vez que se invoque al método.
    }
    //Hagamos esto más interesante, creando la interfaz funcional DoOperation, que nos permitirá escoger tanto la cantidad de veces que queremos que
    // se imprima un mensaje en la terminal, como el contenido del mensaje
    @FunctionalInterface
    interface DoOperation{
        void take(String text);//Método abstracto sin definición. Recibe un parámetro de tipo String y no retorna nada
        default void execute(int x, String text){ //Método default que recibe dos parámetros, uno de tipo int y otro de tipo String, y no retorna
            // ningún valor
            while(x-- > 0){// Ciclo While donde se indica que el ciclo se ejecutará hasta que x sea menor que 0, restando 1 unidad al valor de la
                           // variable por cada iteración
                take(text);//Llamaremos al método abstracto definido en la interfaz funcional en cada iteración del ciclo
            }
        }
    }
}
