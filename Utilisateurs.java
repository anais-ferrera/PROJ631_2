import java.util.ArrayList;

public class Utilisateurs {
	//un utilisateur est defini par un id, une liste de donnees d'interet et l'id d'un noeud accessible 
	private int id_utilisateur, id_noeud;
	private ArrayList<Integer> liste_id = new ArrayList<Integer>();
	//pour generer des id on initialise un compteur a 0
	private static int compteur_id=0;
	
	public Utilisateurs(ArrayList<Integer> liste_id_noeud, int id_noeud) {
		//on incremente le compteur
		compteur_id++;
		this.id_utilisateur=compteur_id;
		this.id_noeud=id_noeud;
		this.liste_id = liste_id_noeud;
		
	}
	public int compteur_zero() {
		return compteur_id=0;
	}
	//getter qui permet de recuperer l'id d'un utilisateur
	public int getId_utilisateur() {
		return id_utilisateur;
	}

	//setter qui permet de changer l'id d'un utilisateur
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	//getter qui permet d'obtenir l'id d'un noeud
	public int getId_noeud() {
		return id_noeud;
	}

	//setter qui permet de changer l'id d'un noeud
	public void setId_noeud(int id_noeud) {
		this.id_noeud = id_noeud;
	}

	//getter qui permet de recuperer la liste des donnees
	public ArrayList<Integer> getListe_id() {
		return liste_id;
	}
	
	//setter qui permet de changer la liste des donnees
	public void setListe_id(ArrayList<Integer> liste_id) {
		this.liste_id = liste_id;
	}

}
