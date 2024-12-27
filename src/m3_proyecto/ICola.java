package m3_proyecto;

/**
 * 
 * TSU EN Tecnologias de la Informacion Area de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Package: m3_t2_colas;
 * Clase: M3_T2_Colas
 * Fecha: 11 nov 2024
 * @param <T>
 */
public interface ICola<T extends Comparable<T>> {
    public boolean colaVacia();
    public void insertar(T elemento);
    public T eliminar();
    public boolean buscar(T elemento);

    public void QueueInsertar(T elemento);
    public T QueueEliminar();
    public T QueuePrimerElemento();
    public void QueueVaciarCola();
    public boolean QueueBuscar(T elemento);
    public int QueueTam();
    
    public Arbol PonerElementosEnArbol();  
}