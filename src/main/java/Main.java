import dao.DAO;
import dao.DbHandler;
import model.Well;

import java.sql.SQLException;
import java.util.List;

public class Main {
  public static void main(String[] args) throws SQLException {

    DAO dao = new DAO(DbHandler.getInstance());

    List<Well> wells = dao.getAllWells();
    wells.forEach(System.out::println);





  }
}
