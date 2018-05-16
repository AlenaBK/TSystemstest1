package com.tsystems.javaschool.tasks.pyramid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {
    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public static int[][] buildPyramid(List<Integer> inputNumbers) {
        if (inputNumbers.contains(null)) throw new CannotBuildPyramidException();
        if (Collections.max(inputNumbers) == 0) throw new CannotBuildPyramidException();
        Collections.sort(inputNumbers);
        int[][] result;
        int n = inputNumbers.size();
        double t = (Math.sqrt(8 * n + 1) - 1) / 2;
        int h = (int) t;
        result = new int[h][h * 2 - 1];
        if (t % 1 == 0) {
            int temp = 0;
            for (int i = 0; i < result.length; i++) {
                for (int j = h - i - 1; j < result[i].length - (h - i - 1); j += 2) {
                    result[i][j] = inputNumbers.get(temp);
                    temp++;
                }
            }
            return result;
        } else throw new CannotBuildPyramidException();
    }
}
