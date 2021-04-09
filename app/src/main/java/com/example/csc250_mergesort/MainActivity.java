package com.example.csc250_mergesort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.Random;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity
{
    {
        public class MergeSort extends Thread
        {
            private int[] ar;
            private int begin;
            private int end;

            public MergeSort(int[] ar, int begin, int end)
            {
                this.ar = ar;
                this.begin = begin;
                this.end = end;
            }

            public void run()
            {
                System.out.println("*** " + this.getId() + " is starting between " + begin + " and " + end);


                if(begin != end)
                {
                    int begin1 = begin;
                    int end1 = (begin + end)/2;
                    int begin2 = end1 + 1;
                    int end2 = end;

                    MergeSort leftHalf = new MergeSort(ar, begin1, end1);
                    MergeSort rightHalf = new MergeSort(ar, begin2, end2);
                    leftHalf.start();
                    rightHalf.start();
                    try
                    {
                        leftHalf.join();
                        rightHalf.join();


                        int[] temp = new int[end - begin + 1];
                        int pos1 = begin1;
                        int pos2 = begin2;

                        for(int i = 0; i < temp.length; i++)
                        {
                            if(pos1 <= end1 && pos2 <= end2)
                            {
                                if(ar[pos1] < ar[pos2])
                                {
                                    temp[i] = ar[pos1];
                                    pos1++;
                                }
                                else
                                {
                                    temp[i] = ar[pos2];
                                    pos2++;
                                }
                            }
                            else if(pos1 <= end1)
                            {
                                temp[i] = ar[pos1];
                                pos1++;
                            }
                            else
                            {
                                temp[i] = ar[pos2];
                                pos2++;
                            }
                        }


                        for(int tempPos = 0, arPos = begin; tempPos < temp.length; tempPos++, arPos++)
                        {
                            ar[arPos] = temp[tempPos];
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
            public static void main(String[] args)
            {
                Random r = new Random();

                int[] ar = new int[20];
                for(int i = 0; i < ar.length; i++)
                {
                    ar[i] = r.nextInt(1000);
                }

                MainActivity.printArray(ar);
                MergeSort firstWorker = new MergeSort(ar, 0, ar.length-1);
                firstWorker.start();
                try
                {
                    firstWorker.join();
                    MainActivity.printArray(ar);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

            public static void printArray(int[] ar)
            {
                for(int i = 0; i < ar.length; i++)
                {
                    System.out.print(ar[i] + " ");
                }
                System.out.println("");
            }

                }

            }
        }
        }
    }
}