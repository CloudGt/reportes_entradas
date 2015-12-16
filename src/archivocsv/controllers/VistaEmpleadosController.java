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
import archivocsv.models.Reporte;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */

public class VistaEmpleadosController implements Initializable {
    
    @FXML
    private TableView<Empleado> tbvEmpleados;
    @FXML
    private TableColumn<Empleado, String> tbcNumero;
    @FXML
    private TableColumn<Empleado, String> tbcID;
    @FXML
    private TableColumn<Empleado, String> tbcNombre;
    @FXML
    private TableColumn<Empleado, String> tbcMarcacion;
    @FXML
    private TextField txtAnio;
    @FXML
    private Label lblAnio;
    
    private ArchivoCSV archivoCsv;
    private Empleado emp;
    
    //Carga la lista de registros al iniciarse el programa
    public void setArchivoCsv(ArchivoCSV archivoCsv) {
        this.archivoCsv = archivoCsv;
        tbvEmpleados.setItems(archivoCsv.getListaEmpleados());
    }
    
    /**
     * Initializes the controller class.
     */
    //Metodo utilizado para mostrar los datos en el TableView
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tbcID.setCellValueFactory(new PropertyValueFactory<Empleado, String>("id"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
         tbcNumero.setCellValueFactory(new PropertyValueFactory<Empleado, String>("codigo"));
        
        tbvEmpleados.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvEmpleados.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Empleado>() {

            @Override
            public void changed(ObservableValue<? extends Empleado> observable, 
                    Empleado oldValue, Empleado newValue) {
                 
            }
        });
    }
    //Metodo para ver tabla de reportes de los empleados
    @FXML
    public void verTablaReportes(){
        int selectedIndex = tbvEmpleados.getSelectionModel().getSelectedIndex();
        if(!"".equals(lblAnio.getText())){
            if(selectedIndex >= 0){
                Reporte reporte = new Reporte();
                String nombre = tbcNombre.getCellData(selectedIndex);
                String id = tbcID.getCellData(selectedIndex);
                String anio = lblAnio.getText();
                this.archivoCsv.mostrarVistaReportes(id, nombre, anio);
            }else{
            Alert error = Dialogs.getDialog(AlertType.ERROR, "DACOTRANS", null, "No ha seleccionado ningn empleado");
            error.showAndWait();
            }
        }else{
            Alert error = Dialogs.getDialog(AlertType.ERROR, "DACOTRANS", null, "Ingrese un año porfavor");
            error.showAndWait();
        }
    }
    //Metodo para eliminar empleados
    @FXML
    public void eliminar(){
        int selectedIndex = tbvEmpleados.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            Empleado eliminarEmpleado = tbvEmpleados.getSelectionModel().getSelectedItem();
            Alert pregunta = Dialogs.getDialog(Alert.AlertType.CONFIRMATION, "DACOTRANS", null, "¿Desea eliminar este empleado?");
            Optional<ButtonType> result = pregunta.showAndWait();
            if(result.get() == ButtonType.OK){
                if(Empleado.eliminarMarcacion(eliminarEmpleado)){
                    tbvEmpleados.getItems().remove(selectedIndex);
                }
            }
        }else{
            Alert error = Dialogs.getDialog(AlertType.ERROR, "DACOTRANS", null, "No ha seleccionado ningún empleado");
            error.showAndWait();
        }
    }
    //Metodo para editar registros
    @FXML
    public void editar(){
        Empleado empleadoSeleccionado = tbvEmpleados.getSelectionModel().getSelectedItem();
        if(empleadoSeleccionado != null){
            boolean okCliked = archivoCsv.mostrarEditarTodos(empleadoSeleccionado, CRUDOperation.Editar);
            if(okCliked){
                actualizarDatos();
            }
        }else{
            Alert error = Dialogs.getDialog(AlertType.ERROR, "DACOTRANS", null, "No se ha seleccionado ningun empleado");
            error.showAndWait();
        }
    }
    //Metodo para cargar los datos del año
    @FXML
    public void cargar() throws IOException{
        String anio = txtAnio.getText();
        Pattern pat = Pattern.compile("[0-9]{4}");
        Matcher mat = pat.matcher(anio);
        if(mat.matches()){
            if(txtAnio.getText().equals(lblAnio.getText())){
                Alert mensaje = Dialogs.getDialog(Alert.AlertType.INFORMATION,"DACOTRANS" , null, "Los reportes del año "+anio+" ya se han cargado");
                mensaje.showAndWait();
                txtAnio.setText("");
            }else{
                lblAnio.setText(anio);
                txtAnio.setText("");
            }
        }else{
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "¡Por favor!"
                    + "\nIngrese un año válido");
            error.showAndWait();
            txtAnio.setText("");
        }
    }
    //Metodo para generar archivos xls
    @FXML
    public void generarArchivo() throws IOException{
            if(!"".equals(lblAnio.getText())){
                archivoCsv.mostrarVentanaMes(lblAnio.getText());
            }else{
                Alert mensaje = Dialogs.getDialog(AlertType.INFORMATION, "DACOTRANS", null, "Ingrese un año por favor");
                mensaje.showAndWait();
            }    
    }
    //Metodo para actualizar los datos despues de editar
    public void actualizarDatos(){
        int selectedIndex = tbvEmpleados.getSelectionModel().getFocusedIndex();
        tbvEmpleados.setItems(null);
        tbvEmpleados.layout();
        tbvEmpleados.setItems(archivoCsv.getListaEmpleados());
        tbvEmpleados.getSelectionModel().select(selectedIndex);
    }
}
