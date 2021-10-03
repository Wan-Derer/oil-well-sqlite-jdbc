import dao.DAO;
import dao.DbHandler;
import model.DBinfo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static DAO dao = null;

    static {
        try {
            dao = new DAO(DbHandler.getInstance());

// TODO: check tables and create them if not exist

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws JAXBException {

        dao.getAllWells().forEach(System.out::println);

        while (true) {
            System.out.println(
                    "\n1. Add Equipment to Well;" +
                            "\n2. Show all Wells;" +
                            "\n3. Report to XML;" +
                            "\n0. Exit" +
                            "\nEnter Command: ");

            switch (sc.nextLine()) {
                case "1":
                    addEquipmentToWell();
                    showAllWells();
                    break;
                case "2":
                    showAllWells();
                    break;
                case "3":
                    reportToXML();
                    break;
                case "0":
                    return;

            }
        }

    }

    private static void addEquipmentToWell() {
        System.out.println("\nEnter Well name (Enter to exit): ");
        String wellName = sc.nextLine();
        if (wellName.isEmpty()) return;

        while (true) {
            System.out.println("\nEnter number of Equipment (0 to exit): ");
            try {
                int qty = sc.nextInt();
                if (qty == 0) return;

                if (qty > 0) {
                    dao.addEquipmentByWellName(wellName, qty);
                    return;
                }

            } catch (NumberFormatException ignored) {
            }
        }
    }


    private static void showAllWells() {
        System.out.println();
        dao.getAllWells().forEach(System.out::println);
    }


    private static void reportToXML() throws JAXBException {
        System.out.println("\nEnter file name: ");
        String xmlFileName = sc.nextLine();
        if (!xmlFileName.toLowerCase().endsWith(".xml")) xmlFileName = xmlFileName + ".xml";

        try (FileWriter fileWriter = new FileWriter(xmlFileName)) {

            JAXBContext context = JAXBContext.newInstance(DBinfo.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            DBinfo dbinfo = new DBinfo(dao.getAllWells());
            marshaller.marshal(dbinfo, fileWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("XML file has been written");

    }

}
