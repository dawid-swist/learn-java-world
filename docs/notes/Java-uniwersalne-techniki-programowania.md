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
 
 
 ## Rozdział 8. Różne narzędzia java.util
 
 ### Użycie StringTokenizer do rozdzielania kody.
 
 StringTokenier działą tylko jako typowy 'rozdzielacz' usuwa nadmiarowe znaki. Patrz ostatni test
 
 ```java
    private String[] tokenizeString(String someString, String delim) {
        StringTokenizer st = new StringTokenizer(someString, delim);

        String[] res = new String[st.countTokens()];

        for (int i = 0; st.hasMoreElements(); i++) {
            res[i] = st.nextToken();
        }

        return res;
    }

  String someString = "1,2,3,4";
  String delim = ",";
  
  assertArrayEquals(new String[]{"1", "2", "3", "4"}, tokenizeString(someString, delim));
  assertArrayEquals(new String[]{"1", "2", "3", "4"}, tokenizeString("1,,2,,3,,4", delim)); //Two characters ',,'
```

### Użycie Patter jako predicate

Można zamienić obiekt wyrażenia regularnego na Predicate interface.
```java
        Pattern pattern = Pattern.compile("\\w+");

        assertTrue(pattern.asPredicate().test("Word"));
        assertFalse(pattern.asPredicate().test("  -"));
```

### Użycie Scanner i wyrażeń regularnych do wyszukiwania na podstawie 'labeli'.

Można użyć kombinacje wyrażeń regularnych i klasy Scanner do znalezienia wartości w ciągu znaków:

`FNAME: Dawid LNAME: Swist`

```java
        String text = "FNAME: Dawid LNAME: Swist";
        String delim = "(FNAME:) | (LNAME:)";
        ArrayList<String> ret = new ArrayList<>();

        Scanner scanner = new Scanner(text).useDelimiter(delim);
        scanner.forEachRemaining(x -> ret.add(x.trim()));

        assertArrayEquals(new String[]{"Dawid","Swist"},ret.toArray());
```

### Typy liczbowe double i BigDecimal

1. Type double reprezentuje tyko pewną dokłądność. Nie można porównywać ich bezpośrednio. 
Trzeba przyjąć jakieś przybliżenie. `abs(d2-d1) <= epsilon`.

2. Do dokłądnych operacji na liczbach niezbędne jest użycie typu BigDecimal.

3. Metoda `Math.ulp` pozwala poznać precyzje danego typu zmienno przecinkowego.
`Math.nextUp` zwraca następną liczbę.

### Użycie data od Java 8
W java 8 zostały wprowadznone nowe obiekty do zarządzania czasem.

```java
    LocalDate localDate = LocalDate.now();
    LocalTime localTime = LocalTime.now();
    ZonedDateTime zonedDateTime = ZonedDateTime.now();
```

Przykłady użycia:

```java
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
```

### Java 8 i Java Collection framework.

W JCF można wyróżnić następujące typy kolecji. List, Map, Set.

Rodzaje List:
- ArrayList, szybki bezpośredni dostęp, wolne wstawianie i usuwanie.
- LinkedList, Wszybkie wstawianie, Wolny dostęp bezpośredni, operacje na kolejkach.
- HashSet, HashMap, Szybkie wstawianie, szybkie contain i get, kolejność dowolna
- TreeSe, TreeMap, Uporządkowanie elementów na podstawie porownywania, szybkie add i put, szybkie contains i get.

**Zastosowanie Comaparators**

Jak zbudować włany komparator do TreeSet:
```java
    class Person {

        private final String fname;
        private final String lname;


        Person(String fname, String lname) {
            this.fname = fname;
            this.lname = lname;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return fname.equals(person.fname) &&
                    lname.equals(person.lname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fname, lname);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "fname='" + fname + '\'' +
                    ", lname='" + lname + '\'' +
                    '}';
        }
    }

    class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            return new StringBuffer(p1.lname).append(p1.fname).toString()
                    .compareTo(new StringBuffer(p2.lname).append(p2.fname).toString());
        }
    }

    @Test
    public void shouldSortPersonalDateInTreeSet() {

        SortedSet<Person> personList = new TreeSet<Person>(new PersonComparator());

        personList.add(new Person("CCC","AAAA"));
        personList.add(new Person("BBB","BBBB"));
        personList.add(new Person("AAA","AAAA"));
        personList.add(new Person("BBB","AAAA"));
        personList.add(new Person("AAA","BBBB"));
        personList.add(new Person("CCC","BBBB"));


        assertEquals(new Person("AAA","AAAA"), personList.first());
        assertEquals(new Person("CCC","BBBB"), personList.last());

    }
``` 

