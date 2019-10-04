package collection;

import domain.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class CollectinigIntoMaps {

    public static void main(String[] args) {

        Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println("idToName: " + idToName);

        Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));

        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity(), (existingValue, newValue) -> {
            throw new IllegalStateException();
        }, TreeMap::new));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(Collectors.toMap(
                Locale::getDisplayLanguage, l -> l.getDisplayLanguage(l),
                (existingValue, newValue) -> existingValue));
        System.out.println("languageNames: " + languageNames);

        locales = Stream.of(Locale.getAvailableLocales());
//        Map<String, Set<String>> countryLanguageSets = locales.collect(Collectors.toMap(
//                Locale::getDisplayCountry,
//                l -> Collections.singleton(l.getDisplayLanguage()),
//                (a, b) -> {
//                    Set<String> union = new HashSet<>(a);
//                    union.addAll(b);
//                    return union;
//                }
//        ));
//        System.out.println("countryLanguageSets: " + countryLanguageSets);
        Map<String, Long> countryToLocaleCounts = locales.collect(groupingBy(Locale::getCountry, counting()));
        System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);
//        Map<String, Set<Locale>> collect = locales.collect(groupingBy(Locale::getCountry,toSet()));
//
//        System.out.println("collect: " + collect);

    }

    public static Stream<Person> people() {
        return Stream.of(new Person(1000, "Mary"), new Person(1002, "Paul"));
    }
}
