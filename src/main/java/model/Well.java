package model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Well {

  @XmlAttribute
  private int id;
  @XmlAttribute(name = "name")
  private String name;
  @XmlElement(name = "equipment")
  private List<Equipment> equipmentList = new ArrayList<>();

  public Well(int id, String name) {
    this.id = id;
    this.name = name;
  }


  @Override
  public String toString() {
    return "Well {" +
      "ID: " + id +
      ", Name: '" + name + '\'' +
      ", Equipment: " + equipmentList +
      '}';
  }
}
