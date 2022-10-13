package com.gmail.at.kotamadeo.util;

import java.math.BigInteger;
import java.util.stream.Stream;

public class FibonacciSequenceGenerator {
    private FibonacciSequenceGenerator() {
    }

    public static BigInteger getNValue(int n) {
        return Stream.iterate(new BigInteger[]{BigInteger.ZERO, BigInteger.ONE},
                        t -> new BigInteger[]{t[1], t[0].add(t[1])})
                .limit(n)
                .map(s -> s[1])
                .reduce((a, b) -> b).orElse(BigInteger.ZERO);
    }
}
