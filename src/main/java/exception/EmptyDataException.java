/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author coulibaly
 */
public class EmptyDataException extends Exception{
    public EmptyDataException(String text){
        super("Empty data exception : "+text);
    }
}
