package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        // fra kompendie med endringer for riktig forelder node.

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi, p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging


    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        // ** Lov med duplikater
        // finnet antall verdier i treet.

        // verdi == null  return 0
        if (verdi == null) {
            return 0;
        }
        // instans av antallet = 0
        int antallet = 0;

        Node<T> rotnode = rot;
        int cmp;

        // Traversere Nodene
        while (rotnode != null) {
            cmp = comp.compare(verdi, rotnode.verdi);
            if (cmp < 0) {  // mindre så går den mot venstre
                rotnode = rotnode.venstre;
            } else {
                if (verdi == rotnode.verdi) {
                    antallet++;    // funnet noden og inkrementerer
                }
                rotnode = rotnode.høyre;
            }

        }
        //returnere antallet.
        return antallet;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        // instansierer rotnoden
        Node<T> rot = p;


        // loop basert på kompendie 5.1.7
        while (true) {
            if (rot.venstre != null) {
                rot = rot.venstre;
            } else if (rot.høyre != null) {
                rot = rot.høyre;
            } else return rot;
        }

        //skal returnere første node postorden med p som rot.
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        //NestePostorden går slik:
        //
        Node<T> foreldre = p.forelder;

        //Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
        if (foreldre == null) {
            return null;
        }
        //Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
        if (p == foreldre.høyre) {
            return foreldre;
        }

        //Hvis p er venstre barn til sin forelder f, gjelder:
        //        - Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
        //        - Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.
        if(p == foreldre.venstre){
            if(foreldre.høyre == null){
                return foreldre;
            }
        }
        return førstePostorden(foreldre.høyre);


    }

    public void postorden(Oppgave<? super T> oppgave) {
        //Start med å finne den første noden p i postorden
        Node<T> node = førstePostorden(rot);
        oppgave.utførOppgave(node.verdi);
        //Deretter vil (f.eks. i en while-løkke) setningen: p = nestePostorden(p); gi den neste. Osv. til p blir null
        while (true){
            node = nestePostorden(node);
            //til p er null
            if (node == null){
                break;
            }
            //utføreoppgave
            oppgave.utførOppgave(node.verdi); // hvis noden ikke er null, skriver
            // den ut oppgaven altså verdiene i nodene,.
        }

    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
         // siden det er et rekkursivt funksjon trenger vi et basistilfelle

        if (p == null){
            return;
        }
        // kalle seg selv.
        postordenRecursive(p.venstre, oppgave);
        postordenRecursive(p.høyre, oppgave);

        //utføreoppgave
        oppgave.utførOppgave(p.verdi);

    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
