/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package bf.menapln.sigec_bac;

 import bf.menapln.ihm.ConnexionForm;
 import java.sql.SQLException;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 /**
  *
  * @author coulibaly
  */
 public class Sigec_bac {
 
     public static void main(String[] args) {
         //createNewDatabase("sigec_bac.db");
         
         try {
             ConnexionForm f = new ConnexionForm();
             f.initComposant();
         } catch (SQLException ex) {
             Logger.getLogger(Sigec_bac.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
 }
 