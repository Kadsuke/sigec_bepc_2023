/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import bf.menapln.entity.Menu;

/**
 *
 * @author coulibaly
 */
public class BarreDeMenu extends JMenuBar{
    private final JMenu parametre;
    private final JMenu inscription;
    private final JMenu organisation;
    private final JMenu administration;
    private final JMenu etat;
    
    private final JMenuItem type;
    private final JMenuItem session;
    private final JMenuItem localite;
    private final JMenuItem etablissement;
    private final JMenuItem serie;
    private final JMenuItem epreuve;
    private final JMenuItem epreuveSerie;
    private final JMenuItem pays;
    
    private final JMenuItem candidat;
    private final JMenuItem candidatPosteJury;
    
    private final JMenuItem centreComposition;
    private final JMenuItem jury;
    private final JMenuItem centreSecondaire;
    private final JMenuItem membresDeJury;
    private final JMenuItem candidatOrg;
    private final JMenuItem creationSalle;
    private final JMenuItem repartitionCandidatSalle;
    
    private final JMenuItem liste;
    private final JMenuItem composition;
    private final JMenuItem saisie;
    private final JMenuItem deliberation;
    private final JMenuItem reclamation;
    private final JMenuItem attestation;
    private final JMenuItem releve;
    private final JMenuItem recapResultat;

    private final JMenuItem menu;
    private final JMenuItem profil;
    private final JMenuItem user;
    //private final JMenuItem rachat;
    
    
    private final Fenetre fenetre;
    
    public LocaliteListener localiteListener = new LocaliteListener();
    public TypeListener typeListener = new TypeListener();
    public SessionListener sessionListener = new SessionListener();
    public EtablissementListener etablissementListener = new EtablissementListener();
    public CompositionListner compositionListener = new CompositionListner();
    public SaisieListener saisieListener = new SaisieListener();
    public DeliberationListener deliberationListener = new DeliberationListener();
    public SerieListener serieListener = new SerieListener();
    public EpreuveListener epreuveListener = new EpreuveListener();
    public EpreuveSerieListene epreuveSerieListene = new EpreuveSerieListene();
    public CandidatListener candidatListener = new CandidatListener();
    public PaysListener paysListener = new PaysListener();
    public CandidatPosteJuryListener candidatPosteJuryListener = new CandidatPosteJuryListener();
    public centreSecondaireListener centreSecondaireListener = new centreSecondaireListener();
    public JuryListener juryListener = new JuryListener();
    private ActeurListener acteurListener = new ActeurListener();
    private CandidatOrgListener candidatOrgListener = new CandidatOrgListener();
    private SalleListener salleListener = new SalleListener();
    private RepartitionSalleListener repartitionSalleListener = new RepartitionSalleListener();
    private MenuListener menuListener = new MenuListener();
    private ProfilListener profilListener = new ProfilListener();
    private UserListener userListener = new UserListener();
    //private RachatListener rachatListener = new RepartitionSalleListener();

    private JLabel tour;
    
