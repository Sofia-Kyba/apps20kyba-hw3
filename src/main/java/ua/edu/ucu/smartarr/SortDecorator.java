package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyComparator;

import java.lang.reflect.Array;
import java.util.Arrays;

// Sorts elements using MyComparator to compare them
public class SortDecorator extends SmartArrayDecorator{

    private MyComparator comparator;
    private int size;

    public SortDecorator(SmartArray smartArray, MyComparator comparator) {
        super(smartArray);
        this.comparator = comparator;
        this.size = smartArray.size();
    }

    @Override
    public Object[] toArray() {
        return sort();
    }

    @Override
    public String operationDescription() {
        return "Sorts two elements basing on comparing them using"
            + " function compare of MyComparator";
    }

    @Override
    public int size() {
        return size;
    }

    public Object[] sort() {
        Object [] array = smartArray.toArray();
        Object [] result = array.clone();
        Arrays.sort(result, comparator);

        return result;
    }
}
