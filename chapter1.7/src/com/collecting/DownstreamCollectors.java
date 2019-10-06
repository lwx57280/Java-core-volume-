package com.collecting;

import com.domain.City;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;



public class DownstreamCollectors {


    public static void main(String[] args) throws IOException {

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(groupingBy(Locale::getCountry, toSet()));
        System.out.println("countyToLocaleSet: " + countryToLocaleSet);
        // 返回所有已安装区域设置的数组。
        locales = Stream.of(Locale.getAvailableLocales());
        // 返回 Collector类型的接受元件 T计数输入元件的数量。
        Map<String, Long> countryToLocaleCounts = locales.collect(groupingBy(Locale::getCountry, counting()));
        System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);

        Stream<City> cities = readCities("alice.txt");
        Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(City::getState, summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation: " + stateToCityPopulation);

        cities = readCities("alice.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(groupingBy(City::getState,
                mapping(City::getName, maxBy(Comparator.comparing(String::length)))));
        System.out.println("stateToLongestCityName: " + stateToLongestCityName);
    }

    public static Stream<City> readCities(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName)).map(l -> l.split(","))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }
}
