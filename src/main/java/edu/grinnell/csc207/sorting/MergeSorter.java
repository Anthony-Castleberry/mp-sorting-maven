package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  /**
   * Helper array to store sorted items
   */
  T[] helper;

  int count = 0;
  int Lcount = 0;
  int Peas = 0;
  
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
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
  helper = values.clone();
  if(values.length > 1) {
     sortHelper(values, 0, values.length);
  }
  values = helper;
  } // sort(T[])

  public void merge(T[] values, int lb, int mb, int ub) {


    int i = lb;
    int j = mb;
    int track = lb;
    while(j < ub) {
      T rightVal = values[j];
      T leftVal = values[i];

      if (i < mb) {
       if (this.order.compare(leftVal, rightVal) > 0) {
          helper[track] = rightVal;
          j++;
          track++;
        } else {
          helper[track] = leftVal;
          i++;
          track++;
        }
      } else {
        helper[track] = rightVal;
        j++;
        track++;
      }
    } // while
  } // merge

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  public void sortHelper(T[] values, int lb, int ub) {
    if(ub - lb == 2) {
      if (this.order.compare(values[lb], values[ub - 1]) > 0) {
        ArrayUtils.swap(values, lb, (ub - 1)); 
      }
    } else if (ub - lb > 2) {
      sortHelper(values, lb, (lb + ub) / 2);
      sortHelper(values, (lb + ub) / 2, ub);
      merge(values, lb, (lb + ub) / 2, ub);
    }
  }

} // class MergeSorter
