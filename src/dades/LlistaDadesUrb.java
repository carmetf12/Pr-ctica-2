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
        LlistaDadesUrb duplicat = new LlistaDadesUrb(); ///// s'han de passar tots els param al constructor??
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

        /**
     *  Afegir les dades d'una nova instància de DadesUrb al final de la llista.
     * @param novaDada tipus DadesUrb a afegir
     */
    public void afegirDades( DadesUrb novaDada) {
        if (numPoblacions<poblacions.length) {
            poblacions[numPoblacions] = novaDada.copia(); // s'afegeix nova dada a última posició lliure
            numPoblacions++;
        }
        else { // com la llista està plena s'ha de crear una nova llista amb capacitat+1 /////////////////////////////////////////////////////////////////////////////////////////
            // TODO: si aquí creem una nova llista no ho podem fer com a void, perquè hauriem de retornar aquesta nova llista
            //       podriem retornar a malas un codi d'error i que un altre mètode s'encarregui de fer una llista més llarga i després se li tornin a afegir dades.
            //       o abans d'implementar el mètode afegirDades fem un mètode més general que comprovi la capacitat i desde ahi ja decidim si hi ha espai per afegir 
            //       més dades o hem de cridar ja als altres métodes
        }
    }

    /**
     * Consultar les dades del municipi que té la superfície total més gran de Catalunya. 
     * En cas d’empat pots retornar la primera instància trobada. 
     * El mètode ha de retornar la instància de dades identificada o null si no hi ha cap element a la llista. 
     * 
     * @return
     */
    public DadesUrb municipiMajorSuperficie() {
        int posmax = -1; //posició on es troba el element amb el municipi de major superfície
        double supmax = -1; //no es declara amb els valors del primer element per si no hi ha primer element

        if (numPoblacions ==0 ) { // no hi ha cap element a la llista
            return(null);
        } else { 
            for (int i = 0; i<numPoblacions; i++) {
                if(poblacions[i].getSuperficie_ha() > supmax) { //si son iguals no s'actualitzarà, però està contemplat a l'enunciat
                    supmax = poblacions[i].getSuperficie_ha();  // he fet el getter a DadesUrb
                    posmax = i;
                }
            }
            return(poblacions[posmax]); // retorna instancia (no duplicat) de supmax a partir de la seva posicio guardada
        }
    }

    /**
     * Retornar un duplicat de la instància on el percentatge de superfície del municipi destinat a zones verdes sigui el més alt de tota la llista. 
     * En cas d’empat es pot retornar qualsevol de les que tinguin el mateix valor. 
     * @return
     */
    public DadesUrb municipiMajorPercentZonesVerdes() {
        int posmax = -1; //posició on es troba el element amb el municipi de major superfície
        double supmax = -1; //no es declara amb els valors del primer element per si no hi ha primer element
        double percentZonaVerda = -1;

        if (numPoblacions ==0 ) { // no hi ha cap element a la llista
            return(null);
        } else { 
            for (int i = 0; i<numPoblacions; i++) {
                percentZonaVerda = (poblacions[i].getSuperfZonesVerdes_ha()/poblacions[i].getSuperficie_ha()*100); //variable auxiliar que calcula el percentatge de superficie destinada a zones verdes x cada municipi
                if(percentZonaVerda > supmax) { //si son iguals no s'actualitzarà, però està contemplat a l'enunciat
                    supmax = percentZonaVerda;
                    posmax = i;
                }
            }
            return(poblacions[posmax].copia()); // retorna DUPLICAT de la instancia TODO: ben fet??
        }
    }

    /**
     * Consultar si un municipi ha modificat la superfície del sòl urbanitzable al llarg dels anys. 
     * El mètode rep el nom del municipi per paràmetre i retorna l’increment (o decrement) de sòl urbanitzable entre les dades del primer any i les de l’últim. 
     * Les dades a la llista estan ordenades per any. 

     * @param nom string amb el nom del municipi
     * @return
     */
    public double modSuperficie(String nom) {
        int posIni = -1;
        int posFi = -1;
        int increment;
        
        //buscar les posicions del primer i últim any del mateix municipi
        for(int i = 0; i<numPoblacions; i++) {
            if(nom.equalsIgnoreCase(poblacions[i].getNomMunicipi())){
                if(posIni == -1) { //encara no s'havia trobat cap coincidencia de nom abans
                    posIni = i;    // es guarda la posició del primer any guardat el municipi
                    posFi = i;     // s'inicialitza per a comparar amb els propers anys i per si no hi ha cap altre objecte d'aquest municipi
                }
                else {
                    if (i>posFi) {
                        posFi = i;
                    }
                }
            }
        }
        if (posIni == -1) {
            return(null);       //TODO: ficar codi d'error o fer algo en cas de que aquell municipi no es trobi a la llista
        }
        else {
<<<<<<< HEAD
            increment = (poblacions[i].getSuperfSolUrbanitzable_ha())
=======
            increment = poblacions[i].getSuperfSolUrbanitzable_ha();
>>>>>>> 1890b04 (Dos metodes mes)
        }


    }

<<<<<<< HEAD
=======
    /*Consultar si hi ha algun municipi de costa que no disposi de sòl urbanitzable (columna 05_SURB)
     en alguna de les anualitats on tenim dades. El mètode ha de retornar la primera 
     instància trobada o null si no n’hi ha cap.  
     
     @return intancia si l'ha trobat, null si no.
     */

     public DadesUrb costaAmbSolUrbanitzable (){
        boolean trobat = false;
        int i =0;

        while ((i<numPoblacions)&&(!trobat)){
            if (poblacions[i].getEsMunicipiDeCosta()){
                if (poblacions[i].getSuperfSolUrbanitzable_ha()== 0){/*només si no té sol urbanitzable? */
                    trobat = true;
                }
            }
            i++;
        }
        if (trobat){
            return (poblacions[i]);
        }
        else{
            return (null); /*com ho declaro? */
        }
     }

     /*Quins són els municipis més densos en població? La densitat de població d’un municipi 
     es calcula com el número d’habitants per km2 agafant el total de superfície del municipi i 
     el total d’habitants. Fes un mètode que retorni una taula de String amb els noms dels
      municipis (sense repetits) que tenen una densitat de població superior a un valor 
      indicat per paràmetre.  
      
      @parametre densitat
      @return nomPob 
      */

      public String [] densitatPoblacions (double densitat){
        String[] nomPob = new String[numPoblacions];/*he ficat numPoblacion perquè és el màxim de poblacion que hi haurà */
        double supTotal;
        double dens;
        int j = 0;
        nomPob[0] = "res";

        
        for (int i = 0; i<numPoblacions; i++){
            supTotal = poblacions[i].getSuperfEquipHabitant() +poblacions[i].getSuperfIndustrial() + poblacions[i].getSuperfLogistica() + poblacions[i].getSuperfServeis() + poblacions[i].getSuperfSolNoUrbanitzable() + poblacions[i].getSuperfSolUrbanitzable_ha() + poblacions[i].getSuperfZonesVerdes_ha() + poblacions[i].getSuperficie_ha();
             /*no se si haig d'agafar totes */
            dens = (poblacions[i].getHabitants()/supTotal);
            if (dens > densitat){
                nomPob[j] = poblacions[i].getNomMunicipi();
                j++;
            }
        }
        return (nomPob);
      }
>>>>>>> 1890b04 (Dos metodes mes)
}
