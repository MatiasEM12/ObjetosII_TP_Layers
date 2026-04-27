package ejercicio_3.modelo;

import java.util.ArrayList;

public interface GestionArchivo <T>{

    public  void crear(T dato);
    public  void eliminarArchivo(String ruta);
    public ArrayList<T> listar();
}
