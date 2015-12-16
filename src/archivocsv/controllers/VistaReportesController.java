/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.controllers;

import archivocsv.ArchivoCSV;
import archivocsv.models.Reporte;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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

public class VistaReportesController implements Initializable {

    @FXML
    public TableView<Reporte> tbvReportes;
    @FXML
    public TableView<Reporte> tbvEnero;
    @FXML
    public TableView<Reporte> tbvFebrero;
    @FXML
    public TableView<Reporte> tbvMarzo;
    @FXML
    public TableView<Reporte> tbvAbril;
    @FXML
    public TableView<Reporte> tbvMayo;
    @FXML
    public TableView<Reporte> tbvJunio;
    @FXML
    public TableView<Reporte> tbvJulio;
    @FXML
    public TableView<Reporte> tbvAgosto;
    @FXML
    public TableView<Reporte> tbvSeptiembre;
    @FXML
    public TableView<Reporte> tbvOctubre;
    @FXML
    public TableView<Reporte> tbvNoviembre;
    @FXML
    public TableView<Reporte> tbvDiciembre;
    @FXML
    public TableColumn<Reporte, String> tbcNumero;
    @FXML
    public TableColumn<Reporte, Date> tbcEntrada;
    @FXML
    public TableColumn<Reporte, Date> tbcSalida;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadas;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroFeb;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaFeb;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaFeb;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasFeb;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroMar;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaMar;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaMar;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasMar;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroAbr;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaAbr;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaAbr;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasAbr;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroMay;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaMay;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaMay;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasMay;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroJun;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaJun;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaJun;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasJun;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroJul;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaJul;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaJul;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasJul;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroAgo;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaAgo;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaAgo;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasAgo;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroSep;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaSep;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaSep;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasSep;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroOct;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaOct;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaOct;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasOct;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroNov;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaNov;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaNov;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasNov;
    @FXML
    public TableColumn<Reporte, String> tbcNumeroDic;
    @FXML
    public TableColumn<Reporte, Date> tbcEntradaDic;
    @FXML
    public TableColumn<Reporte, Date> tbcSalidaDic;
    @FXML
    public TableColumn<Reporte, String> tbcHorasTrabajadasDic;
    @FXML
    public Label lblNombre;
    @FXML
    public Label lblMes;
    @FXML
    public Label lblAnio;
    
    public ArchivoCSV archivoCsv;
    public Reporte reporte = new Reporte(); 
    public String anioReporte;
    public String idEmpleado;
    public String mesReporte;
    public ObservableList<Reporte> listaEnero = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaFebrero = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaMarzo = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaAbril = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaMayo = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaJunio = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaJulio = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaAgosto = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaSeptiembre = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaOctubre = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaNoviembre = FXCollections.observableArrayList();
    public ObservableList<Reporte> listaDiciembre = FXCollections.observableArrayList();
    //Metodo utilizado para setear los datos a las tablas
    public void setArchivoCsv(ArchivoCSV archivoCsv) {
        this.archivoCsv = archivoCsv;
        tbvEnero.setItems(getListaEnero());
        tbvFebrero.setItems(getListaFebrero());
        tbvMarzo.setItems(getListaMarzo());
        tbvAbril.setItems(getListaAbril());
        tbvMayo.setItems(getListaMayo());
        tbvJunio.setItems(getListaJunio());
        tbvJulio.setItems((getListaJulio()));
        tbvAgosto.setItems(getListaAgosto());
        tbvSeptiembre.setItems((getListaSeptiembre()));
        tbvOctubre.setItems(getListaOctubre());
        tbvNoviembre.setItems(getListaNoviembre());
        tbvDiciembre.setItems(getListaDiciembre());
    }
    //Metodo para setear valor a instancia de clase principal
    public void setArchivo(ArchivoCSV archivoCsv){
        this.archivoCsv = archivoCsv;
    }
    
    public ObservableList<Reporte> getListaFebrero() {
        return listaFebrero;
    }

    public ObservableList<Reporte> getListaMarzo() {
        return listaMarzo;
    }

    public ObservableList<Reporte> getListaAbril() {
        return listaAbril;
    }

    public ObservableList<Reporte> getListaMayo() {
        return listaMayo;
    }

    public ObservableList<Reporte> getListaJunio() {
        return listaJunio;
    }

    public ObservableList<Reporte> getListaJulio() {
        return listaJulio;
    }

    public ObservableList<Reporte> getListaAgosto() {
        return listaAgosto;
    }

    public ObservableList<Reporte> getListaSeptiembre() {
        return listaSeptiembre;
    }

    public ObservableList<Reporte> getListaOctubre() {
        return listaOctubre;
    }

    public ObservableList<Reporte> getListaNoviembre() {
        return listaNoviembre;
    }

    public ObservableList<Reporte> getListaDiciembre() {
        return listaDiciembre;
    }
    
    public ObservableList<Reporte> getListaEnero() {
        return listaEnero;
    }
    
