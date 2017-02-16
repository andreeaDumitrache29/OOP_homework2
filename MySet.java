import java.util.ArrayList;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa folostia pentru a reprezenta un set
 *         <p>
 *         Am implementat set-ul drept un ArrayList, in care am verificat
 *         existenta duplicatelor inainte de a introduce un nou element
 */
public class MySet implements DataTypes {
	/**
	 * Vectorul care va reprezenta set-ul
	 */
	private ArrayList<Nod> set;

	/**
	 * Instantierea set-ului
	 */
	public MySet() {
		set = new ArrayList<>();
	}

	/**
	 * Adauga un nou nod in set, Verificant existenta duplicatelor in prealabil
	 */
	@Override
	public void Add(Nod node) {
		if (set.contains(node))
			return;
		set.add(node);
	}

	/**
	 * Sterge un nod din set
	 */
	@Override
	public void Del(Nod node) {
		set.remove(node);
	}

	/**
	 * Returneaza elementul de pe pozitia index din set
	 */
	@Override
	public Nod getElement(int index) {
		return set.get(index);
	}

	/**
	 * Returneaza numarul de elemente din set
	 */
	@Override
	public int getSize() {
		return set.size();
	}

	/**
	 * Verifica daca nodul dat ca parametru este continut in set
	 */
	@Override
	public boolean Contains(Nod node) {
		return set.contains(node);
	}

	/**
	 * Returneaa un string care reprezinta tipul clase, set
	 */
	@Override
	public String toString() {
		return "SET";
	}

}
