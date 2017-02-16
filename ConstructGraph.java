import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *         <p>
 *         Clasa folosita pentru a construi graful si pentru a citi din fisier
 *         operatiile ce urmeaza sa fie aplicate asupra grafului
 *
 */
public class ConstructGraph {
	/**
	 * reader = reader pentru citirea din fisierul de input
	 */
	private BufferedReader reader;
	/**
	 * writer = writer pentru a scrie in fisierele de serializare/ deserializare
	 */
	private BufferedWriter writer;

	/**
	 * constructor
	 */
	public ConstructGraph() {
	}

	/**
	 * 
	 * @param file
	 *            fisierul de input
	 * @param graph
	 *            graful pe care se vor aplica operatiile
	 * @throws IOException
	 *             in caz ca apar exaceptii la citirea/ scrierea din/ in fisier
	 *             <p>
	 *             Metoda citeste din fisier linie cu linie si apeleaza metodele
	 *             potrivite din clasele corespunzatoare in functie de rezulatul
	 *             citirii
	 */
	public void CreateGraph(FileReader file, Graph graph) throws IOException {
		/**
		 * actualizam reader-ul aferent clasei s -> linia curenta din fisier
		 */
		reader = new BufferedReader(file);
		String s = reader.readLine();
		/**
		 * Vectori in care vom retine versiunile implementarilor pentru ficare
		 * tip de nod, pentru a verifica daca schimbarea versiunii s-a efectuat
		 * cu succes sau nu
		 */
		int a[] = new int[50];
		int b[] = new int[50];
		int c[] = new int[50];
		/**
		 * index curent in nod
		 */
		int k = -1;
		String Name;
		String SecondName;
		String NodeType;
		FileWriter out_file;

		while (s != null) {
			/**
			 * despartim linia curenta in cuvinte si verificam primul cuvant,
			 * pentru a stii ce operatie avem de efectuat
			 */
			String[] tokens = s.split(" ");
			switch (tokens[0]) {
			case "Settings":
				/**
				 * Avem de efectuat o schimbae de versiune. Vom incarca noile
				 * versiuni in vectori, pentru a fi folosite ulterior in
				 * constructia noilor noduri
				 */

				/**
				 * incrementam index-ul curent si adaugam noile setari in
				 * vectorii de setari pe pozitia corespunzatoare
				 */
				k++;
				a[k] = Integer.parseInt(tokens[1]); // versiune pentru NodA
				b[k] = Integer.parseInt(tokens[2]); // versiune pentru NodB
				c[k] = Integer.parseInt(tokens[3]); // versiune pentru NodC
				break;
			case "Add":
				/**
				 * Avem de adaugat un nou nod in graf
				 */

				/**
				 * Retinem in variabilele NodeType si Name tipul nodului,
				 * respectiv numele asociat acestuia
				 */
				NodeType = tokens[1];
				Name = tokens[2];

				/**
				 * Verificam tipul de nod si instantiem corepsunzator nodul de
				 * inserat. Setam id-ul nodului in functie de pozitia lui din
				 * vectorul de noduri din graf(va fi inserat ultimul, deci id-ul
				 * va lua valoarea numarului curent de noduri). Inseram noul nod
				 * in graf, apoi parcurgem restul liniei si creem legaturi
				 * inntre noul nod si toate nodurile ale caror nume au fost
				 * date.
				 */
				if (NodeType.contentEquals("NodA")) {
					NodA nod = new NodA(Name, a[k]);
					nod.setId(Graph.numer_of_nodes);

					graph.AddToGraph(nod);
					for (int i = 3; i < tokens.length; i++) {
						graph.addToAdjacencyList(nod.getName(), tokens[i]);
					}
				} else if (NodeType.contentEquals("NodB")) {
					NodB nod = new NodB(Name, b[k]);
					nod.setId(Graph.numer_of_nodes);
					graph.AddToGraph(nod);

					for (int i = 3; i < tokens.length; i++) {
						graph.addToAdjacencyList(nod.getName(), tokens[i]);
					}
				} else {
					NodC nod = new NodC(Name, c[k]);
					nod.setId(Graph.numer_of_nodes);
					graph.AddToGraph(nod);
					for (int i = 3; i < tokens.length; i++) {
						graph.addToAdjacencyList(nod.getName(), tokens[i]);
					}
				}
				break;
			case "Del":
				/**
				 * Trebuie sa stergem un nod din graf citim in variabilele
				 * NodeType si Name tipul nodului de sters, respectiv numele
				 * acestuia si apelam metoda DeleteNode corespunzatoare grafului
				 */

				NodeType = tokens[1];
				Name = tokens[2];
				graph.DeleteNode(Name);
				break;
			case "AddM":
				/**
				 * Trebuie sa adaugam o muchie intre doua noduri. Citim in
				 * variabilele Name si SecondName numele nodurilor intre care
				 * trebuie sa adaugam muchia si apelam metoda addToAdjacencyLis
				 * corespunzatoare grafului, care va crea legatura dintre cele
				 * doua noduri
				 */
				Name = tokens[1];
				SecondName = tokens[2];
				graph.addToAdjacencyList(Name, SecondName);
				break;
			case "DelM":
				/**
				 * Trebuie sa stergem o muchie dintre doua noduri. Citim in
				 * variabilele Name si SecondName numele nodurilor dintre care
				 * trebuie sa stergem muchia si apelam metoda DeleteEdge
				 * corespunzatoare grafului, care va sterge legatura dintre cele
				 * doua noduri
				 */
				Name = tokens[1];
				SecondName = tokens[2];
				graph.DeleteEdge(Name, SecondName);
				break;
			case "Serialize":
				/**
				 * Trebuie sa serializam graful pornind intr-un nod pentru care
				 * ni se va da numele
				 * 
				 * Citim in variabila Name numele nodului din care va porni
				 * serializarea. In Stringul out citim numele fisierului unde se
				 * va scrie rezultatul serializarii. Se creeaza fisierul de
				 * output corespunzator si se instantiaza writer-ul aferent
				 * clasei
				 */
				Name = tokens[1];
				String out = tokens[2];
				out_file = new FileWriter(out);
				writer = new BufferedWriter(out_file);

				/**
				 * Cautam nodul cu numele dat in graf
				 */
				Nod node2 = null;
				for (Nod n : graph.getGraph()) {
					if (n.getName().contentEquals(Name)) {
						node2 = n;
						break;
					}
				}
				if (node2 == null) {
					/**
					 * Mesaj de eroare in caz ca ni s-a dat numele unui nod care
					 * nu exista in graf
					 */
					writer.write("No such node");
					return;
				}
				/**
				 * vector pentru parcurgerea in adancime folosit pentru a retine
				 * daca un nod a fost deja vizitat sau nu
				 */
				int[] v = new int[Graph.numer_of_nodes];
				/**
				 * apelam medota de serializare din graf
				 */
				graph.SerializeRecursive(node2, out_file, writer, 0, v);
				writer.close();
				break;
			case "Deserialize":
				/**
				 * INcepem prin a sterge din memerie toate nodurile continute in
				 * graf
				 */
				graph.Delete();
				/**
				 * instantiem clasa Deserialize, cu ajutorul careia vom reface
				 * graful
				 */
				Deserialize dez = new Deserialize();
				/**
				 * fisierul dat ca paramtetru, pe baza caruia vom reface graful
				 */
				FileReader deserialize_file = new FileReader(tokens[1]);
				/**
				 * Apelam metoda de DeserializeGraph di nclasa Deserialize, care
				 * reconstruieste graful pe baza fisierului dat
				 */
				dez.DeserializeGraph(graph, deserialize_file, tokens[1], a, b, c, k);
				break;
			default:
				break;
			}
			s = reader.readLine();
		}
		reader.close();
	}
}
