package com.my;

/**
 * @program:
 * @description:插入排序 1.从数组的第二个数据开始往前比较，即一开始用第二个数和他前面的一个比较，如果 符合条件（比前面的大或者小，自定义），则让他们交换位置。
 * 2.然后再用第三个数和第二个比较，符合则交换，但是此处还得继续往前比较，比如有 5个数[8，15，20，45, 17],17比45小，需要交换，
 * 但是17也比20小，也要交换，当不需要和15交换以后，说明也不需要和15前面的数据比较了，肯定不需要交换，因为前 面的数据都是有序的。
 * 3.重复步骤二，一直到数据全都排完。
 * @author: liang.liu
 * @create: 2021-10-22 16:12
 */
public class _01InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{8, 15, 20, 45, 17};
        desSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /*
     * 正序
     * */
    public static void aseSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] > arr[j - 1]) {
                    break;
                }
                swap(arr, j, j - 1);
            }
        }
    }

    /*
     * 倒叙
     * */
    public static void desSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    break;
                }
                swap(arr, j, j - 1);
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
