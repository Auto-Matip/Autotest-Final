
package utils.comparators;
import java.util.Comparator;

public class DigitUnnaturalComparator implements Comparator<Integer> {
    String digitsList = "765432198";

    @Override
    public int compare(Integer o1, Integer o2) {

        if (o1.equals(o2)) {
            return 0;
        }
        String s1 = o1.toString();
        String s2 = o2.toString();

        int lenght = Math.max(s1.length(), s2.length());
        for (int i = 0; i < lenght; i++) {
            int index1 = digitsList.indexOf(s1.charAt(i));
            int index2 = digitsList.indexOf(s2.charAt(i));
            if (index1 != index2) {
                return Integer.compare(index2, index1);
            }

        }

if (s1.length() > s2.length()) {
    return 1;
} else {
    return -1;
}

    }
}
