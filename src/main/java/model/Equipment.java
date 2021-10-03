package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@AllArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Equipment {

  @XmlAttribute
  private int id;

  @XmlAttribute
  private String name;


  @Override
  public String toString() {
    return "ID: " + id +
      ", Name: '" + name + '\'';
  }
}
