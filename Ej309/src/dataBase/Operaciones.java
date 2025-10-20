package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Operaciones {

    private static Connection conn;
    private final String usuario = "root";
    private final String clave = "100%Informatico";
    private final String url ="jdbc:mysql://localhost:3306/empleados";

    private Operaciones() {
        try {
            this.conn = DriverManager.getConnection(url, usuario, clave);
        }catch(SQLException sqle) {
            System.out.println("Error al abrir la conexxión");
        }
    }

    public static Connection getConnection() {
        if (Operaciones.conn == null)
            new Operaciones();
        return Operaciones.conn;
    }

    public static boolean existsClient(int idClient) {

    }

    public static boolean existsBook(int idBook) {

    }

    
}
