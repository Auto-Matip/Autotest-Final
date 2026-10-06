package comparatorsTest;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import utils.comparators.DigitUnnaturalComparator;
import utils.comparators.LastLetterComparator;
import utils.comparators.SortByModule;
import utils.comparators.SudoSortingComparator;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TestComparator {
List<String> strings = new ArrayList<>();
    @Test
    void test() {
        strings.add("ABCB");
        strings.add("DEFA");
        strings.add("GHI");
        strings.sort(Comparator.naturalOrder());
        strings.forEach(System.out::println);
System.out.println("==========================");
        strings.sort(new LastLetterComparator());
        strings.forEach(System.out::println);
        assertThat(strings)
                .isSortedAccordingTo(new LastLetterComparator());
    }

    @RepeatedTest(20)
    void test2() {
        Integer int1 = new Random().nextInt(1,999);
        Integer int2 = new Random().nextInt(1,999);

        System.out.println(int1 + " compare to " + int2);
        System.out.println("Standart comparator");
        System.out.println(int1.compareTo(int2));
        System.out.println("\n========================\n");
        System.out.println("Custom comparator");
        System.out.println(new DigitUnnaturalComparator().compare(int1, int2));

    }

@RepeatedTest(5)

        void test3() {
    int int1 = new Random().nextInt(-1000,999);
    int int2 = new Random().nextInt(-1000,999);
    int int3 = new Random().nextInt(-1000,999);
    int int4 = new Random().nextInt(-1000,999);
    int int5 = new Random().nextInt(-1000,999);
    int int6 = new Random().nextInt(-1000,999);
            Integer[] numbers = {int1, int2, int3, int4, int5, int6};
    System.out.println("Original list:");
    System.out.println(Arrays.toString(numbers));
    System.out.println("\n========================\n");
            Arrays.sort(numbers, new SortByModule());

    for (int i = 0; i < numbers.length; i++) {
        if (numbers[i] != null) {
            numbers[i] = Math.abs(numbers[i]);
        }
    }
    System.out.println("Sorted list:");
    System.out.println(Arrays.toString(numbers));
    }




@Test
    void test4() {
        String[] list = {
                "lalala",
                "sudoaaaa",
                "abcsud",
                "aaaaaasudoaaaaa",
                "lalalalasudosudo",
                "pupupuaaaa",
                null
        };
        Arrays.sort(list, new SudoSortingComparator());
        for (String cmd : list) {
            System.out.println(cmd);
        }
    }
}
