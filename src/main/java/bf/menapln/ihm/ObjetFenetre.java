/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JFrame;

/**
 *
 * @author coulibaly
 */
public abstract class ObjetFenetre extends JFrame{
    public String charArrToSTring(char[] tab){
        String str = "";
        for(char caracter : tab){
            str += caracter;
        }
        return str;
    }
    
    public String encrypte(String text){
        String encruptedText = null;
        try   
        {  
            MessageDigest m = MessageDigest.getInstance("SHA-1");  
            m.update(text.getBytes());  
              
            byte[] bytes = m.digest();  
              
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            encruptedText = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }  
        return encruptedText;
    }
}
