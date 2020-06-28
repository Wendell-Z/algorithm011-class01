package week01;

/**
 * Merge Sorted Linked List
 *
 * @author Wendell
 * @Date 2020-6-24  20:15:12
 */
public class P21_Merge_List {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        ListNode mark = node;
        ListNode move1 = l1;
        ListNode move2 = l2;
        while (move1 != null && move2 != null) {
            if (move1.val <= move2.val) {
                mark.next = move1;
                mark = mark.next;
                move1 = move1.next;

            } else {
                mark.next = move2;
                mark = mark.next;
                move2 = move2.next;
            }
        }
        mark.next = move1 == null ? move2 : move1;
        return node.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
