/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.controllers;

import archivocsv.ArchivoCSV;
import archivocsv.Images;
import archivocsv.helpers.Compartir;
import archivocsv.helpers.Dialogs;
import archivocsv.models.Empleado;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */

public class RootLayoutController implements Initializable {

    private ArchivoCSV archivoCsv;
    private Empleado empleado;
    
    Images image = new Images();
    
    public void setArchivoCsv(ArchivoCSV archivoCsv) {
        this.archivoCsv = archivoCsv;
    }
    
     @FXML
     public ImageView imAbrir;
     @FXML
     public ImageView imEmpleados;
     @FXML 
     public ImageView imSalir;
    //Metodo para salir del programa
    @FXML
    public void cerrarPrograma(){
        System.exit(0);
    }
    //Metodo para ver el listado de todos los registros
    @FXML
    public void verTodos(){
        this.archivoCsv.cargarVistaEmpleados();
    }
    
    //Metodo para abrir cuadro de dialogo que permite cargar archivo TXT
    @FXML
    public void cargarArchivo() throws IOException{
        FileChooser elegirArchivo = new FileChooser();
        FileChooser.ExtensionFilter filtroExt = new FileChooser.ExtensionFilter("Archivos CSV (*.txt)", "*.txt");
        elegirArchivo.getExtensionFilters().add(filtroExt);
        File archivo = elegirArchivo.showOpenDialog(archivoCsv.getVentanaPrincipal());
        if(archivo != null){
            File archivoServidor = new File("\\\\192.168.0.71\\Q\\RRHH\\"+archivo.getName());
            boolean existe = archivoServidor.exists();
            if(archivo != null){

              String ruta = archivo.toPath().toString();
              String nombre = archivo.getName();
              String nombreServidor = archivoServidor.getName();
              Empleado em = new Empleado();
              Compartir comp = new Compartir();
              if(existe == false){
                  comp.enviar(ruta,nombre, nombreServidor);
                  em.insertarMarcacion(nombre);
                  archivoCsv.getListaEmpleados();
              }else{
                  Alert mensaje = Dialogs.getDialog(Alert.AlertType.INFORMATION, "DACOTRANS", null, "Los datos ya han sido ingresados");
                  mensaje.showAndWait();
              }       
            }
        }
    }
    
    @FXML
    public void cambiarImagenAbrir(){
        imAbrir.setImage(image.getAbrir());
    }
    @FXML
    public void cambiarDeNuevoAbrir(){
        imAbrir.setImage(image.getAbrirDos());
    }
    
    @FXML
    public void cambiarImagenEmpleado(){
        imEmpleados.setImage(image.getEmpleados());
    }
    
    @FXML
    public void cambiarDeNuevoEmpleados(){
        imEmpleados.setImage(image.getEmpleadosDos());
    }
    
    @FXML
    public void cambiarSalir(){
        imSalir.setImage(image.getSalir());
    }
    
    @FXML
    public void cambiarDeNuevoSalir(){
            imSalir.setImage(image.getSalirDos());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
