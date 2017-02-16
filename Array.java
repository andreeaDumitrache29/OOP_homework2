import java.util.ArrayList;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa folosita pentru a reprezenta un Vector
 *         <p>
 *         Am folosit in implementare clasa ArrayList
 *
 */
public class Array implements DataTypes {
	/**
	 * Vectorul
	 */
	private ArrayList<Nod> array;

	/**
	 * Constructor care instantiaza vectorul
	 */
	public Array() {
		array = new ArrayList<>();
	}

	/**
	 * Adauga un nod, primt ca parametru, in vector
	 */
	@Override
	public void Add(Nod node) {
		array.add(node);
	}

	/**
	 * Sterge un nod, primit ca parametru, din vector
	 */
	@Override
	public void Del(Nod node) {
		array.remove(node);
	}

	/**
	 * Returneaza elementul de pe pozitia index din vector
	 */
	@Override
	public Nod getElement(int index) {
		return array.get(index);
	}

	/**
	 * Returneaza numarul de elemente din vector
	 */
	@Override
	public int getSize() {
		return array.size();
	}

	/**
	 * Verifica daca un nod, primit ca paramtru, se afla in vector
	 */
	@Override
	public boolean Contains(Nod node) {
		return array.contains(node);
	}

	/**
	 * Returneaza un string cu tipul clasei, Array
	 */
	@Override
	public String toString() {
		return "ARRAY";
	}

}
