package utils.streamApi;

import java.util.Arrays;
import java.util.Objects;

public class convertToInteger {

    public static Integer[] convertToIntegerArray(String[] strings) {
        if (strings == null) {
            return new Integer[0];
        }

        return Arrays.stream(strings)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(str -> str.matches("-?\\d+"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }
}