package swistdaw.learn.java.world.Java8UniwersalneTechniki;

import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class Ch9JCFTest {


    @Test
    public void shouldSortPersonalDateInTreeSet() {

        SortedSet<Person> personList = new TreeSet<Person>(new PersonComparator());

        personList.add(new Person("CCC", "AAAA"));
        personList.add(new Person("BBB", "BBBB"));
        personList.add(new Person("AAA", "AAAA"));
        personList.add(new Person("BBB", "AAAA"));
        personList.add(new Person("AAA", "BBBB"));
        personList.add(new Person("CCC", "BBBB"));


        assertEquals(new Person("AAA", "AAAA"), personList.first());
        assertEquals(new Person("CCC", "BBBB"), personList.last());

    }

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
            return (p1.lname + p1.fname)
                    .compareTo(p2.lname + p2.fname);
        }
    }


}
