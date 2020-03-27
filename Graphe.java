import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.ArrayList;
import java.util.List;
import org.jgrapht.alg.*;

public class Graphe<Noeud,Edge> extends SimpleWeightedGraph {
	
	private ArrayList<Utilisateurs> liste_utilisateurs = new ArrayList<Utilisateurs>();
	private ArrayList<Donnees> liste_donneesAPlacer = new ArrayList<Donnees>();
	private ArrayList<NoeudsSysteme> listeNoeudsSysteme = new ArrayList<NoeudsSysteme>();

	public Graphe(Class arg0) {
		super(arg0);
	}
	
	
	
	//methode qui permet d'ajouter un utilisateur a la liste des utilisateurs
	public void ajouterUtilisateur(Utilisateurs ustilisateur) {
		this.liste_utilisateurs.add(ustilisateur);
		
	}
	
	//methode qui permet d'ajouter la liste des utilisateurs au graphe
	public void ajouterUtilisateursAuGraphe(ArrayList<Utilisateurs> listeUtilisateurs)
	{
		this.addVertex(listeUtilisateurs);
	}
	
	
	//methode qui permet d'ajouter la liste des noeuds au graphe
	public void ajouterNoeudsAuGraphe(ArrayList<NoeudsSysteme> listeNoeuds)
	{
		this.addVertex(listeNoeuds);
	}
	
	//methode qui permet d'ajouter des donnees dans la liste des donnees a placer
	public void ajouterDonneesAMettre(ArrayList<Donnees> listeDonnees) {
		for (int i=0;i<listeDonnees.size();i++) {
			this.liste_donneesAPlacer.add(listeDonnees.get(i));
			
		}
	}
	
	//methode qui permet d'ajouter plusieurs noeuds au graphe
		public void ajouterDesNoeudsAuGraphe(ArrayList<NoeudsSysteme> noeuds) {
			for(int i = 0;i<noeuds.size();i++) {
				this.listeNoeudsSysteme.add(noeuds.get(i));
			}
		}
	

	
	//methode qui permet de recuperer les donnees a placer en fonction des demandes de l'utilisateur
	public ArrayList<Donnees> get_donnees_a_placer_par_utilisateur(Utilisateurs utilisateur){
		//on cree une liste de donnees 
		ArrayList<Donnees> donneesUtilisateur = new ArrayList<Donnees>();
		//on parcourt la liste des id_donnee de l'utilisateur
		for(int i = 0;i<utilisateur.getListe_id().size();i++) {
			//si une donnee de l'utilisateur correspond a une donnee des donnees a placer
			if(utilisateur.getListe_id().get(i) == this.liste_donneesAPlacer.get(i).getId_donnees()) {
				//alors on ajoute cette donnee dans la liste de donnees creee precedement
				donneesUtilisateur.add(this.liste_donneesAPlacer.get(i));
			}
		}
		//on retourne la liste de donnees d'un utilisateur
		return donneesUtilisateur;
	}
	
	
	
	//methode qui permet de recuperer les noeuds par leur id 
	public NoeudsSysteme getNoeudId(int idNoeud) {
		//on parcourt la liste des noeuds systeme du graphe
		for(int i = 0;i<this.listeNoeudsSysteme.size();i++) {
			//si l'id d'un noeud systeme correspond a l'id passe en parametre 
			if (this.listeNoeudsSysteme.get(i).getIdNoeud() == idNoeud){
				//alors on retourne l'id de ce noeud
				return this.listeNoeudsSysteme.get(i);
			}
		}
		//sinon le noeud recherche ne fait pas parti du graphe
		System.out.println("Le noeud recherche ne se trouve pas dans le graphe");
		return null;
	}
	
	//methode qui permet de recuperer une liste de donnees intermediaire
	public ArrayList<Donnees> getListeInter(ArrayList<Donnees> donnee){
		ArrayList<Donnees> donnees_inter = new ArrayList<Donnees>();
		//on ajoute dans la liste de donnees intermediaire les donnnees presentent dans 
		//la liste passee en parametre
		for(Donnees d : donnee) {
			donnees_inter.add(d);
		}
		return donnees_inter;
	}
	
	
	
