/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.helpers;

import archivocsv.Images;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */

public class Dialogs {
    
    //Dialgo para mensajes exitosos
     public static Alert getDialog(Alert.AlertType type, String title, String header, String content){    
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Images imagen = new Images();
        Image icon = imagen.getIcon();
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        return alert;
    }
     
     //Dialogo para mensajes de error
     public static Alert getErrorDialog(Alert.AlertType type, String title, String header, String content, String ex){
         Alert alert = Dialogs.getDialog(type, title, header, content);
        String textoDeError = ex;
        Images imagen = new Images();
        Image icon = imagen.getIcon();
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        
        Label label = new Label("El error es:");
        
        TextArea textArea = new TextArea(textoDeError);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        
        GridPane expContenido = new GridPane();
        expContenido.setMaxWidth(Double.MAX_VALUE);
        expContenido.add(label, 0, 0);
        expContenido.add(textArea, 0, 1);
        
        alert.getDialogPane().setExpandableContent(expContenido);
        
        return alert;
    }
}
