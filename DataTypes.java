/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Interfata pentru a reprezenta un tip abstract de date
 *         <p>
 *         Folosind aceasta interfata voi reprezenta lista de adiacenta dintr-un
 *         nod, fara a fi nevoita sa tin cont daca este implementata folosind
 *         list, set sau array
 */
public interface DataTypes {
	/**
	 * 
	 * @param node
	 *            nodul care va fi adaugat in lista de adiacenta
	 */
	public void Add(Nod node);

	/**
	 * 
	 * @param node
	 *            nodul care va fi sters din lista de adicenta
	 */
	public void Del(Nod node);

	/**
	 * 
	 * @param index
	 *            pozitia pe care dorim sa vedem ce element se afla
	 * @return elementul de pe pozitia index
	 */
	public Nod getElement(int index);

	/**
	 * 
	 * @return numarul de elemente din lista de adiacenta
	 */
	public int getSize();

	/**
	 * 
	 * @param node
	 *            nodul care dorim sa vedem daca se afla in lista de adiacenta
	 * @return rezultatul cautarii
	 */
	public boolean Contains(Nod node);
}
