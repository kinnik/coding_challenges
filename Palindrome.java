/**
 * Determines whether the given linked list is a palindrome in O(1) space
 * and O(N) time.
 */

public class Palindrome
{
    public static boolean isPalindrome(LinkedListNode n)
    {
        LinkedListNode head = n;
        LinkedListNode slow = n;
        LinkedListNode fast = n;

        // position the pointers by moving the slow pointer one step at
        // a time and the fast pointer two steps at a time.
        // slow pointer will be at the centre when the fast pointer reaches
        // the end.
        while (fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        // keep moving the slow pointer from the centre until the end.
        // during the traversal, reverse the list so that the 'mid' pointer
        // will end up with a reversed list.
        LinkedListNode mid = null;

        while (slow != null)
        {
            LinkedListNode oldMid = mid; // save the old mid
             mid = slow;        // mid will follow what slow points
            slow = slow.next;  // keep moving slow
            mid.next = oldMid; // the newly-joined node will stay at front
        }

        // now move the head and the mid at equal pace
        while (mid != null)
        {
            if (mid.data != head.data)  return false;

             mid = mid.next;
            head = head.next;
        }
        return true;
    }

    private static class LinkedListNode
    {
        LinkedListNode next;
        int  data;

        private LinkedListNode(int data, LinkedListNode next)
        {
            this.data = data;
            this.next = next;
        }

        public String printForward()
        {
            if (next != null)   return data + "->" + next.printForward();
            else                return ((Integer) data).toString();
        }        
    }

    private static LinkedListNode[] createPalindrome(int length)
    {
        LinkedListNode[] nodes = new LinkedListNode[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null);
        }
        
        for (int i = 0; i < length; i++)
            if (i < length - 1) nodes[i].next = nodes[i + 1];

        return nodes;
    }

    public static void main(String args[])
    {
        final int length = 10;
        LinkedListNode[] nodes = createPalindrome(length);
        nodes[length - 2].data = 9; // Uncomment to ruin palindrome
        
        LinkedListNode head = nodes[0];
        System.out.println(head.printForward());
        System.out.println(isPalindrome(head));
    }
}
