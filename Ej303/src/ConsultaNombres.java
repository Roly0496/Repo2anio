import java.sql.*;
import java.util.Scanner;

public class ConsultaNombres {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir la letra al usuario
        System.out.print("Introduce la letra inicial: ");
        String letra = sc.nextLine();

        // Consulta: LIKE 'A%'
        String sql = "SELECT nombre FROM empleados WHERE nombre LIKE ?";

        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/empleados", "root", "100%Informaticoa");
                PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setString(1, letra + "%"); // ej. "A%"
            ResultSet rs = pst.executeQuery();

            System.out.println("Nombres de empleados que empiezan por '" + letra + "':");
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

