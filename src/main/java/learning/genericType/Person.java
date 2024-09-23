package learning.genericType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Person implements Comparable<Person> {
  private String name;
  private int age;

  @Override
  public int compareTo(Person other) {
    return Integer.compare(this.age, other.age);
  }

}
