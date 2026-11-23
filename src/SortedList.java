import java.util.ArrayList;

/**
 * SortedList - an ArrayList-like container for Strings that always keeps
 * its elements in ascending lexicographic order.
 *
 * New elements are placed using a hand-written binary search
 * (no Collections.binarySearch / Arrays.binarySearch).
 */
public class SortedList
{
    private ArrayList<String> list = new ArrayList<>();

    /**
     * Binary search for target.
     * @return the index of target if found; otherwise the index where
     *         target would be inserted to keep the list sorted.
     */
    public int binarySearch(String target)
    {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high)
        {
            int mid = (low + high) / 2;
            int cmp = target.compareTo(list.get(mid));

            if (cmp == 0)
            {
                return mid;          // found it
            }
            else if (cmp < 0)
            {
                high = mid - 1;      // target is in the lower half
            }
            else
            {
                low = mid + 1;       // target is in the upper half
            }
        }
        return low;                  // not found: low is the insertion point
    }

    /**
     * Adds a String in its sorted position.
     * @return the index where the element was inserted
     */
    public int add(String value)
    {
        int index = binarySearch(value);
        list.add(index, value);
        return index;
    }

    /** @return true if value is in the list */
    public boolean contains(String value)
    {
        int index = binarySearch(value);
        return index < list.size() && list.get(index).equals(value);
    }

    public String get(int index)
    {
        return list.get(index);
    }

    public int size()
    {
        return list.size();
    }

    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    public void clear()
    {
        list.clear();
    }

    @Override
    public String toString()
    {
        return list.toString();
    }
}
