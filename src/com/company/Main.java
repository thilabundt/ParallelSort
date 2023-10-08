package com.company;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    public static void main(String[] args)
    {
        int[] data = new int[16];

        for (int i = 0; i < data.length; i++)
        {
            data[i] = (int) (Math.random() * 100);
        }

        System.out.println("Input Array:");
        for (int i = 0; i < data.length; i++)
        {
            System.out.print(data[i] + " ");
        }
        System.out.println();

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new ParallelSort.SortTask(data));

        System.out.println("Output Array:");
        for (int i = 0; i < data.length; i++)
        {
            System.out.print(data[i] + " ");
        }
    }
}
