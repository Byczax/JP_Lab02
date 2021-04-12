# Laboratorium 2, TN 11:00

Podczas laboratorium należy rozwiązać problem optymalnego doboru repertuaru muzycznego granego podczas imprez okolicznościowych.
Zakładamy, że lista dostępnych utworów muzycznych jest znana. Jest ona zapisana w pliku w postaci tabelarycznej o schemacie jak niżej:

Nr utworu;Rodzaj utworu;Czas utworu
1;pop;180
2;rock;150
...

Pierwsza linia w tym pliku to linia nagłówka, w kolejnych liniach pojawiają się dane. Kolumny rozdzielane są znakiem średnikami. Nr utworu to unikalny numer przypisany do danego utworu (dla uproszczenia tytuł, artystę i inne metadane pomijamy). Rodzaj utworu przeznaczony jest na informację o gatunku muzycznym, do którego utwór został zaklasyfikowany (należy przyjąć, że lista kategorii jest zamknięta), czas utworu to wyrażona w sekundach długość utworu.
Zakładamy, że preferencje odnośnie gatunków muzycznych uczestników imprezy są znane. Są one zapisane w pliku w postaci tabelarycznej o schemacie jak niżej:

```txt
Nr uczestnika;Preferencje 
1;pop,10|rock,90
2;rock,50|blues,50
3;rock,100
...
```

Pierwsza linia w tym pliku to linia nagłówka, w kolejnych liniach pojawiają się dane. Kolumny rozdzielane są znakiem średnikiem. Pierwsza kolumna zawiera unikalne numery uczestników imprezy, druga kolumna zawiera listę ich preferencji w postaci par rozdzielanych znakiem pionowej beleczki. Pierwszy element każdej z par to rodzaj utworu, drugi zaś to jego waga odzwierciedlająca w procentach poziom tym rodzajem przez uczestnika.
Zakładamy, że czas trwania danej imprezy okolicznościowej jest parametrem przekazywanym do programu. Program ma wygenerować listę utworów do odegrania na tej imprezie wg wybieralnych kryteriów:

* maksymalizując "zadowolenie"
* minimalizując "niezadowolenie"

Miarą "zadowolenia" może być suma, jaką uzyska się po dodaniu ilorazów: ("waga odegranego" - "waga minimalna")/ "suma wag" - wyliczanych z preferencji każdego z uczestników indywidualnie dla każdego odegranego utworu.
Miarą "niezadowolenia" może być suma, jaką uzyska się po dodaniu ilorazów: ("waga maksymalna" - "waga odegranego") / "suma wag" - wyliczanych z preferencji każdego z uczestników indywidualnie dla każdego odegranego utworu.
Jeśli razem wzięta długość wszystkich dostępnych utworów jest mniejsza od długości spotkania mają być dopuszczalne ponowne odtworzenia (nie więcej niż dwa, a jeśli to nie wystarczy, nie więcej niż trzy itd.).
Znalezione rozwiązanie, tj. listę utworów do odegrania oraz wartość "zadowolenia" bądź "niezadowolenia" należy wypisać na ekranie.
Pozostałe szczegóły mają być zgodne z ustaleniami poczynionymi na początku zajęć.
