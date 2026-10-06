package utils.comparators;

import java.util.Comparator;

public class SortByModule implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 == null && o2 == null) return 0;
        if (o1 == null) return 1;
        if (o2 == null) return -1;

        int abs1 = Math.abs(o1);
        int abs2 = Math.abs(o2);

        return Integer.compare(abs2, abs1);
    }
}
