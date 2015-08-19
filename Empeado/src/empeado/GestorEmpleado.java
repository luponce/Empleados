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
public class GestorEmpleado {
    private static GestorEmpleado instanciaUnica;
    private ArrayList<Empleado> empleadosDelSistema = new ArrayList<Empleado>();
    Statement snt;

    private GestorEmpleado() {

    }

    public static GestorEmpleado getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new GestorEmpleado();
            instanciaUnica.materializarColeccion();
        }
        return instanciaUnica;
    }

    private void materializarColeccion() {
        ConexionBD miConexion = new ConexionBD();
        Connection conexion = miConexion.getConexion();
        try {
            //Creo la sentencia preparada
            PreparedStatement ps = conexion.prepareStatement("select * from empleado");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setidCargo(rs.getString("id_cargo"));
                e.setFec_ing(rs.getString("fechaingreso"));
                e.setCodigo(rs.getString("codigo"));
                empleadosDelSistema.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Respuesta registrarEmpleado(String codigo, String fechaingreso, String idcargo) {
        ConexionBD miConexion = new ConexionBD();
        Connection conexion = miConexion.getConexion();
        try {
            //Creo la sentencia preparada
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO empleado(codigo, fechaingreso, id_cargo) VALUES (?, ?, ?) RETURNING codigo");
            ps.setString(1, codigo);
            ps.setString(2, fechaingreso);
            ps.setString(3, idcargo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Empleado nuevo = new Empleado();
                nuevo.setCodigo(rs.getString(1));
                nuevo.setFec_ing(fechaingreso);
                nuevo.setidCargo(idcargo);
                empleadosDelSistema.add(nuevo);
                return new Respuesta(true,  "El empleado se ha registrado correctamente");
               
            }
            return new Respuesta(false, "Error al registrar al empleado");
        } catch (SQLException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false, "Error insertando en la DB");
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                return new Respuesta(false, "Error insertando en la DB");
            }
        }

    }
    
    public Respuesta modificarEmpleado(String codigo, String fechaingreso, String idcargo){
//        ConexionBD miConexion = new ConexionBD();
//        Connection conexion = miConexion.getConexion();
//        
//        try{
//            PreparedStatement ps = conexion.prepareStatement("UPDATE persona set fechaingreso="+fechaingreso+"id_crgo="+idcargo+" WHERE codogo="+codigo);
//            ps.executeUpdate();
//            ps.close();
//            return new Respuesta(true, "El empleado se modifico correctamente");
//        }catch (SQLException ex) {
//            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
//            return new Respuesta(false, "Error insertando en la DB");
//        } finally {
//            try {
//                conexion.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
//                return new Respuesta(false, "Error insertando en la DB");
//            }
//        }
        ConexionBD miConexion = new ConexionBD();
        Connection conexion = miConexion.getConexion();
        try // si se conecta
        {
            
            String SQL;
            SQL = "UPDATE empleado set fechaingreso='"+fechaingreso+"', id_cargo='"+idcargo+"' WHERE codigo='"+codigo+"'";
            snt = conexion.createStatement();
            snt.executeUpdate(SQL);
            snt.close();
            return new Respuesta(true, "El empleado se modifico correctamente");
           
        }catch (SQLException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(false, "Error insertando en la DB");
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                return new Respuesta(false, "Error insertando en la DB");
            }
        }

    }
    
     public List<Empleado> getEmpleado() {
        return empleadosDelSistema;
    }
     
    public Empleado buscarEmpleado(String codig){
        
        Empleado emp = new Empleado();        
        
      
        if(!empleadosDelSistema.isEmpty()){
            //JOptionPane.showMessageDialog(null,"SI Existe Registro");
            
            boolean encontrado=false;
            for(int i=0; i< empleadosDelSistema.size(); i++) {
                emp=empleadosDelSistema.get(i);
                if(emp.getCodigo().compareTo(codig)==0){
                    encontrado=true; 
                    return emp;
                                       
                }    
            }
        }
         else {
            JOptionPane.showMessageDialog(null,"No Existe Registro");
            return null;
        }
        
    return emp; 
        
    }
    
}
