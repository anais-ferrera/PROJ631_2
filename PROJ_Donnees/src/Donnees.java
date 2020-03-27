
public class Donnees {
	//une donnee est definie par un id et une taille
	//id et la taille sont des entiers
	private int id_donnees, taille;
	//pour generer des id on initialise un compteur a 0
	private static int compteur_id=0;
	
	//constructeur de la classe Donnees
	public Donnees(int taille) {
		//on incremente le compteur
		compteur_id++;
		this.id_donnees=compteur_id;
		this.taille=taille;
	}
	
	//getter pour recuperer l'id d'une donnee
	public int getId_donnees() {
		return id_donnees;
	}
	
	public int compteur_zero() {
		return compteur_id=0;
	}
	//setter pour changer l'id d'une donnee
	public void setId_donnees(int id_donnees) {
		this.id_donnees = id_donnees;
	}
	
	//getter pour recuperer la taille d'une donnee
	public int getTaille() {
		return taille;
	}
	
	//setter pour changer la taille d'une donnee
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	

}