package learning.genericType;

import java.util.ArrayList;

public class GenericDemo {


  public static void main(String[] args) {
    // Generic Class
    System.out.println("\nGeneric Class");
    //Generic Types Differ Based on Their Type Arguments
    GenericClass<String> stringGeneric = new GenericClass<>("Teo");
    GenericClass<Integer> integerGeneric = new GenericClass<>(10);

    stringGeneric.printGeneric(stringGeneric.getObject());
    stringGeneric.printGeneric(integerGeneric.getObject());

    //--------
    System.out.println("\nGeneric Class: Multiple Generic");
    MultiGenericClass<String, Integer> multiGenericClass = new MultiGenericClass<>("Ti", 20);
    MultiGenericClass<Integer, Integer> multiGenericClassNumber = new MultiGenericClass<>(40, 20);

    multiGenericClass.printGeneric(multiGenericClass.getObj1());
    multiGenericClass.printGeneric(multiGenericClass.getObj2());

    multiGenericClass.printGeneric(multiGenericClassNumber.getObj1());
    multiGenericClass.printGeneric(multiGenericClassNumber.getObj2());


    // Generic Function
    System.out.println("\nGeneric function");
    GenericFunctions.genericDisplay("Ti");
    GenericFunctions.genericDisplay("3 - 8 - 2022");
    GenericFunctions.genericDisplay(2);


    // Advantages of Generics:
    // Code Reuse
    // Type safety

    // Creatinga an ArrayList without any type specified
    ArrayList al = new ArrayList();

    al.add("Sachin");
    al.add("Rahul");
    al.add(10); // Compiler allows this

    String s1 = (String) al.get(0);
    String s2 = (String) al.get(1);

    // Causes Runtime Exception
    try {
      String s3 = (String) al.get(2);
    } catch (Exception e) {
//            throw new RuntimeException(e);
      e.printStackTrace();
    }

    System.out.println("s1: " + s1);
    System.out.println("s2: " + s2);

    // Creating a an ArrayList with String specified
    ArrayList<String> list = new ArrayList<String>();

    list.add("1");
    list.add("2");

    // Now Compiler doesn't allow this
    //al.add(10);

    //  Individual Type Casting is not needed

    String n1 = list.get(0);
    String n2 = list.get(1);
    System.out.println("n1: " + n1);
    System.out.println("n2: " + n2);

    // Sorting Generics
    Integer[] numbers = {100, 22, 58, 41, 6, 50};

    Character[] characters = {'v', 'g', 'a', 'c', 'x', 'd', 't'};

    String[] names = {"Virat", "Rohit", "Abhinay", "Chandu", "Sam", "Bharat", "Kalam"};

    System.out.print("\nSorted Integer array : \n");
    Sorting.sortGeneric(numbers);
    Sorting.printGeneric(numbers);

    System.out.print("\nSorted Character array : \n");
    Sorting.sortGeneric(characters);
    Sorting.printGeneric(characters);

    System.out.print("\nSorted String array :  \n");
    Sorting.sortGeneric(names);
    Sorting.printGeneric(names);


    // Generic algorithms
    System.out.println("\nGeneric Algorithms");
    // Example with Integer array
    Integer[] intArray = {3, 7, 2, 8, 1};
    Integer maxInteger = GenericFindMax.findMax(intArray);
    System.out.println("\nMax Integer: " + maxInteger);

    // Example with Double array
    Double[] doubleArray = {3.5, 7.2, 2.8, 8.1, 1.6};
    Double maxDouble = GenericFindMax.findMax(doubleArray);
    System.out.println("\nMax Double: " + maxDouble);

    // Example with String array
    String[] stringArray = {"apple", "orange", "banana", "pineapple"};
    String maxString = GenericFindMax.findMax(stringArray);
    System.out.println("\nMax String: " + maxString);

    // Example with custom object array
    Person[] personArray = {
      new Person("Alice", 30),
      new Person("Bob", 25),
      new Person("An", 35),
      new Person("Dan", 1)
    };
    Person maxPerson = GenericFindMax.findMax(personArray);
    System.out.println("Oldest Person: " + maxPerson.getName() + " (" + maxPerson.getAge() + ")");

  }


}
