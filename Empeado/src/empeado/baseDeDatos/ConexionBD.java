/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empeado.baseDeDatos;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author Luponce
 */
public class ConexionBD {
    Properties props = new Properties();
    public ConexionBD() {

        try {
            props.load(ConexionBD.class.getClassLoader().getResourceAsStream("empeado/configuracion/config.properties"));
        } catch (IOException ex) {
        }
    }
    public Connection getConexion() {
        Connection miConexion = null;
        ConexionPGSQL pgConexion = new ConexionPGSQL();
        miConexion = pgConexion.getConexion(props.getProperty("dbuser"), props.getProperty("dbpassword"), props.getProperty("dbhost"), Integer.parseInt(props.getProperty("dbport")), props.getProperty("database"));
        return miConexion;
    }
    
}
