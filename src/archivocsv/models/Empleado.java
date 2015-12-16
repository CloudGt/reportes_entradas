/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.models;

import archivocsv.ArchivoCSV;
import archivocsv.helpers.Dialogs;
import archivocsv.helpers.Helper;
import java.io.File;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */

public class Empleado {
    private String id;
    private String nombre;
    private String marcacion;
    public int codigo;
    private ArchivoCSV archivo = new ArchivoCSV();
    
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarcacion() {
        return marcacion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarcacion(String marcacion) {
        this.marcacion = marcacion;
    }
    
    public Empleado(String id, String nombre, String marcacion) {
        this.id = id;
        this.nombre = nombre;
        this.marcacion = marcacion;
    }

    public Empleado() {
    }
    
    //Metodo para importar la lista de registros de la base de datos
    public static ObservableList<Empleado> getListaEmpleado(){
        ObservableList<Empleado> empleados = FXCollections.observableArrayList();
    
        try{
            
            Connection con = Helper.getConnection();
            String sql = "SELECT DISTINCT t_marcacion.id, t_marcacion.nombre FROM t_marcacion";
            int contar = 0;
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
            contar+=1;
            Empleado empleado = new Empleado();
            empleado.setId(rs.getString("id"));
            empleado.setNombre(rs.getString("nombre"));
            empleado.setCodigo(contar);
            
            empleados.add(empleado);
            }
            
        }catch(Exception e){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "Error al cargar la lista", e.getMessage());
            error.showAndWait();
        }
        return empleados;
    }
    
    //Metodo para insertar los registros en la base de datos despues de seleccionar el archivo
    public boolean insertarMarcacion(String nombre) throws UnknownHostException{
        File archivoServidor = new File("\\\\192.168.0.71\\Q\\RRHH\\"+nombre); 
        String insertSQL = "BULK INSERT t_marcacion "
                + "FROM '\\\\192.168.0.71\\Q\\RRHH\\"+nombre+"' "
                + "WITH (FIELDTERMINATOR=',' , ROWTERMINATOR='\n')";
        try{
            PreparedStatement insertarStatement = Helper.getConnection().prepareStatement(insertSQL);
            insertarStatement.executeUpdate();
            Alert mensaje = Dialogs.getDialog(Alert.AlertType.INFORMATION, "DACOTRANS", null, "Datos ingresados correctamente");
            mensaje.showAndWait();
            archivo.importarListas();
        }catch(SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "Error al ingresar datos", ex.getMessage());
            error.showAndWait();
            archivoServidor.delete();
            return false;
        }
            return true;
    }
    //Metodo utilizado para eliminar registros
    public static boolean eliminarMarcacion(Empleado empleado){
        String deleteSQL = "DELETE FROM t_marcacion " + "WHERE id = ? AND nombre = ? AND marcacion = ? ";
        
        try{
            PreparedStatement deleteStatement = Helper.getConnection().prepareStatement(deleteSQL);
            
            deleteStatement.setString(1, empleado.getId());
            deleteStatement.setString(2, empleado.getNombre());
            deleteStatement.setString(3, empleado.getMarcacion());
            
            deleteStatement.executeUpdate();
        }catch(Exception e){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "Error al eliminar registro", e.getMessage());
            error.showAndWait();
            return false;
        }
            return true;
    }
    //Metodo utilizado para editar registros
    public static boolean editarMarcacion(Empleado empleado){
        String updateSql = "UPDATE t_marcacion SET nombre = ? , marcacion = ? WHERE id = ? AND nombre = ? AND marcacion = ?";
        try{
            PreparedStatement update = Helper.getConnection().prepareStatement(updateSql);
            
            update.setString(1, empleado.getNombre());
            update.setString(2, empleado.getMarcacion());
            update.setString(3, empleado.getId());
            update.setString(4, empleado.getNombre());
            update.setString(5, empleado.getMarcacion());

            update.executeUpdate();
            
        }catch(SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "Error al actualizar", ex.getMessage());
            error.showAndWait();
            return false;
        }
        return true;
    }
}



