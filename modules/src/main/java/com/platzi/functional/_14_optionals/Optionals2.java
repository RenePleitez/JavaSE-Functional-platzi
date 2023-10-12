package com.platzi.functional._14_optionals;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Optionals2 {
    public static void main(String[] args) {
        List<String> names = getNames();
        if (names != null){
            //Operar con nombres
        }

        Optional<List<String>> optionalNames = getOptionalNames();
        if (optionalNames.isPresent()){
            //Operar con nombres
        }

        //Hagámoslo más funcional
        optionalNames.ifPresent(namesValue -> namesValue.forEach(System.out::println));
        //optionalNames.flatMap()
        //optionalNames.map()

        
        Optional<String> valuablePlayer = optionalValuablePlayer();
        String valuablePlayerName = valuablePlayer.orElseGet(() -> "No player found");
    }

    static List<String> getNames (){
        List<String> list = new LinkedList<>();
        //return list;
        return Collections.emptyList();
    }

    static String mostValuablePlayer (){
        //return "";
        return null;
    }

    static Integer mostExpensiveItem(){
        return -1;
    }

    static Optional<List<String>> getOptionalNames(){
        List<String> namesList = new LinkedList<>();
        return Optional.of(namesList);
    }

    static Optional<String> optionalValuablePlayer (){
        //return Optional.ofNullable();
        try {
            return Optional.of("René");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
