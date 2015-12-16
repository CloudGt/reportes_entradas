/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.controllers;

import archivocsv.helpers.Dialogs;
import archivocsv.models.Reporte;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

public class VentanaMesController implements Initializable {
    
    @FXML
    private TextField txtMes;
    
    private Stage dialogStage;
    private boolean presionadoOk;
    String anio;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    //Metodo para recibir el año
    public void recibirAnio(String an){
        anio = an;
    }
    
    public boolean fuePresionadoOK(){
        return this.presionadoOk;
    }
    //Boton aceptar de la ventana mes
    @FXML
    private void aceptarMes() throws IOException{
        Pattern pat = Pattern.compile("[a-zA-Z]{4,10}");
        Matcher mat = pat.matcher(txtMes.getText());
        if(mat.matches()){
            if(txtMes.getText().equals("Enero") || txtMes.getText().equals("enero")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "01");
            }
            if(txtMes.getText().equals("Febrero") || txtMes.getText().equals("febrero")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "02");
            }
            if(txtMes.getText().equals("Marzo") || txtMes.getText().equals("marzo")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "03");
            }
            if(txtMes.getText().equals("Abril") || txtMes.getText().equals("abril")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "04");
            }
            if(txtMes.getText().equals("Mayo") || txtMes.getText().equals("mayo")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "05");
            }
            if(txtMes.getText().equals("Junio") || txtMes.getText().equals("junio")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "06");
            }
            if(txtMes.getText().equals("Julio") || txtMes.getText().equals("julio")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "07");
            }
            if(txtMes.getText().equals("Agosto") || txtMes.getText().equals("agosto")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "08");
            }
            if(txtMes.getText().equals("Septiembre") || txtMes.getText().equals("septiembre")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "09");
            }
            if(txtMes.getText().equals("Octubre") || txtMes.getText().equals("octubre")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "10");
            }
            if(txtMes.getText().equals("Noviembre") || txtMes.getText().equals("noviembre")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "11");
            }
            if(txtMes.getText().equals("Diciembre") || txtMes.getText().equals("diciembre")){
                dialogStage.close();
                Reporte.generarReporteExcel(anio, "12");
            }
        }else{
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "¡Por favor!"
                    + "\nIngrese un mes válido");
            error.showAndWait();
            txtMes.setText("");
        }        
    }
    
    @FXML
    private void cancelarMes(){
        dialogStage.close();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
