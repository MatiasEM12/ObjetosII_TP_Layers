package ejercicio_1_test;


import ejercicio_1.modelo.GestorParticipante;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AgregarParticipanteTest {

    @Test
    public void agregarParticipante()  {
        try{
        var fakeRegistroParticipante= new FakeParticipanteDAO();
        var gestorParticipante= new GestorParticipante(fakeRegistroParticipante);


            gestorParticipante.guardarParticipante("Pepe", "123456789", "Buenos Aires");

            assertTrue(fakeRegistroParticipante.participantes().size() == 1, "Se esperaba que el participante se agregara correctamente");
        } catch (Exception e) {
            System.out.println(e);

        }



    }
}
