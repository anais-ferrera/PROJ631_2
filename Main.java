import java.util.ArrayList;

import org.jgrapht.graph.*;

public class Main {
	
	public static void main(String[] args) {
		
		// LE GRAPHE g CORRESPOND A LA QUESTION 2 DU SUJET //
		System.out.println("Question 2 : ");
		// CREATION DU GRAPHE //
		
		Graphe<NoeudsSysteme, Edge> g = new Graphe <>(Edge.class);
		
		
		// CREATION DES DONNEES A PLACER //
		
		Donnees donnee1 = new Donnees(40);
		Donnees donnee2 = new Donnees(25);
		Donnees donnee3 = new Donnees(25);
		
		// CREATION DES NOEUDS SYSTEMES //
		
		NoeudsSysteme n1 = new NoeudsSysteme(50);
		NoeudsSysteme n2 = new NoeudsSysteme(40);
		NoeudsSysteme n3 = new NoeudsSysteme(40);
		
		// ON CREE UNE LISTE DE CES DONNEES QUE L'ON MET DANS LE GRAPHE //
		
		ArrayList<Donnees> listeDonnees = new ArrayList<Donnees>();
		listeDonnees.add(donnee1);
		listeDonnees.add(donnee2);
		listeDonnees.add(donnee3);
		g.ajouterDonneesAMettre(listeDonnees);
		
		// ON MET LES ID DES DONNEES CREEES DANS UNE LISTE //
		// CELA VA REPRESENTER LES DONNEES QUI INTERESSENT LES UTILISATEURS //
				
		ArrayList<Integer> listeIdDonnees = new ArrayList<Integer>();
		listeIdDonnees.add(donnee1.getId_donnees());
		listeIdDonnees.add(donnee2.getId_donnees());
		listeIdDonnees.add(donnee3.getId_donnees());


				
		// ON CREE UN UTILISATEUR ET ON L'AJOUTE AU GRAPHE //
				
		Utilisateurs utilisateur0 = new Utilisateurs(listeIdDonnees,1);
	
		
		g.ajouterUtilisateur(utilisateur0);
		
		// ON AJOUTE LES NOEUDS DANS LE GRAPHE //
		ArrayList<NoeudsSysteme> listeNoeudsSys = new ArrayList<NoeudsSysteme>();
		listeNoeudsSys.add(n1);
		listeNoeudsSys.add(n2);
		listeNoeudsSys.add(n3);
		
		g.ajouterDesNoeudsAuGraphe(listeNoeudsSys);
		//ON AJOUTE AU GRAPHE LES NOEUDS N1, N2, N3 //
		g.addVertex(n1);
		g.addVertex(n2);
		g.addVertex(n3);
		g.addVertex(utilisateur0);
		
		// ON LES RELIE ENTRE EUX //
		//LE NOEUD 1 EST RELIE AU NOEUD 2 //
		n1.ajouterIdNoeud(n2);
		n2.ajouterIdNoeud(n1);
		
		//LE NOEUD 2 EST RELIE AU NOEUD3 //
		n2.ajouterIdNoeud(n3);
		n3.ajouterIdNoeud(n2);
		
		
		//ON AJOUTE LES ARCS EN PRECISANT LES DEUX NOEUDS QU'ILS RELIENT //
		DefaultWeightedEdge arc12 = (DefaultWeightedEdge)g.addEdge(n1, n2);
		DefaultWeightedEdge arc23 = (DefaultWeightedEdge)g.addEdge(n2, n3);
		DefaultWeightedEdge arcu1 = (DefaultWeightedEdge)g.addEdge(utilisateur0, n1);
		
		//ON DONNE AUX ARCS UN POIDS DE 1 //
		g.setEdgeWeight(arc12, 1);
		g.setEdgeWeight(arc23, 1);
		g.setEdgeWeight(arcu1, 2);
		
		
		g.placerDonnee(utilisateur0);
		g.affichageNoeudGraphe();
		
		
		//REINITIALISATION DES ID //
		donnee1.compteur_zero();
		donnee2.compteur_zero();
		donnee3.compteur_zero();
		
		n1.compteur_zero();
		n2.compteur_zero();
		n3.compteur_zero();
		
		utilisateur0.compteur_zero();
		//////////////////////////////
		
		//QUESTION3 //
		System.out.println("Question 3 : ");
		
		Graphe<NoeudsSysteme, Edge> g2 = new Graphe <>(Edge.class);
		
		// CREATION DES DONNEES A PLACER //
		Donnees don1 = new Donnees(40);
		ArrayList<Donnees> listeDonnee = new ArrayList<Donnees>();
		listeDonnee.add(don1);
		g2.ajouterDonneesAMettre(listeDonnee);
		
		NoeudsSysteme noeud1 = new NoeudsSysteme(50);
		NoeudsSysteme noeud2 = new NoeudsSysteme(40);
		NoeudsSysteme noeud3 = new NoeudsSysteme(40);
		NoeudsSysteme noeud4 = new NoeudsSysteme(40);
		
		// ON AJOUTE LES NOEUDS DANS LE GRAPHE //
		ArrayList<NoeudsSysteme> listeNoeudsSys2 = new ArrayList<NoeudsSysteme>();
		listeNoeudsSys2.add(noeud1);
		listeNoeudsSys2.add(noeud2);
		listeNoeudsSys2.add(noeud3);
		listeNoeudsSys2.add(noeud4);
		
		g2.ajouterDesNoeudsAuGraphe(listeNoeudsSys2);
		
		
		// ON LES RELIE ENTRE EUX //
		//LE NOEUD 1 EST RELIE AU NOEUD 2 //
		noeud1.ajouterIdNoeud(noeud2);
		noeud2.ajouterIdNoeud(noeud1);
				
		//LE NOEUD 2 EST RELIE AU NOEUD 3 //
		noeud2.ajouterIdNoeud(noeud3);
		noeud3.ajouterIdNoeud(noeud2);
		
		//LE NOEUD 3 EST RELIE AU NOEUD 4 //
		noeud3.ajouterIdNoeud(noeud4);
		noeud4.ajouterIdNoeud(noeud3);
		
		//ON AJOUTE AU GRAPHE LES NOEUDS N1, N2, N3 //
		g2.addVertex(noeud1);
		g2.addVertex(noeud2);
		g2.addVertex(noeud3);
		g2.addVertex(noeud4);
		
		//ON AJOUTE LES ARCS EN PRECISANT LES DEUX NOEUDS QU'ILS RELIENT //
		DefaultWeightedEdge edgen1n2 = (DefaultWeightedEdge)g2.addEdge(noeud1, noeud2);
		DefaultWeightedEdge edgen2n3 = (DefaultWeightedEdge)g2.addEdge(noeud2, noeud3);
		DefaultWeightedEdge edgen3n4 = (DefaultWeightedEdge)g2.addEdge(noeud3, noeud4);
		
		//ON DONNE AUX ARCS UN POIDS DE 1 //
		g2.setEdgeWeight(edgen1n2, 1);
		g2.setEdgeWeight(edgen2n3, 1);
		g2.setEdgeWeight(edgen3n4, 1);
		
		//ON CREE UNE LISTE QUI COMPREND LES DONNEES A PLACER //
		ArrayList<Integer> l_donnees = new ArrayList<Integer>();
		l_donnees.add(don1.getId_donnees());
		
		// ON CREE NOS DEUX UTILISATEURS //
		Utilisateurs utilisateur1 = new Utilisateurs(l_donnees,1);
		Utilisateurs utilisateur2 = new Utilisateurs(l_donnees,4);
		
		// ON AJOUTE LES DEUX UTILISATEURS CREES DANS UNE LISTE D'UTILISATEURS //
		ArrayList<Utilisateurs> listeUtilisateurs = new ArrayList<Utilisateurs>();
		listeUtilisateurs.add(utilisateur1);
		listeUtilisateurs.add(utilisateur2);
		
		// ON AJOUTE AU GRAPHE LES UTILISATEURS EN TEMPS QUE NOEUDS //
		g2.addVertex(utilisateur1);
		g2.addVertex(utilisateur2);
		
		// ON RELIE LES UTILISATEURS AU NOEUD SYSTEME AUQUEL ILS ONT ACCES //
		DefaultWeightedEdge edgeU1 = (DefaultWeightedEdge)g2.addEdge(utilisateur1, noeud1);
		g.setEdgeWeight(edgeU1, 2);
		DefaultWeightedEdge edgeU2 = (DefaultWeightedEdge)g2.addEdge(noeud4,utilisateur2);
		g.setEdgeWeight(edgeU2, 2);
		
		// ON AJOUTE LA LISTE D'UTILISATEURS AU GRAPHE //
		g2.ajouterUtilisateursAuGraphe(listeUtilisateurs);
		
		// ON APPELLE LA METHODE QUI PERMET DE PLACER UNE DONNEE DEMANDEE PAR DEUX UTILISATEURS //
		g2.placerDonnee2Utilisateurs(utilisateur1,utilisateur2,don1);
		
		g2.affichageNoeudGraphe();
		
	}
}


