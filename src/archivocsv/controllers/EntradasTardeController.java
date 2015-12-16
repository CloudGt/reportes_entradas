/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.controllers;

import archivocsv.ArchivoCSV;
import archivocsv.models.Reporte;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */

public class EntradasTardeController implements Initializable {
    
    private ArchivoCSV archivoCsv = new ArchivoCSV();
    private Reporte rep = new Reporte();
    public ObservableList<Reporte> listaEntradas = FXCollections.observableArrayList();
    
    @FXML
    private Label lblNombreTarde;
    @FXML
    private Label lblMesTarde;
    @FXML
    private Label lblAnioTarde;
    @FXML
    private TableView tbvEntradasTarde;
    @FXML
    private TableColumn tbcNumeroTarde;
    @FXML
    private TableColumn tbcIDTarde;
    @FXML
    private TableColumn tbcNombreTarde;
    @FXML
    private TableColumn tbcMarcaciónTarde;
    
    public void setArchivoCsv(ArchivoCSV archivoCsv) {
        this.archivoCsv = archivoCsv;
        tbvEntradasTarde.setItems(getListaEntradas());
        cargarDatos();
    }

    public ObservableList<Reporte> getListaEntradas() {
        return listaEntradas;
    }
    //Metodo para colocar los datos en los label de la vista entradas tarde y para importa la lista de registros
    public void recibirDatos(String nombre, String anio, String mes, String id){
        lblNombreTarde.setText(nombre);
        lblMesTarde.setText(mes);
        lblAnioTarde.setText(anio);
        rep.setId(id);
        if(mes.equals("Enero")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "01", nombre);
        }
        if(mes.equals("Febrero")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "02", nombre);
        }
        if(mes.equals("Marzo")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "03", nombre);
        }
        if(mes.equals("Abril")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "04", nombre);
        }
        if(mes.equals("Mayo")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "05", nombre);
        }
        if(mes.equals("Junio")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "06", nombre);
        }
        if(mes.equals("Julio")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "07", nombre);
        }
        if(mes.equals("Agosto")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "08", nombre);
        }
        if(mes.equals("Septiembre")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "09", nombre);
        }
        if(mes.equals("Octubre")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "10", nombre);
        }
        if(mes.equals("Noviembre")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "11", nombre);
        }
        if(mes.equals("Diciembre")){
            this.listaEntradas = Reporte.getListaEntradasTarde(rep.getId(), lblAnioTarde.getText(), "12", nombre);
        }
    }
    
    @FXML
    public void regresar(){
        this.archivoCsv.quitarVistaEntradasTarde();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO   
    }
    
    public void cargarDatos(){
        tbcNumeroTarde.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcIDTarde.setCellValueFactory(new PropertyValueFactory<Reporte, String>("id"));
        tbcNombreTarde.setCellValueFactory(new PropertyValueFactory<Reporte, String>("nombre"));
        tbcMarcaciónTarde.setCellValueFactory(new PropertyValueFactory<Reporte, String>("entrada"));
        
        tbvEntradasTarde.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvEntradasTarde.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {
            
            @Override
            public void changed(ObservableValue<? extends Reporte> observable,
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    
}
