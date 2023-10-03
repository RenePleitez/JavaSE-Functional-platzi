package com.platzi.functional._04_functional;

public class CLIArguments {
    //Clase 13 - Revisando el paquete java.util.function: Consumer y Supplier
    //Adicional a lasa funciones y predicados que tenemos en Java Functional, también tenemos otros dos elementos de tipo interfaz que están diseñados para consumir o proveer de datos, 'Consumer<>' y 'Supplier<>' respectivamente. Veamos como podemos consumir un objeto o proveer uno.
    // Esta clase CLIArguments lo único que hace es contener los elementos que se le pasarán por terminal a nuestro programa. Vamos a agregar primero algo muy común en una terminal, que es pedir el manual de usuario.
    //En este caso creamos un atributo isHelp, que es un booleano que cuando esté presente lanzaremos el manual de la terminal o si no está presente continuaremos operando normalmente.
    private boolean isHelp;
    //Getter de la variable creada, con sintaxis generada automáticamente por el IDE.
    public boolean isHelp() {
        return isHelp;
    }
}
