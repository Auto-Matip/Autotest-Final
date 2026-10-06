package utils.comparators;
import java.util.Comparator;

public class LastLetterComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2){

        if (o1.equalsIgnoreCase(o2)) {
            return 0;
        } else {
            Character char1=o1.charAt(o1.length()-1);
            Character char2=o2.charAt(o2.length()-1);

            return char1.compareTo(char2);
        }
    }
}
