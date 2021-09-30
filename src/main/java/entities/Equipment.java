package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Equipment {

  private int id;
  private String name;


  @Override
  public String toString() {
    return "Equipment{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
