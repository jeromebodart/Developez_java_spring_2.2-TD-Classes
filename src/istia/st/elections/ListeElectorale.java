package istia.st.elections;

/**
 * @author IN-LL-004
 *
 */
public class ListeElectorale {

	/**
	 * identité de la liste
	 */
	private int id;

	/**
	 * nom de la liste
	 */
	private String nom;

	/**
	 * nombre de voix de la liste
	 */
	private int voix;

	/**
	 * nombre de sièges de la liste
	 */
	private int sieges;

	/**
	 * indique si la liste est éliminée ou non
	 */
	private boolean elimine;
	
	private double moyenneListe;
	/**
	 * constructeur par défaut
	 */
	public ListeElectorale() {
	}

	/**
	 * 
	 * @param nom
	 *          String : le nom de la liste
	 * @param voix
	 *          int : son nombre de voix
	 * @param sieges
	 *          int : son nombre de sieges
	 * @param elimine
	 *          boolean : son état éliminé ou non
	 */
	public ListeElectorale(int id, String nom, int voix, int sieges, boolean elimine) {
		setId(id);
		setNom(nom);
		setVoix(voix);
		setSieges(sieges);
		setElimine(elimine);
	}

	/**
	 * 
	 * @return int : l'identifiant de la liste
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * initialise l'identifiant de liste
	 * 
	 * @param id
	 *          int : identifiant de la liste
	 * @throws ElectionsException
	 *           si id<1
	 */
	public void setId(int id) {
		if (id < 0) throw new ElectionsException("id entré négatif");
		this.id = id;
	}

	/**
	 * 
	 * @return String : le nom de la liste
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * initialise le nom de la liste
	 * 
	 * @param nom
	 *          String : nom de la liste
	 * @throws ElectionsException
	 *           si le nom est vide ou blanc
	 */
	public void setNom(String nom) {
		if (nom.length() == 0) throw new ElectionsException("Nom non renseigne");
		this.nom = nom.trim();
	}

	/**
	 * 
	 * @return int : le nombre de voix de la liste
	 */
	public int getVoix() {
		return this.voix;
	}

	/**
	 * initialise le nombre de voix de la liste
	 * 
	 * @param voix
	 *          int : le nombre de voix de la liste
	 * @throws ElectionsException
	 *           si voix<0
	 */
	public void setVoix(int voix) {
		if (voix < 0) throw new ElectionsException("nombre de voix entré négatif");
		this.voix = voix;
	}

	/**
	 * 
	 * @return int : le nombre de sièges de la liste
	 */
	public int getSieges() {
		return this.sieges;
	}

	/**
	 * fixe le nombre de sièges de la liste
	 * 
	 * @param sieges
	 *          int : le nombre de sièges de la liste
	 * @throws ElectionsException
	 *           si sieges<0
	 */
	public void setSieges(int sieges) {
		if (sieges < 0) throw new ElectionsException("nombre de sièges entré négatif");
		this.sieges = sieges;
	}

	/**
	 * 
	 * @return boolean : valeur du champ elimine
	 */
	public boolean isElimine() {
		return this.elimine;
	}

	/**
	 * 
	 * @param sieges
	 *          int
	 */
	public void setElimine(boolean elimine) {
		this.elimine = elimine;
	}
	
	

	public double getMoyenneListe() {
		return moyenneListe;
	}

	public void setMoyenneListe(double moyenneListe) {
		this.moyenneListe = moyenneListe;
	}

	/**
	 * 
	 * @return String : identité de la liste électorale
	 */
	public String toString() {
		return String.format("[%d,%s,%d,%d,%s]", new Object[] { id, nom, voix, sieges, elimine });
	}
}
