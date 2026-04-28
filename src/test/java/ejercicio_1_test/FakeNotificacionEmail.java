package ejercicio_1_test;

import ejercicio_2.modelo.Notificador;

import java.util.ArrayList;

public class FakeNotificacionEmail implements Notificador {
    ArrayList<String> notificaciones;
    int cantNotificaciones;

    public FakeNotificacionEmail(){
        this.notificaciones=new ArrayList<>();
        this.cantNotificaciones=0;
    }

    @Override
    public void notificar(String email, String mensaje) {
        validacionMensaje(mensaje);
        validacionEmail(email);
        notificaciones.add(email+mensaje);
        this.cantNotificaciones++;
    }

    @Override
    public String obtenerUltimoMail() {
        return this.notificaciones.getLast();
    }

    @Override
    public int cantidadDeNotificaciones() {
        return this.cantNotificaciones;
    }


    private void validacionEmail(String email) throws IllegalArgumentException{
        if(email==null || email.isEmpty()){
            throw new IllegalArgumentException("El email no puede ser null o vacio");
        }
    }

    private void validacionMensaje(String mensaje) throws IllegalArgumentException{
        if(mensaje==null || mensaje.isEmpty()){
            throw new IllegalArgumentException("El mensaje no puede ser null o vacio");
        }
    }
}
