package swistdaw.learn.java.world.Java8UniwersalneTechniki;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class Ch7LambdaTest {


    // Chapter 7 - How we can use java.util.function and compose function

    @Test
    public void shouldUsePredicateInterfaceWithAndAndOr() {
        Predicate<Integer> isLessZero = x -> x < 0;
        Predicate<Integer> moreThanMinusTen = x -> x > -10;

        assertTrue(isLessZero.and(moreThanMinusTen).test(-1));
        assertTrue(isLessZero.and(moreThanMinusTen).test(-9));
        assertFalse(isLessZero.and(moreThanMinusTen).test(0));
        assertFalse(isLessZero.and(moreThanMinusTen).test(-11));
    }

    @Test
    public void shouldUseComposeFutureInterfaces() {

        Function<Integer, Integer> doubleSum = (x) -> x + 2;
        Function<Integer, Integer> plusTen = (x) -> x + 10;

        assertEquals(Integer.valueOf(22), doubleSum.compose(plusTen).apply(10));
    }
}