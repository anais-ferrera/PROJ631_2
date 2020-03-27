import java.util.ArrayList;

public class NoeudsSysteme implements Noeud {
	//un noeud systeme est defini par un id et une capacite memoire
	private int id_noeud_sys, capa_memoire;
	//le noeud systeme a egalement une liste de donnees qu'il contient, la liste des utilisateurs
	//qui accedent au noeud ainsi que la liste des noeuds accessibles
	private ArrayList<Integer> liste_idDonnees;
	private ArrayList<NoeudsSysteme> liste_idNoeuds;
	private ArrayList<Integer> liste_idUtlisateurs;
	private static int compteur_id=0;

	public NoeudsSysteme(int capa_memoire){
		//on incremente le compteur
		compteur_id++;
		this.capa_memoire=capa_memoire;
		this.id_noeud_sys=compteur_id;
		liste_idDonnees = new ArrayList<Integer>();
		liste_idNoeuds = new ArrayList<NoeudsSysteme>();
		liste_idUtlisateurs = new ArrayList<Integer>();
	}
	public int compteur_zero() {
		return compteur_id=0;
	}

	//methode qui permet d'ajouter une donnee a la liste des donnees
	public void ajouterIdDonnee (Donnees donnee) {
		this.liste_idDonnees.add(donnee.getId_donnees());
		//on modifie la capacite memoire du noeud systeme en soustrayant la taille de la
		//donnee ajoutee a la capacite actuelle du noeud 
		this.capa_memoire=this.capa_memoire-donnee.getTaille();
	}
	
	public int getCapa_memoire() {
		return capa_memoire;
	}

	//methode qui permet d'ajouter un noeud systeme a la liste des noeuds
	public void ajouterIdNoeud (NoeudsSysteme noeud) {
		this.liste_idNoeuds.add(noeud);
	}
	
	//getter qui permet d'obtenir l'id d'un noeud systeme
	public int getId_noeud_sys() {
		return id_noeud_sys;
	}

	//getter qui permet d'obtenir la liste des donnees
	public ArrayList<Integer> getListe_idDonnees() {
		return liste_idDonnees;
	}
	
	//getter qui permet d'obtenir la liste des noeuds
	public ArrayList<NoeudsSysteme> getListe_idNoeuds() {
		return liste_idNoeuds;
	}

	//getter qui permet d'obtenir la liste des utilisateurs
	public ArrayList<Integer> getListe_idUtlisateurs() {
		return liste_idUtlisateurs;
	}
	
	public int getIdNoeud() {
		return this.id_noeud_sys;
	}

	public String toString() {
		return this.id_noeud_sys+"";
	}

}
