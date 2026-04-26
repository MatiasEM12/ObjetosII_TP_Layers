package ejercicio_2.modelo;

import java.time.MonthDay;
import java.time.temporal.ValueRange;

public class MensajeCumpleaños {
    private RegistroEmpleados registroEmpleados;
    private Notificador notificador;

    public MensajeCumpleaños(RegistroEmpleados registroEmpleados, Notificador notificador) {
        this.registroEmpleados = registroEmpleados;
        this.notificador = notificador;
    }

     public void enviarMensajeCumpleaños(MonthDay hoy) {
         var empleados= registroEmpleados.empleados();

         for( var empleado : empleados){
             if(empleado.esTuCumpleaños(hoy)){
                    notificador.notificar(empleado.email(), "Feliz cumpleaños ");
             }
         }

    }
}
