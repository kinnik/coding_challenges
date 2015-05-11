/**
  * Merges two sorted lists.
  * Avoids using the get() method of the list to retrieve the elements
  * because that is inefficient – whenever an index is provided, the traversal
  * starts from the end (head or tail), making it O(N^2).
  * The iterators are used instead to traverse the given lists.
  *
  * http://www.careercup.com/question?id=5187906612232192
  */

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MergeTwoSortedList
{
    private MergeTwoSortedList() {}

    public static List<Integer> merge(List<Integer> listA,
                                      List<Integer> listB)
    {
        if (listA == null) return listB;
        if (listB == null) return listA;

        final ListIterator<Integer> iterA = listA.listIterator(0);
        final ListIterator<Integer> iterB = listB.listIterator(0);

        final List<Integer> result = new LinkedList<Integer>();
    
        merge(iterA, iterB, result);
        return result;
    }

    private static void merge(ListIterator<Integer> iterA,
                              ListIterator<Integer> iterB,
                              List<Integer>  result)
    {
        if (!iterA.hasNext())
        {
            while (iterB.hasNext()) { result.add(iterB.next()); }
            return;
        }

        if (!iterB.hasNext())
        {
            while (iterA.hasNext()) {result.add(iterA.next()); }
            return;
        }

        Integer a = iterA.next();
        Integer b = iterB.next();

        if (a < b)
        {
            result.add(a);
            b = iterB.previous(); // rewind
        }
        else if (b < a)
        {
            result.add(b);
            a = iterA.previous(); // rewind
        }
        else
        {
            result.add(a);
            result.add(b);
        }

        merge(iterA, iterB, result);
    }

    public static void main(String[] args)
    {
        List<Integer> result;

        // Test 1
        List<Integer> listA = new LinkedList<Integer>();
        List<Integer> listB = new LinkedList<Integer>();

        listA.add(1);
        listB = listA;
        result = merge(listA, listB);
        
        for (int i: result) System.out.print(i + " ");
        System.out.println();


        // Test 2
        listA.add(2);
        listA.add(3);
        listA.add(4);
        listA.add(5);

        listB = new LinkedList<Integer>();        
        listB.add(6);
        listB.add(7);
        listB.add(8);
        listB.add(9);
        listB.add(10);

        result = merge(listA, listB);
        for (int i: result) System.out.print(i + " ");
        System.out.println();

        // Test 3
        listB = listA;
        result = merge(listA, listB);
        for (int i: result) System.out.print(i + " ");
        System.out.println();

        // Test 4
        listA = null;
        result = merge(listA, listB);
        for (int i: result) System.out.print(i + " ");
        System.out.println();
    }
}
