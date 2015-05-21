import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        basic();
        lazy();
        filterAndPrint();
        mapAndFilter();
        filterAndMap();
        sortFilterAndMap();
        reuse();
        correctReuse();
        toList();
        toMap();
        intMap();
    }

    private static void basic() {

        List<String> myList =
                Arrays.asList("apple", "banana", "beet", "carrot", "cherry");

        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    private static void lazy() {
        Stream.of("clementine", "apple", "date", "fig", "grape")
                .filter(fruit -> {
                    System.out.println("filter: " + fruit);
                    return true;
                });
    }

    private static void filterAndPrint() {
        Stream.of("clementine", "apple", "date", "fig", "grape")
                .filter(fruit -> {
                    System.out.println("filter: " + fruit);
                    return true;
                })
                .forEach(fruit -> System.out.println("forEach: " + fruit));
    }

    private static void mapAndFilter() {
        Stream.of("clementine", "apple", "date", "fig", "grape")
                .map(fruit -> {
                    System.out.println("map: " + fruit);
                    return fruit.toUpperCase();
                })
                .filter(fruit -> {
                    System.out.println("filter: " + fruit);
                    return fruit.startsWith("A");
                })
                .forEach(fruit -> System.out.println("forEach: " + fruit));
    }

    private static void filterAndMap() {
        Stream.of("clementine", "apple", "date", "fig", "grape")
                .filter(fruit -> {
                    System.out.println("filter: " + fruit);
                    return fruit.startsWith("a");
                })
                .map(fruit -> {
                    System.out.println("map: " + fruit);
                    return fruit.toUpperCase();
                })
                .forEach(fruit -> System.out.println("forEach: " + fruit));
    }

    private static void sortFilterAndMap() {
        Stream.of("date", "apple")
                .sorted((fruit1, fruit2) -> {
                    System.out.printf("sort: %s; %s\n", fruit1, fruit2);
                    return fruit1.compareTo(fruit2);
                })
                .filter(fruit -> {
                    System.out.println("filter: " + fruit);
                    return fruit.startsWith("a");
                })
                .map(fruit -> {
                    System.out.println("map: " + fruit);
                    return fruit.toUpperCase();
                })
                .forEach(fruit -> System.out.println("forEach: " + fruit));
    }

    private static void reuse() {
        Stream<String> stream =
                Stream.of("clementine", "apple", "date", "fig", "grape")
                        .filter(fruit -> fruit.startsWith("a"));

        System.out.println(stream.anyMatch(fruit -> true));
        System.out.println(stream.noneMatch(fruit -> true));
    }

    private static void correctReuse() {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("clementine", "apple", "date", "fig", "grape")
                .filter(fruit -> fruit.startsWith("a"));

        System.out.println(streamSupplier.get().anyMatch(fruit -> true));
        System.out.println(streamSupplier.get().noneMatch(fruit -> true));
    }

    private static void toList() {
        List<String> fruits = Stream.of("clementine", "apple", "date", "fig", "grape")
                .filter(fruit -> fruit.contains("e"))
                .collect(Collectors.toList());
        System.out.println(fruits);
    }

    private static void toMap() {
        Map<String, Integer> map =
                Stream.of("clementine", "apple", "date", "fig", "grape")
                .collect(Collectors.toMap(
                        fruit -> fruit,
                        String::length
                ));
        System.out.println(map);
    }

    private static void intMap() {
        Stream.of("clementine", "apple", "date", "fig", "grape")
                .map(String::length)
        .forEach(System.out::println);
        Stream.of("clementine", "apple", "date", "fig", "grape")
                .mapToInt(String::length)
                .forEach(System.out::println);
    }
}
