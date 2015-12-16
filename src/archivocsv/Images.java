/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv;

import javafx.scene.image.Image;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */

public class Images {
    
    //Icono de los cuadros de dialogo
    private Image icon = new Image(getClass().getResourceAsStream("resources/images/icono.png"));
    private Image abrir = new Image(getClass().getResourceAsStream("resources/images/abrirdos.png"));
    private Image abrirDos = new Image(getClass().getResourceAsStream("resources/images/abrir.png"));
    private Image empleados = new Image(getClass().getResourceAsStream("resources/images/empleadodos.png"));
    private Image empleadosDos = new Image(getClass().getResourceAsStream("resources/images/empleado.png"));
    private Image salir = new Image(getClass().getResourceAsStream("resources/images/salirdos.png"));
    private Image salirDos = new Image(getClass().getResourceAsStream("resources/images/salir.png"));

    public Image getAbrirDos() {
        return abrirDos;
    }

    public Image getEmpleados() {
        return empleados;
    }

    public Image getEmpleadosDos() {
        return empleadosDos;
    }

    public Image getSalir() {
        return salir;
    }

    public Image getSalirDos() {
        return salirDos;
    }
    
    
    public Image getIcon() {
        return icon;
    }

    public Image getAbrir() {
        return abrir;
    }
    
}
