Aby otworzyæ projekt nale¿y:
1. w³¹czyæ aplikacjê "NetBeans"
2. Wybraæ zak³adkê Plik i wybraæ opcjê Open Projekt - Otwórz Projekt.
3. Wybraæ lokalizacjê w której znajduje siê projekt i klikn¹æ Open Projekt.

G³ówn¹ klas¹ projektu jest klasa Frame znajduj¹ca siê w pliku o tej samej nazwie


Aby stworzyæ bazê danych nale¿y w sqlite3 utworzyæ bazê danych o nazwie "Projekty"
lub jakiejkolwiek innej ale wtedy nale¿y pamiêtaæ o tym aby zmieniæ nazwê bazy
w metodzie StartConnect w klasie ProjektyBaza.

Poni¿ej podajê kod do stworzenia tabeli:
CREATE TABLE Projekty (
    Projekt_id INT           CONSTRAINT Pr_Projekt_id_nn NOT NULL,
    Nazwa      VARCHAR (25)  CONSTRAINT Pr_Nazwa_nn NOT NULL,
    Adres      VARCHAR (255),
    Opis       VARCHAR (12),
    CONSTRAINT Pr_id_pk PRIMARY KEY (
        Projekt_id
    )
);


Wszystkie metody w Programie s¹ opisane w komentarzach nad nimi.