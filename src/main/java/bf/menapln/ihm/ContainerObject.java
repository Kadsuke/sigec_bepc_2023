/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import java.awt.Insets;
import javax.swing.JPanel;

/**
 *
 * @author coulibaly
 */
public class ContainerObject extends JPanel{
    @Override
    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 5, normal.left + 5,
        normal.bottom + 5, normal.right + 5);
    }
}
