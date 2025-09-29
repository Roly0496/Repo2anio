import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransaccionEmpleado {

    static String usuario = "root";
    static String clave = "100%Informatico";
    static String url ="jdbc:mysql://localhost:3306/empleados";
    static Connection conn;

    public static void realizarTransaccion(String nombre1, String nombre2, String nombre3) {
        String insert = "UPDATE cuentas SET saldo = saldo - ? WHERE numero_cuenta = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, clave)) {
            conn.setAutoCommit(false); // 🚨 Desactivamos auto-commit

            try (
                    PreparedStatement psInsert = conn.prepareStatement(insert);
            ) {
                // Insertar
                psInsert.setString(1, "111111");
                psInsert.setString(1, "111111");
                psInsert.setString(1, "111111");
                psInsert.setString(1, "111111");
                psInsert.setString(1, "111111");
                psInsert.setString(1, "111111");


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
