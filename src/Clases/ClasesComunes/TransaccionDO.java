package Clases.ClasesComunes;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDO {

    // Método para crear una nueva transacción
    // Crear transacción
public void crearTransaccion(Transaccion transaccion) throws SQLException {
    String sql = "INSERT INTO transacciones (fecha, descripcion, monto, tipo, docRespaldo, idDoc) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection con = ConexionBDFT.establecerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDate(1, java.sql.Date.valueOf(transaccion.getFecha()));
        ps.setString(2, transaccion.getDescripcion());
        ps.setDouble(3, transaccion.getMonto());
        ps.setString(4, transaccion.getTipo());
        ps.setString(5, transaccion.getDocRespaldo());
        ps.setString(6, transaccion.getIdDoc());
        ps.executeUpdate();
    }
}

// Leer transacciones
public List<Transaccion> leerTransacciones() throws SQLException {
    String sql = "SELECT * FROM[dbo].[transacciones]";
    List<Transaccion> transacciones = new ArrayList<>();

    try (Connection con = ConexionBDFT.establecerConexion();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            LocalDate fecha = rs.getDate("fecha").toLocalDate();
            String descripcion = rs.getString("descripcion");
            double monto = rs.getDouble("monto");
            String tipo = rs.getString("tipo");
            String docRespaldo = rs.getString("docRespaldo");
            String idDoc = rs.getString("idDoc");

            transacciones.add(new Transaccion(fecha, descripcion, monto, tipo, docRespaldo, idDoc));
        }
    }
    return transacciones;
}
}
