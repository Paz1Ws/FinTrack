package Clases.ClasesComunes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBDFT {

    // Atributos de la conexión
    private static final String IP = "LAPTOP-3U789987";
    private static final String PUERTO = "1433";
    private static final String BASE_DATOS = "BDFINTRACK";
    private static final String USUARIO = "FINTRACK";
    private static final String PASSWORD = "123";
    
    // Cadena de conexión
    private static final String CADENA_CONEXION = 
        "jdbc:sqlserver://" + IP + ":" + PUERTO + ";databaseName=" + BASE_DATOS + 
        ";encrypt=true;trustServerCertificate=true";

    // Método para obtener la conexión
    public static Connection establecerConexion() {
        Connection conexion = null;
        try {
            // Establecer la conexión
            conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD);
            JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        // Prueba de conexión
        Connection conexion = establecerConexion();
        if (conexion != null) {
            try {
                conexion.close(); // Cerrar la conexión
                JOptionPane.showMessageDialog(null, "Conexión cerrada correctamente.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
    

