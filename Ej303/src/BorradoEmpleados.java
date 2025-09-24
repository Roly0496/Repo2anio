import java.sql.*;
import java.util.Scanner;

public class BorradoEmpleados {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir el número del empleado
        System.out.print("Introduce el número de empleado a borrar: ");
        int numEmpleado = sc.nextInt();

        String sql = "DELETE FROM empleados WHERE numero = ?";

        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/empleados", "root", "100%Informatico");
                PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setInt(1, numEmpleado);
            int filas = pst.executeUpdate();

            if (filas > 0) {
                System.out.println("Empleado borrado correctamente.");
            } else {
                System.out.println("No se encontró un empleado con ese número.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

