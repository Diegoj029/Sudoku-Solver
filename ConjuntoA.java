import java.util.Iterator;

/**
 *
 * @author ale
 * @param <T>
 */
public class ConjuntoA <T> implements ConjuntoADT <T> {
    private T [] conjunto;
    private int cardinalidad; //es el total y por eso es cero y no -1
    private final int MAX = 20;
    
    public ConjuntoA() {
        conjunto = (T[]) new Object[MAX];
        cardinalidad = 0;
    }
    public ConjuntoA(int max) {
        conjunto = (T[]) new Object[max];
        cardinalidad = 0;
    }
    
    public int getCardinalidad() {
        return cardinalidad;
    }
    public boolean estaVacio() {
        return cardinalidad == 0;
    }
    public Iterator <T> iterator() {
        return new IteradorArreglo(cardinalidad, conjunto);
    }
    public boolean contiene(T elem) {
        boolean resp;
        resp = false;
        Iterator <T> it = iterator();
        while(it.hasNext() &&  !resp) {
            resp = elem.equals(it.next());
        }
        return resp;
    }
    public boolean contieneRec(T elem) {
        return contieneRec(elem, iterator());
    }
    private boolean contieneRec(T elem, Iterator <T> it) {
        if(it.hasNext()) 
            return false;
        else
            if(it.next().equals(elem))
                return true;
        else
                return contieneRec(elem, it);
    }
    public boolean agrega(T elem) {
        boolean resp = false;
        if(!contiene(elem)) {
            resp = true;
            if(cardinalidad==conjunto.length)
                expande();
            conjunto[cardinalidad] = elem;
            cardinalidad++;
        }
        return resp;
    }
    public void expande() {
        T[] nuevo = (T[]) new Object[conjunto.length*2];
        for(int i = 0; i<=cardinalidad; i++) //para ir pasando los datos de la pila pasada a esta
            nuevo[i] = conjunto[i];
        conjunto = nuevo;
    }
    public T quita(T elem) {
        T res;
        int pos;
        pos = buscaPos(elem);
        if(pos>=0) {
            res = conjunto[pos];
            conjunto[pos] = conjunto[cardinalidad];
            conjunto[cardinalidad-1] = null;
            cardinalidad--;
        }
        else
            res = null;
        return res;
    }
    private int buscaPos(T elem) {
        int i;
        i = 0;
        while(i<cardinalidad && !conjunto[i].equals(elem))
            i++;
        if(i==cardinalidad)
            i = -1;
        return i;
      
    }
    public ConjuntoADT <T> union(ConjuntoADT <T> otro) {
        ConjuntoADT <T> union = new ConjuntoA(cardinalidad+otro.getCardinalidad());
        Iterator <T> it = iterator();
        Iterator <T> i = otro.iterator();
        
        if(conjunto!=null && otro!=null) {
            while(it.hasNext())
                union.agrega(it.next());
            while(i.hasNext())
                union.agrega(i.next());
            
            
        }
        else
            union = null;
        return union;
            
    }
    public ConjuntoADT <T> interseccion(ConjuntoADT <T> otro) {
        ConjuntoA <T> inter = new ConjuntoA(cardinalidad+otro.getCardinalidad());
        Iterator <T> it = iterator();
        
        int i;
        i = 0;
        T aux;
        
        if(conjunto!=null && otro!=null) {
            while(it.hasNext()) {
                aux = it.next();
                if(contiene(aux)) {
                    inter.conjunto[i] = aux;
                    i++;
                }
            }
        }
        else
            inter = null;
        return inter;
    }
    public ConjuntoADT <T> diferencia(ConjuntoADT <T> otro) {
        ConjuntoA <T> dif = new ConjuntoA(cardinalidad);
        Iterator <T> it = iterator();
        
        T aux;
        int j = 0, cont = 0, n = otro.getCardinalidad();
        
        if(otro!=null) {
            
            while(it.hasNext() && cont<n) {
                aux = it.next();
                if(!otro.contiene(aux)) {
                    dif.conjunto[j] = aux;
                    j++;
                }
                else
                    cont++;
            }
            dif.cardinalidad = j;
        }
        else
            dif = null;
        return dif;
    }
        public String toString() {
            StringBuilder res = new StringBuilder();
            Iterator <T> it = iterator();
            while(it.hasNext()) {
                res.append(it.next()).append(" ");
            }
            return res.toString();
        }
    }