    public BarreDeMenu(Fenetre fenetr){
        this.fenetre = fenetr;
        
        this.session = new JMenuItem("Session");
        this.session.addActionListener(this.sessionListener);
        
        this.localite = new JMenuItem("Localité");
        this.localite.addActionListener(this.localiteListener);
        
        this.type = new JMenuItem("Type");
        this.type.addActionListener(this.typeListener);
    
        this.etablissement = new JMenuItem("Etablissement");
        this.etablissement.addActionListener(this.etablissementListener);
        
        this.serie = new JMenuItem("Option");
        this.serie.addActionListener(this.serieListener);
        
        this.epreuve = new JMenuItem("Epreuve");
        this.epreuve.addActionListener(this.epreuveListener);
        
        this.epreuveSerie = new JMenuItem("Epreuve option");
        this.epreuveSerie.addActionListener(this.epreuveSerieListene);
        
        this.pays = new JMenuItem("Pays");
        this.pays.addActionListener(this.paysListener);

        this.menu = new JMenuItem("Menu");
        this.menu.addActionListener(this.menuListener);
        
        this.profil = new JMenuItem("Profil");
        this.profil.addActionListener(this.profilListener);
        
        this.user = new JMenuItem("Utilisateur");
        this.user.addActionListener(this.userListener);
        
        //this.postePjury = new JMenuItem("Poste jury");
        
        this.parametre = new JMenu("Paramètre");
        //this.parametre.setIcon(new ImageIcon("icones/parametre.png"));
        this.parametre.add(this.type);
        this.parametre.addSeparator();
        this.parametre.add(this.localite);
        this.parametre.addSeparator();
        this.parametre.add(session);
        this.parametre.addSeparator();
        this.parametre.add(etablissement);
        this.parametre.addSeparator();
        this.parametre.add(this.serie);
        this.parametre.addSeparator();
        this.parametre.add(this.epreuve);
        this.parametre.addSeparator();
        this.parametre.add(this.epreuveSerie);
        this.parametre.addSeparator();
        this.parametre.add(this.pays);
        this.parametre.addSeparator();
        this.parametre.add(this.menu);
        this.parametre.addSeparator();
        this.parametre.add(this.profil);
        this.parametre.addSeparator();
        this.parametre.add(this.user);
        
        this.candidat = new JMenuItem("Candidat à l'examen");
        this.candidat.addActionListener(this.candidatListener);
        
        
        this.inscription = new JMenu("Inscription");
        //this.inscription.setEnabled(false);
        this.inscription.add(this.candidat);
        
        this.centreComposition = new JMenuItem("Centre Composition");
        this.centreComposition.addActionListener(new CentreListener());
        this.jury = new JMenuItem("Jury");
        this.jury.addActionListener(this.juryListener);
        this.centreSecondaire = new JMenuItem("Centre Secondaire");
        /* this.centreSecondaire.addActionListener(this.centreSecondaireListener);
        this.candidatPosteJury = new JMenuItem("Membre du jury");
        this.candidatPosteJury.addActionListener(this.candidatPosteJuryListener);
        this.membresDeJury = new JMenuItem("Validation Membre Jury");
        this.membresDeJury.addActionListener(this.acteurListener); */
        this.candidatOrg = new JMenuItem("Candidats");
        this.candidatOrg.addActionListener(this.candidatOrgListener);
        this.creationSalle = new JMenuItem("Salle de Composition");
        this.creationSalle.addActionListener(this.salleListener);
        this.repartitionCandidatSalle = new JMenuItem("Répartition Candidat Salle");
        this.repartitionCandidatSalle.addActionListener(this.repartitionSalleListener);
        
        this.organisation = new JMenu("Organisation");
        this.organisation.add(this.centreComposition);
        this.organisation.addSeparator();
        this.organisation.add(this.jury);
        this.organisation.addSeparator();
        this.organisation.add(this.centreSecondaire);
        this.organisation.addSeparator();
   /*      this.organisation.add(candidatPosteJury);
        this.organisation.addSeparator();
        this.organisation.add(this.membresDeJury);
        this.organisation.addSeparator(); */
        this.organisation.add(this.candidatOrg);
        /* this.organisation.addSeparator();
        this.organisation.add(this.creationSalle);
        this.organisation.addSeparator();
        this.organisation.add(this.repartitionCandidatSalle);
 */
        this.releve = new JMenuItem("Réleve de Note");
        this.releve.addActionListener(this.salleListener);
        
        this.attestation = new JMenuItem("Attestation de Reussite");
        this.attestation.addActionListener(this.salleListener);

        this.recapResultat = new JMenuItem("Récap. Concours");
        this.recapResultat.addActionListener(this.salleListener);

        this.etat = new JMenu("Génération Etat");
        this.etat.add(this.recapResultat);
        this.etat.addSeparator();
        this.etat.add(this.releve);
        if(this.fenetre.getUserSession().getJury().getEtape().getTourCode()==2){
            this.etat.addSeparator();
            this.etat.add(this.attestation);
        }
        

        tour = new JLabel(this.fenetre.getUserSession().getJury().getEtape().getLibelle());
        tour.setForeground(Color.red);
        
        this.liste = new JMenuItem("Liste générale");
        this.liste.addActionListener(new ListeListener());
        
        this.composition = new JMenuItem("Composition");
        this.composition.addActionListener(this.compositionListener);
        
        this.saisie = new JMenuItem("Gestion des copies");
        this.saisie.addActionListener(this.saisieListener);
        //this.saisie.setEnabled(false);
        
        this.deliberation = new JMenuItem("Délibération");
        //this.deliberation.setEnabled(false);
        this.deliberation.addActionListener(this.deliberationListener);
        
        this.reclamation = new JMenuItem("Réclamation");
        //this.reclamation.setEnabled(false);
        this.reclamation.addActionListener(new ReclamationListener());

        this.centreSecondaire.addActionListener(this.centreSecondaireListener);
        this.candidatPosteJury = new JMenuItem("Membre du jury");
        this.candidatPosteJury.addActionListener(this.candidatPosteJuryListener);
        this.membresDeJury = new JMenuItem("Validation Membre Jury");
        this.membresDeJury.addActionListener(this.acteurListener);
        
        this.administration = new JMenu("Administration");
        this.administration.addSeparator();
        this.administration.add(tour);
        this.administration.addSeparator();
        this.administration.add(candidatPosteJury);
        this.administration.addSeparator();
        this.administration.add(this.membresDeJury);
        this.administration.addSeparator();
        this.administration.add(this.liste);
        this.administration.addSeparator();
        this.administration.add(this.composition);
        this.administration.addSeparator();
        this.administration.add(this.saisie);
        this.administration.addSeparator();
        this.administration.add(this.deliberation);
    }
       /*  this.administration.addSeparator();
        this.administration.add(this.reclamation);
    } */
    
