package ejercicio_3.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Concurso {
    private int id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Concurso(int id, String nombre, LocalDate fechaInicio, LocalDate fechaFin)throws IllegalArgumentException {
        validarId(id);
        validarNombre(nombre);
        validarFechas(fechaInicio, fechaFin);

        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }



    private void validarId(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser un número positivo");
        }
    }

    private void validarNombre(String nombre) throws IllegalArgumentException {
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del concurso no puede estar vacío");
        }
    }

    public void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) throws IllegalArgumentException{
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin");
        }
    }



    public boolean estaActivo(){
        LocalDate hoy = LocalDate.now();
        return (hoy.isEqual(fechaInicio) ||
                hoy.isAfter(fechaInicio)) && (hoy.isEqual(fechaFin) ||
                hoy.isBefore(fechaFin));
    }

    public int id() {
        return id;
    }
    public String nombre() {
        return nombre;
    }

    public LocalDate fechaInicio() {
        return fechaInicio;
    }
    public LocalDate fechaFin() {
        return fechaFin;
    }

    public boolean mismoId(Concurso otro) {
        return this.id == otro.id;
    }

    public String toFile(){
        return id + "," + nombre + "," + toLocalDate(fechaInicio) + "," + toLocalDate(fechaFin);
    }

    private String toLocalDate(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return fecha.format(formatter);
    }



}
