package learning.genericType;

public class MultiGenericClass<T, U> {
  T obj1;
  U obj2;

  public MultiGenericClass(T obj1, U obj2) {
    this.obj1 = obj1;
    this.obj2 = obj2;
  }

  public T getObj1() {
    return obj1;
  }

  public U getObj2() {
    return obj2;
  }

  public <T, U> void printGeneric(T generic) {
    System.out.println("Print generic " + generic);
  }
}
