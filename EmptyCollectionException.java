/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Santiago Bolio
 */
public class EmptyCollectionException extends RuntimeException{
    public EmptyCollectionException() {
            super("La coleccion esta vacia.");
        }
}
