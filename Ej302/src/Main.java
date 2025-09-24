import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        String usuario = "root";
        String clave = "100%Informatico";
        String url ="jdbc:mysql://localhost:3306/empleados";
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, usuario,clave);
            amosarInformacionBD(conn);
            amosarProxectos(conn);
            inserirProxecto(conn, 11, "Bases de datos", "Lugo", 3);
            amosarProxectos(conn);
            eliminarProxecto(conn, 11);
            amosarProxectos(conn);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void amosarInformacionBD(Connection conn){
        try {
            DatabaseMetaData dbmd =  conn.getMetaData();
            String xestor         = dbmd.getDatabaseProductName();
            String conector       = dbmd.getDriverName();
            String url            = dbmd.getURL();
            String usuario        = dbmd.getUserName();

            System.out.println("Información da base de datos");
            System.out.println("----------------------------");
            System.out.println("Xestor: "   + xestor);
            System.out.println("Conector: " + conector);
            System.out.println("URL: "      + url);
            System.out.println("Usuario: "  + usuario);

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void amosarProxectos(Connection conn){
        Statement stmt;
        ResultSet rs;
        String sql = "SELECT * from proyecto;";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println("Proxectos:");
            while (rs.next()) {
                System.out.println("\tNúmero: "        + rs.getInt("Numproy") +
                        ", Nome: "         + rs.getString("Nombreproy") +
                        ", Lugar: "        + rs.getString("Lugarproy") +
                        ", Departamento: " + rs.getInt("departamento_Numdep"));
            };
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void inserirProxecto(Connection conn, int num, String nome, String lugar, int depto){
        PreparedStatement ps;
        Statement st;
        String sql = "INSERT INTO proyecto VALUES (?,?,?,?);";
        try {
            ps = conn.prepareStatement(sql);
            st = conn.createStatement();
            ps.setInt(1, num);
            ps.setString(2, nome);
            ps.setString(3, lugar);
            ps.setInt(4, depto);
            int numTuplas = ps.executeUpdate();
            System.out.println("Sentenza: " + ps.toString());
            System.out.println("Tuplas afectadas: " + numTuplas);
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void eliminarProxecto(Connection conn, int num){
        PreparedStatement ps;
        String sql = "DELETE FROM proyecto WHERE Numproy = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);
            int numTuplas = ps.executeUpdate();
            System.out.println("Sentenza: " + ps.toString());
            System.out.println("Tuplas afectadas: " + numTuplas);
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}