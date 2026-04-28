package ejercicio_2_test;

import ejercicio_2.modelo.Email;
import ejercicio_2.modelo.Empleado;
import ejercicio_2.modelo.MensajeCumpleaños;
import ejercicio_2.modelo.NotificacionEmail;
import ejercicio_2.persistencia.ArchivoEmpleados;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.MonthDay;


import static org.junit.jupiter.api.Assertions.*;

public class MensajeCumpleañosTest {

    @Test
    public void MensajeCumpleaños(){
        var fakeNotificacionEmail= new FakeNotificacionEmail();
        var fakeArchivoEmpleados= new FakeArchivoEmpleados();

        fakeArchivoEmpleados.guardar(new Empleado( "Angus",
                "Young",
                LocalDate.parse("1982-10-08"),
                new Email("angus@acdc.com")));

        fakeArchivoEmpleados.guardar(new Empleado(  "Brian",
                "Johnson",
                LocalDate.parse("1975-09-11"),
                new Email("brian@acdc.com")));

        fakeArchivoEmpleados.guardar((new Empleado(
                "Pepe",
                "Argento",
                fechaCompleta(), new Email("test@gmail.com")
        )));


        var mensajeCumpleaños= new MensajeCumpleaños(fakeArchivoEmpleados,fakeNotificacionEmail);

        mensajeCumpleaños.enviarMensajeCumpleaños(MonthDay.now());

        assertEquals(1, fakeNotificacionEmail.cantidadDeNotificaciones());

    }
    @Test
    void notificacionEmailValidaSeCrea() {
        var session = jakarta.mail.Session.getInstance(new java.util.Properties());
        var notif = new NotificacionEmail(session, "test@mail.com");

        assertEquals(0, notif.cantidadDeNotificaciones());
    }
    @Test
    void enviarCumpleConFechaNullFalla() {
        var fakeNotificacionEmail = new FakeNotificacionEmail();
        var fakeArchivoEmpleados = new FakeArchivoEmpleados();

        var mensaje = new MensajeCumpleaños(fakeArchivoEmpleados, fakeNotificacionEmail);

        assertThrows(IllegalArgumentException.class, () ->
                mensaje.enviarMensajeCumpleaños(null));
    }

    @Test
    void notificarEmailNullFalla() {
        var session = jakarta.mail.Session.getInstance(new java.util.Properties());
        var notif = new NotificacionEmail(session, "test@mail.com");

        assertThrows(IllegalArgumentException.class, () ->
                notif.notificar(null, "mensaje"));
    }

    @Test
    void mensajeCumpleConNotificadorNullFalla() {
        var fakeArchivoEmpleados = new FakeArchivoEmpleados();

        assertThrows(IllegalArgumentException.class, () ->
                new MensajeCumpleaños(fakeArchivoEmpleados, null));
    }
    @Test
    void notificarMensajeNullFalla() {
        var session = jakarta.mail.Session.getInstance(new java.util.Properties());
        var notif = new NotificacionEmail(session, "test@mail.com");

        assertThrows(IllegalArgumentException.class, () ->
                notif.notificar("test@mail.com", null));
    }
    @Test
    void obtenerUltimoMailLanzaExcepcion() {
        var session = jakarta.mail.Session.getInstance(new java.util.Properties());
        var notif = new NotificacionEmail(session, "test@mail.com");

        assertThrows(RuntimeException.class, notif::obtenerUltimoMail);
    }

    @Test
    void mensajeCumpleConRegistroNullFalla() {
        var fakeNotificacionEmail = new FakeNotificacionEmail();

        assertThrows(IllegalArgumentException.class, () ->
                new MensajeCumpleaños(null, fakeNotificacionEmail));
    }

    private static LocalDate fechaCompleta() {
        LocalDate hoy = LocalDate.now();
        int dia = hoy.getDayOfMonth();
        int mes = hoy.getMonthValue();

        MonthDay diaMes = MonthDay.of(mes, dia);
        int año = 1999;
        LocalDate fechaCompleta = diaMes.atYear(año);
        return fechaCompleta;

    }

    @Test
    void emailInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Email("pruebagmail.com"));
    }

    @Test
    void emailValidoSeCrea() {
        var email = new Email("test@mail.com");
        assertEquals("test@mail.com", email.email());
    }

    @Test
    void empleadoValidoSeCrea() {
        var empleado = new Empleado(
                "Juan",
                "Perez",
                LocalDate.parse("1990-01-01"),
                new Email("juan@mail.com")
        );

        assertEquals("Juan", empleado.nombre());
        assertEquals("Perez", empleado.apellido());
    }

    @Test
    void empleadoConNombreNullFalla() {
        assertThrows(IllegalArgumentException.class, () ->
                new Empleado(null, "Perez", LocalDate.parse("1990-01-01"), new Email("a@mail.com")));
    }

    @Test
    void empleadoConApellidoVacioFalla() {
        assertThrows(IllegalArgumentException.class, () ->
                new Empleado("Juan", "", LocalDate.parse("1990-01-01"), new Email("a@mail.com")));
    }

    @Test
    void empleadoMenorDeEdadFalla() {
        assertThrows(IllegalArgumentException.class, () ->
                new Empleado("Juan", "Perez", LocalDate.now().minusYears(10), new Email("a@mail.com")));
    }

    @Test
    void esTuCumpleanosDevuelveTrue() {
        var empleado = new Empleado(
                "Juan",
                "Perez",
                LocalDate.parse("1990-05-10"),
                new Email("juan@mail.com")
        );

        assertTrue(empleado.esTuCumpleaños(MonthDay.of(5, 10)));
    }

    @Test
    void esTuCumpleanosDevuelveFalse() {
        var empleado = new Empleado(
                "Juan",
                "Perez",
                LocalDate.parse("1990-05-10"),
                new Email("juan@mail.com")
        );

        assertFalse(empleado.esTuCumpleaños(MonthDay.of(1, 1)));
    }

    @Test
    void guardarYLeerEmpleado() {
        String ruta = "test_empleados.txt";
        new File(ruta).delete(); // limpiar antes

        var archivo = new ArchivoEmpleados(ruta);

        var empleado = new Empleado(
                "Juan",
                "Perez",
                LocalDate.parse("1990-01-01"),
                new Email("juan@mail.com")
        );

        archivo.guardar(empleado);

        assertEquals(1, archivo.empleados().size());

        archivo.eliminarRegistro(ruta); // limpiar después
    }

    @Test
    void eliminarArchivo() {
        String ruta = "test_empleados.txt";
        var archivo = new ArchivoEmpleados(ruta);

        archivo.guardar(new Empleado(
                "Juan",
                "Perez",
                LocalDate.parse("1990-01-01"),
                new Email("juan@mail.com")
        ));

        archivo.eliminarRegistro(ruta);

        assertFalse(new java.io.File(ruta).exists());
    }

    @Test
    void guardarEmpleadoNullFalla() {
        String ruta = "test_empleados.txt";
        var archivo = new ArchivoEmpleados(ruta);

        assertThrows(IllegalArgumentException.class, () ->
                archivo.guardar(null));
    }

    @Test
    void eliminarArchivoInexistenteFalla() {
        String ruta = "no_existe.txt";
        var archivo = new ArchivoEmpleados(ruta);

        assertThrows(RuntimeException.class, () ->
                archivo.eliminarRegistro(ruta));
    }

}
