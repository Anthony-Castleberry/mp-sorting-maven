package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

public class TestCastleberryAnthonySorter extends TestSorter{
  /**
   * Test my custom sorter
   */
  @BeforeAll
  static void setup() {
    stringSorter = new CastleberryAnthonySorter<String>((x,y) -> x.compareTo(y));
    intSorter = new CastleberryAnthonySorter<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestMergeSorter
