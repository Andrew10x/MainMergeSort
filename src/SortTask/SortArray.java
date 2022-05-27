package SortTask;

abstract class SortArray {
    protected double[] arr;

    public SortArray(double[] arr) {
        this.arr = arr;
    }

    abstract void SortArr();


    public double[] getArr() {
        return arr;
    }

}