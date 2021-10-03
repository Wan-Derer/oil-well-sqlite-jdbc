package dao;

import lombok.AllArgsConstructor;
import model.Equipment;
import model.Well;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class DAO {
    private final DbHandler handler;

    public List<Well> getAllWells() {

        List<Well> wells = new ArrayList<>();

        try (Statement statement = handler.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM well");
            while (resultSet.next()) {
                wells.add(new Well(resultSet.getInt("ID"), resultSet.getString("NAME")));
            }

            wells.forEach(well -> well.setEquipmentList(getEquipmentListByWell(well)));

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return wells;
    }

    public void addEquipmentByWellName(String wellName, int equipmentQty) {

        Well well;

        try {
            well = getWellByName(wellName);
        } catch (IllegalArgumentException e) {
            well = addNewWell(wellName);
        }

        for (int i = 0; i < equipmentQty; i++) {
            addOneEquipmentByWell(well);
        }

    }

    private Well addNewWell(String wellName) {

        try (PreparedStatement statement = handler.getConnection().prepareStatement(
                "INSERT INTO well (name) VALUES (?)"
        )) {
            statement.setString(1, wellName);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getWellByName(wellName);
    }

    private void addOneEquipmentByWell(Well well) {

        try (PreparedStatement statement = handler.getConnection().prepareStatement(
                "INSERT INTO equipment (name, well_id) VALUES (?, ?)"
        )) {
            statement.setString(1, UUID.randomUUID().toString());
            statement.setInt(2, well.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Well getWellByName(String wellName) {
        Well well = null;

        try (Statement statement = handler.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM well WHERE name = '" + wellName + "'"
            );

            if (resultSet.isClosed()) {
                throw new IllegalArgumentException("No such well(s)");
            }

            resultSet.next();
            well = new Well(resultSet.getInt("ID"), resultSet.getString("NAME"));
            well.setEquipmentList(getEquipmentListByWell(well));

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return well;
    }

    private List<Equipment> getEquipmentListByWell(Well well) {
        List<Equipment> equipmentList = new ArrayList<>();

        try (Statement statement = handler.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM equipment where WELL_ID = " + well.getId());
            while (resultSet.next()) {
                equipmentList.add(new Equipment(resultSet.getInt("ID"), resultSet.getString("NAME")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipmentList;
    }
}




