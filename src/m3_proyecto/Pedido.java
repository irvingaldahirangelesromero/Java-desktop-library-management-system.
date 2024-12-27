package m3_proyecto;

/**
 * 
 * TSU EN Tecnologias de la Informacion Area de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Package: m3_proyecto;
 * Clase: M3_Proyecto
 * Fecha: 16 nov 2024
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Pedido implements Comparable<Pedido>{
    private long id;
    private String ubicacion;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;
    private static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");


    public Pedido(){
        this.id = 0;
        this.ubicacion = "";
        this.descripcion = "";
        this.fecha = null;
        this.hora = null;
    }
    
    public Pedido(long id, String ubicacion, String descripcion, LocalDate fecha, LocalTime hora){
        this.id = id;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
    }
    
    public DateTimeFormatter getFormatoFecha() {
        return formatoFecha;
    }

    public DateTimeFormatter getFormatoHora() {
        return formatoHora;
    }    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

//    @Override
//    public int hashCode() { 
//        return fecha.hashCode();
//    }

@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Pedido pedido = (Pedido) obj;
    return Objects.equals(fecha, pedido.fecha);
}

@Override
public int hashCode() {
    return Objects.hash(fecha);
}

    
@Override
public int compareTo(Pedido obj) {
    // Comparar primero por fecha
    return this.fecha.compareTo(obj.fecha);
    
}

    
    @Override
    public String toString() {
        return "Pedido:" + id + "\nUbicacion:" + ubicacion + "\nFecha:" + fecha + "\nHora:" + hora + "\nDescripcion:" + descripcion  + "\n\n";
    }

}