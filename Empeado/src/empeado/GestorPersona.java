/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empeado;

import empeado.baseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luponce
 */
public class GestorPersona {
    private static GestorPersona instanciaUnica;
    private final ArrayList<Persona> personasDelSistema = new ArrayList<>();
    private FrmPrincipal principal;
    Statement snt;
    
    private GestorPersona() {

    }
    public static GestorPersona getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new GestorPersona();
            instanciaUnica.materializarColeccion();
        }
        return instanciaUnica;
    }
    
    private void materializarColeccion() {
        ConexionBD miConexion = new ConexionBD();
        Connection conexion = miConexion.getConexion();
        try {
            //Creo la sentencia preparada
            PreparedStatement ps = conexion.prepareStatement("select * from persona");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setCodigo(rs.getString("codigo"));
                p.setNombres(rs.getString("nombres"));
                p.setApellido(rs.getString("apellido"));
                personasDelSistema.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public Respuesta modificarPersona(String cod, String app, String nom){
        ConexionBD miConexion = new ConexionBD();
        Connection conexion = miConexion.getConexion();
        try // si se conecta
        {
            
            String SQL;
            SQL = "UPDATE persona set apellido='"+app+"', nombres='"+nom+"' WHERE codigo='"+cod+"'";
            snt = conexion.createStatement();
            snt.executeUpdate(SQL);
            snt.close();
            return new Respuesta(true,null);
//             "La persona se modifico correctamente"
           
        }catch (SQLException ex) {
            Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false, "Error insertando en la DB");
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
                return new Respuesta(false, "Error insertando en la DB");
            }
        }
                
//        boolean ok=true;
//        try{
//            PreparedStatement ps = conexion.prepareStatement("UPDATE persona set apellido="+app+"nombres="+nom+" WHERE codogo="+cod);
//            ps.executeUpdate();
//            ps.close();
//            return new Respuesta(true, "La persona se modifico correctamente");
//        }catch (SQLException ex) {
//            Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
//            return new Respuesta(false, "Error insertando en la DB");
//        } finally {
//            try {
//                conexion.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
//                return new Respuesta(false, "Error insertando en la DB");
//            }
//        }

    }
    public Respuesta registrarPersona(String codigo, String apellido, String nombre) {
        ConexionBD miConexion = new ConexionBD();
        Connection conexion = miConexion.getConexion();
        try {
            //Creo la sentencia preparada
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO persona(codigo, apellido, nombres) VALUES (?, ?, ?) RETURNING codigo");
            ps.setString(1, codigo);
            ps.setString(2, apellido);
            ps.setString(3, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Persona nuevo = new Persona();
                nuevo.setCodigo(rs.getString(1));
                nuevo.setApellido(apellido);
                nuevo.setNombres(nombre);
                personasDelSistema.add(nuevo);
                return new Respuesta(true,null );
//                "La persona se ha registrado correctamente"
            }
            return new Respuesta(false, "Error al registrar al jugador");
        } catch (SQLException ex) {
            Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false, "Error insertando en la DB");
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
                return new Respuesta(false, "Error insertando en la DB");
            }
        }

    }
    
    public List<Persona> getPersona() {
        return personasDelSistema;
    }
    
    public Persona buscarPersona(String codig){
        
        Persona per = new Persona();        
        
      
        if(!personasDelSistema.isEmpty()){
            //JOptionPane.showMessageDialog(null,"SI Existe Registro");
            
            boolean encontrado=false;
            for(int i=0; i< personasDelSistema.size(); i++) {
                per=personasDelSistema.get(i);
                if(per.getCodigo().compareTo(codig)==0){
                    //JOptionPane.showMessageDialog(null,"SI");
                    encontrado=true; 
                    return per;
                                       
                }    
            }
        }
         else {
            JOptionPane.showMessageDialog(null,"No Existe Registro");
            return null;
        }
        
    return per; 

    }
}
