import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        maxSlidingWindow(new int[]{1} , 1);
    }


    public static int[] maxSlidingWindow(int[] nums, int k) {

        int firstIndex = 0;
        int lastIndex = firstIndex + k -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int [] outPut = new int[nums.length - k +1];

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i =0; i<k; i++){
            queue.add(nums[i]);
            map.computeIfPresent(nums[i], (key, val) -> val +1);
            map.computeIfAbsent(nums[i], key -> 1);
        }


        while (lastIndex < nums.length){

            int outPutValue = 0;
            if(!map.containsKey(queue.peek())){
                outPutValue = getMaxValueWithinTheWindow(queue, map, k);
            }else {
                outPutValue = queue.peek();
            }

            outPut[firstIndex] = outPutValue;
            if(map.get(nums[firstIndex]) == 1)
                map.remove(nums[firstIndex]);
            else {
                map.put(nums[firstIndex], map.get(nums[firstIndex]) - 1);
            }

            firstIndex ++;
            lastIndex ++;

            if(lastIndex <nums.length){
                map.computeIfPresent(nums[lastIndex], (key, val) -> val +1);
                map.computeIfAbsent(nums[lastIndex], key -> 1);
                queue.add(nums[lastIndex]);
            }
        }


        return outPut;
    }

    private static int getMaxValueWithinTheWindow(PriorityQueue<Integer> queue, HashMap<Integer, Integer> map, int k) {

        while (queue.size() >= k){
            queue.poll();

            if(map.containsKey(queue.peek()))
                return queue.peek();
        }

        return queue.peek();
    }
}