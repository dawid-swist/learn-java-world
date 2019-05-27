package swistdaw.learn.java.world.Java8UniwersalneTechniki;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class Ch8SimpleToolTest {

    // Testing StringTokenizer //

    private String[] tokenizeString(String someString, String delim) {
        StringTokenizer st = new StringTokenizer(someString, delim);

        String[] res = new String[st.countTokens()];

        for (int i = 0; st.hasMoreElements(); i++) {
            res[i] = st.nextToken();
        }

        return res;
    }

    @Test
    public void shouldUseStringTokenizerToSplitString() {
        String someString = "1,2,3,4";
        String delim = ",";

        assertArrayEquals(new String[]{"1", "2", "3", "4"}, tokenizeString(someString, delim));
        assertArrayEquals(new String[]{"1", "2", "3", "4"}, tokenizeString("1,,2,,3,,4", delim)); //Two characters ',,'
    }

    @Test
    public void shouldUsePatternClassAsPredicate() {

        Pattern pattern = Pattern.compile("\\w+");

        assertTrue(pattern.asPredicate().test("Word"));
        assertFalse(pattern.asPredicate().test("  -"));
    }


    @Test
    public void shouldFindValueByOnLabelInString() {
        String text = "FNAME: Dawid LNAME: Swist";
        String delim = "(FNAME:) | (LNAME:)";
        ArrayList<String> ret = new ArrayList<>();

        Scanner scanner = new Scanner(text).useDelimiter(delim);
        scanner.forEachRemaining(x -> ret.add(x.trim()));

        assertArrayEquals(new String[]{"Dawid", "Swist"}, ret.toArray());
    }


    // new Data in java 8
    @Test
    public void shouldReturnNewLocalDate() {
        LocalDate localDate = LocalDate.of(2019, Month.MARCH, 30);

        assertEquals(2019, localDate.getYear());
        assertEquals(Month.MARCH, localDate.getMonth());
        assertEquals(30, localDate.getDayOfMonth());
    }

    @Test
    public void shouldPlus3DaysReturnInLocalDate() {
        LocalDate localDate = LocalDate.of(2019, Month.MARCH, 30);

        localDate = localDate.plusDays(1);
        assertEquals(31, localDate.getDayOfMonth());
    }

    @Test
    public void shouldMeasureDataDuration(){

        LocalDate initialDate = LocalDate.parse("2007-05-10");
        LocalDate finalDate = initialDate.plus(Period.ofDays(10));

        Period period = Period.between(initialDate,finalDate);

        assertEquals(10 ,period.getDays());
    }
}