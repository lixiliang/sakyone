package alg;

import org.junit.Test;

/**
 *@Description:<p>实现快速排序算法</p>
 *@author 王旭
 *@time 2016-3-3 下午5:07:29
 */
public class QuickSort {
    
    /**
     * 划分
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] arr, int left, int right) {
        int pivotKey = arr[left];
        
        while(left < right) {
            while(left < right && arr[right] >= pivotKey)
                right --;
            arr[left] = arr[right]; //把小的移动到左边
            while(left < right && arr[left] <= pivotKey)
                left ++;
            arr[right] = arr[left]; //把大的移动到右边
        }
        arr[left] = pivotKey; //最后把pivot赋值到中间
        return left;
    }
    
    /**
     * 递归划分子序列
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        if(left >= right)
            return ;
        int pivotPos = partition(arr, left, right);
        quickSort(arr, left, pivotPos-1);
        quickSort(arr, pivotPos+1, right);
    }
    
    public static void sort(int[] arr) {
        if(arr == null || arr.length == 0)
            return ;
        quickSort(arr, 0, arr.length-1);

    }
    @Test
    public void test(){
        int[] arr = {1,3,8,2,5,9};
        QuickSort.sort(arr);
        for (int a:arr){
            System.out.println(a);
        }
    }
    
}