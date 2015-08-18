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
public class Empleado extends Persona{
    private String idcargo;
    private String fec_ing;
    private String codigo;
    
    public Empleado(){}
    public Empleado(String codigo, String apellido, String nombres, String fec_ing, String idcargo ) {
        super(codigo,apellido,nombres);
        this.idcargo = idcargo;
        this.fec_ing = fec_ing;
    }
    public String getidCargo() {
        return idcargo;
    }
    public void setidCargo(String idcargo) {
        this.idcargo = idcargo;
    }
    public String getFec_ing() {
        return fec_ing;
    }
    public void setFec_ing(String fec_ing) {
        this.fec_ing = fec_ing;
    }

    public String getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(String idcargo) {
        this.idcargo = idcargo;
    }
    
     public String toString(){
    
        return this.fec_ing +" "+ this.idcargo;
        
    }
    
}

    

