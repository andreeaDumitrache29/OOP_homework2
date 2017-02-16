/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa folosita pentru a abstractiza tipurile de nod.
 *         <p>
 *         Aceasta este clasa cu care se va contrui graful
 *
 */
public abstract class Nod {
	/**
	 *Numele nodului
	 */
	protected String Name;
	/**
	 * id-ul nodului
	 */
	protected int id;
	/**
	 * Versiunea nodului
	 */
	protected int Version;
	/**
	 * lista de adiacenta a nodului
	 */
	protected DataTypes adjacencyList;

	/**
	 * 
	 * @return Numele nodului
	 */
	public String getName() {
		return Name;
	}

	/**
	 * 
	 * @param name
	 *            noul nume al nodului
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * 
	 * @return id-ul nodului
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            noul id al nodului
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Versiunea nodului
	 */
	public int getVersion() {
		return Version;
	}

	/**
	 * 
	 * @param version
	 *            noua versiunea a nodului
	 */
	public void setVersion(int version) {
		Version = version;
	}

	/**
	 * 
	 * @return lista de adiacenta a nodului
	 */
	public DataTypes getAdjacencyList() {
		return adjacencyList;
	}

	/**
	 * 
	 * @param adjacencyList
	 *            noua lista de adiacenta a nodului
	 */
	public void setAdjacencyList(DataTypes adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	/**
	 * Creeaza lista de adiacenta corespunzator versiunii nodului
	 * <p>
	 * Pentru a facilita instantierea diferitelor tipuri de lista, ma folosesc
	 * de un factory.
	 */
	public void CreateAdjacencyList() {
		/**
		 * Instantieze factory
		 */
		DataTypesFactory factory = DataTypesFactory.getInstance();

		/**
		 * In functie de versiune, vom apela corespunzator functia getDAtaType
		 * din factory care returneaza un obiect de tipul List, Array sau MySet
		 */
		switch (Version) {
		case 1:
			adjacencyList = factory.getDataType("LIST");
			break;
		case 2:
			adjacencyList = factory.getDataType("ARRAY");
			break;
		case 3:
			adjacencyList = factory.getDataType("SET");
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @param node
	 *            nodul care va fi adaugat in lista de adiacenta a nodului
	 *            curent
	 */
	public abstract void addNod(Nod node);

	/**
	 * 
	 * @return un string care reprezinta tipul de nod: NodA, NodB sau NodC
	 */
	public abstract String GetNodeType();
}
