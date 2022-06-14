package uz.pdp.websocketdemo;


import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class Solution {


    public static void main(String[] args) {
        Map<String, A> map = new Hashtable<>();
        for(int i=0; i<5000; i++) {
            A object = new A(i);
            map.put(""+i,object);
        }
        map.forEach((s, a) -> System.out.println(""+s+": "+a));


//        ListNode listNode = new ListNode(9, new ListNode(2, new ListNode(3, new ListNode(1, new ListNode(5, new ListNode(4, new ListNode(52, new ListNode(12))))))));
//        sortList(listNode);
//        while (listNode!=null){
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }
        int [][] mat = {{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}};
        Solution solution = new Solution();
        solution.kWeakestRows(mat, 3);
    }





    public int[] kWeakestRows(int[][] mat, int k) {
        int[] res = new int[k];
        int l=mat.length;

        int[] sc = new int[l];

        for(int i=0; i<l; i++){
            for(int j=0; j<l; j++){
                if(mat[i][j] ==0) break;
                sc[i]=sc[i]+1;
            }
        }
        System.out.print(Arrays.toString(sc));

        for(int i=0; i<k; i++){
            int mindx = 0;
            for(int j =1; j<l; j++){
                if(sc[mindx]>sc[j]){
                    mindx = j;
                }
            }
            sc[mindx] = 1001;
            res[i]=mindx;
        }

        return res;
    }



    public static ListNode sortList(ListNode h) {
        ListNode i = h;
        ListNode last = null;
        while(i.next != null){
            ListNode j = h;
            while(j.next != null){
                if(j.val>j.next.val){
                    swap(j);
                }
                if (Objects.equals(last, j.next)) {
                    last = j;
                    break;
                }
                j = j.next;

            }
            last = j;
            i = i.next;
        }
        return h;
    }
    
    static void swap(ListNode ls){
        int t = ls.val;
        ls.val = ls.next.val;
        ls.next.val = t;
    }
}