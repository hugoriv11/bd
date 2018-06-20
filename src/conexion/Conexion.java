/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HugoJr. <Hugo Rivera at 00161417@uca.edu.sv>
 */
public class Conexion {
    
    private String user, pass, driver, url;
    
    private Connection conect;
    
    public static Conexion instance;
    
    public synchronized static Conexion conectar(){
        if (instance == null) {
            return new Conexion();
        }
        return instance;
    }
    
    private Conexion() {
        cargarCredenciales();

        try {

            Class.forName(this.driver);
            conect = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCredenciales() {
        user = "root";
        pass = "";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/inscripciones";
    }

    public Connection getCnx() {
        return conect;
    }

    public void cerrarConexion() {
        instance = null;
    }
}
