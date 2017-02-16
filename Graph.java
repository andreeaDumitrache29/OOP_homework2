import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa folosita pentru a reprezenta un graf
 *
 */
public class Graph {
	/**
	 * Vector in care se retin nodurile introduse in graf
	 */
	private ArrayList<Nod> graph;
	/**
	 * Numarul de noduri din graf
	 * <p>
	 * Initial este 0
	 * <p>
	 * Variabila este statica pentru a ramane aceeasi pentru toate instantele
	 */
	 static int numer_of_nodes = 0;

	/**
	 * Constructor
	 * <p>
	 * Instantiaza vectorul in care se retin nodurile din graf
	 */
	public Graph() {
		graph = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return lista de noduri din graf
	 */
	public ArrayList<Nod> getGraph() {
		return graph;
	}

	/**
	 * 
	 * @param graph noua lista de adiacenta a grafului
	 */
	public void setGraph(ArrayList<Nod> graph) {
		this.graph = graph;
	}

	/**
	 * 
	 * @param node
	 *            nodul care va fi adaugat in graf
	 *            <p>
	 *            Se adauga nodul in vectorul grafului si se incrementeaza
	 *            numarul total de noduri
	 */
	public void AddToGraph(Nod node) {
		graph.add(node);
		numer_of_nodes++;
	}

	/**
	 * Asigneaza fiecarul nod id-ul corespunzator pozitiei pe care se afla in
	 * vectorul de noduri din graf
	 * 
	 */
	void RemakeID() {
		for (int i = 0; i < numer_of_nodes; i++) {
			graph.get(i).setId(i);
		}
	}

	/**
	 * Sterge toate nodurile din graf
	 * <p>
	 * Numarul de noduri din graf devine 0
	 */
	public void Delete() {
		graph.removeAll(graph);
		numer_of_nodes = 0;
	}

	/**
	 * 
	 * @param name1
	 *            Numele primul nod
	 * @param name2
	 *            Numele celui de-al doiela nod
	 *            <p>
	 *            Metoda adauga nodul cu numele name2 in lista de adiacenta a
	 *            nodului cu numele name1 si invers.
	 *            <p>
	 *            Se cauta nodul al carui nume corespunde cu primul parametru,
	 *            iar apoi, nodul al carui nume corespunde celui de-al doilea
	 *            parametru.
	 *            <p>
	 *            Dupa ce au fost gasite cele doua noduri, se adauga unul in
	 *            lista de adiacenta a celuilalt.
	 */
	public void addToAdjacencyList(String name1, String name2) {
		for (Nod node : graph) {
			if (node.getName().contentEquals(name1)) {
				for (Nod n : graph) {
					if (n.getName().contentEquals(name2)) {
						node.addNod(n);
						n.addNod(node);
						break;
					}
				}
				break;
			}
		}
	}

	/**
	 * 
	 * @param name
	 *            numele nodului ce urmeaza a fi sters
	 *            <p>
	 *            metoda sterge un nod din graf
	 *            <p>
	 *            se cauta nodul cu numele corespunzator in vectorul de noduri
	 *            din graf
	 *            <p>
	 *            Dupa ce a fost gasit, parcurg din nou toate nodurile din graf
	 *            si sterg nodul de eliminat din listele tuturor nodurilor cu
	 *            care are legatura.
	 *            <p>
	 *            Dupa aceasta, refac id-urile nodurilor de dupa el, scazandu-le
	 *            cu 1, pentru ca id-ul unui nod sa corespunda in continuare
	 *            pozitiei in vectorul de noduri din graf al nodului
	 *            <p>
	 *            La final, elimin nodul si din vectorul de noduri din graf si
	 *            scad numarul total de elemente
	 */
	public void DeleteNode(String name) {
		for (Nod n : graph) {
			if (n.getName().contentEquals(name)) {
				for (Nod aux : graph) {
					if (aux.getAdjacencyList().Contains(n)) {
						aux.getAdjacencyList().Del(n);
					}
					if (aux.getId() > n.getId()) {
						aux.setId(aux.getId() - 1);
					}
				}
				graph.remove(n);
				break;
			}
		}
		numer_of_nodes--;
	}

	/**
	 * 
	 * @param name1
	 *            numele primului nod
	 * @param name2
	 *            numele celui de-al doilea nod
	 *            <p>
	 *            Metoda sterge muchia dintre doua noduri se cauta nodul cu
	 *            numele name1 in vectorul de noduri din graf
	 *            <p>
	 *            Dupa ce a fost gasit, se parcurg din nou toate nodurile din
	 *            graf pana se gaseste nodul cu numele nme2.
	 *            <p>
	 *            Se sterge nodul cu numele name2 din lista de adiacenta a
	 *            nodului name1, apoi invers
	 * 
	 */
	void DeleteEdge(String name1, String name2) {
		for (Nod node : graph) {
			if (node.getName().contentEquals(name1)) {
				for (Nod n : graph) {
					if (n.getName().contentEquals(name2)) {
						node.getAdjacencyList().Del(n);
						n.getAdjacencyList().Del(node);
						break;
					}
				}
				break;
			}
		}
	}

	/**
	 * 
	 * @param node
	 *            nodul de unde se incepe serializarea
	 * @param out_file
	 *            fisierul in care se va scrie rezultatul serializarii
	 * @param writer
	 *            writer-ul cu care vom scrie in fisier
	 * @param tab
	 *            desemneaza numarul de tab-uri din afisare
	 * @param id
	 *            un vector cu ajutorul caruia vom stii daca nodul a mai fost
	 *            sau nu vizitat
	 * @throws IOException
	 *             in caz ca apare o eroare la scrierea in fisier
	 *             <p>
	 *             Metoda realizeaza serializarea grafului folosind parcurgerea
	 *             in adancime
	 */
	public void SerializeRecursive(Nod node, FileWriter out_file, BufferedWriter writer, int tab, int[] id)
			throws IOException {
		/**
		 * Instanta a clasei display, folosita pentru afisari
		 */
		Display display = new Display();
		/**
		 * Marcheaza nodul curent ca fiind vizitat
		 */
		id[node.getId()] = 1;
		/**
		 * Afiseaza informatiile despre nodul curent din tag-ul de object
		 */
		display.DisplayObject(node, writer, tab);
		tab++;
		/**
		 * Afiseaza numele nodului curent
		 */
		display.DisplayName(node, writer, tab);
		/**
		 * Deschid tag-ul corespunzator listei de adiacenta a nodului curent
		 */
		display.DisplayList(node, writer, tab);
		tab++;

		/**
		 * Parcurge lista de adiacenta a nodului curent Afiseaza in tag-ul de
		 * reference informatiile despre nodurile deja vizitate din lista de
		 * adiacenta a nodului curent
		 */
		for (int i = 0; i < node.getAdjacencyList().getSize(); i++) {
			if (id[node.getAdjacencyList().getElement(i).getId()] == 1) {
				display.DisplayReferences(node.getAdjacencyList().getElement(i), writer, tab);
			}
		}
		/**
		 * Parcurge din nou lista de adiacenta a nodului
		 * Daca un nod nu a fost deja vizitat, apeleaza recursiv functia si pentru acesta
		 */
		for (int i = 0; i < node.getAdjacencyList().getSize(); i++) {
			if (id[node.getAdjacencyList().getElement(i).getId()] == 0) {
				SerializeRecursive(node.getAdjacencyList().getElement(i), out_file, writer, tab, id);
			}
		}
		tab--;
		/**
		 * Inchide tag-ul pentru lista de adicenta pentru nodul curent
		 */
		display.CloseListTags(node, writer, tab);
		tab--;
		writer.write("\n");
		for (int i = 0; i < tab; i++)
			writer.write("\t");
		tab--;
		/**
		 * Inchide tag-ul de object pentru nodul curent
		 */
		writer.write("</Object>");
		writer.write("\n");
	}
}
