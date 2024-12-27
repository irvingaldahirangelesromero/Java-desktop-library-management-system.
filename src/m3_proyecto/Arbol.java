package m3_proyecto; 

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
/**
 * 
 * TSU EN Tecnologias de la Informacion Area de Software Multiplataforma
 * @author Irving Aldahir Angeles Romero
 * Estructura de Datos Aplicadas
 * Fecha: 11 nov 2024
 * @param <T>
 */
public class Arbol<T extends Comparable<T>> {
    Nodo<T> raiz; //unico elemento cuando se crea un arbol
    private int tam;
    private TreeSet<T> treeset = new TreeSet<>();

    public Arbol() {
        this.raiz = null; //se crea vacio
        this.tam = 0;
    }
        
    public boolean esVacio(){
        return (raiz==null);
    }

    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new Nodo<>(dato);
            tam++;
        } else {
            Nodo<T> aux = raiz;
            boolean band = true;
            while (band) {
                if (dato.compareTo(aux.dato) < 0) {
                    if (aux.izq != null) {
                        aux = aux.izq;
                    } else {
                        aux.izq = new Nodo<>(dato, aux);
                        tam++;
                        band = false;
                    }
                } else {
                    if (aux.der != null) {
                        aux = aux.der;
                    } else {
                        aux.der = new Nodo<>(dato, aux);
                        tam++;
                        band = false;
                    }
                }
            }
        }
        
        treeset.add(dato);
    }

    public boolean buscar(T dato) {
        Nodo<T> aux = raiz; //creamos un nodo que se ubicara en raiz
        
        while (aux != null) { //mientras el nodo actual no este vacio
            if (dato.compareTo(aux.dato) == 0)  //compara si el dato del nodo actual es el que se busca
                return true;
            else { 
                if (dato.compareTo(aux.dato)<0) // si el dato que se busca es MENOR al del nodo actual
                    aux = aux.izq; //saltar a la izquierda
                else if(dato.compareTo(aux.dato) > 0) // si el dato que se busca es MAYOR al del nodo actual
                    aux = aux.der;   //saltar a la derecha
            }
        }
        
        return false;
    }

    private Nodo buscarNodo(T dato) {
        Nodo<T> aux = raiz; //creamos un nodo que se ubicara en raiz
        
        while (aux != null) { //mientras el nodo actual no este vacio
            if (dato.compareTo(aux.dato) == 0)  //compara si el dato del nodo actual es el que se busca
                return aux;
            else { 
                if (dato.compareTo(aux.dato)<0) // si el dato que se busca es MENOR al del nodo actual
                    aux = aux.izq; //saltar a la izquierda
                else if(dato.compareTo(aux.dato) > 0) // si el dato que se busca es MAYOR al del nodo actual
                    aux = aux.der;   //saltar a la derecha
            }
        }
        
        return null;
    }

    private boolean esHoja(Nodo<T> aux){
        return(aux.izq == null && aux.der == null);  // las hojas no tienen nodo hijo izquierdo ni derecho 
    }
   
    private void eliminaHoja(Nodo<T> aux){
        if(esHoja(aux)){ //Solo si es hoja
            if(aux.padre == null) //si la hoja a eliminar se trata de raiz
                raiz = null; //vaciar raiz
            else {   
                if(aux == aux.padre.izq)  //Si la hoja se trata de ser hoja izquierda del padre
                    aux.padre.izq = null;//poner la hoja izquierda del padre en null
                else if(aux == aux.padre.der) //Si la hoja se trata de ser hoja derecha del padre
                    aux.padre.der = null; //poner la hoja derecha del padre en null
            }   
        }
    }
    private boolean tieneUnHijo(Nodo<T> aux){ //un nodo tiene un hijo cuando uno de sus lados esta ocupado y el otro nulo
        return((aux.izq == null && aux.der != null) || 
               (aux.izq != null && aux.der == null));
    }
    
    private void eliminaUnHijo(Nodo<T> aux){                                                                                 
        if(tieneUnHijo(aux)){ //Solo si tiene un hijo
            if(aux.padre==null){ //si el nodo a eliminar es raiz
                if(aux.izq != null) //si tiene hijo izquierdo significa que el unico hijo que tenia era izquierdo
                   raiz = aux.izq; //por lo tanto el hijo izquierdo del nodo a eliminar se convertira ahora en raiz
                else if(aux.der != null) //si tiene hijo derecho significa que el unico hijo que tenia era derecho
                   raiz = aux.der; //por lo tanto el hijo derecho del nodo a eliminar se convertira ahora en raiz 
                
                raiz.padre = null;  //el padre del que anteriormete era nodo hijo apuntaba a raiz, la cual ya no existe y al convertirse en raiz su padre debera apuntar a nulo
            } else {      
                if(aux.izq != null){ // Si no, si el nodo a eliminar tiene un hijo izquierdo 
                    if(aux == aux.padre.izq) //si el nodo a eliminar corresponde a ser hijo izquierdo
                        aux.padre.izq = aux.izq;  //la izquierda del padre apuntara al hijo izquierdo del hijo
                    else if(aux == aux.padre.der) //si no, si el nodo a eliminar corresponde a ser hijo derecho
                        aux.padre.der = aux.izq; // la derecha del padre apuntara a la izquierda del hijo

                    aux.izq.padre=aux.padre;  // ???
                    
                } else if(aux.der != null) { // Si no, si el nodo a eliminar tiene un hijo derecho 
                    if(aux == aux.padre.izq) //si el nodo a eliminar corresponde a ser hijo izquierdo
                        aux.padre.izq = aux.der; //la izquierda del padre apuntara al hijo derecho del hijo
                    else if(aux == aux.padre.der) //si no, si el nodo a eliminar corresponde a ser hijo derecho
                        aux.padre.der = aux.der;// la derecha del padre apuntara a la derecha del hijo

                    aux.der.padre = aux.padre; //  ???                                                                                  
                }
            }                          
        }
     }
    
    private Nodo buscarMayor(Nodo<T> m){
        while(m.der != null)
            m = m.der;
        return m;
    }
    
    private void eliminaDosHijos(Nodo<T> aux){ //se usara el caso a, que es encontrar el predecesor
        Nodo<T> mayor = buscarMayor(aux.izq); //siempre sera del lado izquierdo del arbol
        aux.dato = mayor.dato; // colocar el dato del mayor en el nodo que se eliminara [intercambio]
        
        if(esHoja(mayor)) // de acuerdo al tipo de nodo que sea el elemento mayor del lado izq solo se eliminara
            eliminaHoja(mayor);
        else
            eliminaUnHijo(mayor);
    }
    
    public boolean eliminar(T dato) {
        Nodo<T> aux = buscarNodo(dato); //primero obtener el nodo del dato a eliminar
  
        if(aux!=null){ // si el nodo existe
            if(esHoja(aux)) //preguntar si es hoja
                eliminaHoja(aux); //eliminar hoja
            else if(tieneUnHijo(aux)) //sino preguntar si tiene un padre e hijo
                eliminaUnHijo(aux); //conectar el padre del nodo a eliminar con el hijo del nodo a eliminar
            else 
                eliminaDosHijos(aux); //sino tendra 2 hijos y se eliminara ese nodo
            treeset.remove(dato); // Devuelve true si se eliminó correctamente.
            tam--;      
            return true;
        }
        
        return false;
    }
    
    private String preorden(Nodo<T> n) {
        if(n!=null){
           return n.dato + " ," + preorden(n.izq) + preorden(n.der);
        }
        return "";
    }

    public String preorden(){
        return preorden(raiz);
    }
    
    private String inorden(Nodo n) {
        if(n!=null){
            return inorden(n.izq) + n.dato + " ," + inorden(n.der);
        }
        return "";
    }

    public String inorden(){
        return inorden(raiz);
    } 
    
    private String postorden(Nodo n) {
        if(n!=null){
            return postorden(n.izq) + postorden(n.der) + n.dato + " ,";
        }
        return "";
    }

    public String postorden(){
        return postorden(raiz);
    }     
    
    public Set<T> obtenerRango(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin)) {
            return null;
        }

        // Crear objetos marcadores para el rango
        Pedido inicio = new Pedido(0, null, null, fechaInicio, null);
        Pedido fin = new Pedido(0, null, null, fechaFin, null);

        // Obtener el subconjunto del TreeSet
        return treeset.subSet((T) inicio, true, (T) fin, true);
    }


    
    public String mostrarElementosEnRango(LocalDate fechaInicio, LocalDate fechaFin) {
        Set<T> elementosEnRango = obtenerRango(fechaInicio, fechaFin);

        if (elementosEnRango == null || elementosEnRango.isEmpty()) {
            return "No hay elementos en el rango de fechas especificado.";
        }
        String cad = "";
        for (T elemento : elementosEnRango) {
            cad+=elemento.toString();
        }
        return cad;
    }
    
    private class Nodo<T> {
        T dato;
        Nodo<T> izq;
        Nodo<T> der;
        Nodo<T> padre; 

        public Nodo(T dato) {
            this.dato = dato;
            izq = null;
            der = null;
            padre = null;
        }
        public Nodo(T dato, Nodo<T> padre) {
            this.padre = padre;
            this.dato = dato;
            izq = null;
            der = null;
        }
    }
}