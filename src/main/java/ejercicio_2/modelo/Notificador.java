package ejercicio_2.modelo;

public interface Notificador {

    public void notificar(String email, String mensaje);

    public String obtenerUltimoMail();

    public int cantidadDeNotificaciones();
}
