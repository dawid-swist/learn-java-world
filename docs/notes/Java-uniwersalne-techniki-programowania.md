# Notatki do książki "Java - uniwersalne techniki programowania". Wersja PL.

## Rozdział 7 Lambda. 

[Link do przykładów](../../src/test/java/swistdaw/learn/java/world/Java8UniwersalneTechniki/Ch7LambdaTest.java)

- Można zastosować następujące domyślne Functional interfaces. [Link](https://docs.oracle.com/javase/8/docs/api/?java/util/function/package-summary.html)
- Niektóre z nich można je komponować. Przykłady

Predicate:
```java
        Predicate<Integer> isLessZero = x -> x < 0;
        Predicate<Integer> moreThanMinusTen = x -> x > -10;

        assertTrue(isLessZero.and(moreThanMinusTen).test(-1));
        assertFalse(isLessZero.and(moreThanMinusTen).test(-11));
```

Functional:
```java
        Function<Integer, Integer> doubleSum = (x) -> x + 2;
        Function<Integer, Integer> plusTen = (x) -> x + 10;

        assertEquals(Integer.valueOf(22), doubleSum.compose(plusTen).apply(10));

```
 