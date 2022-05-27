package SortTask;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static double[] arr;

    public static void main(String[] args) throws IOException {
        chooseSort();
    }

    public static void task(int type, String fName, String resFName) throws IOException {
        ReadArrFromFile rf = new ReadArrFromFile();
        arr = rf.readArr(fName);
        SortArrTest sArrTest = new SortArrTest();
        if(type == 0) {
            SortReturn sr = sort(resFName);
            System.out.println("Merge Sort");
            System.out.println("Time: " + sr.getTime());
            System.out.println("Sorted: " + sArrTest.checkArr(sr.getArr()));
            System.out.println("Length doesn't change: " + sArrTest.arrHasLength(sr.getArr(), arr.length));
        }
        else if(type == 1) {
            SortReturn sr =  parallelSort(resFName);
            System.out.println("Merge Sort Parallel");
            System.out.println("Time: " + sr.getTime());
            System.out.println("Sorted: " + sArrTest.checkArr(sr.getArr()));
            System.out.println("Length doesn't change: " + sArrTest.arrHasLength(sr.getArr(), arr.length));
            System.out.println();
        }
        else if(type == 2) {
            SortReturn sr1 = sort(resFName);

            System.out.println("Merge Sort");
            System.out.println("Time: " + sr1.getTime());
            System.out.println("Sorted: " + sArrTest.checkArr(sr1.getArr()));
            System.out.println("Length doesn't change: " + sArrTest.arrHasLength(sr1.getArr(), arr.length));
            System.out.println();

            SortReturn sr2 = parallelSort("");

            System.out.println("Merge Sort Parallel");
            System.out.println("Time: " + sr2.getTime());
            System.out.println("Sorted: " + sArrTest.checkArr(sr2.getArr()));
            System.out.println("Length doesn't change: " + sArrTest.arrHasLength(sr2.getArr(), arr.length));

            System.out.println("Arrays are equal: " + sArrTest.arrsAreEqual(sr1.getArr(), sr2.getArr()));
            System.out.println("SpeedUp: " + ((double) sr1.getTime()/sr2.getTime()));
        }

    }

    public static void chooseSort() throws IOException {
        var in = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String fName = in.nextLine();

        int type = 0;
        System.out.println("Choose sort type: ");
        System.out.println("0 - Merge sort");
        System.out.println("1- Merge sort parallel");
        System.out.println("2 - Compare 0 and 1");
        type = in.nextInt();

        int writeInFile = 0;
        System.out.println("Do you want to write sorted array in file? 0 - no, 1 - yes");
        writeInFile = in.nextInt();
        in.nextLine();
        String resFName = "";
        if(writeInFile == 1) {
            System.out.print("Enter new file path: ");
            resFName = in.nextLine();
        }
        task(type, fName, resFName);

    }



    public static SortReturn sort(String resFName) {
        long sTime = System.currentTimeMillis();
        MergeSort ms = new MergeSort(arr.clone());
        ms.SortArr();
        long resTime = System.currentTimeMillis();
        long Time = resTime - sTime;

        if(!Objects.equals(resFName, "")) {
            ShowArr.writeInFile(ms.arr, resFName);
        }

        return new SortReturn(ms.arr, Time);
    }

    public static SortReturn parallelSort(String resFName) {
        long sTime = System.currentTimeMillis();
        int minElInOneTask = 3250000;
        MergeSortParallel msp = new MergeSortParallel(arr.clone(), minElInOneTask);
        msp.SortArr();
        long resTime = System.currentTimeMillis();
        long Time = resTime - sTime;

        if(!Objects.equals(resFName, "")) {
            ShowArr.writeInFile(msp.arr, resFName);
        }
        return new SortReturn(msp.arr, Time);
    }
}

class SortReturn {
    private final double[] arr;
    private final long Time;
    public SortReturn(double[] arr, long Time) {
        this.arr = arr;
        this.Time = Time;
    }

    public double[] getArr() {
        return arr;
    }

    public long getTime() {
        return Time;
    }
}