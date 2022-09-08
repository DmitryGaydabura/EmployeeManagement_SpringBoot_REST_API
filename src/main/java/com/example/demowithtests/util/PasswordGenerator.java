package com.example.demowithtests.util;

import java.util.stream.IntStream;

public class PasswordGenerator {
    public static String generate() {
        StringBuilder p = new StringBuilder();
        String[] a = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "g", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int j = 0; j < 3; j++) {
            IntStream.range(0, 8).map(i -> (int) (Math.random() * 36)).forEach(r1 -> {
                int r2 = (int) (Math.random() * 3);
                p.append(r2 < 1.5 ? a[r1] : a[r1].toUpperCase());
            });
            if (j != 2) {
                p.append("-");
            }
        }
        return p.toString();
    }
}
