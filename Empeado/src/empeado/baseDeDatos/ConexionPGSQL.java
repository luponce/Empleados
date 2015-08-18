/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empeado.baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luponce
 */
public class ConexionPGSQL {
     public ConexionPGSQL() {
    }

    public Connection getConexion(String usuario, String clave, String host, int port, String nombreBaseDatos) {
        try {
            Connection miConexionPostgres = null;
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.put("user", usuario);
            props.put("password", clave);
            miConexionPostgres = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + nombreBaseDatos, props);
            return miConexionPostgres;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionPGSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPGSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
