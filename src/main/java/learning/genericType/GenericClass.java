package learning.genericType;

public class GenericClass<T> {
  T object;

  public GenericClass(T object) {
    this.object = object;
  }

  public T getObject() {
    return object;
  }

  public <T> void printGeneric(T generic) {
    System.out.println("Print generic " + generic);
  }
}
