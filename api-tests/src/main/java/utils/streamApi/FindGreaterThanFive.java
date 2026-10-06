package utils.streamApi;

import java.util.Collection;

public class FindGreaterThanFive {
    public static <T extends Number> long countGreaterThanFive(Collection<T> collection) {
        if (collection == null) return 0;
        return collection.stream()
                .filter(java.util.Objects::nonNull)
                .filter(num -> num.doubleValue() > 5)
                .count();
    }
}