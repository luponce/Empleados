/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empeado;

/**
 *
 * @author Luponce
 */
public class Cargos {
    protected String idcargo;
    protected String descripcion;

    public Cargos(){}
    public Cargos(String idcargo, String descripcion) {
        this.idcargo = idcargo;
        this.descripcion = descripcion;
    } 

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(String idcargo) {
        this.idcargo = idcargo;
    }

      
    
}
