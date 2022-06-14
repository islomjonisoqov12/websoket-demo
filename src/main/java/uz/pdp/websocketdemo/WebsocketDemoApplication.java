package uz.pdp.websocketdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsocketDemoApplication {

    public static void main(String[] args) {

//        BaseInterface baseInterface = null ;
//        baseInterface.printHello("islom");





        SpringApplication.run(WebsocketDemoApplication.class, args);
    }
//        int[] nums = {3,4,9,4,7,1,48,47, 46, 2};
////        insertionSort(nums);
////        selectionSort(nums);
//        bubbleSort(nums);
//        System.out.println(nums);
//    }
//
//
//    static void insertionSort(int[] nums){
//        for (int i = 1; i < nums.length; i++) {
//            int j = i-1;
//            int lastNum = nums[i];
//
//            while (j >-1 && lastNum < nums[j]){
//                nums[j+1] = nums[j];
//                j--;
//            }
//            nums[j+1] = lastNum;
//        }
//    }
//
//    static void selectionSort(int[] nums){
//        for (int i = 0; i < nums.length; i++) {
//            int min = i;
//            for (int j = i+1; j < nums.length; j++) {
//                if(nums[min]>nums[j]) min =j;
//            }
//            int t = nums[i];
//            nums[i] = nums[min];
//            nums[min] = t;
//        }
//    }
//
//    static void bubbleSort(int [] nums){
//        for (int i = 0; i < nums.length-1; i++) {
//            for (int j = 0; j < nums.length - i - 1; j++) {
//                if(nums[j]> nums[j+1]) {
//                    int t = nums[j+1];
//                    nums[j+1] = nums[j];
//                    nums[j] = t;
//                }
//            }
//        }
//    }
//
//    static void mergeS

}



