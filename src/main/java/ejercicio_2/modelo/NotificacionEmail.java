package ejercicio_2.modelo;



import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


public class NotificacionEmail implements Notificador{

    private Session session;
    private String origen;
    private int cantNotificaciones;

    public NotificacionEmail(Session session ,String origen)  throws IllegalArgumentException{
        validacionSession(session);
        validacionOrigen(origen);
        this.session = session;
        this.origen=origen;
        this.cantNotificaciones=0;
    }

    @Override
    public void notificar(String email,String mensaje) throws IllegalArgumentException{
        validacionEmail(email);
        validacionMensaje(mensaje);
        try{
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(origen));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));

            message.setSubject("Notificacion");
            message.setText(mensaje);
            jakarta.mail.Transport.send(message);

            this.cantNotificaciones++;
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al envair el correo: "+e);
        }
    }

    @Override
    public String obtenerUltimoMail() {
        throw new RuntimeException("Obtener Ultimo mail no implementado");
    }

    @Override
    public int cantidadDeNotificaciones() {
        return this.cantNotificaciones;
    }

    private void validacionSession(Session session) throws IllegalArgumentException{
        if(session==null){
            throw new IllegalArgumentException("La session no puede ser null");
        }
    }

     private void validacionOrigen(String origen) throws IllegalArgumentException{
        if(origen==null || origen.isEmpty()){
            throw new IllegalArgumentException("El origen no puede ser null o vacio");
        }
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