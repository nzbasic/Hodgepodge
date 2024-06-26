package com.mitchej123.hodgepodge.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// https://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java
public class RomanNumerals {

    private static final TreeMap<Integer, String> map = new TreeMap<>();
    private static final Map<Integer, String> cache = new HashMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    /**
     * Returns the roman number representation of this number as a String
     */
    public static String toRoman(int number) {
        return cache.computeIfAbsent(number, RomanNumerals::toRomanUncached);
    }

    /**
     * Returns the roman number representation of this number as a String If the number is greater than the limit, it
     * returns a readable number
     */
    public static String toRomanLimited(int number, int limit) {
        if (number > limit) {
            return String.valueOf(number);
        }
        return toRoman(number);
    }

    private static String toRomanUncached(int number) {
        if (number == 0) return "0";
        if (number < 0) return "-" + toRomanUncached(Math.abs(number));
        int floor = map.floorKey(number);
        if (number == floor) {
            return map.get(number);
        }
        return map.get(floor) + toRomanUncached(number - floor);
    }
}
