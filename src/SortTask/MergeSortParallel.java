package SortTask;

import java.util.concurrent.ForkJoinPool;

public class MergeSortParallel extends SortArray {
    private final int minElInOneTask;
    public MergeSortParallel(double[] arr, int minElInOneTask) {
        super(arr);
        this.minElInOneTask = minElInOneTask;
    }

    @Override
    void SortArr() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new ArrSortAction(arr, 0, arr.length-1, minElInOneTask));
    }
}