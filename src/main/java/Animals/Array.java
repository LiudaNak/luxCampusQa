package Animals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Array {
    static int counter = 0;

    public static void method() {


        List<String> stringList = Arrays.asList("a", "b", "c");
        Stream<String> stringStream = stringList.stream().filter(e -> e.equals("b"));
        long count = stringStream.count();
        // filter - is a Predicate stream
        //термінальна операція вже відбулась, тому інша термінальна не може бути тут
        //Optional<String> first = stringStream.findFirst();
        System.out.println(" " + count);


        stringList.stream().filter(e -> {
            wasCalled();
            return e.contains("2");
        }).map(e -> {
            System.out.println("in map");
            return e.toUpperCase();
        }).findFirst();

        long count1 = stringList.stream().map(e -> {
            wasCalled();
            return e.toLowerCase();
        }).skip(2).count();
        System.out.println("was called: " + counter);
        System.out.println("count1: " + count1);

        stringList.stream().forEach(e -> System.out.println(e));


        //способи виклику стримів
        //можна з файла, з рядка
        Stream.of("a", "b", "c");

        String[] array = {"a", "b"};
        Stream<String> stream = Arrays.stream(array);

        IntStream chars = "abc".chars();

        IntStream
                .builder();

        //private static void test(){

        // }

    }
        //.findFirst.orElse("вписуємо щось, що виведе, якщо не буде там результату")

        private static void wasCalled() {
            counter++;
        }

}
