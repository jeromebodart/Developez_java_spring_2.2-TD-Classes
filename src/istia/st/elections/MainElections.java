package istia.st.elections;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MainElections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int nbSieges = 0;
		System.out.println("le nombre de sièges à pourvoir :");
		boolean saisieOk = false;
		do {
			try {
					nbSieges = sc.nextInt();
					if(nbSieges > 0) saisieOk=true;
					else System.out.println("Erreur : tapez un nombre entier >0");
			}
			catch(Exception e) {
				System.out.println("Erreur : tapez un nombre entier >0");
				sc.nextLine();
			}
		}while(saisieOk != true );
		boolean elimine = false;
		int sieges = 0;
		int id = 0;
		int nbVoix = 0;
		ArrayList<ListeElectorale> myList = new ArrayList<ListeElectorale>();
		do{
			try {
				saisieOk=false;
				System.out.println("Nom de la liste : ");
				String nom= sc.next();
				if(!nom.equals("*")) {
					sc.nextLine();
					System.out.println("Nombre de voix : ");
					int voix = sc.nextInt();
					ListeElectorale tmp = new ListeElectorale(id,nom,voix, sieges, elimine);
					myList.add(tmp);
					id++;
					nbVoix+= voix;
					nbSieges += sieges;
				} else {
					saisieOk=true;
				}
				sc.nextLine();
			}
			catch (ElectionsException e) {
				System.out.println(e.toString());
			}
			catch (Exception inputMismatchException ) {
				System.out.println("Vous avez mal rempli une des ressources demandée");
			}
		}while(saisieOk != true);
		sc.close();
		if(myList.size()==0) {
			System.out.println("Pas de listes électorales");
			System.exit(0);
		}
		if(nbVoix==0) {
			System.out.println("Total de nombre de voix égal à 0 !");
			System.exit(0);
		}
		int nbVoixUtiles = 0;
		// calcul des voix utiles
		 for(ListeElectorale l : myList) {
			 if((double)l.getVoix()/nbVoix < 0.05) l.setElimine(true);
				else {
					l.setElimine(false);
					nbVoixUtiles = nbVoixUtiles +l.getVoix();
				}	
		 }
		// y-a-t-il des listes non éliminées ?
		if(nbVoixUtiles == 0) {
			System.out.println("Erreur : toutes les listes ont été éliminées");
			System.exit(0);		
		}
		 // répartition des sièges au quotient 
		double quotientElectoral;
		int nbSiegesPourvus;
		quotientElectoral = (double)nbVoixUtiles / nbSieges;
		nbSiegesPourvus = 0;
		for(ListeElectorale l : myList) {
			if(!l.isElimine()) {
				l.setSieges((int) Math.floor((double)l.getVoix()/quotientElectoral));
				l.setMoyenneListe((double)l.getVoix()/ (l.getSieges()+ 1));
				nbSiegesPourvus = nbSiegesPourvus + l.getSieges();
			}
			else {
				l.setSieges(0);
			}
		}
		 // répartition des sièges restants à la plus forte moyenne 115. 
		// 1 siège est attribué à chaque tour de boucle
		int Max = 0;
		for (int Siege = 0; Siege <= nbSieges - nbSiegesPourvus - 1; Siege++) {
			// recherche de la liste ayant la + forte moyenne
			double moyenneMax = -1;
			for(ListeElectorale l : myList) {
				if(!l.isElimine() & l.getMoyenneListe() > moyenneMax) {
					moyenneMax = l.getMoyenneListe();
					Max = l.getId();
				}
			}
			// on attribue 1 siège à la liste de + forte moyenne
			myList.get(Max).setSieges(myList.get(Max).getSieges()+ 1);
			// et on change sa moyenne
			myList.get(Max).setMoyenneListe((double)myList.get(Max).getVoix() / (myList.get(Max).getSieges() + 1));
		}
		
		// tri des listes
		myList.sort(new CompareListesElectorales());
			// affichage résultats sans tri 
		for(ListeElectorale l : myList) {
				if(l.isElimine()) {
					System.out.println("La liste " + l.getNom() + "a été éliminée");
				}
				else {
					System.out.println("La liste " + l.getNom() + " a obtenu " + l.getSieges() + " siege(s)");
				}
			}
		}	
			
	}

//classe de comparaison de listes électorales
class CompareListesElectorales implements Comparator<ListeElectorale> {
	//comparaison de deux listes électorales selon le nombre de voix
		public int compare(ListeElectorale listeElectorale1, ListeElectorale listeElectorale2) {
	//on compare les voix de ces deux listes
			if(listeElectorale1.getVoix() < listeElectorale2.getVoix()) return 1;
			else if (listeElectorale1.getVoix() > listeElectorale2.getVoix()) {
				return -1;
			} else {
				return 0;
			}
		}
	}

//on utilisera la classe [ListeElectorale] pour représenter une liste candidate ;
//• l'application demandera au clavier les informations suivantes :
//• le nombre de sièges à pourvoir ;
//• les noms et voix des listes. On ne sait pas à priori combien il y a de listes. La fin des listes sera signalée par un nom égal
//à la chaîne "*" ;
//• parce qu'on ne connaît pas à priori le nombre de listes, celles-ci seront tout d'abord mémorisées dans un objet de type
//[ArrayList]. Puis, lorsque toutes les listes auront été saisies, elles seront transférées dans un tableau de listes ;
//• les résultats seront affichés par ordre décroissant du nombre de sièges obtenus ;
//• le programme signalera une erreur et s'arrêtera dans les deux cas suivants :
//◦ il n'y a aucune liste ;
//◦ le total des voix obtenues par les différentes listes vaut 0 ;