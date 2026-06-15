package ejercicio_3.modelo;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
public class LogAspect {

    private static final String ARCHIVO = "src/main/resources/logs.txt";

    @Before("execution(* ejercicio_3..*.listarConcursos(..)) || execution(* ejercicio_3..*.guardarInscripto(..))")
    public void loguear(JoinPoint joinPoint) {

        System.out.println("ENTRO AL ASPECTO");

        String nombreMetodo = joinPoint.getSignature().getName();

        Object[] parametros = joinPoint.getArgs();

        String valoresParametros;

        if (parametros.length == 0) {
            valoresParametros = "sin parametros";
        } else {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < parametros.length; i++) {

                sb.append(parametros[i]);

                if (i < parametros.length - 1) {
                    sb.append("|");
                }
            }

            valoresParametros = sb.toString();
        }

        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        try (FileWriter writer = new FileWriter(ARCHIVO, true)) {

            writer.write("\"" + nombreMetodo + "\", "
                    + "\"" + valoresParametros + "\", "
                    + "\"" + fechaHora + "\"");

            writer.write(System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

