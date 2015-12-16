/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.scene.control.Alert;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */

public class Compartir {
    
    FileInputStream in = null;
    FileOutputStream out = null;
    
    //Metodo utilizado para copiar el arcihvo seleccionado al servidor para poder ingresarlo a la base de datos
    public void enviar(String origen, String nombre, String arServidor){
        File archivoServidor = new File("\\\\192.168.0.71\\Q\\RRHH\\"+nombre);    
        try{
            FileInputStream in = new FileInputStream(origen);
            FileOutputStream out = new FileOutputStream("\\\\192.168.0.71\\Q\\RRHH\\"+nombre);
            
            int c;
            while( (c = in.read() ) != -1)
		out.write(c);

		in.close();
		out.close();
            
            }catch(Exception e){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "Error al copiar archivo", e.getMessage());
            error.showAndWait();
            archivoServidor.delete();
        }
            Alert mensaje = Dialogs.getDialog(Alert.AlertType.INFORMATION, "DACOTRANS", null, "Archivo copiado exitosamente");
            mensaje.showAndWait();  
    }
}








