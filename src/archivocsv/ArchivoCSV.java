/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv;

import archivocsv.controllers.EditarMarcacionController;
import archivocsv.controllers.EntradasTardeController;
import archivocsv.controllers.RootLayoutController;
import archivocsv.controllers.VentanaMesController;
import archivocsv.controllers.VistaEmpleadosController;
import archivocsv.controllers.VistaReportesController;
import archivocsv.helpers.Dialogs;
import archivocsv.models.Empleado;
import archivocsv.models.Reporte;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */
public class ArchivoCSV extends Application {
    
    private Stage ventanaPrincipal;
    private AnchorPane rootLayout;
    Images imagen = new Images();
    private ObservableList<Empleado> empleadosList = FXCollections.observableArrayList();
    public enum CRUDOperation {Nada, Editar, Eliminar};
    AnchorPane paneEmpleados = new AnchorPane(); 
    AnchorPane reportesPane = new AnchorPane();
    AnchorPane entradasPane = new AnchorPane();
    Boolean colocado = false;
    //Metodo utilizado para mostrar la ventana principal
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.ventanaPrincipal = primaryStage;
        this.ventanaPrincipal.setTitle("DACOTRANS");
        this.ventanaPrincipal.getIcons().add(new Image(ArchivoCSV.class.getResource("resources/images/icono.png").toString()));
        this.ventanaPrincipal.setResizable(false);
        this.ventanaPrincipal.setMaxWidth(1325);
        this.ventanaPrincipal.setMaxHeight(685);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ArchivoCSV.class.getResource("views/RootLayout.fxml"));
            rootLayout = (AnchorPane) loader.load();
            RootLayoutController controller = loader.getController();
            controller.setArchivoCsv(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            }catch(IOException e){
                Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "No se encontro la vista", e.getMessage());
                error.showAndWait();
                e.printStackTrace();
            }
        importarListas();
        cargarVistaEmpleados();
        }

    public Stage getVentanaPrincipal() {
        return ventanaPrincipal;
    }
    
    public ObservableList<Empleado> getListaEmpleados(){
        return empleadosList;
    }
    
    //Mostrar tabla de empleados
    public void cargarVistaEmpleados(){
            try{
                if(colocado == false){
                    rootLayout.getChildren().removeAll(paneEmpleados);
                    rootLayout.getChildren().removeAll(reportesPane);
                    rootLayout.getChildren().removeAll(entradasPane);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ArchivoCSV.class.getResource("views/VistaEmpleados.fxml"));
                    paneEmpleados = loader.load();
                    VistaEmpleadosController controller = loader.getController();
                    controller.setArchivoCsv(this);
                    Reporte rep = new Reporte();
                    rep.setArchivoCsv(this);
                    AnchorPane.setTopAnchor(paneEmpleados, -3.4);
                    AnchorPane.setLeftAnchor(paneEmpleados,119.0);
                    rootLayout.getChildren().addAll(paneEmpleados);
                    colocado = true;
                }else{
                   rootLayout.getChildren().removeAll(reportesPane);
                   rootLayout.getChildren().removeAll(entradasPane);
                   paneEmpleados.setVisible(true);
                }
            }catch(Exception e){
                Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "No se encontro la vista empleados", e.getMessage());
                error.showAndWait();
            }
            importarListas();
        }
    
    //Metodo que muestra la ventana utilizada para editar un registro
    public boolean mostrarEditarTodos(Empleado empleado, CRUDOperation operation){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ArchivoCSV.class.getResource("views/EditarMarcacion.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("DACOTRANS");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.getIcons().add(imagen.getIcon());
            dialogStage.initOwner(ventanaPrincipal);
            Scene escena = new Scene(pane);
            dialogStage.setScene(escena);
            dialogStage.setResizable(false);
            EditarMarcacionController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOperacion(operation);
            controller.setEmpleado(empleado);
            
            dialogStage.showAndWait();
            
            return controller.fuePresionadoOk();
        }catch(Exception e){
            e.printStackTrace();
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "No se encontro la vista", e.getMessage());
            error.showAndWait();
            return false;
        }
    }
    //Metodo para mostrar la tabla de reportes
    public void mostrarVistaReportes(String id, String nombre, String anio){
        try{
                paneEmpleados.setVisible(false);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ArchivoCSV.class.getResource("views/VistaReportes.fxml"));
                reportesPane = loader.load();
                VistaReportesController controller = loader.getController();
                controller.recibirNombre(id, nombre, anio);
                controller.setArchivo(this);
                AnchorPane.setTopAnchor(reportesPane, -3.4);
                AnchorPane.setLeftAnchor(reportesPane,119.0);
                rootLayout.getChildren().addAll(reportesPane);
        }catch(Exception e){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "No se encontro la vista", e.getMessage());
            error.showAndWait();
            e.printStackTrace();
        }
    }
    //Mostrar tabla de entradas tarde
    public void mostrarVistaEntradasTarde(String nombre, String anio, String mes, String id){
        try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ArchivoCSV.class.getResource("views/EntradasTarde.fxml"));
                entradasPane = loader.load();
                AnchorPane.setTopAnchor(entradasPane, -3.4);
                AnchorPane.setLeftAnchor(entradasPane,119.0);
                rootLayout.getChildren().addAll(entradasPane);
                EntradasTardeController controller = loader.getController();
                controller.recibirDatos(nombre, anio, mes, id);
                controller.setArchivoCsv(this);
        }catch(Exception e){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "No se encontro la vista", e.getMessage());
            error.showAndWait();
            e.printStackTrace();
        }
    }
    //Metodo para mostrar la ventana del mes para generar archivo Excel
    public boolean mostrarVentanaMes(String anio){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ArchivoCSV.class.getResource("views/VentanaMes.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("DACOTRANS");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.getIcons().add(imagen.getIcon());
            dialogStage.initOwner(ventanaPrincipal);
            Scene escena = new Scene(pane);
            dialogStage.setScene(escena);
            dialogStage.setResizable(false);
            VentanaMesController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.recibirAnio(anio);
            
            dialogStage.showAndWait();
            
            return controller.fuePresionadoOK();
        }catch(Exception e){
            e.printStackTrace();
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "No se encontro la vista", e.getMessage());
            error.showAndWait();
            return false;
        }
    }
    //Esconder vista de reportes al mostrar tabla de entradas tarde
    public void esconderVistaReportes(){
        reportesPane.setVisible(false);
    }
    //Quitar tabla de entradas tarde
    public void quitarVistaEntradasTarde(){
        rootLayout.getChildren().removeAll(entradasPane);
        reportesPane.setVisible(true);
    }
    //Importar lista de empleados 
    public void importarListas(){
        this.empleadosList = Empleado.getListaEmpleado();
    }
        
        /**
        * @param args the command line arguments
        */

        public static void main(String[] args) {
            launch(args);
        }
    }
    

        
   
