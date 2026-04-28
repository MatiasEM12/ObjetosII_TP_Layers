package ejercicio_1_test;

import ejercicio_1.modelo.Participante;
import ejercicio_1.modelo.RegistroParticipante;

import java.util.ArrayList;

public class FakeParticipanteDAO implements RegistroParticipante {

    ArrayList<Participante> participantes = new ArrayList<>();

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    @Override
    public void guardar(Participante participante) throws Exception {
        validacionParticipante(participante);
        participantes.add(participante);
    }

    public ArrayList<Participante> participantes() {

        return this.participantes;
    }

    private void validacionParticipante(Participante participante) throws Exception {
        if(participante==null){
            throw new IllegalArgumentException("El participante no puede ser nulo");
        }
    }
}
