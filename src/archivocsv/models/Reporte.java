 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.models;

import archivocsv.ArchivoCSV;
import archivocsv.helpers.Dialogs;
import archivocsv.helpers.Helper;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author DACO78
 */
public class Reporte {
    private String id;
    private String nombre;
    private String entrada;
    private String salida;
    private String mes;
    private String horasTrabajadas;
    private int numero;
    private String anio;
    private static ArchivoCSV archivoCsv;
    
    public String getId() {
        return id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setArchivoCsv(ArchivoCSV archivoCsv) {
        this.archivoCsv = archivoCsv;
    }
    
    public String getAnio() {
        return anio;
    }
    
    public String getEntrada() {
        return entrada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getSalida() {
        return salida;
    }

    public String getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public void setHorasTrabajadas(String horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
    
    //Metodo para importar reportes de la base de datos
    public static ObservableList<Reporte> getListaReportes(String id, String anio, String mes) throws IOException{
       ObservableList<Reporte> reportes = FXCollections.observableArrayList();
            try{
                Connection con = Helper.getConnection();
                String entrada = "SELECT marcacion FROM t_marcacion WHERE id="+id+" AND marcacion LIKE '%/"+mes+"/"+anio+"%'"
                    +" AND marcacion LIKE '%a%'";
                int contar = 0;
                ResultSet rs = con.createStatement().executeQuery(entrada);
                while(rs.next()){
                    Reporte reporte = new Reporte();
                    contar+=1;
                    reporte.setEntrada(rs.getString("marcacion"));
                    reporte.setNumero(contar);
                    //Query que verifica si existe el registro de salida
                    String coincidir = "SELECT marcacion FROM t_marcacion WHERE id="+id+""
                            +" AND marcacion LIKE '%"+rs.getString("marcacion").substring(0, 10)+"%' AND marcacion LIKE '%p%'";
                    ResultSet rs2 = con.createStatement().executeQuery(coincidir);
                    if(rs2.next()){
                        reporte.setSalida(rs2.getString("marcacion"));
                        String horaInicial = rs.getString("marcacion").substring(11, 15)+" AM";
                        String horaFinal = rs2.getString("marcacion").substring(11, 15)+" PM";
                        if(horaFinal.substring(0, 2).equals("12")){
                            horaFinal = rs2.getString("marcacion").substring(11, 15)+" AM";
                        }
                        DateFormat sdf = new SimpleDateFormat("KK:mm a");
                        Date date = sdf.parse(horaInicial);
                        Date date2 = sdf.parse(horaFinal);

                        double hrsInicialMs = date.getTime();
                        double hrsFinalMs = date2.getTime();
                        double diferencia = hrsFinalMs - hrsInicialMs;
                        double resta = (diferencia / (1000 * 60 * 60));
                        int primerNumero = (int)resta;
                        double segundoNumero = resta - primerNumero;
                        int convertirNumero = (int) (segundoNumero * 60);
                        int restarAlumerzo = primerNumero - 1;
                        if(convertirNumero == 0){
                            reporte.setHorasTrabajadas(restarAlumerzo+":00");
                        }else{
                            if(convertirNumero > 0 & convertirNumero < 10){
                                reporte.setHorasTrabajadas(restarAlumerzo+":0"+convertirNumero);
                            }else{
                                reporte.setHorasTrabajadas(restarAlumerzo+":"+convertirNumero);
                            }
                        }
                        }else{
                            reporte.setSalida("Sin registro");
                            reporte.setHorasTrabajadas("Sin registro");
                        }
                    reportes.add(reporte);
                }
            }catch(Exception e){
                Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "Error al cargar la lista de reportes", e.getMessage());
                error.showAndWait();
            }   
            return reportes;
    }
    //Metodo para obtener lista de entradas tarde 
    public static ObservableList<Reporte> getListaEntradasTarde(String id, String anio, String mes, String nombre){
        ObservableList<Reporte> entradas = FXCollections.observableArrayList();
        try{
            Connection con = Helper.getConnection();
            String tarde = "SELECT * FROM t_marcacion WHERE id="+id+" AND marcacion LIKE '%/"+mes+"/"+anio+"%'"
                    + "AND marcacion LIKE '%a%'";
            int contar = 0;
            int numero = 0;
            ResultSet rs = con.createStatement().executeQuery(tarde);
            while(rs.next()){
                Reporte reporte = new Reporte();
                DateFormat sdf = new SimpleDateFormat("KK:mm a");
                String horaInicio = "08:00 AM";
                String horaFin = "12:00 PM";
                String horaEntrada = rs.getString("marcacion");
                if(horaEntrada.substring(11,12).equals("0")){
                    horaEntrada = rs.getString("marcacion").substring(11, 16) + " AM";
                }
                else{
                    if(horaEntrada.substring(11,13).equals("10") || horaEntrada.substring(11,13).equals("11")){
                        horaEntrada = rs.getString("marcacion").substring(11, 16) + " AM";
                    }else{
                        horaEntrada = rs.getString("marcacion").substring(11, 15) + " AM";
                    }
                }
                
                Date hrInicio = sdf.parse(horaInicio);
                Date hrFin = sdf.parse(horaFin);
                Date hrEntrada = sdf.parse(horaEntrada);
                Calendar calInicio = new GregorianCalendar();
                Calendar calFin = new GregorianCalendar();
                Calendar calEntrada = new GregorianCalendar();
                calInicio.setTime(hrInicio);
                calFin.setTime(hrFin);
                calEntrada.setTime(hrEntrada);
                
                if(calEntrada.after(calInicio) & calEntrada.before(calFin)){
                    contar+=1;
                    numero = contar;
                    reporte.setNumero(contar);
                    reporte.setId(rs.getString("id"));
                    reporte.setNombre(rs.getString("nombre"));
                    reporte.setEntrada(rs.getString("marcacion"));
                    entradas.add(reporte);
                }
            }
            if(numero == 0){
                Reporte reporte = new Reporte();
                contar+=1;
                reporte.setNumero(contar);
                reporte.setId(id);
                reporte.setNombre(nombre);
                reporte.setEntrada("Sin entradas tarde");
                entradas.add(reporte);
            }
        }catch(Exception e){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "DACOTRANS", null, "Error al cargar la lista de entradas tarde", e.getMessage());
            error.showAndWait();
            e.printStackTrace();
        }
        return entradas;
    }
    
    public static ArrayList<Reporte> generarReporteExcel(String anio, String mes) throws IOException{
        ArrayList<Reporte> archivoExcel = new ArrayList<>();
            try {
                FileChooser elegirArchivo = new FileChooser();
                FileChooser.ExtensionFilter filtroExt = new FileChooser.ExtensionFilter("Archivos Excel (*.xls)", "*.xls");
                elegirArchivo.getExtensionFilters().add(filtroExt);
                File archivo = elegirArchivo.showSaveDialog(archivoCsv.getVentanaPrincipal());
                archivo.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream enviar = new FileOutputStream(archivo);
                Sheet hoja = libro.createSheet("Reportes");
                Sheet hoja2 = libro.createSheet("Entradas Tarde");
                Row fila = hoja.createRow(0);
                Row fila2 = hoja2.createRow(0);
                    for(int c=0;c<5;c++){
                        Cell celda = fila.createCell(c);
                            if(c==0){
                                celda.setCellValue("ID");
                            }
                            if(c==1){
                                celda.setCellValue("Nombre");
                            }
                            if(c==2){
                                celda.setCellValue("Entrada");
                            }
                            if(c==3){
                                celda.setCellValue("Salida");
                            }
                            if(c==4){
                                celda.setCellValue("Horas Trabajadas");
                            }
                    }       
                    for(int c2=0;c2<3;c2++){
                        Cell celda2 = fila2.createCell(c2);
                            if(c2==0){
                                celda2.setCellValue("ID");
                            }
                            if(c2==1){
                                celda2.setCellValue("Nombre");
                            }
                            if(c2==2){
                                celda2.setCellValue("Entrada");
                            }
                    }
                Connection con = Helper.getConnection();
                String entrada = "SELECT * FROM t_marcacion WHERE marcacion LIKE '%/"+mes+"/"+anio+"%'"
                    +" AND marcacion LIKE '%a%'";
                ResultSet rs = con.createStatement().executeQuery(entrada);
                ResultSet rs3 = con.createStatement().executeQuery(entrada);
                int contar = 0;
                int contar2 = 1;
                while(rs.next()){
                    contar+=1;
                    Row filaDatos = hoja.createRow(contar);
                    Cell celdaID = filaDatos.createCell(0);
                    Cell celdaNombre = filaDatos.createCell(1);
                    Cell celdaEntrada  = filaDatos.createCell(2);
                    Cell celdaSalida = filaDatos.createCell(3);
                    Cell celdaHoras = filaDatos.createCell(4);
                    Reporte reporte = new Reporte();
                    reporte.setEntrada(rs.getString("marcacion"));
                    celdaEntrada.setCellValue(reporte.getEntrada());
                    reporte.setId(rs.getString("id"));
                    celdaID.setCellValue(reporte.getId());
                    reporte.setNombre(rs.getString("nombre"));
                    celdaNombre.setCellValue(reporte.getNombre());
                    //Query que verifica si existe el registro de salida
                    String coincidir = "SELECT * FROM t_marcacion WHERE id="+rs.getString("id")+" AND marcacion LIKE '%"+rs.getString("marcacion").substring(0, 10)+"%'"
                            + " AND marcacion LIKE '%p%'";
                    ResultSet rs2 = con.createStatement().executeQuery(coincidir);
                    if(rs2.next()){
                        reporte.setSalida(rs2.getString("marcacion"));
                        celdaSalida.setCellValue(reporte.getSalida());
                        String horaInicial = rs.getString("marcacion").substring(11, 15)+" AM";
                        String horaFinal = rs2.getString("marcacion").substring(11, 15)+" PM";
                        if(horaFinal.substring(0, 2).equals("12")){
                            horaFinal = rs2.getString("marcacion").substring(11, 15)+" AM";
                        }
                        DateFormat sdf = new SimpleDateFormat("KK:mm a");
                        Date date = sdf.parse(horaInicial);
                        Date date2 = sdf.parse(horaFinal);

                        double hrsInicialMs = date.getTime();
                        double hrsFinalMs = date2.getTime();
                        double diferencia = hrsFinalMs - hrsInicialMs;
                        double resta = (diferencia / (1000 * 60 * 60));
                        int primerNumero = (int)resta;
                        double segundoNumero = resta - primerNumero;
                        int convertirNumero = (int) (segundoNumero * 60);
                        int restarAlumerzo = primerNumero - 1;
                        if(convertirNumero == 0){
                            reporte.setHorasTrabajadas(restarAlumerzo+":00");
                            celdaHoras.setCellValue(reporte.getHorasTrabajadas());
                        }else{
                            if(convertirNumero > 0 & convertirNumero < 10){
                                reporte.setHorasTrabajadas(restarAlumerzo+":0"+convertirNumero);
                                celdaHoras.setCellValue(reporte.getHorasTrabajadas());
                            }else{
                                reporte.setHorasTrabajadas(restarAlumerzo+":"+convertirNumero);
                                celdaHoras.setCellValue(reporte.getHorasTrabajadas());
                            }
                        }
                        }else{
                            reporte.setSalida("Sin registro");
                            celdaSalida.setCellValue(reporte.getSalida());
                            reporte.setHorasTrabajadas("Sin registro");
                            celdaHoras.setCellValue(reporte.getHorasTrabajadas());
                        }       
                }
                while(rs3.next()){
                    Row filaDatos2 = hoja2.createRow(contar2);
                    Cell celdaId = filaDatos2.createCell(0);
                    Cell celdaNombre = filaDatos2.createCell(1);
                    Cell celdaMarcacion = filaDatos2.createCell(2);
                    Reporte reporte = new Reporte();
                    DateFormat sdf = new SimpleDateFormat("KK:mm a");
                    String horaInicio = "08:00 AM";
                    String horaFin = "12:00 PM";
                    String horaEntrada = rs3.getString("marcacion");
                        if(horaEntrada.substring(11,12).equals("0")){
                            horaEntrada = rs3.getString("marcacion").substring(11, 16) + " AM";
                        }
                        else{
                            if(horaEntrada.substring(11,13).equals("10") || horaEntrada.substring(11,13).equals("11")){
                                horaEntrada = rs3.getString("marcacion").substring(11, 16) + " AM";
                            }else{
                                horaEntrada = rs3.getString("marcacion").substring(11, 15) + " AM";
                            }
                        }
                    Date hrInicio = sdf.parse(horaInicio);
                    Date hrFin = sdf.parse(horaFin);
                    Date hrEntrada = sdf.parse(horaEntrada);
                    Calendar calInicio = new GregorianCalendar();
                    Calendar calFin = new GregorianCalendar();
                    Calendar calEntrada = new GregorianCalendar();
                    calInicio.setTime(hrInicio);
                    calFin.setTime(hrFin);
                    calEntrada.setTime(hrEntrada);
                
                    if(calEntrada.after(calInicio) & calEntrada.before(calFin)){
                        contar2+=1;
                        reporte.setId(rs3.getString("id"));
                        celdaId.setCellValue(reporte.getId());
                        reporte.setNombre(rs3.getString("nombre"));
                        celdaNombre.setCellValue(reporte.getNombre());
                        reporte.setEntrada(rs3.getString("marcacion"));
                        celdaMarcacion.setCellValue(reporte.getEntrada());
                        archivoExcel.add(reporte);
                    }
                }
                hoja.setColumnWidth(0, 850);
                hoja.setColumnWidth(1, 3000);
                hoja.setColumnWidth(2, 6000);
                hoja.setColumnWidth(3, 6000);
                hoja.setColumnWidth(4, 4000);
                hoja2.setColumnWidth(0, 850);
                hoja2.setColumnWidth(1, 3000);
                hoja2.setColumnWidth(2, 6000);
                libro.write(enviar);
                enviar.close();
                Desktop.getDesktop().open(archivo);    
                }catch(Exception e){
                        
                }
       return archivoExcel;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
