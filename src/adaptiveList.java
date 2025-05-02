import java.util.Comparator;

public class adaptiveList <T>{

    public Object[] array;
    public int numItems;


    public adaptiveList(){
        array = new Object[10];
        numItems = 0;
    }

    /**
     *
     * @param length of array to create
     */
    public adaptiveList(int length){
        array = new Object[length];
        numItems = 0;
    }



    public void addBack(T input){
        if (array.length <= numItems){
            expand();
        }

        array[numItems] = input;
        numItems++;
    }


    public T popFront(){
        T data = (T) array[0];

        if (array.length <= numItems){
            expand();
        }


        Object[] tempArray = new Object[numItems];
        for (int i = 0; i < numItems; i++) {
            tempArray[i] = array[i+1];
        }
        array = tempArray;

        return data;
    }

    public T popIndex(int i){
        T data = (T) array[i];

        if (array.length <= numItems){
            expand();
        }


        Object[] tempArray = new Object[numItems];

        for (int j = 0; j < numItems; j++) {
            if (j != i) {
                tempArray[j] = array[j];
            }
        }

        array = tempArray;

        return data;
    }



    public T peepFront(){
        return (T) array[0];
    }

    public T peepIndex(int i){
        return (T) array[i];
    }


    public boolean contains(T data){
        for (int i = 0; i < numItems; i++) {
            if (data == array[i]){
                return true;
            }
        }
        return false;
    }


    public void expand(){
        Object[] tempArray = new Object[numItems * 2];
        for (int i = 0; i < numItems; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
    }


    @Override
    public String toString() {
        String returnString = "";

        for (int i = 0; i < numItems; i++) {
            returnString += i + ", ";
        }

        return returnString;
    }
}
