import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> {
    private T[] arr;
    private int size;
    private Class<T> type;

    public DynamicArray(Class<T> t) {
        type = t;
        size = 0;
    }

    public int size() {
        if (arr == null) {
            return 0;
        }
        size = arr.length;
        return size;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index){
        if (index > size-1) {
            return null;
        }
        return arr[index];

    }

    public boolean contains (T element) {
        for (int i = 0; i < size; i++) {
            if( element == arr[i]) {
                return true;
            }
        }
        return false;
    }

    public void add (T element) {
        size+=1;
        T[] newArray;
        if (arr == null) {
            newArray = (T[]) Array.newInstance(type, 1);
            newArray[0] = element;

        }
        else {
            newArray = Arrays.copyOf (arr, size);
            newArray[size-1] = element;
        }
        arr = newArray;


    }

    public void add (int index, T element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        size+=1;
        T[] resizedArray;
        if (arr == null) {
            resizedArray = (T[]) Array.newInstance(type, 1);
            resizedArray[0] = element;

        }
        else {
            resizedArray = Arrays.copyOf (arr, size);
            boolean putIn = false;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    resizedArray[i] = element;
                    putIn = true;
                } else if (putIn) {
                    resizedArray[i] = arr[i - 1];
                } else {
                    resizedArray[i] = arr[i];
                }
            }
        }

        arr = resizedArray;

    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        arr[index] = element;

    }

    public T remove (int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        size-=1;
        T element = arr[index];
        arr[index] = null;
        boolean removed = false;
        if (size <= 0) {
            size = 0;
            arr = null;
        }
        else {
            T[] arrayTemp = (T[]) Array.newInstance(type, size);
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    i++;
                    removed = true;
                    arrayTemp[i-1] = arr[i];
                }
                else if (removed) {
                    arrayTemp[i-1] = arr[i];
                }
                else {
                    arrayTemp[i] = arr[i];
                }
            }

            arr = arrayTemp;

        }
        return element;
    }

    public T remove (T element) {
        size -= 1;
        boolean removed = false;
        if (size <= 0) {
            size = 0;
            arr = null;
        }
        else {

            T[] arrayTemp = (T[]) Array.newInstance(type, size);
            for (int i = 0; i < size+1; i++) {
                if (arr[i] == element && !removed) {
                    i++;
                    removed=true;
                    arrayTemp[i-1] = arr[i];
                }
                else if(removed){
                    arrayTemp[i-1] = arr[i];
                }
                else{
                    arrayTemp[i] = arr[i];
                }
            }
            arr = arrayTemp;
        }
        return element;

    }

    public T removeAll (T element) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i] == element) {
                count++;
            }
        }
        size-=count;
        int r = 0;
        if (size <= 0) {
            size = 0;
            arr = null;
        }
        else {
            T[] arrayTemp = (T[]) Array.newInstance(type, size);
            for (int i = 0; i < size+count; i++) {
                if (arr[i] == element) {
                    i++;
                    r++;
                    arrayTemp[i-r] = arr[i];
                }
                else{
                    arrayTemp[i-r] = arr[i];
                }

            }

            arr = arrayTemp;

        }

        return element;

    }

    public void clear(){
        size = 0;
        arr = null;


    }

}
