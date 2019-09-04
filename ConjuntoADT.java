import java.util.Iterator;

/**
 *
 * @author ale
 * @param <T>
 */
public interface ConjuntoADT <T> extends Iterable <T> {
    public Iterator <T> iterator();
    public boolean agrega(T elem);
    public T quita(T elem);
    public boolean contiene(T elem);
    public int getCardinalidad();
    public boolean estaVacio();
    public ConjuntoADT <T> diferencia(ConjuntoADT <T> otro);
}
