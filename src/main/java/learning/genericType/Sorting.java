package learning.genericType;

public class Sorting {
  public static <T extends Comparable<T>> void sortGeneric(T[] array) {

    // Bubble sort
    for (int i = 0; i < array.length - 1; i++) {
      for (int j = i + 1; j < array.length; j++) {
        if (array[i].compareTo(array[j]) > 0) {
          swap(array, i, j);
        }
      }
    }

  }

  public static <T> void printGeneric(T[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }
  }

  public static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}
