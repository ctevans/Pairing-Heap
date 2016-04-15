import java.util.Comparator;

/**
 * This comparator class is used by the built-in java priority queue, which I compare my pairing heap implementation
 * against. Follows the standard Comparator implementation for an integer.
 */
public class SimpleIntComparator implements Comparator<Integer> {
    /**
     * Standard compare function for a class that implements Comparator.
     * @param n1 One of two integers to be compared.
     * @param n2 The second of two integers to be compared.
     * @return 0 if equal, 1 if n1 > n2, -1 if n1 < n2.
     */
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
