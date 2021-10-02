package dao;

import lombok.AllArgsConstructor;
import model.Equipment;
import model.Well;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DAO {
  private final DbHandler handler;

  public List<Well> getAllWells() throws SQLException {

      List<Well> wells = new ArrayList<>();

    try(Statement statement = handler.getConnection().createStatement()){
      ResultSet resultSet = statement.executeQuery("SELECT * FROM well");
      while (resultSet.next()){
        wells.add(new Well(resultSet.getInt("ID"), resultSet.getString("NAME")));
      }

      List<Equipment> equipmentList;

      for (Well well:wells){
        equipmentList = new ArrayList<>();

        resultSet = statement.executeQuery("SELECT * FROM equipment where WELL_ID = " + well.getId());
        while (resultSet.next()){
          equipmentList.add(new Equipment(resultSet.getInt("ID"), resultSet.getString("NAME")));
        }

        well.setEquipmentList(equipmentList);
      }

    }catch (SQLException e){
      e.printStackTrace();

    }

    return wells;
  }

}
