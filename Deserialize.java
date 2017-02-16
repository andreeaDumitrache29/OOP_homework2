import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa este folosita pentru a deserializa un graf pe baza unui fisier
 *         dat si pentru a scrie fisierul de log aferent deserializarii
 *
 */
public class Deserialize {
	/**
	 * deserialize_reader = folosit pentru a citi din fisierul dat
	 */
	private BufferedReader deserialize_reader;
	/**
	 * deserialize_writer = folosit pentru a scrie in fisierul de log
	 */
	private BufferedWriter deserialize_writer;

	/**
	 * Constructor
	 */
	public Deserialize() {

	}

	/**
	 * 
	 * @param graph
	 *            graful ce urmeaza a fi creat
	 * @param deserialize_file
	 *            fisierul pe baza carui vom reconstrui graful
	 * @param file_name
	 *            numele fisierului pe baza caruia vom contrui fisierul de log
	 * @param a
	 *            vector cu setarile nodului de tip NodA
	 * @param b
	 *            vector cu setarile nodului de tip NodB
	 * @param c
	 *            vector cu setarile nodlui de tip NodC
	 * @param k
	 *            pozitia curenta in vectori
	 * @throws IOException
	 *             in caz ca se genereaza exceptii la scrierea in fisier
	 *             <p>
	 *             Metoda reconstruieste un graf pe baza fisierului dat si scrie
	 *             rezultatele schimbarii de versiuni in fisierul de log
	 *             corespunzator
	 */
	public void DeserializeGraph(Graph graph, FileReader deserialize_file, String file_name, int[] a, int[] b, int[] c,
			int k) throws IOException {
		/**
		 * initializam reader-ul aferent clasei
		 */
		deserialize_reader = new BufferedReader(deserialize_file);
		/**
		 * linia curenta
		 */
		String deserialize = deserialize_reader.readLine();
		/**
		 * construim numele fisierului de log
		 */
		String out_deserialize = "Deserialize_";
		out_deserialize += file_name;
		out_deserialize += "_CAST.log";
		/**
		 * declaram fisierul de log si intializam writer-ul aferent clasei
		 */
		FileWriter writer = new FileWriter(out_deserialize);
		deserialize_writer = new BufferedWriter(writer);

		Nod node = null;
		int id = 0;
		int ok = 0;
		/**
		 * Citim cat timp mai avem linii in fisier
		 */
		while (deserialize != null) {
			/**
			 * Despartim linia curenta in cuvinte dupa spatiu si eliminam
			 * spatiile in plus
			 */
			String[] t = deserialize.split(" ");
			for (int i = 0; i < t.length; i++) {
				t[i] = t[i].trim();
			}

			/**
			 * Daca linia curenta corespunde unui tag de tip Object atunci
			 * construim un nou nod ce urmeaza safie inserat in graf
			 */
			if (t[0].contentEquals("<Object")) {
				/**
				 * Al patrulea cuvant de pe linie reprezinta id-ul nodului
				 * Separam dupa - si dupa " pentru a obtine numarul dorit pentru
				 * id
				 */
				String[] t3 = t[3].split("=");
				String[] aux = t3[1].split("\"");
				id = Integer.parseInt(aux[1]); // id of the node
				/**
				 * Al doilea cuvant din linie contine tipul nodului In functie
				 * de acest tip, vom instantia noul nod
				 */
				String[] t1 = t[1].split("=");

				if (t1[1].contentEquals("\"NodA\"")) {
					/**
					 * Verificam, inainte de instantiere, daca schimbarea
					 * setarilor pentru tipul de nod corespunzator nodului
					 * curent va produce o eroare sau nu. Variabila ok este
					 * folosita pentru a retine acest lucru -> este 1 in caz ca
					 * schimbarea nu s-a putut efectua cu succes, 0 in caz ca
					 * schimbarea se efectueaza cu succes si 2 in caz ca nu
					 * apare nicio schimbare. Dupa aceste verificari, se
					 * intantiaza corespunzator variabila nod, in functie de
					 * tipul nodului, determinat mai sus
					 */
					if (k == 0) { // nu s-a produs nicio schimbare de setari
						ok = 2;
					} else if (a[k - 1] > a[k]) {
						ok = 1; // nu putem trece la noua versiune
					} else if (a[k - 1] == a[k]) {
						ok = 2;
					} // versiune noua este egala cu cea precedenta
					node = new NodA(a[k]);
				} else if (t1[1].contentEquals("\"NodB\"")) {
					if (k == 0) { // nu s-a produs nicio schimbare de setari
						ok = 2;
					} else if (b[k - 1] > b[k]) { // nu putem trece la noua
													// versiune
						ok = 1;
					} else if (b[k - 1] == b[k]) {
						ok = 2; // versiune noua este egala cu cea precedenta
					}
					node = new NodB(b[k]);
				} else {
					if (k == 0) { // nu s-a produs nicio schimbare de setari
						ok = 2;
					} else if (c[k - 1] > c[k]) { // /nu putem trece la noua
													// versiune
						ok = 1;
					} else if (c[k - 1] == c[k]) {
						ok = 2; // versiune noua este egala cu cea precedenta
					}
					node = new NodC(c[k]);
				}
			} else if (t[0].contentEquals("<Reference")) {
				/**
				 * Daca am intalnit un tag de reference, atunci stim ca va
				 * trebui sa adaugam o legatura intre nodul curent si nodul al
				 * carui id se afla in tag-ul de reference. Deoarece
				 * serializarea este implementata in asa fel incat tag-urile de
				 * reference se afiseaza mereu primele, inaintea obiectelor inca
				 * nevizitate din lista nodului curent, stim sigur ca legatura
				 * se va face intre nodul curent si nodul din tag.
				 */

				/**
				 * Folosim acelasi principu ca ma sus pentru a obtine id-ul
				 * nodului din tag-ul de reference
				 */
				String[] t3_1 = t[3].split("=");
				String[] aux_1 = t3_1[1].split("\"");
				int new_id = Integer.parseInt(aux_1[1]);

				/**
				 * Parcurgem lista de noduri din graf. Cand gasim un nod cu
				 * id-ul egal cu cel al obiectului din tag-ul curent de
				 * reference, creem muchia dintre nodul curent si nodul
				 * descoperit.
				 */
				for (int i = 0; i < graph.getGraph().size(); i++) {
					if (graph.getGraph().get(i).getId() == new_id) {
						graph.addToAdjacencyList(node.getName(), graph.getGraph().get(i).getName());
					}
				}

			} else if (t[0].contains("Nume")) {
				/**
				 * Daca am gasit un tag de name, atunci putem seta numele
				 * nodului curent si sa il introducem in graf
				 */

				/**
				 * Numele nodului se va afla intre pozitia 6 din linie(unde se
				 * inchide tagul de Nume) si pozitia unde incepe tag-ul de Nume
				 */
				int n = t[0].indexOf("</Nume>");
				String name = t[0].substring(6, n);

				/**
				 * setam numele si id-ul nodului curent si il introducem in graf
				 */
				node.setName(name);
				node.setId(id);
				graph.AddToGraph(node);
				Display display = new Display();
				/**
				 * verificam variabila ok si afisam mesajul de fail cast sau de
				 * ok cast, in functie de rezultat. Afisarea o facem folosind
				 * metodele corespunzatoare din clasa clasa Display. In caz ca
				 * ok == 2 inseamna ca am trecut de o versiune la aceeasi
				 * versiune, deci nu trebuie sa afisam niciun mesaj in fisierul
				 * de log.
				 */
				if (ok == 0) {
					display.DisplayOKCast(node, deserialize_writer, a, b, c, k);
					deserialize_writer.write("\n");
				} else if (ok == 1) {
					display.DisplayFailCast(node, deserialize_writer, a, b, c, k);
					deserialize_writer.write("\n");
				}
				ok = 0;
			}
			deserialize = deserialize_reader.readLine();
		}
		deserialize_writer.close();
		deserialize_reader.close();
		/**
		 * folosim metoda RemakeId pentru a asigna fiecarui nod id-ul
		 * corespunzator pozitiei pe care a fost inserat in graf
		 */
		graph.RemakeID();
	}
}
