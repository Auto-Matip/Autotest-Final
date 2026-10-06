package utils.comparators;


import java.util.Comparator;

public class SudoSortingComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return 1;
        if (s2 == null) return -1;
            boolean hasSudo1 = s1.toLowerCase().contains("sudo");
        boolean hasSudo2 = s2.toLowerCase().contains("sudo");

    if (hasSudo1 && hasSudo2) {
                return 0;
        }
  if (hasSudo1) {
             return -1;
        }
          if (hasSudo2) {
              return 1;
        }

 return 0;
    }
}