package StreamAPI;
//Lambda , Stream

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // older way
//        Walkable obj = new WalkFast();
//        obj.walk(15);

//        Walkable obj = new Walkable() {
//            @Override
//            public int walk(int steps) {
//                return 0;
//            }
//        };


        // Lambda Expression
        /*
        Walkable obj = (steps,isValid) -> {
            System.out.println("Waling fast "+steps+" steps !");
            return 2*steps;
        };

        Walkable obj2 = (steps,isValid) -> steps*3;

        obj.walk(11,true);
        System.out.println(obj2.walk(10,false));
        */

        // Stream
        List<String>fruits = List.of("apple","orange","kiwi","banana","orange");

        Set<Integer> fruitSet = fruits
                .stream()
                .map(fruit -> fruit.length())
                .collect(Collectors.toSet());

        System.out.println(fruitSet);

/*
        Stream<String> strm = fruits.stream();
        strm
                .filter(fruit -> fruit.length()<5)
                .sorted()
                .map(fruit -> fruit.length())
                .map(fruitLength -> 2*fruitLength)
                .forEach((fruit)->System.out.println(fruit));
*/



    }
}



@FunctionalInterface //contains only 1 method
interface Walkable{
    int walk(int steps , boolean isValid);
}



//class WalkFast implements Walkable{
//    @Override
//    public int walk(int steps) {
//        System.out.println("Waling fast "+steps+" steps !");
//        return 2*steps;
//    }
//}