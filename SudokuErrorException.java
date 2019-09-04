
import javax.swing.JOptionPane;

/**
 *
 * @author Diego P.
 */
public class SudokuErrorException extends RuntimeException{
    public SudokuErrorException() {
        super("Los datos no son válidos");
        JOptionPane.showMessageDialog(null, "Los datos no son válidos", "ERROR", JOptionPane.WARNING_MESSAGE);
    }
    public SudokuErrorException(int i,int j) {
        super("El dato en la casilla ["+i+","+j+"] no es válido");
        JOptionPane.showMessageDialog(null,"El dato en la casilla ["+i+","+j+"] no es válido", "ERROR", JOptionPane.WARNING_MESSAGE);
    }
    public SudokuErrorException(int i,int j,String message) {
        super("El dato en ["+i+","+j+"] "+message);
        JOptionPane.showMessageDialog(null,"El dato en la casilla ["+i+","+j+"] "+message, "Revisar", JOptionPane.INFORMATION_MESSAGE);
    }
}
