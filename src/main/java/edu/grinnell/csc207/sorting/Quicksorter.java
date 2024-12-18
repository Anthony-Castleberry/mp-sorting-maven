package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;
import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Anthony Castleberry
 */

public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  int initPivot;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length > 1) {
      Random Pivot = new Random();
      int pivotOne = Pivot.nextInt(values.length - 2);
      int pivotTwo = Pivot.nextInt(values.length - 2);
      int pivotThree = Pivot.nextInt(values.length - 2);
      int pivotNum = findMedian(pivotOne, pivotTwo, pivotThree);
      int[] Flag = this.dnf(values, pivotNum, 0, values.length);
      sortHelper(values, Flag[0], Flag[1], 0, values.length);

    } // if
  } // sort(T[])



  public void sortHelper(T[] values, int r, int b, int lb, int ub) {
    if (r > 1 && ub - r > 1) {
      int rPivotOne = (new Random()).nextInt(ub - (ub - r));
      int rPivotTwo = (new Random()).nextInt(ub - (ub - r));
      int rPivotThree = (new Random()).nextInt(ub - (ub - r));
      int rPivot = findMedian(rPivotOne, rPivotTwo, rPivotThree);
      int[] rFlag = dnf(values, rPivot, lb, r);
      sortHelper(values, rFlag[0], rFlag[1], lb, r);
    } // if

    if (ub - b > 1 && b > 2) {
      int bPivotOne = (new Random()).nextInt(ub - b) + b;
      int bPivotTwo = (new Random()).nextInt(ub - b) + b;
      int bPivotThree = (new Random()).nextInt(ub - b) + b;
      int bPivot = findMedian(bPivotOne, bPivotTwo, bPivotThree);
      int[] bFlag = dnf(values, bPivot, b, values.length);
      sortHelper(values, bFlag[0], bFlag[1], b, values.length);
    }
  }

  /**
   * partitions the array into 3 seperate areas
   * 
   * @param values
   * array to be partitioned
   */
  private int[] dnf(T[] values, int pivot, int lb, int ub) {
    int r = lb;
    int w = lb;
    int b = ub;
    int count = 0;
    while(b != w) {
      count++;
      if (this.order.compare(values[w], values[pivot]) > 0) {
        ArrayUtils.swap(values, w, b - 1);
        b--;
      } else if (this.order.compare(values[w], values[pivot]) < 0) {
        ArrayUtils.swap(values, w, r);
        r++;
        w++;
      } else if (this.order.compare(values[w], values[pivot]) == 0) {
        w++;
      } 
    }
    return new int[] {r, b};
  } // dnf

  private int findMedian (int x, int y, int z) {
    if ((x >= y && x <= z) || (x <= y && x >= z)) {
      return x;
    } else if ((y >= x && y <= z) || (y <= x && y >= z)) {
      return y;
    } if (((z >= y && z <= x) || (z <= y && z >= x))) {
      return z;
    } else {
      return x;
    }

  }
} // class Quicksorter
