package com.company;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
public class ParallelSort
{
    private static final int maxArraySize = 4;
    public static class SortTask extends RecursiveAction
    {
        private final int[] data;
        private final int start;
        private final int end;
        SortTask(int[] data)
        {
            this(data, 0, data.length);
        }
        SortTask(int[] data, int start, int end)
        {
            this.data = data;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute()
        {
            if (end - start <= maxArraySize)
            {
                Arrays.sort(data, start, end);
            }
            else
            {
                int middle = start + (end - start) / 2;

                SortTask left = new SortTask(data, start, middle);
                SortTask right = new SortTask(data, middle, end);

                invokeAll(left, right);

                Merge(data, start, middle, end);
            }
        }

        private void Merge(int[] data, int start, int middle, int end)
        {
            int[] temp = new int[end - start];

            int i = start;
            int j = middle;
            int k = 0;

            while (i < middle && j < end)
            {
                if (data[i] < data[j])
                {
                    temp[k] = data[i];
                    k++;
                    i++;
                }
                else
                {
                    temp[k] = data[j];
                    k++;
                    j++;
                }
            }

            while (i < middle)
            {
                temp[k] = data[i];
                k++;
                i++;
            }

            while (j < end)
            {
                temp[k] = data[j];
                k++;
                j++;
            }

            System.arraycopy(temp, 0, data, start, temp.length);
        }
    }
}
