/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa este folosita pentru a reprezenta nodurile de tip A din graf
 *
 */
public class NodA extends Nod {

	/**
	 * 
	 * @param Version
	 *            versiunea nodului
	 *            <p>
	 *            Constructorul seteaza versiunea nodului confirm parametrului
	 *            primit si intializeaza corespunzator lista de adiacenta prin
	 *            metoda CreadeAdjacencyList
	 */
	public NodA(int Version) {
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
	 *            Constructorul seteaza numele si versiunea nodului conform
	 *            parametrului, intializeaza id-ul nodului cu -1 si creeaza
	 *            lista de adiacenta conform versiunii prin metoda
	 *            CreateAdjacencyList
	 */
	public NodA(String Name, int Version) {
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

	/**
	 * Returneaza un string cu tipul nodului
	 */
	@Override
	public String GetNodeType() {
		return "NodA";
	}

}
