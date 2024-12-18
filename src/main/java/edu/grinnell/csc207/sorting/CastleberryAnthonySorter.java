package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;


/**
 * My custom sort
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Anthony Castleberry
 */

public class CastleberryAnthonySorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

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
  public CastleberryAnthonySorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
   *
   * @param values
   *   an array to sort.
   */
  @Override
  public void sort(T[] values) {

    if (values.length == 0){
      return;
    } else if(isSorted(values)) {
      insertionSort(values);
    } else {
      selectionSort(values);
    }
  
  }

  // +---------+-------------------------------------------------------
  // | Helpers |
  // +---------+


  public boolean isSorted(T[] values)  {
    int sortPercentage = 0;

    for (int i = 0; i < values.length - 1; i++) {
      if (this.order.compare(values[i], values[i + 1]) < 0) {
        sortPercentage++;
      }
    }

    return (sortPercentage / values.length) > .7;
  }

  public void insertionSort(T[] values) {
    int p = 1;
    /* 
      |-----------------|--------------------|
      |     sorted      |      unsorted      |
      |-----------------|--------------------|
                        p
    */
    for (int i = 1; i < values.length; i++) {
    /* 
      |-----------------|--------------------|
      |     sorted (p-1)| ?    unsorted      |
      |-----------------|--------------------|
                        p
    */
    // if ? is smaller than the last value in sorted
    if (this.order.compare(values[i], values[p - 1]) < 0) {
      for (int j = 0; j < p; j++) {
        // when ? is smaller than the current value being checked in sorted
        if (this.order.compare(values[i], values[j]) < 0) {
          insert(values, j, p, values[p]);
            /* 
              |--------------------|-----------------|
              |   X<?   ?   X>?    |   unsorted      |
              |--------------------|-----------------|
                                   p                               
            */
        // when ? is equal to the current value being checked in sorted, ensures stability
        } else if (this.order.compare(values[i], values[j]) == 0) {
          insert(values, j + 1, p, values[p]);
            /* 
              |--------------------|-----------------|
              |   X<?   ?   X>?    |   unsorted      |
              |--------------------|-----------------|
                                   p                               
            */
        } 
      }
    }
      p++;
    } // for
  } // sort(T[])

  /**
   * shifts all elements one to the right from lb to ub by one
   * replacing the newly opened lb with val,
   * overrides the element at index ub.
   * 
   * @param array array where lements will be shifted in-place
   * @param lb beggining of elements to be shifted(inclusive)
   * @param ub end of elements to be shifted (exclusive); will be overwritten
   * @param val value to replace lb
   */
  private void insert(T[] array, int lb, int ub, T val) {
    /* 
      |---|---|---|---|---|---|
      | 0 | 1 | 2 | 3 | 4 | 5 |
      |---|---|---|---|---|---|
      lb              i   ub      
    */
    for (int i = ub - 1; i >= lb; i--) {
      array[i + 1] = array[i];
    /* 
      |---|---|---|---|---|---|
      | 0 | 1 | 2 | 3 | 4 | 4 |
      |---|---|---|---|---|---|
      lb              i   ub(i + 1)      
    */

    /* 
    after loop updates i
      |---|---|---|---|---|---|
      | 0 | 1 | 2 | 3 | 4 | 4 |
      |---|---|---|---|---|---|
      lb          i  i+1  ub      
    */
  } // for

    /* 
    after loop terminates
      |---|---|---|---|---|---|
      | 0 | 0 | 1 | 2 | 3 | 4 |
      |---|---|---|---|---|---|
      lb                  ub      
    */

    array[lb] = val;

    /* 
      |---|---|---|---|---|---|
      |val| 0 | 1 | 2 | 3 | 4 |
      |---|---|---|---|---|---|
      lb                  ub      
    */
  }

   /**
   * Sort an array in place using selection sort.
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
  public void selectionSort(T[] values) {
    for (int i = 0; i < values.length; i++) {
      int smallest = select(values, i);
      T temp = values[i];
      values[i] = values[smallest];
      values[smallest] = temp; 
    }
  } // sort(T[])

  public int select(T[] array, int lb) {
    int minIndex = lb;
    for (int i = lb + 1; i < array.length; i++) {
      if (this.order.compare(array[minIndex], array[i]) > 0) {
        minIndex = i;
      }
    }
    return minIndex;
  }
}