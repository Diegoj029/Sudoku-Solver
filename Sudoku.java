/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class Sudoku {
    private static int[][] sudo;
    private static ConjuntoADT < Integer > renglon[] = new ConjuntoADT[9];
    private static ConjuntoADT < Integer > columna[] = new ConjuntoADT[9];
    private static ConjuntoADT < Integer > submatriz[] = new ConjuntoADT[9];
    
     
    public static int [][] resolverSudoku(int[][]matriz) throws Exception{
        sudo=new int[9][9];
        for(int i = 0; i < 9; i++) {
            renglon[i] = new ConjuntoA<>();
            columna[i] = new ConjuntoA<>();
            submatriz[i] = new ConjuntoA<>();
        }
        
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++) {
                int x = matriz[r][c];
                if(x != 0) {
                    if(!espDisponible(r, c, x)) 
                        throw new SudokuErrorException (r,c,"se encuentra repetido o no puede estar ahí");
                    renglon[r].agrega(x); 
                    columna[c].agrega(x);
                    submatriz[getSubMatrix(r, c)].agrega(x);
                }
                sudo[r][c]=x;
            }  
        }
        
        if(resolver(0,0))
            return sudo;
        else
            throw new Exception("Sudoku sin solución");
    }
    
    private static boolean resolver(int r,int c){
        if (r>=9)
           return true;
       else if(sudo[r][c]!=0){
           if(c+1<9)
               return(resolver(r,c+1));
           else
               return(resolver(r+1,0));
       }else{
            ConjuntoADT<Integer> ev=evaluar(r,c);
            if(!evaluar(r,c).estaVacio()){
                IteradorArreglo it=(IteradorArreglo) ev.iterator();
                while(it.hasNext()){
                    int i=(int) it.next();
                        sudo[r][c]=i;
                        boolean res;
                        if(c+1<9)
                            res=(resolver(r,c+1));
                        else
                            res=(resolver(r+1,0));
                        if(res)
                            return true;
                        sudo[r][c] = 0;
                }
            }
            return false;
       }
    }
    
    private static boolean espDisponible(int r,int c, int x){
        return !(renglon[r].contiene(x) || columna[c].contiene(x) || submatriz[getSubMatrix(r, c)].contiene(x));
    }
    private static int getSubMatrix(int r, int c) {
        return r/3 * 3 + c/3;
    }
    
    public static ConjuntoADT<Integer> evaluar(int i, int j){
        ConjuntoA<Integer> cuadro = new ConjuntoA<>();
        ConjuntoA<Integer> col = new ConjuntoA<>();
        ConjuntoA<Integer> ren = new ConjuntoA<>();
        ConjuntoA<Integer> dig = new ConjuntoA<>();
        ConjuntoA<Integer> aux;
        
        for(int z=1;z<=9;z++){
            dig.agrega(z);
        }
        
        for(int k=0;k<sudo.length;k++){
            ren.agrega(sudo[i][k]);
        }
        
        for(int m=0;m<sudo[j].length;m++){
            col.agrega(sudo[m][j]);
        }
        
        miniMatrizC(i, j, cuadro);
        aux = (ConjuntoA)cuadro.union(col);
        aux = (ConjuntoA)aux.union(ren);
        
        return dig.diferencia(aux);
    }
    
    public static void miniMatrizC(int i, int j, ConjuntoADT<Integer> conj){
        int row,col,z,y;
        row = i - (i%3);
        col = j - (j%3);
        z = row + 3;
        y = col + 3;
        
        for(int m=row;m<z;m++){
            for(int n=col;n<y;n++){
                conj.agrega(sudo[m][n]);
            }
        }
    }
}