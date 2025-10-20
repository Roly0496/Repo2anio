import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransaccionEmpleado {

    static String usuario = "root";
    static String clave = "100%Informatico";
    static String url ="jdbc:mysql://localhost:3306/empleados";
    //static String dataBase = "empleados";
    static Connection conn;

    public static void realizarTransaccion(String nombre1, String nombre2, String nombre3) {
        String insert = "Insert into empleado (NSS, Nombre, Numdept) Values (?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, clave)) {
            conn.setAutoCommit(false); // 🚨 Desactivamos auto-commit

            try (
                    PreparedStatement psInsert = conn.prepareStatement(insert);
            ) {
                // Insertar
                psInsert.setString(1,"123456");
                psInsert.setString(2,nombre1);
                psInsert.setString(3,"1");
                psInsert.executeUpdate();

                psInsert.setString(1,"654123");
                psInsert.setString(2,nombre2);
                psInsert.setString(3,"2");
                psInsert.executeUpdate();

                psInsert.setString(1,"321456");
                psInsert.setString(2,nombre3);
                psInsert.setString(3,"3");
                psInsert.executeUpdate();

                conn.commit(); // ✅ Si todo sale bien, confirmamos
                System.out.println("Transacción completada con éxito.");

            } catch (SQLException e) {
                conn.rollback(); // ❌ Si algo falla, revertimos
                System.err.println("Error en transacción, rollback ejecutado: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true); // 🔄 Buen hábito: restaurar estado
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
