package dades; // serveix per organitzar el codi en carpetes

/**
 * Llegir dades del fitxer
 * OJO
 * - format números
 *      - transformar d'estring a enter    = Integer.parseInt(10)
 *      - a double = Integer.parseDouble(10)
 *  - format paraules (maj i min)
 * 
 * MÈTODES ÚTILS
 *  - numeros format coma -> si s'ha de canviar
 *      - reemplaçar tots els punt i coma per punts    =     replaceAll(";";".")
 *  - llegir part de dades que ens interesa
 *      - dades.split(;) (crec que era aixi), agafarà fins al primer ;
 */

public class LlistaDadesUrb {
    //MÈTODES A FER (constructor, get del número d’elements de la llista, consultar les dades d’una certa posició, copia, toString)

    //atributs
    private DadesUrb[] poblacions;
    private int numPoblacions;

    /**
     * Constructor
     * Inicialitza la taula de mesures al segons els mum de linies (poblacions que vulgui afegir)
     * @param capacitat num linies / poblacions a afegir de fitxer
     */
    public LlistaDadesUrb (int capacitat) {
        poblacions = new DadesUrb[capacitat];   
        numPoblacions = 0;
    }

    /**
     * Mètode que retorna el número d'elements que hi ha a la llista
     * @return
     */
    public int numElemLlista() {
        return(numPoblacions);
    }

    /**
     * Getter: retorna les dades d'una certa posició de la llista
     * @pos numero de la posició la qual es vol consultar les dades
     * @return
     */
    public DadesUrb getDadesUrb(int pos) {
        if ((pos-1)>0 && pos < numPoblacions) {  //POS-1 pq ens introdueixen el valor a get suposant que el seu 1 es el nostre 0
            return(poblacions[pos-1]);             ///////////////// retornar com a còpia?
        } else {
            return(null);
        }
    }

    /**
     * Mètode que crea una nova instància d'un objecte a partir de la info d'un altre objecte
     * @return
     */
    public LlistaDadesUrb copia() {
        LlistaDadesUrb duplicat = new LlistaDadesUrb();
        duplicat.poblacions = this.poblacions; /////////////////// està bé?
        duplicat.numPoblacions = this.numPoblacions;
        return duplicat;
    }

    /**
     * Mètode toString
     * @return cadena amb el contingut de la instància
     */
    public String toSting() {
        String aux = "\n\nLlistaDadesUrb:\n";

        if (numPoblacions > 0) {
            for (int i = 0; i < numPoblacions; i++) {
                aux = aux + "\n\t" + poblacions[i];  ////////////// està bé? cridarà al toString de DadesUrb?
            }
        }
        return aux;
    }




}
