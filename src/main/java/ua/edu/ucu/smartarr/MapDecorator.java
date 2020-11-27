package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyFunction;

// Map every element to another object using MyFunction
public class MapDecorator extends SmartArrayDecorator{

    private MyFunction function;
    private int size;

    public MapDecorator(SmartArray smartArray, MyFunction function) {
        super(smartArray);
        this.function = function;
        this.size = smartArray.size();
    }

    @Override
    public Object[] toArray() {
        return express();
    }

    @Override
    public String operationDescription() {
        return "Expresses each element of SmartArray" +
                " into another object using MyFunction";
    }

    @Override
    public int size() {
        return size;
    }

    public Object [] express() {

        Object [] array = smartArray.toArray();
        Object [] result = new Object [smartArray.toArray().length];

        for (int i = 0; i < array.length; i++) {
            Object res  = function.apply(array[i]);
            result[i] = res;
        }
        return result;
    }
}
