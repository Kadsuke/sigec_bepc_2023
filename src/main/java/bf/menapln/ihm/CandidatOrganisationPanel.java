/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import bf.menapln.ihm.composants.Bouton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coulibaly
 */
public class CandidatOrganisationPanel extends Panneau{
    private Bouton repartitionJury;
    private Bouton generationPv;
    
    private GenerationPvListener generationPvListener = new GenerationPvListener();
    public CandidatOrganisationPanel(Fenetre fenetre) {
        super(fenetre);
        this.repartitionJury = new Bouton("Repartition des candidats par Jury et/ou Centre Secondaire");
        this.repartitionJury.addActionListener(this.fenetre.getBarreDeMenu().getCandidatOrgListener());
        this.generationPv = new Bouton("Génération de PV");
        this.generationPv.addActionListener(this.generationPvListener);
       
    }
    
    @Override
    public void initComposant() throws SQLException{
        super.initComposant();
        this.head.ongletPanel = new ContainerObject();
        this.head.ongletPanel.add(this.repartitionJury);
        this.head.ongletPanel.add(this.generationPv);
        this.head.getSearchPanel().add(this.head.ongletPanel,BorderLayout.WEST);
        this.head.initComposant();
    }
    
    class GenerationPvListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                CandidatOrganisationPanel p = new CandidatOrganisationPanel(fenetre);
                //fenetre.getContainer()
                GenerationPvPanel pb = new GenerationPvPanel(p);
                fenetre.setContainer(p);
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(CandidatOrganisationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } 
                
        }
}
}
