package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyPredicate;

// Tests every element and removes it if it doesn't satisfy MyPredicate
public class FilterDecorator extends SmartArrayDecorator {

    private MyPredicate predicate;
    private int size;

    public FilterDecorator(SmartArray smartArray, MyPredicate predicate) {
        super(smartArray);
        this.predicate = predicate;
        this.size = smartArray.size();
    }

    @Override
    public Object[] toArray() {
        return deleteUnnecessary();
    }

    @Override
    public String operationDescription() {
        return "Deletes elements that doesn't fit" +
                " predicate's conditions. The function" +
                " test() is used.";
    }

    @Override
    public int size() {
        return size;
    }

    public Object[] deleteUnnecessary() {
        Object [] array = smartArray.toArray();

        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (!predicate.test(array[i])) {
                array[i] = null;
                counter ++;
            }
        }

        int ind = 0;
        Object [] result = new Object[array.length - counter];
        for (int j = 0; j < array.length; j++) {
            if (array[j] != null) {
                result[ind] = array[j];
                ind ++;
            }
        }

        size -= counter;
        return result;
    }
}
