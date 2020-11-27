package ua.edu.ucu.smartarr;

// Remove duplicates from SmartArray. Use method equals() to compare objects
public class DistinctDecorator extends SmartArrayDecorator{

    private int size;


    public DistinctDecorator(SmartArray smartArray) {
        super(smartArray);
        this.size = smartArray.size();
    }

    @Override
    public Object[] toArray() {
        return deleteDuplicates();
    }

    @Override
    public String operationDescription() {
        return "Deletes duplicates comparing" +
                " elements with operation equals()";
    }

    @Override
    public int size() {
        return size;
    }

    public Object [] deleteDuplicates(){

        Object [] array = smartArray.toArray();
        Object [] newArray = smartArray.toArray();

        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < newArray.length; j++) {
                if (array[i].toString().equals(array[j].toString())) {
                    newArray[j] = null;
                    counter ++;
                }
            }
        }


        int ind = 0;
        Object [] result = new Object[array.length - counter];

        for (int k = 0; k < newArray.length; k++) {
            if (newArray[k] != null){
                result[ind] = newArray[k];
                ind ++;
            }
        }

        size -= counter;
        return result;

    }
}
