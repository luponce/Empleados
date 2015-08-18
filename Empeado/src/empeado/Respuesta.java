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
class Respuesta {
    private boolean estado;
    private String mensaje;

    public Respuesta(boolean estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }
    
}
