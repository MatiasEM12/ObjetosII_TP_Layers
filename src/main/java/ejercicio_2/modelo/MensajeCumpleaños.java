package ejercicio_2.modelo;

import java.time.MonthDay;
import java.time.temporal.ValueRange;
import java.util.List;

public class MensajeCumpleaños {
    private RegistroEmpleados registroEmpleados;
    private Notificador notificador;

    public MensajeCumpleaños(RegistroEmpleados registroEmpleados, Notificador notificador) throws IllegalArgumentException {
        validacionNotificador(notificador);
        validaionRegistroEmpleados(registroEmpleados);
        this.registroEmpleados = registroEmpleados;
        this.notificador = notificador;
    }

     public void enviarMensajeCumpleaños(MonthDay hoy)throws IllegalArgumentException {
        validacionMonthDay(hoy);
         List<Empleado> empleados= registroEmpleados.empleados();

         for( Empleado empleado : empleados){
             if(empleado.esTuCumpleaños(hoy)){
                    notificador.notificar(empleado.email(), "Feliz cumpleaños ");
             }
         }

    }

    private void validacionMonthDay(MonthDay monthDay) throws IllegalArgumentException{
        if(monthDay==null){
            throw new IllegalArgumentException("El mes y día no pueden ser null");
        }
    }

    private void validaionRegistroEmpleados(RegistroEmpleados registroEmpleados) throws IllegalArgumentException{
        if(registroEmpleados==null){
            throw new IllegalArgumentException("El registro de empleados no puede ser null");
        }
    }

     private void validacionNotificador(Notificador notificador) throws IllegalArgumentException{
        if(notificador==null){
            throw new IllegalArgumentException("El notificador no puede ser null");
        }
    }
}
