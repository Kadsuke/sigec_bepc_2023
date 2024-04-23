/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.ihm;


import bf.menapln.entity.Candidat;
import bf.menapln.entity.CandidatSalle;
import bf.menapln.entity.Objet;
import bf.menapln.entity.SalleComposition;
import bf.menapln.ihm.composants.Bouton;
import bf.menapln.ihm.tableModel.SalleCandidatTableModel;
import bf.menapln.ihm.tableModel.SalleCompositionCandidatTableModel;
import bf.menapln.service.CandidatJuryService;
import bf.menapln.service.CandidatSalleService;
import bf.menapln.service.SalleCompositionService;
import exception.EmptyDataException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;




/**
 *
 * @author coulibaly
 */
public class RepartitionSallePanel extends Panneau{

    private JLabel salleLabel;
    private JComboBox salleField;
    private ContainerObject sallePanel;
    
    private JLabel capaciteSalleLabel;
    private JTextField capaciteSalleField;
    private ContainerObject capaciteSallePanel;

    private JLabel totalSalleLabel;
    private JTextField totalSalleField;
    private ContainerObject totalSallePanel;
    
    private ContainerObject westPanel;
    private final ContainerObject formPanel;
    private final ContainerObject listePanel;
    private final JSplitPane bodyPanel;

    ArrayList<SalleComposition> salleAlls = new ArrayList<SalleComposition>();
    ArrayList<CandidatSalle> candidatSalles = new ArrayList<CandidatSalle>();
    
