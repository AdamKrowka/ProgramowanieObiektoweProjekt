Aby otworzy� projekt nale�y:
1. w��czy� aplikacj� "NetBeans"
2. Wybra� zak�adk� Plik i wybra� opcj� Open Projekt - Otw�rz Projekt.
3. Wybra� lokalizacj� w kt�rej znajduje si� projekt i klikn�� Open Projekt.

G��wn� klas� projektu jest klasa Frame znajduj�ca si� w pliku o tej samej nazwie


Aby stworzy� baz� danych nale�y w sqlite3 utworzy� baz� danych o nazwie "Projekty"
lub jakiejkolwiek innej ale wtedy nale�y pami�ta� o tym aby zmieni� nazw� bazy
w metodzie StartConnect w klasie ProjektyBaza.

Poni�ej podaj� kod do stworzenia tabeli:
CREATE TABLE Projekty (
    Projekt_id INT           CONSTRAINT Pr_Projekt_id_nn NOT NULL,
    Nazwa      VARCHAR (25)  CONSTRAINT Pr_Nazwa_nn NOT NULL,
    Adres      VARCHAR (255),
    Opis       VARCHAR (12),
    CONSTRAINT Pr_id_pk PRIMARY KEY (
        Projekt_id
    )
);


Wszystkie metody w Programie s� opisane w komentarzach nad nimi.