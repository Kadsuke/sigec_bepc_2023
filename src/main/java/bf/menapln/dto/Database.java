/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.dto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author coulibaly
 */
public class Database {
    private Factory factory;
    private Connection connexion;
    
    public Database(Factory factory) throws SQLException{
        this.factory = factory;
        this.connexion = this.factory.connect();
    }
    public void initDatabase() throws SQLException{
        this.createTableSession();
        this.createTableOffreEnseignement();
        this.createTableStatutEtablissement();
        this.createTableSerie();
        this.createTableTypeCandidat();
        this.createTableTypeInscription();
        this.createTableTypeLocalite();
        this.createTableLocalite();
        this.createTableTypeStructure();
        this.createTableStructure();
        this.createTableEtablissement();
        this.createTableCentreComposition();
        this.createTableTypeHandicap();
        this.createTableNatureHandicap();
        this.createTablePrescription();
        this.createTablePrescriptionHandicap();
        this.createTablePays();
        this.createTableCandidat();
        this.createTableInscription();
        this.createTableDispense();
        this.createTableJury();
        this.createTableTourComposition();
        this.createTableEtatJury();
        this.createTableResultat();
        //this.createTableJuryCentre();
        this.createTableCandidatJury();
        //this.createTableUnite();
        this.createConcoursRatache();
        this.createTableMenu();
        this.createTableProfil();
        this.createTableProfilMenus();
        this.createTableUser();
        
        //this.insertTourComposition();
        this.createTableTypeEpreuve();
        this.createTableEpreuve();
        this.createTableEpreuveSerie();
        this.createTableComposition();
        this.createTableFeuilleComposition();
        this.createTableCandidatPosteJury();
        this.createTableEpreuveCandidatPosteJury();
        this.createTablePersonnel();
        this.createTablePosteMembreJury();
        this.createTableMembreJury();
        this.createTableDisciplineCorrecteur();
        this.createTableCentreSecondaire();
        this.createTableSalleComposition();
        this.createTableCandidatSalleComposition();

        this.createViewRegion();
        this.createViewProvince();
        this.createViewCommune();
        this.createViewVilleComposition();
        this.createViewSerieVilleComposition();
        this.createViewStructure();
        this.createViewEpreuveSerie();
        this.createViewCentre();
        this.createViewJury();
        this.createViewCandidat();
        this.createViewCandidatJury();
        this.createViewComposition();
        this.createViewFeuilleComposition();
        this.createViewCandidatPosteJury();
        this.createViewMembreJury();
        this.createViewResultat();
        this.createViewCentreSecondaire();
        this.createViewCandidatSalle();
        this.createViewEpreuveComposer();
        this.createViewUser();
    }
    
