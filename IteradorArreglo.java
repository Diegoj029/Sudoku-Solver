import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author ale
 * @param <T>
 */
public class IteradorArreglo <T> implements Iterator <T> {
    private T [] coleccion;
    private int total;
    private int actual;
    
    public IteradorArreglo(int tot, T [] arreglo) {
        coleccion = arreglo;
        total = tot;
        actual = 0;
    }
    public boolean hasNext() {
        return actual<total;
    }
    public T next() { 
        if(hasNext()) {
            T res;
            res = coleccion[actual];
            actual++;
            return res;
        }
        else
            throw new NoSuchElementException();
    }
    public void remove() {
        throw new UnsupportedOperationException("Operación no soportada");
    }
    
}
