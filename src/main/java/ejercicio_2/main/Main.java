package ejercicio_2.main;


import ejercicio_2.modelo.*;
import ejercicio_2.persistencia.ArchivoEmpleados;
import jakarta.mail.Session;

import java.time.LocalDate;
import java.time.MonthDay;

public class Main {

    private static String RUTA="src/main/java/resources/empleados.txt";

    public static void main(String[] args) {


        Session session = SetUpMailTrap.crearSession();
        String origen = SetUpMailTrap.getOrigen();

        Notificador notificador = new NotificacionEmail(session, origen);
        RegistroEmpleados registroEmpleados= inizializarEmpleados(origen);

        MensajeCumpleaños mensajeCumpleaños = new MensajeCumpleaños(registroEmpleados, notificador);
        mensajeCumpleaños.enviarMensajeCumpleaños(MonthDay.now());

        registroEmpleados.eliminarRegistro(RUTA);
    }



    private static  RegistroEmpleados inizializarEmpleados(String origen) {

        RegistroEmpleados archivo = new ArchivoEmpleados(RUTA);

        archivo.guardar(new Empleado(
                "Angus",
                "Young",
                LocalDate.parse("1982-10-08"),
                new Email("angus@acdc.com")
        ));

        archivo.guardar(new Empleado(
                "Brian",
                "Johnson",
                LocalDate.parse("1975-09-11"),
                new Email("brian@acdc.com")
        ));


        archivo.guardar(new Empleado(
                "Pepe",
                "Argento",
                fechaCompleta(), new Email(origen)
        ));

        System.out.println("Empleados inicializados correctamente");
        return archivo;
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