    public void initComponent(){
        List menus = this.fenetre.getUserSession().getUser().getProfil().getMenus();
        for(Object menu : menus){
            switch(((Menu)menu).getMenuLibelle()){
                case "Paramètre":
                    this.add(this.parametre);
                break;
                case "Inscription":
                    this.add(this.inscription);
                break;
                case "Organisation":
                    this.add(this.organisation);
                break;
                case "Administration":
                    this.add(this.administration);
                break;
            }
        }
    }

    public ActeurListener getActeurListener() {
        return acteurListener;
    }

    public void setActeurListener(ActeurListener acteurListener) {
        this.acteurListener = acteurListener;
    }

    public SalleListener getSalleListener() {
        return salleListener;
    }

    public void setSalleListener(SalleListener salleListener) {
        this.salleListener = salleListener;
    }

    public CandidatOrgListener getCandidatOrgListener() {
        return candidatOrgListener;
    }

    public void setCandidatOrgListener(CandidatOrgListener candidatOrgListener) {
        this.candidatOrgListener = candidatOrgListener;
    }

    public JLabel getTour() {
        return tour;
    }

    public void setTour(JLabel tour) {
        this.tour = tour;
    }
    
    
    
    class TypeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           TypePanel p = new TypePanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    class LocaliteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           LocalitePanel p;
            try {
                p = new LocalitePanel(fenetre);
                fenetre.setContainer(p);
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class SessionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           SessionPanel p = new SessionPanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();            
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    class EtablissementListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           EtablissementPanel p = new EtablissementPanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    
    class SerieListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            SeriePanel p = new SeriePanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    } 
    
    class EpreuveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            EpreuvePanel p = new EpreuvePanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    
    class EpreuveSerieListene implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            EpreuveSeriePanel p = new EpreuveSeriePanel(fenetre);
            
            try {
                //p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class PaysListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                PaysPanel p = new PaysPanel(fenetre);
                //p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class CandidatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //System.out.println("yes");
            try {
                CandidatForm p = new CandidatForm(fenetre);
                //p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    class CandidatPosteJuryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            CandidatPosteJuryPanel p = new CandidatPosteJuryPanel(fenetre);
           fenetre.setContainer(p);
            try {
                //fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    
    class CentreListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            CentrePanel p = new CentrePanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    
    class JuryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JuryPanel p = new JuryPanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }

    class centreSecondaireListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            CentreSecondairePanel p = new CentreSecondairePanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    

    class ActeurListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ActeurPanel p = new ActeurPanel(fenetre);
           fenetre.setContainer(p);
            try {
                //fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    class SalleListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            SalleCompositionPanel p = new SalleCompositionPanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }

    class RepartitionSalleListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            RepartitionSallePanel p = new RepartitionSallePanel(fenetre);
           fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
    
    class CandidatOrgListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                CandidatOrganisationPanel p = new CandidatOrganisationPanel(fenetre);
                RepartitionCandidatPanel pb = new RepartitionCandidatPanel(p);
                fenetre.setContainer(p);
                //fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class ListeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           try {
                CandidatPanel p = new CandidatPanel(fenetre);
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class CompositionListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            CompositionPanel p = new CompositionPanel(fenetre);
            fenetre.setContainer(p);
            try {
                //fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    class SaisieListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           
            try {
                 SaisiePanel p = new SaisiePanel(fenetre);
                fenetre.setContainer(p);
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    class DeliberationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                 DeliberationPanel p = new DeliberationPanel(fenetre);
                fenetre.setContainer(p);
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    class ReclamationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ReclamationPanel p = new ReclamationPanel(fenetre);
            fenetre.setContainer(p);
            try {
                fenetre.getContainer().initComposant();
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    class MenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                MenuPanel p = new MenuPanel(fenetre);
                //p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class ProfilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                ProfilPanel p = new ProfilPanel(fenetre);
                //p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    class UserListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                UserPanel p = new UserPanel(fenetre);
                //p.initComposant();
                fenetre.setContainer(p);
                fenetre.initComponent();
            } catch (SQLException ex) {
                Logger.getLogger(BarreDeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
