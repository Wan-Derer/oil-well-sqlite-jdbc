package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Well {
  private int id;
  private String name;
  private List<Equipment> equipmentList = new ArrayList<>();

  public Well(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public void addEqupment(Equipment equipment) {

  }


  @Override
  public String toString() {
    return "Well{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", equipmentList=" + equipmentList +
      '}';
  }
}
