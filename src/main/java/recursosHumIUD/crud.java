/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recursosHumIUD;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Juan
 */
public class crud {
    public void insertarDato(String tipo_identificacion, int numero_identificacion, String nombres, String apellidos){
        String query = "insert into funcionario (tipo_identificacion, numero_identificacion ,nombres , apellidos)values (?,?,?,?)";
        try {
            Connection conn = dbConnection.conectar();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tipo_identificacion);
            ps.setInt(2, numero_identificacion);
            ps.setString(3, nombres);
            ps.setString(4, apellidos);
            
            ps.executeUpdate();
            System.out.println("Insertado con exito");
                    
        } catch (Exception e) {
            System.out.println("error en la insersion de datos");
            e.printStackTrace();
        }

    }
    
//    public void leerDatos(){
//        String query = "select * from funcionario";
//        try {
//            Connection conn = dbConnection.conectar();
//            PreparedStatement ps = conn.prepareStatement(query);
//            
//            ResultSet rs = ps.executeQuery();
//            Object[] funcionario = new Object[8];
//            
//            while (rs.next()) {
//                String tipo_identificacion=rs.getString("tipo_identificacion");
//                int numero_identificacion=rs.getInt("numero_identificacion");
//                String nombres=rs.getString("nombres");
//                String apellidos=rs.getString("apellidos");
//                String estado_civil=rs.getString("estado civil");
//                String sexo=rs.getString("sexo");
//                String direccion=rs.getString("direccion");
//                String telefono=rs.getString("telefono");
//                
//                
//                System.out.println("tipo_identificacion" + tipo_identificacion + "numero_identificacion" + numero_identificacion + "nombres" + nombres + "apellidos" + apellidos);
//                
//            }
//        } catch (Exception e) {
//            System.out.println("error en la lectura de datos");
//            e.printStackTrace();
//        }
//    }
    
public void leerDatos(JTable tablaFuncionarios) {
        String query = "SELECT * FROM funcionario";

        try {
            Connection conn = dbConnection.conectar(); // Tu clase de conexión
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // Crear modelo de tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Tipo Identificación");
            modelo.addColumn("Número Identificación");
            modelo.addColumn("Nombres");
            modelo.addColumn("Apellidos");
            modelo.addColumn("Estado Civil");
            modelo.addColumn("Sexo");
            modelo.addColumn("Dirección");
            modelo.addColumn("Teléfono");

            // Llenar el modelo con los datos del ResultSet
            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getString("tipo_identificacion");
                fila[1] = rs.getInt("numero_identificacion");
                fila[2] = rs.getString("nombres");
                fila[3] = rs.getString("apellidos");
                fila[4] = rs.getString("estado_civil"); // 👈 asegúrate de usar guion bajo, no espacio
                fila[5] = rs.getString("sexo");
                fila[6] = rs.getString("direccion");
                fila[7] = rs.getString("telefono");

                modelo.addRow(fila);
            }

            // Asignar el modelo a la JTable
            tablaFuncionarios.setModel(modelo);

            // Cerrar recursos
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al leer los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
//    public void actualizarDato(int numero_identificacion, String nombres, String apellidos){
//        String query = "update funcionario set nombres=?, apellidos=? where numero_identificacion=?";
//        try {
//            Connection conn = dbConnection.conectar();
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setInt(3, numero_identificacion);
//            ps.setString(1, nombres);
//            ps.setString(2, apellidos);
//            
//            ps.executeUpdate();
//            System.out.println("actualizado con exito");
//                    
//        } catch (Exception e) {
//            System.out.println("error en la actualizacion de datos");
//            e.printStackTrace();
//        }
//    }
//    
//        public void eliminarDato(int numero_identificacion){
//        String query = "delete from funcionario where numero_identificacion=?";
//        try {
//            Connection conn = dbConnection.conectar();
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setInt(1, numero_identificacion);
//            
//            ps.executeUpdate();
//            System.out.println("eliminado con exito");
//                    
//        } catch (Exception e) {
//            System.out.println("error en la eliminacion de datos");
//            e.printStackTrace();
//        }
//    }
public void insertarFuncionario(String tipo_identificacion, int numero_identificacion, String nombres, String apellidos,
                                    String estado_civil, String sexo, String direccion, String telefono) {
        String query = "INSERT INTO funcionario (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, tipo_identificacion);
            ps.setInt(2, numero_identificacion);
            ps.setString(3, nombres);
            ps.setString(4, apellidos);
            ps.setString(5, estado_civil);
            ps.setString(6, sexo);
            ps.setString(7, direccion);
            ps.setString(8, telefono);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionario agregado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar funcionario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actualizarFuncionario(String tipo_identificacion, int numero_identificacion, String nombres, String apellidos,
            String estado_civil, String sexo, String direccion, String telefono) {
        String query = "UPDATE funcionario SET tipo_identificacion = ?, nombres = ?, apellidos = ?, estado_civil = ?, sexo = ?, direccion = ?, telefono = ? "
                + "WHERE numero_identificacion = ?";

        try (Connection conn = dbConnection.conectar(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, tipo_identificacion);
            ps.setString(2, nombres);
            ps.setString(3, apellidos);
            ps.setString(4, estado_civil);
            ps.setString(5, sexo);
            ps.setString(6, direccion);
            ps.setString(7, telefono);
            ps.setInt(8, numero_identificacion);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el funcionario con ese número de identificación");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar funcionario: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void eliminarFuncionario(int numero_identificacion) {
        String query = "DELETE FROM funcionario WHERE numero_identificacion = ?";

        try (Connection conn = dbConnection.conectar(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, numero_identificacion);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el funcionario con ese número de identificación");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar funcionario: " + e.getMessage());
            e.printStackTrace();
        }
    }
        
    
    
}