	//methode qui permet de placer les donnees dans le graphe 
	public void placerDonnee(Utilisateurs utilisateur) {	
		//on initialise la liste pour les donnees a placer
		ArrayList<Donnees> donneesaPlacer = new ArrayList<Donnees>();
		//on initialise une liste de donnees a placer intermediaire
		ArrayList<Donnees> donneesaPlacer_inter = new ArrayList<Donnees>();
			//on recupere les donnnees qui interessennt l'utilisateur
			donneesaPlacer = this.get_donnees_a_placer_par_utilisateur(utilisateur);
			donneesaPlacer_inter = this.getListeInter(donneesaPlacer);
			//on recupere le noeud systeme qui est relie a l'utilisateur
			NoeudsSysteme noeudSysUtilisateur = this.getNoeudId(utilisateur.getId_noeud());
			//on parcourt la liste des donnees a placer
			for (int i = 0;i<donneesaPlacer.size();i++) {
				//si la taille de la donnee est inferieure ou egale a la capacite memoire du noeud
				if (donneesaPlacer.get(i).getTaille() <= noeudSysUtilisateur.getCapa_memoire()) {
					//alors on ajoute la donnee dans le noeud systeme 
					noeudSysUtilisateur.ajouterIdDonnee(donneesaPlacer.get(i));
					//on enleve cette donnee placee de la liste a ajouter
					donneesaPlacer_inter.remove(donneesaPlacer.get(i));
				//sinon
				}
				else {
					//on cree une liste de noeuds systeme qui correspond au noeud accessibles depuis le noeudSysUtilisateur
					ArrayList<NoeudsSysteme> noeudAccessibles = noeudSysUtilisateur.getListe_idNoeuds();
					//on fixe une valeur par defaut du noeud suivant
					double minimum = 100;
					NoeudsSysteme noeudSuivant = null;
					//on parcourt les noeuds accessibles depuis le noeud systeme
					for(NoeudsSysteme noeudSys : noeudAccessibles) {
						//si le poids de l'arc entre le noeud actuel et un noeud de la liste des noeuds accessibles
						//est inferieur au minimum fixe precedement et si le noeud accessible a une capacite
						//memoire suffisante pour stocker la donnee
						if (this.getEdgeWeight(this.getEdge(noeudSysUtilisateur,noeudSys))< minimum & 
								noeudSys.getCapa_memoire()>= donneesaPlacer.get(i).getTaille()) {
							//alors le minimum devient egal au poids de l'arc entre le noeud actuel et le noeud accessible
							minimum = this.getEdgeWeight(this.getEdge(noeudSysUtilisateur,noeudSys));
							//le noeud suivant devient le noeud de la liste
							noeudSuivant = noeudSys;
						}
					}
					
					//Si un noeud suivant est dispo
					if(noeudSuivant != null) {
						//on ajoute la donnee dans ce noeud
						noeudSuivant.ajouterIdDonnee(donneesaPlacer.get(i));
						//on supprime la donnee qui a ete ajoute de la liste intermediaire
						donneesaPlacer_inter.remove(donneesaPlacer.get(i));	
					}//si il n'y pas de noeud suivant dispo
					else {
						//on appelle la mathode qui permet de placer les donnees dans un noeud suivant
						this.placerDonneesNoeudSuivant(noeudSysUtilisateur, donneesaPlacer.get(i));
						donneesaPlacer_inter.remove(donneesaPlacer.get(i));
					}
				}
		}//Si la liste de donnees intermediaire n'est pas vide
			if(donneesaPlacer_inter.size() > 0 ) {
			//alors il n'y a pas assez de memoire pour placer toutes les donnees
			//on affiche les donnees qui n'ont pas pu etre placees
			System.out.println(donneesaPlacer_inter);
			System.out.println("Pas assez de memoire pour placer cette donnee");
		}//Sinon on affiche que les donnees de l'utilisateur ont bien ete placees
			else {
			System.out.println("Les donnees de l'utilisateur "+utilisateur.getId_utilisateur()+" ont bien ete placees");
		}
	}
	
	
	
	//on cree une methode pour calculer le plus court chemin entre 2 noeuds systeme
		public List cheminLePlusCourt (NoeudsSysteme noeud1, NoeudsSysteme noeud2) {
			DijkstraShortestPath<NoeudsSysteme,Edge> dij = 
					new DijkstraShortestPath<NoeudsSysteme,Edge> (this, noeud1, noeud2);
			return dij.findPathBetween(this, noeud1, noeud2);
		}
	
