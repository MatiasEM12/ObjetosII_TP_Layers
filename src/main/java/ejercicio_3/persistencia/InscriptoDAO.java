package ejercicio_3.persistencia;

import ejercicio_3.modelo.Inscripto;

import java.util.List;

public interface InscriptoDAO {
    public  void create(Inscripto inscripto);

    public  List<Inscripto> findAll();

    public List<String> findAllInscriptos();

    public void truncateTabla();
}
