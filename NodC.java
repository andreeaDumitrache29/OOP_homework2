/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 * 		Clasa este folosita pentru a simula nodurile de tip C din graf
 */
public class NodC extends Nod {
	/**
	 * 
	 * @param Version
	 *            versiunea nodului
	 *            <p>
	 * 			Constructorul seteaza versiunea nodului confirm parametrului
	 *            primit si intializeaza corespunzator lista de adiacenta prin
	 *            metoda CreadeAdjacencyList
	 */
	public NodC(int Version) {
		this.Version = Version;
		this.CreateAdjacencyList();
	}

	/**
	 * 
	 * @param Name
	 *            numele nodului
	 * @param Version
	 *            versiunea nodului
	 *            <p>
	 * 			Constructorul seteaza numele si versiunea nodului conform
	 *            parametrului, intializeaza id-ul nodului cu -1 si creeaza
	 *            lista de adiacenta conform versiunii prin metoda
	 *            CreateAdjacencyList
	 */
	public NodC(String Name, int Version) {
		this.Name = Name;
		this.Version = Version;
		this.id = -1;
		this.CreateAdjacencyList();
	}

	/**
	 * Adauga un nod in lista de adiacenta a nodului curent
	 */
	@Override
	public void addNod(Nod node) {
		this.adjacencyList.Add(node);
	}

	@Override
	/**
	 * Returneaza un string cu tipul nodului
	 */
	public String GetNodeType() {
		return "NodC";
	}

}
