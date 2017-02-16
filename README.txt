	DUMITRACHE DANIELA ANDREEA
	321 CB
	TEMA 2

	Pentru a reprezenta cele 3 tipuri de noduri, NodA, NodB si NodC am ales sa implementez 
3 clase separate pentru fiecare, care sa mosteneasca o clasa abstracta, Nod. In clasa 
nod se afla campurile de id, Name si Version, specifice fiecarui nod, precum si lista 
de adiacenta a nodului, de tipul DataTypes. Pe langa getteri si setteri, clasa nod mai 
are o metoda CreateAdjacencyList() care instantiaza corespunzator lista de adiacenta cu set, 
vector sau lista, in functie de versiunea nodului. De asemenea, clasa nod contine si metodele 
abstracte addNod(Nod node), pentru a adauga un nod in lista de adiacenta a nodului curent, 
respectiv GetNodeType(), care returneaza un string cu tipul clasei. Aceste metode sunt 
implementate in fiecare dintre subclasele NodA, NodB si NodC.
	
	Am ales sa folosesc o clasa abstracta pentru a putea avea elemente de acelasi tip in graf.
Intrucat toate cele 3 tipuri de nod mostenesc clasa Nod, aceasta poate fi instantiata cu 
oricare dintre subtipurile NodA, NodB sau NodC, fiind astfel perfecta pentru constructia grafului.

	Pentru a implementa cele 3 tipuri de versiuni am construit o interfata, DataTypes, care 
este implementata de catre clasele Array, List si MySet. Aceasta interfata specifica 
operatiile de baza, care sun apoi implementate in mod specific de catre fiecare dintre 
cele 3 clase concrete. Aceste operatii sunt: Add(Node node), Del(Nod node), getElement(int index),
getSize(), Contains(Nod node).

	Pentru instantierea acestora am ales sa folosesc design pattern-ul de factory, 
implementat de catre clasa DataTypesFactory. Aceasta este implementata folosind 
design pattern-ul de singleton, pentru a fii instantiata o singura data. Clasa 
contine metoda getInstance(), pentru instantiere, si metoda getDataType(String listType) 
care, in functie de parametrul primit, returneaza o instanta de set, array sau list.

	Am ales aceasta implementare deoarece, in acest fel, nu voi fi constransa de modul de 
implementare al listei de adiacenta a nodului, metodele fiind aceleasi pentru toate cele
 3 tipuri de implementare. Astfel, tot ce trebuie sa fac este sa instantiez corespunzator 
lista in momentul in care o creez, urmand ca apelul de metode sa fie independent de implementare.

	Serializarea am ales sa o implementez ca o metoda specifica grafului deoarece este, in 
esenta, o parcurgere in adancime : SerializeRecursive(Nod node, FileWriter out_file, BufferedWriter
writer, int tab, int[] id). Astfel: node reprezinta nodul curent de serializat, out_file este fisierul 
unde se va scrie rezultatul serializarii, tab reprezinta numarul de tab-uri care trebuiesc puse in afisarea
curenta, iar id[] este un vector de care ma folosesc pentru a stii daca un nod a fost sau nu vizitat. 

	Incep prin a marca nodul curent ca viziat in vectorul id, adica fac 1 pe pozitia corespunzatoare 
id-ului nodului din vector. Apoi afisez informatiile corespunzatoare nodului din tagul <Object> , afisez 
tag-ul cu numele nodului si deschid tag-ul corespunzator listei de adiacenta,in functie de versiunea nodului.
In continuare, parcurg lista de adiacenta a nodului si afisez, in tagul de <Reference>, toate nodurile din lista 
care au fost deja vizitate. Am ales acesta metoda de afisare, deoarece este mult mai usor la deserializare sa 
refac legaturile, stiind ca un tag de <Reference> apartine in mod sigur nodului curent. Dupa aceasta, apelez 
recursiv functia de serializare pe nodurile nevizitate din lista nodului curent. La intoarcerea din recursivitate 
se vor inchide tag-urile de object si cele corespunzatoare listelor.

	Pentru afisari am creat o clasa separata, Display, pentru a nu incarca codul.
Aceasta este folosita atat la afisarile de la serializare, cat si la scrierea 
fisierului de log din cadrul deserializarii.

	De asemenea, am ales sa fac citirea instructiunilor din fisierul de input intr-o clasa separata, 
ConstructGraph, pentru a nu supraincarca main-ul. Aceasta clasa, folosind metoda 
CreateGraph(FileReader file, Graph graph), citeste din fisier linie cu linie, folosind un 
BufferedReader, si apeleaza metodele corespunzatoare in functie de operatiile citite.

	Pentru deserializare am creeat clasa Deserialize, care, folosind metoda 
DeserializeGraph(Graph graph, FileReader deserialize_file, String file_name, int[] a, int[] b, int[] c, int k), 
reface graful dat ca parametru, conform datelor din fisierul deserialize_file. Vectorii a, b si c au rolul de a 
mentine versiunile claselor NodA, NodB, NodC, iar k reprezinta pozitia curenta in vector. Am ales sa retin 
versiunile claselor folosind vectori pentru a putea avea acces usor la versiunea anterioara, aceasta fiind 
necesara in afisare mesajelor de ok, respectiv fail cast. String-ul name este folosit pentru a creea numele 
fisierului de log in care vor fi afisate informatiile despre cast-uri.

	Pentru deserializare: parcurg linie cu linie fisierul dat si verific structura liniei curente: 
Daca primul cuvand din linie est "<Object", atunci sti uca se deschide un obiect nou, deci trebuie 
creat un nou nod. Voi parsa restul cuvintelor pentru a obtine tipu de nod si id-ul acestuia si voi instantia
corenspunzator noul nod. De asemenea, inainte de instantiere, verific daca schimbarea setarilor va rezulta 
intr-un ok cast ( caz in care variabile ok ramane 0), intr-un fail cast(caz in care ok devine 1), sau setarile 
raman neschimbare(caz in care ok devine 2), pentru a putea afisa in fisierul de log mesajul corespunzator.

	Daca primul cuvant de pe linie este un tag de "<Reference", atunci stiu ca trebuie sa creez o legatura intre 
nodul curent si nodul al carui id se afla in cadrul tag-ului de reference(acest lucru este posibil deoarece, 
in serializare, am afisat intai toate referintele pentru un nod, apoi am continuat cu vecinii nodului care nu 
fusesera vizitati). Pentru aceatsa, parcurg lista de noduri din graf, si cand gasesc nodul cu id-ul dorit, creez o 
muchie intre acesta si ndoul curent. Daca linia curenta contine un tag de "</Nume>", atunci parsez linia pentru 
a obtin numele nodului, il setez si introduc nodul in graf. De asemenea, tot aici verific rezultatul schimbarii 
de versiune si scriu mesajele de OK cast si Fail cast in fisierul de log in caz de cast reusit/ nereusit. Daca
verisiunea ramane aceeasi nu afisez niciun mesja in fisier.

	Restul tag-urilor nu sunt analizate pentru ca nu ma influenteaza cu nimic in  implementare.
La finalul metodei se apeleaza metoda RemakeId specifica grafului, pentru a asigna fiecarui nod id-ul
corespunzator pozitiei pe care a fost inserat in graf.
