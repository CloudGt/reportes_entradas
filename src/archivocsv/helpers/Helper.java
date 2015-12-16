/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivocsv.helpers;

import archivocsv.ArchivoCSV;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Angel Cabrera
 * @cliente Dacotrans
 */

public class Helper {
    
    private static ArchivoCSV archivoCSV;
    private Connection connection;
    private static Helper instance;
    
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_SERVER = "192.168.0.71";
    private static final String DB_INSTANCE = "SQLSYSTEM_DACO";
    private static final String DB_NAME = "RRHH";
    
    public static void setArchivoCSV(ArchivoCSV archivoCSV) {
        Helper.archivoCSV = archivoCSV;
    }
    
    //Metodo para conectar a la base de datos
    public Helper() throws ClassNotFoundException, SQLException{
       Class.forName(DRIVER);
        connection = DriverManager.getConnection("jdbc:sqlserver://" + DB_SERVER + ";instanceName=" + DB_INSTANCE +";"
                + "databaseName=" + DB_NAME + ";user=sa" + ";password=daco");
    }
     
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if (instance == null){
            instance = new Helper();
        }
        return instance.connection;
    }
    
}
