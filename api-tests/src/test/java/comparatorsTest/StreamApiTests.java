
package comparatorsTest;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.*;
import utils.streamApi.FindGreaterThanFive;
import utils.streamApi.convertToInteger;

public class StreamApiTests {

    @RepeatedTest(10)
    void test() {
        int int1 = new Random().nextInt(-10,10);
        int int2 = new Random().nextInt(0,10);
        int int3 = new Random().nextInt(0,4);
        int int4 = new Random().nextInt(0,6);
        int int5 = new Random().nextInt(1,10);
        int int6 = new Random().nextInt(0,7);
        List<Integer> numbers = java.util.Arrays.asList(int1, int2, int3, int4, int5, int6, null);
        System.out.println("Исходный список: ");
        System.out.println(numbers);


        long count = FindGreaterThanFive.countGreaterThanFive(numbers);

            System.out.println("Количество элементов больше 5: " + count);

    }

    @RepeatedTest(5)
    void test1() {
        int int1 = new Random().nextInt(-10,10);
        int int2 = new Random().nextInt(0,100);
        int int3 = new Random().nextInt(0,100);
        double int4 = new Random().nextInt(-10,100);
        String a = String.valueOf(int1);
        String b = String.valueOf(int2);
        String c = String.valueOf(int3);
        String e = String.valueOf(int4);
        String[] input = {a, "aaaaa", b, c, null, e, "123sda"};
        System.out.println("Изначальный список:");
        System.out.println(Arrays.toString(input));
        Integer[] result = convertToInteger.convertToIntegerArray(input);
        System.out.println("\n========================\n");
        System.out.println("Отфильтрованный список:");
        System.out.println(Arrays.toString(result));

    }

}