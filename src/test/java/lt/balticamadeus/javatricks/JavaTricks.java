package lt.balticamadeus.javatricks;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaTricks {

    @Test
    public void defaultValues() {
        int y = 10;
        int x;
//        assertEquals(10, y + x);
    }

    @Test
    public void naming() {
//        String A$B;
//        String _helloWorld;
//        String 1980_s;
//        String true;
    }

    @Test
    public void numbers() {
//        int    A = 1_234_000;
//        double B = 1_234_.0;
//        double C = 1_234._0;
//        double D = 1_234.0_;
//        double E = 1_234.0;

//        int    A = 9L;
        int    B = 0b101;
        int    C = 0xE;
        double D = 0xE;
//        int    E = 1_2_;
        System.out.println(B);
        System.out.println(C);
        System.out.println(D);
    }

    @Test
    public void maxInt() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE+1);
        System.out.println(Integer.MAX_VALUE+2);




        System.out.println(Long.MAX_VALUE);   // 9223372036854775807
        System.out.println(Long.MAX_VALUE+1); // -9223372036854775808
    }

    @Test
    public void flowControl() {
        FIRST_LOOP: for (int a = 1; a <= 4; a++) {
            for (char x = 'a'; x <= 'c'; x++) {
                if (a == 2 || x == 'b')
                    continue FIRST_LOOP;
                System.out.print(" " + a + x);
            }
        }
    }

    @Test
    public void strings() {
        String x = "Hello World";
        String y = "Hello World";
        System.out.println(x == y);
    }

    @Test
    public void binarySearch() {
        int[] numbers = {2,4,6,8};
        System.out.println(Arrays.binarySearch(numbers, 2)); // 0
        System.out.println(Arrays.binarySearch(numbers, 4)); // 1
        System.out.println(Arrays.binarySearch(numbers, 1)); // -1
        System.out.println(Arrays.binarySearch(numbers, 3)); // -2
        System.out.println(Arrays.binarySearch(numbers, 9)); // -5

        int[] numbers2 = {3,2,1};
        System.out.println(Arrays.binarySearch(numbers2, 2)); // 1
        System.out.println(Arrays.binarySearch(numbers2, 3)); // -4
    }

    @Test
    public void arraysAndLists() {
        String[] array = { "baltic", "amadeus" };
        List<String> list = Arrays.asList(array);
        list.set(1, "IT");
        array[0] = "enjoy";
        for (String b : array) System.out.print(b + " ");
        for (String b : list) System.out.print(b + " ");
    }

    @Test
    public void dates() {
        System.out.println(LocalDate.of(2019, 3, 7));
        System.out.println(LocalDate.of(2019, 4, 7));
        System.out.println(LocalDate.of(2019, Calendar.MARCH, 7));
        System.out.println(LocalDate.of(2019, Month.MARCH, 7));

//        LocalDate date = LocalDate.of(2019, Month.FEBRUARY, 30);
//        date = date.plusDays(2);
//        System.out.println(date);
    }

    public interface JuniorProgrammer {
        default int getBugsPerSecond() {
            return 10;
        }
    }
    public interface SeniorProgrammer {
        default int getBugsPerSecond() {
            return 10;
        }
    }
    public class Programmer implements JuniorProgrammer, SeniorProgrammer {
        public int getBugsPerSecond() {
            return 10;
        }
    }

    public static class InitOrder {
        static { add(2); }
        static void add(int num) { System.out.print(num + " "); }
        InitOrder() { add(5); }
        static { add(4); }
        { add(6); }
        { add(8); }}

    @Test
    public void initOrder() {
        new InitOrder();
    }

    public class Bug extends Exception {}
    public class Junior {
        protected String writeCode() throws Bug {
            throw new Bug();
        }
        protected int getCoffeePerDay() throws Exception {
            return 1;
        }
    }
    public class Senior extends Junior {
        protected String writeCode() {
            return "hello_world() -> io:fwrite('hello, world').";
        }
        protected int getCoffeePerDay() throws Bug {
            return 10;
        }
    }

    @Test
    public void exceptions() throws Exception {
        Junior programmer = new Senior();
        System.out.println(programmer.writeCode());
        System.out.println(programmer.getCoffeePerDay());
    }

}
