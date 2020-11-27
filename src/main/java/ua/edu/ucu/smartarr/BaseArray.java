package ua.edu.ucu.smartarr;

// Base array for decorators
public class BaseArray implements SmartArray{

    private Object [] array;
    private int arraySize;

    public BaseArray(Object [] array) {
        this.array = array;
        this.arraySize = array.length;

    }

    @Override
    public Object[] toArray() {
        Object [] result = this.array.clone();
        return result;
    }

    @Override
    public String operationDescription() {
        return null;
    }

    @Override
    public int size() {
        return arraySize;
    }
}