	//methode qui permet de placer une donnee quand 2 utilisateurs sont interessees par elle
	public void placerDonnee2Utilisateurs(Utilisateurs utilisateur1,Utilisateurs utilisateur2,Donnees donnee) {
		//on cree une liste de type edge qui contiendra le chemin le plus court entre les noeuds accessibles 
		//par les utilisateurs
		ArrayList<Edge> distance_noeud = (ArrayList) this.cheminLePlusCourt
				(this.getNoeudId(utilisateur1.getId_noeud()),this.getNoeudId(utilisateur2.getId_noeud()));
			//on divise par 2 la distance pour savoir si le nombre d'arc est pair
			int i = distance_noeud.size()/2;
			//on recupere le noeud situe a mi-chemin entre les deux utilisateurs
			String noeud = distance_noeud.get(i).toString().charAt(1)+"";
			int idNoeud = Integer.parseInt(noeud);
			//si la capacite memoire du noeud est superieure a la taille de la donnee
			if(this.getNoeudId(idNoeud).getCapa_memoire() >= donnee.getTaille()) {
				//alors on peut ajouter la donnee dans le noeud
				this.getNoeudId(idNoeud).ajouterIdDonnee(donnee);
			}
			//sinon si la capacite memoire n'est pas suffisante
			else {
				//on change de noeud systeme
				noeud = distance_noeud.get(i).toString().charAt(5)+"";
				idNoeud = Integer.parseInt(noeud);
				//si la capacite memoire du noeud est suffisante on ajoute la donnee
				if(this.getNoeudId(idNoeud).getCapa_memoire() >= donnee.getTaille()) {
					this.getNoeudId(idNoeud).ajouterIdDonnee(donnee);
				}
			}
	}
	
	//methode qui permet recuperer les noeuds suivants accessibles
	public ArrayList<NoeudsSysteme> getNoeudSuivant(NoeudsSysteme noeudSuivant) {
		//on cree une liste de noeuds systeme
		ArrayList<NoeudsSysteme> noeuds = new ArrayList<NoeudsSysteme>();
		//pour chaque noeudSysteme accessible depuis le noeud courant
		for (NoeudsSysteme noeud : noeudSuivant.getListe_idNoeuds()) {
			NoeudsSysteme noeud2 = noeud;
			for(NoeudsSysteme noeudIntermed : noeud2.getListe_idNoeuds()) {
				noeuds.add(noeudIntermed);
				}
			}
		return noeuds;
	}
	
	//methode qui permet de placer des donnees 
	public void placerDonneesNoeudSuivant(NoeudsSysteme noeud,Donnees donnee) {
		//on cree une liste de noeuds qui est composee des prochains noeuds accessibles depuis un noeud
		ArrayList<NoeudsSysteme> noeudSuivant = this.getNoeudSuivant(noeud);
		//on parcourt les noeuds suivants du noeud courant
		for(NoeudsSysteme n : noeudSuivant) {
			//si le noeud a une capacite memoire superieure ou egale a la taille de la donnee 
			if(n.getCapa_memoire() >= donnee.getTaille()) {
				//alors on ajoute la donnee a la liste des donnees
				n.ajouterIdDonnee(donnee);
			}
		}
		
	}
	//methode qui permet d'afficher les noeuds du graphe avec ce qui est present dedans 
	public void affichageNoeudGraphe() {
		for (Utilisateurs u : this.liste_utilisateurs) {
			System.out.println("L'utilisateur "+ u.getId_utilisateur() + " est interesse par les donnees "+u.getListe_id());
		}
		System.out.println("Les donnees a placer sont : ");
		for (Donnees d : this.liste_donneesAPlacer) {
			System.out.println( d.getId_donnees() + " de taille : "+d.getTaille());
		}
		//pour tous les noeuds systeme du graphe
		for(NoeudsSysteme n : this.listeNoeudsSysteme) {
			//on affiche l'id du noeud avec sa capacite et les donnees qui ont ete placeees dedans
			System.out.println("Noeud systeme : " + n.getIdNoeud() + " capacite memoire : "+n.getCapa_memoire() + " donnees placees : "+n.getListe_idDonnees());
		}
	}

	
	

}
