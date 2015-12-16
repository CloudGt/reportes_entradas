/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.controllers;

import archivocsv.ArchivoCSV;
import archivocsv.ArchivoCSV.CRUDOperation;
import archivocsv.helpers.Dialogs;
import archivocsv.models.Empleado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */

public class EditarMarcacionController implements Initializable {
    
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNumero;
    
    private Stage dialogStage;
    private Empleado empleado;
    private boolean presionadoOk;
    private ArchivoCSV.CRUDOperation operacion;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOperacion(ArchivoCSV.CRUDOperation operacion) {
        this.operacion = operacion;
    }
    
    public boolean fuePresionadoOk(){
    return this.presionadoOk;
    }
    
    //Metodo utilizado para mandar los datos a la ventana de editar marcación
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        
        txtID.setText((empleado.getId()));
        txtNombre.setText((empleado.getNombre()));
        txtNumero.setText((Integer.toString(empleado.getCodigo())));
    }
    //Metodo para el boton aceptar de la ventana editar registro
    @FXML
    private void aceptarCambios(){
        if(esMarcacionValida()){
            empleado.setId(txtID.getText());
            empleado.setNombre(txtNombre.getText());
            if(operacion.equals(CRUDOperation.Editar)){
              presionadoOk = Empleado.editarMarcacion(empleado);
            }
            dialogStage.close();
        }
    }
    //Metodo para el boton cancelar de la ventana editar registro
    @FXML
    private void cancelarCambios(){
        dialogStage.close();
    }
    //Metodo que verifica si un registro es válido a la hora de editarlo
    private boolean esMarcacionValida(){
        if(txtNombre.getText() == null || txtNombre.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "Nombre inválido"
                    + "\nIngrese un valor válido");
            error.showAndWait();
            txtNombre.requestFocus();
            return false;
        }
        return true;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
