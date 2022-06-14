package uz.pdp.websocketdemo;

import ch.qos.logback.core.pattern.Converter;

import java.util.Objects;
import java.util.TimeZone;

public class InsertionSortListNode {


    public ListNode insertionSortList(ListNode head) {



        if(head ==null || head.next ==null)  return head;
        ListNode t = head;
        ListNode last = null;
        while (t.next!=null){
            last = head;
            while (last.next!=null){
//                if(Objects.equals())
                if(last.val> last.next.val){
                    swap(last);
                    last = last.next;
                }
            }
            t = t.next;
        }
        return null;
    }

    void swap(ListNode ls){
        int t = ls.val;
        ls.val = ls.next.val;
        ls.next.val = t;
    }
}
