package ejercicio_1_test;

import ejercicio_2.modelo.Email;
import ejercicio_2.modelo.Empleado;
import ejercicio_2.modelo.MensajeCumpleaños;
import org.junit.jupiter.api.Test;

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

        assertTrue(fakeNotificacionEmail.cantidadDeNotificaciones()==1 ,"Se esperaba que se enviara un mensaje de cumpleaños a Pepe Argento");


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
}