    public RepartitionSallePanel(Fenetre fenetre) {
        super(fenetre);
        
        this.listePanel = new ContainerObject();
        this.listePanel.setLayout(new BorderLayout());

        this.salleLabel = new JLabel("Salle");
        this.salleField = new JComboBox();
        //this.salleField.addActionListener(new ChangeSalleListener());
        this.sallePanel = new ContainerObject();
        
        this.sallePanel.setLayout(new BoxLayout(this.sallePanel,BoxLayout.Y_AXIS));
        this.salleField.setPreferredSize(new Dimension(200,30));
        this.salleField.setMinimumSize(new Dimension(200,30));
        this.salleField.setMaximumSize(new Dimension(400,30));
        this.sallePanel.add(this.salleLabel);
        this.sallePanel.add(this.salleField);

        this.capaciteSalleLabel = new JLabel("Nombre Total Candidat");
        this.capaciteSalleField = new JTextField();
        this.capaciteSalleField.setEditable(false);
        this.capaciteSalleField.setPreferredSize(new Dimension(200,30));
        this.capaciteSalleField.setMinimumSize(new Dimension(200,30));
        this.capaciteSalleField.setMaximumSize(new Dimension(400,30));
        
        this.capaciteSallePanel = new ContainerObject();
        this.capaciteSallePanel.setLayout(new BoxLayout(this.capaciteSallePanel,BoxLayout.Y_AXIS));
        this.capaciteSallePanel.add(this.capaciteSalleLabel);
        this.capaciteSallePanel.add(this.capaciteSalleField);

        this.totalSalleLabel = new JLabel("Nombre Total de Salle");
        this.totalSalleField = new JTextField();
        this.totalSalleField.setEditable(false);
        this.totalSalleField.setPreferredSize(new Dimension(200,30));
        this.totalSalleField.setMinimumSize(new Dimension(200,30));
        this.totalSalleField.setMaximumSize(new Dimension(400,30));
        
        this.totalSallePanel = new ContainerObject();
        this.totalSallePanel.setLayout(new BoxLayout(this.totalSallePanel,BoxLayout.Y_AXIS));
        this.totalSallePanel.add(this.totalSalleLabel);
        this.totalSallePanel.add(this.totalSalleField);
        
        this.save = new Bouton("Répartir");
        this.save.addActionListener(new RepartitionCandidatSalleSaveListener());
        this.formControlPanel = new ContainerObject();
        this.formControlPanel.add(this.save);

        this.formPanel = new ContainerObject();
        this.formPanel.setLayout(new BoxLayout(this.formPanel,BoxLayout.Y_AXIS));
        this.formPanel.setBorder(BorderFactory.createTitledBorder("Nombre Candidat Par Salle"));
        ((TitledBorder)this.formPanel.getBorder()).setTitleColor(Color.blue);
        //this.formPanel.add(this.capaciteSallePanel);
        this.formPanel.add(this.totalSallePanel);
        this.formPanel.add(this.formControlPanel);

        this.westPanel = new ContainerObject();
        this.westPanel.setLayout(new BoxLayout(this.westPanel,BoxLayout.X_AXIS));
        this.westPanel.add(this.sallePanel);
        
        this.head.getSearchPanel().add(this.westPanel,BorderLayout.WEST);
       

        this.bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.listePanel,this.formPanel);
        this.bodyPanel.setDividerLocation((this.fenetre.getWidth()/4)*3);
        this.body.setLayout(new BorderLayout());
        this.body.add(this.bodyPanel);
    }
    
    public void initComposant() throws SQLException,NullPointerException{
        super.initComposant();


        this.head.initComposant();
        

        this.service = new CandidatSalleService();
        this.head.initComposant();
        this.tableModel = new SalleCandidatTableModel(new ArrayList<Objet>(),this.fenetre);
        JTable tableau = new JTable(this.tableModel);
        tableau.setRowHeight(25);
        tableau.getColumnModel().getColumn(0).setMaxWidth(60);
        this.listePanel.add(new JScrollPane(tableau));


        SalleCompositionService salleCompositionService = new SalleCompositionService();
        salleAlls = (ArrayList<SalleComposition>) salleCompositionService.getAllByTour(fenetre.getUserSession().getSession().getId(),fenetre.getUserSession().getTourComposition().getId());
       // tableModel.setModelData(salleAlls);
        this.totalSalleField.setText(""+salleAlls.size());
        this.capaciteSalleField.setText(""+tableau.getModel().getRowCount());

        List salleList = salleCompositionService.getAllByTour(fenetre.getUserSession().getSession().getId(),fenetre.getUserSession().getTourComposition().getId());
        for(Object salle : salleList){
            this.salleField.addItem((SalleComposition)salle);
        }
    }

    class ChangeSalleListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            try {
            CandidatSalleService service = new CandidatSalleService();
            SalleComposition salleComposition = (SalleComposition) salleField.getSelectedItem();
            List types = null;
                types = service.getBySalleId(salleComposition);
                    tableModel.setModelData(types);
                    tableModel.fireTableDataChanged();
        } catch (SQLException | NullPointerException e1) {
            Logger.getLogger(RepartitionCandidatPanel.class.getName()).log(Level.SEVERE, null, e1);
        }
        }

    }


    class RepartitionCandidatSalleSaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
            SalleCompositionService serviceSalle = new SalleCompositionService();
            CandidatJuryService candidatJuryService = new CandidatJuryService();
            CandidatSalleService serviceCandidatSalle = new CandidatSalleService();
            List<CandidatSalle> candidatSalles = new ArrayList<>();
            List<Candidat> candidats = candidatJuryService.getCandidatJuryT(fenetre.getUserSession().getJury());
    
            List<SalleComposition> salles = serviceSalle.getAllByTour(fenetre.getUserSession().getSession().getId(), fenetre.getUserSession().getTourComposition().getId());    
            
            System.out.println(candidats.get(1).getNom());
            if (!candidats.isEmpty() && !salles.isEmpty()) {
                salles.forEach(salle -> {
                    List<Candidat> candidatsTMP;
                    if (candidats.size() >= salle.getCapacite()) {
                        candidatsTMP = candidats.subList(0, salle.getCapacite());
                    } else {
                        candidatsTMP = candidats;
                    }
                    if (!candidatsTMP.isEmpty()) {
                        candidatsTMP.forEach(cand -> {
                            CandidatSalle candidatSalle = new CandidatSalle();
                            candidatSalle.setCandidat(cand);
                            candidatSalle.setSalleComposition(salle);
                            candidatSalles.add(candidatSalle);
                            });
                    }
                    candidats.removeAll(candidatsTMP);
                });
            }
            serviceCandidatSalle.setFormData(new HashMap<String,String>());
                     for(int i=0; i < candidatSalles.size(); i++){
                        serviceCandidatSalle.getFormData().put("salleComposition", candidatSalles.get(i).getSalleComposition());
                        serviceCandidatSalle.getFormData().put("candidat", candidatSalles.get(i).getCandidat());
                        serviceCandidatSalle.save();
                    } 
                }
            catch (SQLException  | NullPointerException | EmptyDataException ex) {
                Logger.getLogger(TypePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }








































            /*try { 
                SalleCompositionService serviceSalle = new SalleCompositionService();

                CandidatSalleService serviceCandidatSalle = new CandidatSalleService();


                CandidatJuryService candidatJuryService = new CandidatJuryService();

                List<Candidat> toutCandidat = candidatJuryService.getCandidatJuryT(fenetre.getUserSession().getJury())
                .stream()
                .sorted(Comparator.comparing((o1) -> o1.getNumeroPv()))
                .collect(Collectors.toList());
                List<SalleComposition> toutSalleComposition = serviceSalle.getAllByTour(fenetre.getUserSession().getSession().getId(), fenetre.getUserSession().getTourComposition().getId());

               if(!toutCandidat.isEmpty() && !toutSalleComposition.isEmpty()){
                System.out.println("premier if");
                for (SalleComposition salleComposition : toutSalleComposition) {
                    List<Candidat> candidatsTmp;
                    if(toutCandidat.size() >= salleComposition.getCapacite()){
                        System.out.println("deuxieme if");
                        candidatsTmp = toutCandidat.subList(0, salleComposition.getCapacite());
                    }else{
                        System.out.println("premier else");
                        candidatsTmp = toutCandidat;
                    }
                    if(!candidatsTmp.isEmpty()){
                        System.out.println("troisieme if");
                        for (Candidat candidat : candidatsTmp) {
                            CandidatSalle candidatSalle = new CandidatSalle();
                            candidatSalle.setCandidat(candidat);
                            candidatSalle.setSalleComposition(salleComposition);
                            candidatSalles.add(candidatSalle);
                        }
                    }
                }
               }
               serviceCandidatSalle.setFormData(new HashMap<String,String>());
                     for(int i=0; i < candidatSalles.size(); i++){
                        System.out.print(candidatSalles.get(i).getCandidat().getNumeroPv());
                        serviceCandidatSalle.getFormData().put("salleComposition", candidatSalles.get(i).getSalleComposition());
                        serviceCandidatSalle.getFormData().put("candidat", candidatSalles.get(i).getCandidat());
                        serviceCandidatSalle.save();
                    } 
                } */
                   