    public void createTableSession() throws SQLException{
        
        // SQL statement for creating a new table
        
         String sql = "CREATE TABLE IF NOT EXISTS session (\n"
                + "	session_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	annee text NOT NULL,\n"
                + "	dateDebut text NOT NULL,\n"
                + "	dateFin text NOT NULL,\n"
                + "	dateDebutInsc text NOT NULL,\n"
                + "	dateFinInsc text NOT NULL,\n"
                + "	dateDebutSessNorm text NOT NULL,\n"
                + "	dateFinSessNorm text NOT NULL,\n"
                + "	moyAdmission real NOT NULL,\n"
                + "	moyRachat real NOT NULL,\n"
                + "	moyAdmissible real NOT NULL,\n"
                + "	statutSession text NOT NULL DEFAULT false,\n"
                + "	statutInsc text NOT NULL DEFAULT false\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    //Offre d'enseignement
    
    public void createTableOffreEnseignement() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS offreEnseignement (\n"
                + "	offreEnseignement_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	offreEnseignementLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableStatutEtablissement() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS statutEtablissement (\n"
                + "	statut_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	statutLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableSerie() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS serie;";
        String sql = "CREATE TABLE IF NOT EXISTS serie (\n"
                + "	serie_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	offreEnseignement_id integer NOT NULL REFERENCES offreEnseignement(offreEnseignement_id),\n"
                + "	serieLibelle text NOT NULL,\n"
                + "	definition text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    public void createTableTypeCandidat() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS typeCandidat (\n"
                + "	typeCandidat_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	typeCandidatLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createConcoursRatache() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS concoursratache (\n"
                + "	concours_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	concoursLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    public void createTableTypeInscription() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS typeInscription (\n"
                + "	typeInscription_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	typeInscriptionLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableTypeLocalite() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS typeLocalite (\n"
                + "	typeLocalite_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	typeLocaliteLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableLocalite() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS localite;";
        String sql = "CREATE TABLE IF NOT EXISTS localite (\n"
                + "	localite_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	codeLocalite text,\n"
                + "	typeLocalite_id integer NOT NULL REFERENCES typeLocalite(typeLocalite_id),\n"
                + "	parentLocalite integer REFERENCES localite(localite_id),\n"
                + "	nomLocalite text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableTypeStructure() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS typeStructure (\n"
                + "	typeStructure_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	typeStructureLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableStructure() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS structure;";
        String sql = "CREATE TABLE IF NOT EXISTS structure (\n"
                + "	structure_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	codeStructure text,\n"
                + "	typeStructure_id integer NOT NULL REFERENCES typeStructure(typeStructure_id),\n"
                + "	parent_id integer REFERENCES structure(structure_id),\n"
                + "	localite_id integer NOT NULL REFERENCES localite(localite_id),\n"
                + "	centreExamen_id integer NOT NULL REFERENCES localite(localite_id),\n"
                + "	nomStructure text NOT NULL,\n"
                + "	acronymeStructure text\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableEtablissement() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS etablissement;";

        String sql = "CREATE TABLE IF NOT EXISTS etablissement (\n"
                + "	etablissement_id integer NOT NULL REFERENCES structure(structure_id),\n"
                + "	statut_id integer NOT NULL REFERENCES statutEtablissement(statut_id),\n"
                + "	offreEnseignement_id integer NOT NULL REFERENCES offreEnseignement(offreEnseignement_id),\n"
                + "	PRIMARY KEY(etablissement_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableCentreComposition() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS centreComposition;";
        String sql = "CREATE TABLE IF NOT EXISTS centreComposition (\n"
                + "	session_id integer NOT NULL REFERENCES session(session_id),\n"
                + "	etablissement_id integer NOT NULL REFERENCES etablissement(etablissement_id),\n"
                + "	capacite integer,\n"
                + "	primary key(session_id,etablissement_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableTypeHandicap() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS typeHandicap (\n"
                + "	typeHandicap_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	typeHandicapLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableNatureHandicap() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS natureHandicap (\n"
                + "	natureHandicap_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	typeHandicap_id integer NOT NULL REFERENCES typeHandicap(typeHandicap_id),\n"
                + "	natureHandicapLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTablePrescription() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS prescription (\n"
                + "	prescription_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	prescriptionLibelle text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTablePrescriptionHandicap() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS prescriptionHandicap (\n"
                + "	natureHandicap_id integer NOT NULL REFERENCES natureHandicap(natureHandicap_id),\n"
                + "	prescription_id integer NOT NULL REFERENCES prescription(prescription_id),\n"
                + "	PRIMARY KEY(natureHandicap_id,prescription_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTablePays() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS pays (\n"
                + "	pays_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	indicatif text NOT NULL,\n"
                + "	nomPays text NOT NULL,\n"
                + "	nationalite text NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
        
    public void createTableCandidat() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS inscription;";
        String sql = "CREATE TABLE IF NOT EXISTS candidat (\n"
                + "	candidat_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	iue text UNIQUE,\n"
                + "	paysNaissance_id integer NOT NULL REFERENCES pays(pays_id),\n"
                + "	paysNationalite_id integer NOT NULL REFERENCES pays(pays_id),\n"
                + "	nom text NOT NULL,\n"
                + "	prenom text NOT NULL,\n"
                + "	sexe text NOT NULL,\n"
                + "	telephone text,\n"
                + "	dateNaissance text NOT NULL,\n"
                + "	typeDateNaissance text NOT NULL DEFAULT 'le',\n"
                + "	numeroActeNaissance integer,\n"
                + "	lieuNaissance text NOT NULL,\n"
                + "	lienActeNaissance text,\n"
                + "	lienPhoto text\n"
                //+ "	primary key(session_id,etablissement_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableInscription() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS inscription;";

        String sql =
                "CREATE TABLE IF NOT EXISTS inscription (\n"
                + "	session_id integer NOT NULL REFERENCES session(session_id),\n"
                + "	candidat_id integer NOT NULL REFERENCES candidat(candidat_id),\n"
                + "	etablissement_id integer NOT NULL REFERENCES etablissement(structure_id),\n"
                + "	typeCandidat_id integer NOT NULL REFERENCES typeCandidat(typeCandidat_id),\n"
                + "	serie_id integer NOT NULL REFERENCES serie(serie_id),\n"
                + "	typeInscription_id integer NOT NULL REFERENCES typeInscription(typeInscription_id),\n"
                + "	natureHandicap_id integer REFERENCES natureHandicap(natureHandicap_id),\n"
                + "	prescription_id integer REFERENCES prescription(prescription_id),\n"
                + "	secteurVillage integer NOT NULL REFERENCES localite(localite_id),\n"
                + "	numeroPV integer,\n"
                + "	telephoneParent text,\n"
                + "	sport text NOT NULL DEFAULT 'Apte',\n"
                + "	dernierDiplome text NOT NULL,\n"
                + "	anneeDernierDiplome integer NOT NULL,\n"
                + "	statutCandidature text NOT NULL DEFAULT 'false',\n"
                + "	motifRejetCandidature text,\n"
                + "	composeSessionRemplacement text NOT NULL DEFAULT 'false',\n"
                + "	dateInscription text NOT NULL,\n"
                + "	concoursRatache integer NOT NULL REFERENCES concoursRatache(concours_id),\n"
                + "	centreExamen_id integer NOT NULL REFERENCES localite(localite_id),\n"
                + "	PRIMARY KEY(session_id,candidat_id),\n"
                + "	FOREIGN KEY(natureHandicap_id,prescription_id) REFERENCES prescriptionHandicap(natureHandicap_id,prescription_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableDispense() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS dispense;";

        String sql = "CREATE TABLE IF NOT EXISTS dispense (\n"
                + "	session_id integer NOT NULL REFERENCES session(session_id),\n"
                + "	candidat_id integer NOT NULL REFERENCES candidat(candidat_id),\n"
                + "	epreuve_id integer NOT NULL,\n"
                + "	PRIMARY KEY(session_id,candidat_id,epreuve_id),\n"
                + "	FOREIGN KEY(epreuve_id) REFERENCES epreuve(epreuve_id),\n"
                + "	FOREIGN KEY(session_id,candidat_id) REFERENCES inscription(session_id,candidat_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableJury() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS jury (\n"
                + "	jury_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	session_id integer NOT NULL,\n"
                + "	etablissement_id integer NOT NULL,\n"
                + "	centreExamen_id integer NOT NULL REFERENCES localite(localite_id),\n"
                + "	juryLibelle text NOT NULL,\n"
                + "	juryCapacite integer NOT NULL,\n"
                + "	juryEtat text NOT NULL DEFAULT 'inactif',\n"
                + "	EtatPremierTour text NOT NULL DEFAULT 'inactif',\n"
                + "	EtatSecondTour text NOT NULL DEFAULT 'inactif',\n"
                + "	UNIQUE(session_id,juryLibelle),\n"
                + "	FOREIGN KEY(session_id,etablissement_id) REFERENCES centreComposition(session_id,etablissement_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableCandidatJury() throws SQLException{
        String sql1 = "DROP TABLE IF EXISTS candidatJury;";
        String sql = "CREATE TABLE IF NOT EXISTS candidatJury (\n"
                + "	session_id integer NOT NULL,\n"
                + "	jury_id integer NOT NULL,\n"
                + "	centreSecondaire_id integer,\n"
                + "	candidat_id integer NOT NULL,\n"
                + "	PRIMARY KEY(session_id,jury_id,candidat_id),\n"
                + "	UNIQUE(session_id,candidat_id),\n"
                + "	FOREIGN KEY(jury_id) REFERENCES jury(jury_id),\n"
                + "	FOREIGN KEY(centreSecondaire_id) REFERENCES centreSecondaire(centreSecondaire_id),\n"
                + "	FOREIGN KEY(session_id,candidat_id) REFERENCES inscription(session_id,candidat_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    
    public void createTableTourComposition() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tourComposition (\n"
                + "	tourComposition_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	tourCode integer UNIQUE NOT NULL,\n"
                + "	tourCompositionLibelle text UNIQUE NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void insertTourComposition() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "INSERT INTO tourComposition(tourComposition_id,tourCompositionLibelle)\n"
                + "	values(1,1,'1er tour'),(2,2,'2e tour')\n"
                + ";";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableEtatJury() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS etatJury (\n"
                + "	session_id integer NOT NULL,\n"
                + "	jury_id integer NOT NULL,\n"
                + "	tourComposition_id integer NOT NULL,\n"
                + "	actif text NOT NULL,\n"
                + "	valide text NOT NULL,\n"
                + "	PRIMARY KEY(session_id,jury_id,tourComposition_id),\n"
                + "	CONSTRAINT fk_juryEtat FOREIGN KEY(jury_id) REFERENCES jury(jury_id),\n"
                + "	CONSTRAINT fk_tourEtat FOREIGN KEY(tourComposition_id) REFERENCES tourComposition(tourComposition_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableResultat() throws SQLException{
        String sql1 = "DROP TABLE IF EXISTS resultat;";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS resultat (\n"
                + "	session_id integer NOT NULL,\n"
                + "	candidat_id integer NOT NULL,\n"
                + "	tourComposition_id integer NOT NULL,\n"
                + "	totalPondere integer NOT NULL,\n"
                + "	totalPondereMax integer NOT NULL,\n"
                + "	moyenne real NOT NULL,\n"
                + "	codeDecisionJury text NOT NULL,\n"
                + "	decisionJury integer NOT NULL,\n"
                + "	mention text NOT NULL,\n"
                + "	PRIMARY KEY(session_id,candidat_id,tourComposition_id),\n"
                + "	CONSTRAINT fk_candidatResultat FOREIGN KEY(session_id,candidat_id) REFERENCES inscription(session_id,candidat_id),\n"
                + "	CONSTRAINT fk_tourResultat FOREIGN KEY(tourComposition_id) REFERENCES tourComposition(tourComposition_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableTypeEpreuve() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS typeEpreuve (\n"
                + "	typeEpreuve_id integer PRIMARY KEY,\n"
                + "	typeEpreuveLibelle text UNIQUE NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableEpreuve() throws SQLException{
        
        // SQL statement for creating a new tabl
        String sql1 = "DROP TABLE IF EXISTS epreuve;";
        String sql = "CREATE TABLE IF NOT EXISTS epreuve (\n"
                + "	epreuve_id integer PRIMARY KEY,\n"
                + "	epreuveLibelle text UNIQUE NOT NULL,\n"
                + "	epreuveAcronyme text\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableEpreuveSerie() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS epreuveSerie (\n"
                + "	epreuve_id integer NOT NULL REFERENCES epreuve(epreuve_id),\n"
                + "	serie_id integer NOT NULL REFERENCES serie(serie_id),\n"
                + "	session_id integer NOT NULL REFERENCES session(session_id),\n"
                + "	typeEpreuve_id integer NOT NULL REFERENCES typeEpreuve(typeEpreuve_id),\n"
                + "	coefficient integer NOT NULL,\n"
                + "	noteEliminatoire integer NOT NULL DEFAULT 0,\n"
                + "	duree text NOT NULL,\n"
                + "	estComposeSecTour text NOT NULL DEFAULT 'false',\n"
                + "	estRachetable text NOT NULL DEFAULT 'false',\n"
                + "	estComposePreTour text NOT NULL DEFAULT 'false',\n"
                + "	estTypeFrancais text NOT NULL DEFAULT 'false',\n"
                + "	PRIMARY KEY(session_id,serie_id,epreuve_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableComposition() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS composition;";
        String sql = "CREATE TABLE IF NOT EXISTS composition (\n"
                + "	epreuve_id integer NOT NULL,\n"
                + "	jury_id integer NOT NULL REFERENCES jury(jury_id),\n"
                + "	session_id integer NOT NULL,\n"
                + "	tourComposition_id integer NOT NULL,\n"
                + "	dateComposition text NOT NULL,\n"
                + "	heureDebutComposition text NOT NULL,\n"
                + "	heureFinComposition text NOT NULL,\n"
                + " fraude text NOT NULL DEFAULT 'false',\n"
                + " incidence text NOT NULL DEFAULT 'false',\n"
                + "	observation text,\n"
                + "	PRIMARY KEY(session_id,jury_id,epreuve_id,tourComposition_id),\n"
                + "	FOREIGN KEY(tourComposition_id) REFERENCES tourComposition(tourComposition_id),\n"
                + "	FOREIGN KEY(session_id,epreuve_id) REFERENCES epreuveSerie(session_id,epreuve_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }

    public void createTableCentreSecondaire() throws SQLException{
        
        // SQL statement for creating a new table
        String sql1 = "DROP TABLE IF EXISTS centreSecondaire;";
        String sql = "CREATE TABLE IF NOT EXISTS centreSecondaire (\n"
                + "	centreSecondaire_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	jury_id integer NOT NULL REFERENCES jury(jury_id),\n"
                + "	session_id integer NOT NULL,\n"
                + "	etablissement_id integer NOT NULL,\n"
                +"libelle text,\n"
                + "	FOREIGN KEY(jury_id) REFERENCES jury(jury_id),\n"
                + "	FOREIGN KEY(session_id,etablissement_id) REFERENCES centreComposition(session_id,etablissement_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableFeuilleComposition() throws SQLException{
        String sql1 = "DROP TABLE IF EXISTS feuilleComposition;";
        // SQL statement for creating a new table
       String sql = "CREATE TABLE IF NOT EXISTS feuilleComposition (\n"
                + "	epreuve_id integer NOT NULL,\n"
                + "	serie_id integer NOT NULL,\n"
                + "	session_id integer NOT NULL,\n"
                + "	jury_id integer NOT NULL,\n"
                + "	tourComposition_id integer NOT NULL,\n"
                + "	correcteur_id integer,\n"
                + "	candidat_id integer NOT NULL,\n"
                + "	presence text NOT NULL,\n"
                + "	anonymat text,\n"
                + "	note integer,\n"
                + "	cycle integer NOT NULL,\n"
                + "	verouille text default '0',\n"
                + "	incidence text NOT NULL DEFAULT 'false',\n"
                + "	PRIMARY KEY(session_id,tourComposition_id,epreuve_id,candidat_id),\n"
                + "	FOREIGN KEY(session_id,jury_id,serie_id,epreuve_id,tourComposition_id) REFERENCES composition(session_id,jury_id,serie_id,epreuve_id,tourComposition_id),\n"
                + "	FOREIGN KEY(session_id,jury_id,candidat_id) REFERENCES candidatJury(session_id,jury_id,candidat_id),\n"
                + "	FOREIGN KEY(correcteur_id) REFERENCES personnel(personnel_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableCandidatPosteJury() throws SQLException{
        
        // SQL statement for creating a new table
       String sql1 = "DROP TABLE IF EXISTS candidatPosteJury;";
        String sql = "CREATE TABLE IF NOT EXISTS candidatPosteJury (\n"
                + "	candidat_id integer PRIMARY KEY,\n"
                + "	session_id integer NOT NULL,\n"
                + "	localiteCandidat_id integer NOT NULL,\n"
                + "	structure_id text,\n"
                + "	poste_id integer NOT NULL,\n"
                + "	epreuve_id integer ,\n"
                + "	nom text NOT NULL,\n"
                + "	prenom text NOT NULL,\n"
                + "	sexe text NOT NULL,\n"
                + "	telephone text NOT NULL,\n"
                + "	matricule text UNIQUE,\n"
                + "	nip text UNIQUE,\n"
                + "	etatCandidature text NOT NULL DEFAULT 'En attente',\n"
                + "	UNIQUE(session_id,matricule,epreuve_id),\n"
                + "	FOREIGN KEY(session_id) REFERENCES session(session_id),\n"
                + "	FOREIGN KEY(localiteCandidat_id) REFERENCES localite(localite_id),\n"
                + "	FOREIGN KEY(poste_id) REFERENCES posteMembreJury(poste_id)\n"
                + ");";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableEpreuveCandidatPosteJury() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS epreuveCandidatPosteJury (\n"
                + "	candidat_id integer NOT NULL,\n"
                + "	epreuve_id integer NOT NULL,\n"
                + "	serie_id integer NOT NULL,\n"
                + "	PRIMARY KEY(candidat_id,epreuve_id,serie_id),\n"
                + "	FOREIGN KEY(candidat_id) REFERENCES candidatPosteJury(candidat_id),\n"
                + "	FOREIGN KEY(epreuve_id) REFERENCES epreuve(epreuve_id),\n"
                + "	FOREIGN KEY(serie_id) REFERENCES serie(serie_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTablePersonnel() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS personnel (\n"
                + "	personnel_id integer PRIMARY KEY,\n"
                + "	nom text NOT NULL,\n"
                + "	prenom text NOT NULL,\n"
                + "	sexe text NOT NULL,\n"
                + "	telephone text NOT NULL,\n"
                + "	matricule text UNIQUE,\n"
                + "	nip text UNIQUE\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTablePosteMembreJury() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS posteMembreJury (\n"
                + "	poste_id integer PRIMARY KEY,\n"
                + "	posteLibelle text NOT NULL UNIQUE\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableMembreJury() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS membreJury (\n"
                + "	poste_id integer NOT NULL,\n"
                + "	jury_id integer NOT NULL,\n"
                + "	session_id integer NOT NULL,\n"
                + "	personnel_id integer NOT NULL,\n"
                + "	membreDeliberant text NOT NULL,\n"
                + "	PRIMARY KEY(session_id,jury_id,personnel_id),\n"
                + "	FOREIGN KEY(poste_id) REFERENCES posteMembreJury(poste_id),\n"
                + "	FOREIGN KEY(jury_id) REFERENCES jury(jury_id),\n"
                + "	FOREIGN KEY(session_id) REFERENCES session(session_id),\n"
                + "	FOREIGN KEY(personnel_id) REFERENCES personnel(personnel_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableDisciplineCorrecteur() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS disciplineCorrecteur (\n"
                + "	session_id integer NOT NULL,\n"
                + "	personnel_id integer NOT NULL,\n"
                + "	epreuve_id text NOT NULL,\n"
                + "	serie_id text NOT NULL,\n"
                + "	PRIMARY KEY(session_id,personnel_id,epreuve_id,serie_id),\n"
                + "	FOREIGN KEY(session_id,serie_id,epreuve_id) REFERENCES epreuveSerie(session_id,serie_id,epreuve_id),\n"
                + "	FOREIGN KEY(personnel_id) REFERENCES personnel(personnel_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }


    public void createTableSalleComposition() throws SQLException{

        // SQL statement for creating a new table

        String sql1 = "DROP TABLE IF EXISTS salleComposition;";
        String sql = "CREATE TABLE IF NOT EXISTS salleComposition (\n"
                + "	salleComposition_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	session_id integer NOT NULL,\n"
                + "	jury_id integer NOT NULL,\n"
                + "	libelleSalle text,\n"
                + "	capacite integer,\n"
                + "	tourComposition_id integer NOT NULL,\n"
                + "	FOREIGN KEY(jury_id) REFERENCES jury(jury_id),\n"
                + "	FOREIGN KEY(tourComposition_id) REFERENCES tourComposition(tourComposition_id),\n"
                + "	FOREIGN KEY(session_id) REFERENCES session(session_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }


    public void createTableCandidatSalleComposition() throws SQLException{
        
        // SQL statement for creating a new table

        String sql1 = "DROP TABLE IF EXISTS candidatSalle;";
        String sql = "CREATE TABLE IF NOT EXISTS candidatSalle (\n"
                + "	salleComposition_id integer NOT NULL,\n"
                + "	candidat_id integer NOT NULL,\n"
                + "	PRIMARY KEY(salleComposition_id,candidat_id),\n"
                + "	UNIQUE(salleComposition_id,candidat_id),\n"
                + "	FOREIGN KEY(salleComposition_id) REFERENCES salleComposition(salleComposition_id),\n"
                + "	FOREIGN KEY(candidat_id) REFERENCES inscription(candidat_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    public void createTableMenu() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS menu (\n"
                + "	menu_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	menuParent_id integer,\n"
                + "	menuLibelle text UNIQUE NOT NULL,\n"
                + "	FOREIGN KEY(menuParent_id) REFERENCES menu(menu_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableProfil() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS profil (\n"
                + "	profil_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	profilLibelle text UNIQUE NOT NULL\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableProfilMenus() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS profilMenus (\n"
                + "	profil_id integer NOT NULL,\n"
                + "	menu_id integer NOT NULL,\n"
                + "	PRIMARY KEY(profil_id,menu_id),\n"
                + "	CONSTRAINT fk_prolil_pm FOREIGN KEY(profil_id) REFERENCES profil(profil_id),\n"
                + "	CONSTRAINT fk_menu_pm FOREIGN KEY(menu_id) REFERENCES menu(menu_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createTableUser() throws SQLException{
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS user (\n"
                + "	user_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	profil_id integer NOT NULL,\n"
                + "	personnel_id integer,\n"
                + "	username text NOT NULL UNIQUE,\n"
                + "	pwd text NOT NULL,\n"
                + "	CONSTRAINT fk_prolil_user FOREIGN KEY(profil_id) REFERENCES profil(profil_id),\n"
                + "	CONSTRAINT fk_personnel_user FOREIGN KEY(personnel_id) REFERENCES personnel(personnel_id)\n"
                + ");";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    

    public void createViewRegion() throws SQLException{
        String sql = "CREATE VIEW IF NOT EXISTS region AS\n"
                + "	SELECT l.localite_id, l.codeLocalite, l.nomLocalite\n"
                + "	FROM localite l\n"
                + "	INNER JOIN typeLocalite t ON t.typeLocalite_id = l.typeLocalite_id\n"
                + "	WHERE t.typeLocaliteLibelle = 'Région'\n"
                + ";";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewProvince() throws SQLException{
        String sql = "CREATE VIEW IF NOT EXISTS province AS\n"
                + "	SELECT t.typeLocalite_id,t.typeLocaliteLibelle,\n"
                + "     p.localite_id as parent_id, p.codeLocalite as parentCode, p.nomLocalite as parentLibelle,\n"
                + "     l.localite_id, l.codeLocalite, l.nomLocalite\n"
                + "	FROM localite l\n"
                + "	INNER JOIN typeLocalite t ON t.typeLocalite_id = l.typeLocalite_id\n"
                + "	INNER JOIN localite p ON p.localite_id = l.parentLocalite\n"
                + "	WHERE t.typeLocaliteLibelle = 'Province'\n"
                + ";";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewCommune() throws SQLException{
        String sql = "CREATE VIEW IF NOT EXISTS commune AS\n"
                + "	SELECT t.typeLocalite_id,t.typeLocaliteLibelle,\n"
                + "     p.localite_id as parent_id, p.codeLocalite as parentCode, p.nomLocalite as parentLibelle,\n"
                + "     l.localite_id, l.codeLocalite, l.nomLocalite\n"
                + "	FROM localite l\n"
                + "	INNER JOIN typeLocalite t ON t.typeLocalite_id = l.typeLocalite_id\n"
                + "	INNER JOIN localite p ON p.localite_id = l.parentLocalite\n"
                + "	WHERE t.typeLocaliteLibelle = 'Commune'\n"
                + ";";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewVilleComposition() throws SQLException{
        /*Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS villeComposition;");*/
        String sql = "CREATE VIEW IF NOT EXISTS villeComposition AS\n"
                + "	SELECT distinct t.typeLocalite_id,t.typeLocaliteLibelle,\n"
                + "     p.localite_id as parent_id, p.codeLocalite as parentCode, p.nomLocalite as parentLibelle,\n"
                + "     l.localite_id, l.codeLocalite, l.nomLocalite,s.*\n"
                + "	FROM localite l\n"
                + "	INNER JOIN typeLocalite t ON t.typeLocalite_id = l.typeLocalite_id\n"
                + "	INNER JOIN localite p ON p.localite_id = l.parentLocalite\n"
                + "	INNER JOIN inscription i ON i.villeComposition = l.localite_id\n"
                + "	INNER JOIN session s ON s.session_id = i.session_id\n"
                + "	WHERE t.typeLocaliteLibelle = 'Commune'\n"
                + ";";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewSerieVilleComposition() throws SQLException{
        /*Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS serieVilleComposition;");*/
        String sql = "CREATE VIEW IF NOT EXISTS serieVilleComposition AS\n"
                + "	SELECT distinct s.*,vp.*,s.*\n"
                + "	FROM serie s\n"
                + "	INNER JOIN inscription i ON i.serie_id = s.serie_id\n"
                + "	INNER JOIN localite vp ON vp.localite_id = i.villeComposition\n"
                + "	INNER JOIN session s ON s.session_id = i.session_id\n"
                + ";";
        
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewStructure() throws SQLException{
        /*Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewStructure;");*/
        String sql = "CREATE VIEW IF NOT EXISTS viewStructure AS\n"
                + "	SELECT ts.*,p.*,l.*,s.*,e.*,statut.*,o.*\n"
                + "	FROM structure s\n"
                + "	INNER JOIN typeStructure ts ON ts.typeStructure_id = s.typeStructure_id\n"
                + "	INNER JOIN structure p ON p.structure_id = s.parent_id\n"
                + "	INNER JOIN localite l ON l.localite_id = s.localite_id\n"
                + "	LEFT JOIN etablissement e ON e.structure_id = s.structure_id\n"
                + "	LEFT JOIN statutEtablissement statut ON statut.statut_id = e.statut_id\n"
                + "	LEFT JOIN offreEnseignement o ON o.offreEnseignement_id = e.offreEnseignement_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewEpreuveSerie() throws SQLException{
        /*Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewStructure;");*/
        String sql = "CREATE VIEW IF NOT EXISTS viewEpreuveSerie AS\n"
                + "	SELECT ses.*,serie.*,tepr.*,e.*,es.*\n"
                + "	FROM epreuveSerie es\n"
                + "	INNER JOIN serie ON serie.serie_id = es.serie_id\n"
                + "	INNER JOIN session ses ON ses.session_id = es.session_id\n"
                + "	INNER JOIN typeEpreuve tepr ON tepr.typeEpreuve_id = es.typeEpreuve_id\n"
                + "	INNER JOIN epreuve e ON e.epreuve_id = es.epreuve_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewCentre() throws SQLException{
        /*Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewCentre;");*/
        String sql = "CREATE VIEW IF NOT EXISTS viewCentre AS\n"
                + "	SELECT struct.*,c.*,sess.*\n"
                + "	FROM structure struct\n"
                + "	LEFT JOIN centreComposition c ON c.etablissement_id = struct.structure_id\n"
                + "	LEFT JOIN session sess ON sess.session_id = c.session_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewJury() throws SQLException{
        /*Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewJury;");*/
        String sql = "CREATE VIEW IF NOT EXISTS viewJury AS\n"
                + "	SELECT str.*,j.*,sess.*,centre.*\n"
                + "	FROM jury j\n"
                + "	INNER JOIN structure str ON j.etablissement_id = str.structure_id\n"
                + "	INNER JOIN session sess ON sess.session_id = j.session_id\n"
                + "	INNER JOIN centreComposition centre ON centre.etablissement_id = str.structure_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewCandidat() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewCandidat;");
        String sql = "CREATE VIEW IF NOT EXISTS viewCandidat AS\n"
                + "	SELECT c.*,i.*,sess.*,serie.*,vilcompo.*,str.*\n"
                + "	FROM candidat c\n"
                + "	INNER JOIN inscription i ON i.candidat_id = c.candidat_id\n"
                + "	INNER JOIN structure str ON str.structure_id = i.etablissement_id\n"
                + "	INNER JOIN session sess ON sess.session_id = i.session_id\n"
                + "	INNER JOIN localite vilcompo ON vilcompo.localite_id = i.centreExamen_Id\n"
                + "	INNER JOIN serie ON serie.serie_id = i.serie_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewCandidatJury() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewCandidatJury;");
        String sql = "CREATE VIEW IF NOT EXISTS viewCandidatJury AS\n"
                + "	SELECT c.*,i.*,sess.*,serie.*,vilcompo.*,str.*,j.*,r.*,ces.*\n"
                + "	FROM candidat c\n"
                + "	INNER JOIN inscription i ON i.candidat_id = c.candidat_id\n"
                + "	INNER JOIN structure str ON str.structure_id = i.etablissement_id\n"
                + "	INNER JOIN session sess ON sess.session_id = i.session_id\n"
                + "	INNER JOIN localite vilcompo ON vilcompo.localite_id = i.centreExamen_Id\n"
                + "	INNER JOIN serie ON serie.serie_id = i.serie_id\n"
                + "	LEFT JOIN candidatJury cj ON cj.candidat_id = c.candidat_id\n"
                + "LEFT JOIN centreSecondaire ces ON ces.centreSecondaire_id = cj.centreSecondaire_id\n"
                + "	LEFT JOIN jury j ON j.jury_id = cj.jury_id\n"
                + "	LEFT JOIN resultat r ON r.candidat_id = i.candidat_id and r.session_id = i.session_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }

    public void createViewCandidatSalle() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewCandidatSalle;");
        String sql = "CREATE VIEW IF NOT EXISTS viewCandidatSalle AS\n"
                + "	SELECT c.*,i.*,sess.*,serie.*,vilcompo.*,str.*,j.*,r.*\n"
                + "	FROM candidat c\n"
                + "	INNER JOIN inscription i ON i.candidat_id = c.candidat_id\n"
                + "	INNER JOIN structure str ON str.structure_id = i.etablissement_id\n"
                + "	INNER JOIN session sess ON sess.session_id = i.session_id\n"
                + "	INNER JOIN localite vilcompo ON vilcompo.localite_id = i.centreExamen_Id\n"
                + "	INNER JOIN serie ON serie.serie_id = i.serie_id\n"
                + "	LEFT JOIN candidatSalle cj ON cj.candidat_id = c.candidat_id\n"
                + "	LEFT JOIN salleComposition j ON j.salleComposition_id = cj.salleComposition_id\n"
                + "	LEFT JOIN resultat r ON r.candidat_id = i.candidat_id and r.session_id = i.session_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
  /*   
    public void createViewComposition() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewComposition;");
        
        String sql = "CREATE VIEW IF NOT EXISTS viewComposition AS\n"
                + "	SELECT c.*,sess.*,j.*,e.*,t.*\n"
                + "	FROM composition c\n"
                + "	INNER JOIN session sess ON sess.session_id = c.session_id\n"
                + "	INNER JOIN jury j ON j.jury_id = c.jury_id\n"
                + "	INNER JOIN epreuve e ON e.epreuve_id = c.epreuve_id\n"
                + "	INNER JOIN tourComposition t ON t.tourComposition_id = c.tourComposition_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }

     */
      
     public void createViewComposition() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewComposition;");
        
        String sql = "CREATE VIEW IF NOT EXISTS viewComposition AS\n"
                + "	SELECT c.*,sess.*,j.*,e.*,t.*,eps.*,ser.*\n"
               + " FROM composition c\n"
               + " INNER JOIN session sess ON sess.session_id = c.session_id\n"
               + " INNER JOIN jury j ON j.jury_id = c.jury_id\n"
               + " INNER JOIN epreuve e ON e.epreuve_id = c.epreuve_id\n"
               + " JOIN epreuveSerie eps ON eps.epreuve_id = e.epreuve_id\n"	
               + "JOIN serie ser ON ser.serie_id = eps.serie_id\n"	

               + " INNER JOIN tourComposition t ON t.tourComposition_id = c.tourComposition_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }

    
    public void createViewEpreuveComposer() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS ViewEpreuveComposer;");
        
        String sql = "CREATE VIEW IF NOT EXISTS ViewEpreuveComposer AS\n"
                // + "	SELECT DISTINCT e.*,eps.coefficient,eps.estComposeSecTour\n"
                +"SELECT DISTINCT e.*,eps.coefficient,eps.estComposeSecTour,j.jury_id,sess.session_id,t.tourComposition_id AS tour\n"
                + "FROM epreuve e \n"
                + "JOIN epreuveSerie eps ON eps.epreuve_id = e.epreuve_id\n"
                + "JOIN feuilleComposition c ON eps.epreuve_id = e.epreuve_id AND c.serie_id=eps.serie_id\n"
                + "INNER JOIN tourComposition t ON t.tourComposition_id = c.tourComposition_id\n"
                + "INNER JOIN session sess ON sess.session_id = c.session_id\n"
                + "INNER JOIN jury j ON j.jury_id = c.jury_id\n"	

               + " INNER JOIN tourComposition t ON t.tourComposition_id = c.tourComposition_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    

    public void createViewFeuilleComposition() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewFeuilleComposition;");
        String sql = "CREATE VIEW IF NOT EXISTS viewFeuilleComposition AS\n"
                + "	SELECT vc.*,fc.*,c.*,i.*,cor.matricule,cor.nom as nomCorrecteur, cor.prenom as prenomCorrecteur,sc.*,lc.*\n"
                + "	FROM viewComposition vc\n"
                + "	INNER JOIN feuilleComposition fc ON\n"
                +"      fc.session_id = vc.session_id and fc.jury_id = vc.jury_id and\n"
                +"      fc.serie_id = vc.serie_id and fc.epreuve_id = vc.epreuve_id and\n"
                +"      fc.tourComposition_id = vc.tourComposition_id\n"
                + "	INNER JOIN candidat c ON c.candidat_id = fc.candidat_id\n"
                + "	INNER JOIN inscription i ON i.candidat_id = c.candidat_id\n"
                +"INNER JOIN structure sc on sc.structure_id = i.etablissement_id\n"
                +"INNER JOIN localite lc ON lc.localite_id = i.centreExamen_id\n"
                + "	LEFT JOIN personnel cor ON cor.personnel_id = fc.correcteur_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
     
    
     /* public void createViewFeuilleComposition() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewFeuilleComposition;");
        String sql = "CREATE VIEW IF NOT EXISTS viewFeuilleComposition AS\n"
                + "	SELECT vc.*,fc.*,c.*,i.*,cor.matricule,cor.nom as nomCorrecteur, cor.prenom as prenomCorrecteur,eps.*\n"
                + "FROM viewComposition vc\n"
               + " INNER JOIN feuilleComposition fc ON\n"
               + "   fc.session_id = vc.session_id and fc.jury_id = vc.jury_id and\n"
               + "   fc.tourComposition_id = vc.tourComposition_id and\n"
               + "   fc.epreuve_id = vc.epreuve_id\n"
               + " INNER JOIN epreuveSerie eps ON eps.epreuve_id = fc.epreuve_id and eps.serie_id = fc.serie_id\n"
               + " INNER JOIN candidat c ON c.candidat_id = fc.candidat_id\n"
               + " INNER JOIN inscription i ON i.candidat_id = c.candidat_id\n"
               + " LEFT JOIN personnel cor ON cor.personnel_id = fc.correcteur_id\n"
                + ";";

                
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
     */

    public void createViewCandidatPosteJury() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewCandidatPosteJury;");
        String sql = "CREATE VIEW IF NOT EXISTS viewCandidatPosteJury AS\n"
                + "	SELECT cpj.*,ecpj.*,e.*,s.*,sess.*,str.*,loc.*,poste.*\n"
                + "	FROM candidatPosteJury cpj\n"
                + "	LEFT JOIN epreuveCandidatPosteJury ecpj ON ecpj.candidat_id = cpj.candidat_id\n"
                + "	LEFT JOIN epreuve e ON e.epreuve_id= cpj.epreuve_id\n"
                + "	LEFT JOIN serie s ON s.serie_id = ecpj.serie_id\n"
                + "	INNER JOIN session sess ON sess.session_id = cpj.session_id\n"
                + "	INNER JOIN structure str ON str.structure_id = cpj.structure_id\n"
                + "	INNER JOIN localite loc ON loc.localite_id = cpj.localiteCandidat_id\n"
                + "	INNER JOIN posteMembreJury poste ON poste.poste_id = cpj.poste_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewMembreJury() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewMembreJury;");
        String sql = "CREATE VIEW IF NOT EXISTS viewMembreJury AS\n"
                + "	SELECT m.membreDeliberant,p.*,sess.*,j.*,poste.*,e.*,s.*\n"
                + "	FROM membreJury m\n"
                + "	INNER JOIN personnel p ON p.personnel_id = m.personnel_id\n"
                + "	INNER JOIN session sess ON sess.session_id = m.session_id\n"
                + "	INNER JOIN posteMembreJury poste ON poste.poste_id = m.poste_id\n"
                + "	INNER JOIN jury j ON j.jury_id = m.jury_id\n"
                + "	LEFT JOIN disciplineCorrecteur dc ON dc.personnel_id = m.personnel_id\n"
                + "	LEFT JOIN serie s ON s.serie_id = dc.serie_id\n"
                + "	LEFT JOIN epreuve e ON e.epreuve_id = dc.epreuve_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
    
    public void createViewResultat() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewResultat;");
        String sql = "CREATE VIEW IF NOT EXISTS viewResultat AS\n"
                + "	SELECT c.*,r.*,t.*,cj.*\n"
                + "	FROM viewCandidat c\n"
                + "	INNER JOIN resultat r ON r.candidat_id = c.candidat_id and r.session_id = c.session_id\n"
                + "	INNER JOIN candidatJury cj ON cj.candidat_id = c.candidat_id\n"
                + "	INNER JOIN tourComposition t ON t.tourComposition_id = r.tourComposition_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }

    public void createViewCentreSecondaire() throws SQLException{
        /*Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewCentre;");*/
        String sql = "CREATE VIEW IF NOT EXISTS viewCentreSecondaire AS\n"
                + "	SELECT j.*,sess.*,c.*,centres.*\n"
                + "	FROM centreSecondaire centres\n"
                + "	INNER JOIN centreComposition c ON c.etablissement_id = centres.etablissement_id\n"
                + "	INNER JOIN jury j ON j.jury_id = centres.jury_id\n"
                + "	INNER JOIN session sess ON sess.session_id = centres.session_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }

     
    public void createViewUser() throws SQLException{
        Statement stmtt = this.connexion.createStatement();
        stmtt.execute("DROP VIEW IF EXISTS viewUser;");
        String sql = "CREATE VIEW IF NOT EXISTS viewUser AS\n"
                + "	SELECT pr.*,u.*,pe.*,m.jury_id\n"
                + "	FROM user u\n"
                + "	INNER JOIN profil pr ON pr.profil_id = u.profil_id\n"
                + "	LEFT JOIN personnel pe ON pe.personnel_id = u.personnel_id\n"
                + "	LEFT JOIN membreJury m ON pe.personnel_id = m.personnel_id\n"
                + ";";
        Statement stmt = this.connexion.createStatement();
        stmt.execute(sql);
    }
}
