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
public class Persona {
    private String codigo;
    private String apellido;
    private String nombres;
    
    public Persona(String codigo, String apellido, String nombres) {
        this.codigo = codigo;
        this.apellido = apellido;
        this.nombres = nombres;}

    Persona() {
    }

    public String getCodigo() {
        return codigo;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
     public String toString(){
    
        return this.nombres +" "+ this.apellido;
        
    }
    
    
  
}

   


