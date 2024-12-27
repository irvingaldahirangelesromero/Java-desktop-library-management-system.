package m3_proyecto;

/**
 * 
 * TSU EN Tecnologias de la Informacion Area de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Fecha: 11 nov 2024
 */
import java.util.LinkedList;
import java.util.Queue;
                     
public class Cola<T extends Comparable<T>> implements ICola<T>{
    Queue<T> queue = new LinkedList<>();
    
    private DNodo<T> frente;
    private DNodo<T> fin;
    private int tam;
    
    public Cola(){
        frente = null;
        fin = null;
        tam = 0;
    }
    
    @Override
    public boolean colaVacia() {
        return (frente==null && fin == null && tam==0);
    }

    @Override
    public void insertar(T elemento) {
        if(colaVacia()){
            frente = new DNodo<>(elemento);
            fin = frente;
        }else{
            fin.sig = new DNodo<>(fin, elemento);
            fin = fin.sig;
        }
        tam++;
    }

    @Override
    public T eliminar() {
        T dato = null;
        if(!colaVacia()){
            dato = frente.dato;
            
            if(tam == 1){
                frente = fin = null;
                tam = 0;
            }else{
                frente = frente.sig;
                frente.ant = null;
                tam--;
            }
        }
        return dato;
    }

    @Override
    public String toString() {
        DNodo<T> aux = frente;
        String cad = "";
        while (aux!=null){
            cad += aux.dato;
            aux= aux.sig;
        }
        return cad;
    }

    @Override
    public boolean buscar(T elemento) {
        DNodo<T> aux = frente;
        while (aux != null) {
            if (aux.dato.equals(elemento)) {
                return true;
            }
            aux = aux.sig;
        }
        return false;
    }

    @Override
    public void QueueInsertar(T elemento) {
        queue.add(elemento);
    }

    @Override
    public T QueueEliminar() {
        return queue.poll();
    }

    @Override
    public T QueuePrimerElemento() {
        return queue.peek();
    }


    @Override
    public void QueueVaciarCola() {
        queue.clear();
    }

    @Override
    public int QueueTam() {
        return queue.size();
    }

    @Override
    public boolean QueueBuscar(T elemento) {
        return queue.contains(elemento);
    }

    @Override
    public Arbol<T> PonerElementosEnArbol() {
        Arbol<T> bst = new Arbol<>();
        while (!colaVacia()) {
            T dato = eliminar();
            bst.insertar(dato);
        }
        return bst;
    }
    
    private class DNodo<T> {
    T dato;
    DNodo<T> ant;
    DNodo<T> sig;
    
    public DNodo(T dato){
        this.dato = dato;
        ant = null;
        sig = null;
    }
    
    public DNodo(DNodo<T> ant, T dato){
        this.ant = ant;
        this.dato = dato;
        sig = null;
    }
    }

}