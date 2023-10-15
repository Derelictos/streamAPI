package streamAPI;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Data
@Builder
public class Person {
    private String name, address;
    private int age;

    public static void main(String[] args) {
        //By using Faker and Lombok ann builder creating some info about person
        Faker faker = new Faker();
        Stream<Person> peopleStream = Stream.generate(() ->
                Person.builder()
                        .name(faker.name().fullName())
                        .age(faker.random().nextInt(7, 80))
                        .address(faker.address().fullAddress())
                        .build()
        ).limit(5);
        //Use stream of Integer
        IntStream.of(10, 20, 30, 50, 20, 40, 50, 10, 4, 10, 0, 10, 50, 10, 111, 23123, 123, 34, 12)
                .filter(x -> x < 60)
                .map(x -> x + 10)
                .limit(4)
                .forEach(System.out::println);

        String[] array = {"WE", "LOVE", "streamAPI"};
        Stream<String> streamOfArray = Arrays.stream(array);
        streamOfArray
                //Converting a word to an array of letters
                .map(s -> s.split(""))
                //flattens each generated stream into one stream
                .flatMap(Arrays::stream).distinct()
                //convertation to list
                .toList()
                //output with forEach
                .forEach(System.out::println);

        Stream.of(9, 1, 3, 5, 6, 1, 2, 4, 5, 6, 1, 23, 4, 5, 6, 2, 312, 4, 4, 435, 213, 5, 23, 3, 4)
                .flatMapToInt(x -> IntStream.range(0, x))
                .forEach(System.out::print);


        System.out.println("\nRandom person:");
        peopleStream.forEach(person -> {
            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());
            System.out.println("Address: " + person.getAddress());
            System.out.println();
        });
    }
}

