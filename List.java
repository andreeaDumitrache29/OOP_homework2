import java.util.LinkedList;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa folosita pentru a reprezenta lista
 *         <p>
 *         Am folosit in implementare clasa LinkedList
 */
public class List implements DataTypes {
	/**
	 * Lista
	 */
	private LinkedList<Nod> list;

	/**
	 * Constructorul care instantiaza lista
	 */
	public List() {
		list = new LinkedList<>();
	}

	/**
	 * Adauga un element in lista
	 */
	@Override
	public void Add(Nod node) {
		list.add(node);
	}

	/**
	 * Sterge un element din lista
	 */
	@Override
	public void Del(Nod node) {
		list.remove(node);

	}

	/**
	 * Verifica daca nodul dat ca parametru apartine listei
	 */
	@Override
	public boolean Contains(Nod node) {
		return list.contains(node);
	}

	/**
	 * Returneaza elementul de pe pozitia index din lista
	 */
	@Override
	public Nod getElement(int index) {
		if (list.isEmpty() == true)
			return null;
		return list.get(index);
	}

	/**
	 * Returneaza numarul de elemente din lista
	 */
	@Override
	public int getSize() {
		return list.size();
	}

	/**
	 * Returneaza un string care reprezinta tipul clase, List
	 */
	@Override
	public String toString() {
		return "LIST";
	}

}
