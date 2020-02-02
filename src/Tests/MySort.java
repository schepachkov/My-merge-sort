package Tests;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.*;


public class MySort {

    public static void main(String[] args) {
        //int[] array = {5,11,6,13,1,3,15,9,8,4,12,7,14,2,10};
        int capacity = 100_000;
        List<Integer> list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = list.get(i);
        }

        long l1 = new Date().getTime();
        array = mysort(array);
        long l2 = new Date().getTime();

        System.out.println(Arrays.toString(array));
        String toLog = "Java sort has worked " + (l2-l1) + " milliseconds.\n";
        try(FileOutputStream fileOutputStream = new FileOutputStream(new File("timeSorts.txt"),true)){
            fileOutputStream.write(toLog.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] mysort(int[] array) {
        if (array.length < 2) return array;

        int[] leftSide = mysort(Arrays.copyOfRange(array,0,array.length/2));
        int[] rightSide = mysort(Arrays.copyOfRange(array,array.length/2,array.length));
        return merge(leftSide,rightSide);

    }

    private static int[] merge(int[] leftSide, int[] rightSide) {
        int[] result = new int[leftSide.length + rightSide.length];
        int li = 0;
        int ri = 0;
        while (true){
            if (leftSide[li] < rightSide[ri]){
                result[li + ri] = leftSide[li];
                li++;
            }else {
                result[li + ri] = rightSide[ri];
                ri++;
            }
            if (leftSide.length == li){
                while (ri != rightSide.length){
                    result[li + ri] = rightSide[ri];
                    ri++;
                }
            }
            if (rightSide.length == ri){
                while (li != leftSide.length){
                    result[li + ri] = leftSide[li];
                    li++;
                }
            }
            if (li + ri == result.length) break;
        }
        return result;
    }

}