    //Metodo para colocar el nombre del empleado en el label
    public void recibirNombre(String id, String nombre, String anio) throws IOException{
        idEmpleado = id;
        anioReporte = anio;
        lblNombre.setText(nombre);
        lblAnio.setText(anio);
    }
    //Metodo para voler a la vista de empleados
    @FXML
    public void regresar(){
        this.archivoCsv.cargarVistaEmpleados();
    }
    //Metodo para cargar la vista de entradas tarde
    @FXML
    public void cargarEntradasTarde(){
        this.archivoCsv.esconderVistaReportes();
        this.archivoCsv.mostrarVistaEntradasTarde(lblNombre.getText() , lblAnio.getText() , lblMes.getText(), idEmpleado);
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesEnero() throws IOException{
        lblMes.setText(("Enero"));
        reporte.setMes("01");
        this.listaEnero = Reporte.getListaReportes(idEmpleado , anioReporte , reporte.getMes());
        setArchivo(archivoCsv);
        cargarDatosEnero();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesFebrero() throws IOException{
        lblMes.setText(("Febrero"));
        reporte.setMes("02");
        this.listaFebrero = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosFebrero();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesMarzo() throws IOException{
        lblMes.setText("Marzo");
        reporte.setMes("03");
        this.listaMarzo = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosMarzo();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesAbril() throws IOException{
        lblMes.setText("Abril");
        reporte.setMes("04");
        this.listaAbril = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosAbril();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesMayo() throws IOException{
        lblMes.setText("Mayo");
        reporte.setMes("05");
        this.listaMayo = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosMayo();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesJunio() throws IOException{
        lblMes.setText("Junio");
        reporte.setMes("06");
        this.listaJunio = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosJunio();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesJulio() throws IOException{
        lblMes.setText("Julio");
        reporte.setMes("07");
        this.listaJulio = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosJulio();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesAgosto() throws IOException{
        lblMes.setText("Agosto");
        reporte.setMes("08");
        this.listaAgosto = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosAgosto();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesSeptiembre() throws IOException{
        lblMes.setText("Septiembre");
        reporte.setMes("09");
        this.listaSeptiembre = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosSeptiembre();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesOctubre() throws IOException{
        lblMes.setText("Octubre");
        reporte.setMes("10");
        this.listaOctubre = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosOctubre();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesNoviembre() throws IOException{
        lblMes.setText("Noviembre");
        reporte.setMes("11");
        this.listaNoviembre = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosNoviembre();
    }
    //Metodo para cambiar el mes en el label
    @FXML
    public void colocarMesDiciembre() throws IOException{
        lblMes.setText("Diciembre");
        reporte.setMes("12");
        this.listaDiciembre = Reporte.getListaReportes(idEmpleado, anioReporte, reporte.getMes());
        setArchivoCsv(archivoCsv);
        cargarDatosDiciembre();
    }
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    //Enviar los datos a la tabla
    public void cargarDatosEnero(){
        tbcNumero.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntrada.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalida.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadas.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvEnero.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvEnero.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosFebrero(){
        tbcNumeroFeb.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaFeb.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaFeb.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasFeb.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvFebrero.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvFebrero.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosMarzo(){
        tbcNumeroMar.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaMar.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaMar.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasMar.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvMarzo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvMarzo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosAbril(){
        tbcNumeroAbr.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaAbr.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaAbr.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasAbr.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvAbril.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvAbril.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosMayo(){
        tbcNumeroMay.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaMay.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaMay.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasMay.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvMayo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvMayo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosJunio(){
        tbcNumeroJun.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaJun.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaJun.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasJun.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvJunio.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvJunio.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosJulio(){
        tbcNumeroJul.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaJul.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaJul.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasJul.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvJulio.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvJulio.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosAgosto(){
        tbcNumeroAgo.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaAgo.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaAgo.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasAgo.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvAgosto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvAgosto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosSeptiembre(){
        tbcNumeroSep.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaSep.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaSep.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasSep.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvSeptiembre.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvSeptiembre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosOctubre(){
        tbcNumeroOct.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaOct.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaOct.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasOct.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvOctubre.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvOctubre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosNoviembre(){
        tbcNumeroNov.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaNov.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaNov.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasNov.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvNoviembre.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvNoviembre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
    //Enviar los datos a la tabla
    public void cargarDatosDiciembre(){
        tbcNumeroDic.setCellValueFactory(new PropertyValueFactory<Reporte, String>("numero"));
        tbcEntradaDic.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("entrada"));
        tbcSalidaDic.setCellValueFactory(new PropertyValueFactory<Reporte, Date>("salida"));
        tbcHorasTrabajadasDic.setCellValueFactory(new PropertyValueFactory<Reporte, String>("horasTrabajadas"));
        
        tbvDiciembre.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvDiciembre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reporte>() {

            @Override
            public void changed(ObservableValue<? extends Reporte> observable, 
                    Reporte oldValue, Reporte newValue) {
            }
        });
    }
}
