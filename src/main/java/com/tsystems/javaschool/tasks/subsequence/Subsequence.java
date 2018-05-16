package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Subsequence {
    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public static boolean find(List x, List y) {
        if (x == null || y == null) throw new IllegalArgumentException();

        if (x.size() > y.size()) return false;

        List list1 = new ArrayList();
        List list2 = new ArrayList();

        int temp = 0;
        for (int i = 0; i < x.size(); i++) {
            for (int j = temp; j < y.size(); j++) {
                if (x.get(i).equals(y.get(j)) && !list1.contains(x.get(i)) && !list2.contains(y.get(j))) {
                    list1.add(x.get(i));
                    list2.add(y.get(j));
                    temp = j;
                }
            }
        }

        if (x.size() != list1.size()) return false;

        return list1.toString().equals(list2.toString());
    }
}
