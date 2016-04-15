import java.util.Comparator;

public class SimpleIntComparator implements Comparator<Integer> {
    public int compare(Integer n1, Integer n2){
        if(n1 > n2){
            return 1;
        }
        if (n1 < n2){
            return -1;
        }
        return 0;

    }

}
