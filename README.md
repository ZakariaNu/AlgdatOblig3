# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Zakariya Ahmed Nur Gutale S362082, s362082@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å kopiere kode som var allerede laget fra kompendiet der jeg endret riktige pekere til forelder noden.

I Oppgave 2 så skal returnere antall forekomster av verdi i treet. Traverserer Nodene, så bruker jeg en 'comp.compare'. hvis verdien er mindre så går den mot venstre. Hvis ikke, til høyere. når vi har funnet noden, inkrementeres den.

I Oppgave 3 så bruker en loop inspirert/basert på kompendie 5.1.7. Den går som følgende: Hvis p.venstre ikke er null, vil p bli settet som p.neste siden det ikke er bladnoden. Samme med høyre og returnerer p tilslutt.

I Oppgave 3 b) Brukte jeg en framgangsmåte som lyder som følgende:" Hvis p ikke har en forelder (p er rotnoden), så er p den siste i postorden.
Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
Hvis p er venstre barn til sin forelder f, gjelder:
     -Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
     -Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot."

- Framgangsmåten funnet i Kompendiet.

I Oppgave 4 så starter med å finne den første noden p i postorden. Deretter vil (f.eks. i en while-løkke) setningen: p = nestePostorden(p); gi den neste. Osv. til p blir null. Hvis noden ikke er null, skriver den ut oppgaven altså verdiene i nodene. i den rekurisive metoden, trenger vi et basistilfelle. Returnerer hvis noden er tom. Så traverserer den venstre subtreet også det høyre så tilslutt skrive ut oppgaven.

