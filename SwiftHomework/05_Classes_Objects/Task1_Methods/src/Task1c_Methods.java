
public class Task1c_Methods {

    static int indexOf(int[] arr, int element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(indexOf(arr, 4));
        System.out.println(indexOf(arr, 11));
    }
}
