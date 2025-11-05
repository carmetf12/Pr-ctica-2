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
        LlistaDadesUrb duplicat = new LlistaDadesUrb(numPoblacions); ///// s'han de passar tots els param al constructor??
        duplicat.poblacions = this.poblacions; /////////////////// està bé?
        duplicat.numPoblacions = this.numPoblacions;
        return duplicat;
    }

    /**
     * Mètode toString
     * @return cadena amb el contingut de la instància
     */
    public String toSting() {
        String aux = "\n\nLlistaDadesUrb amb " +numPoblacions+ " poblacions:\n";

        if (numPoblacions > 0) {
            for (int i = 0; i < numPoblacions; i++) {
                aux = aux + "\n\t" + poblacions[i].toString();  ////////////// està bé? cridarà al toString de DadesUrb?
            }
        }
        return aux;
    }

        /**
     *  Afegir les dades d'una nova instància de DadesUrb al final de la llista.
     * @param novaDada tipus DadesUrb a afegir
     */
    public void afegirDades( DadesUrb novaDada) {
        int diferencia;
        if (numPoblacions<poblacions.length) {
            poblacions[numPoblacions] = novaDada.copia(); // s'afegeix nova dada a última posició lliure
            numPoblacions++;
        }
        else { // com la llista està plena s'ha de crear una nova llista amb capacitat+1 /////////////////////////////////////////////////////////////////////////////////////////
            // TODO: si aquí creem una nova llista no ho podem fer com a void, perquè hauriem de retornar aquesta nova llista
            //       podriem retornar a malas un codi d'error i que un altre mètode s'encarregui de fer una llista més llarga i després se li tornin a afegir dades.
            //       o abans d'implementar el mètode afegirDades fem un mètode més general que comprovi la capacitat i desde ahi ja decidim si hi ha espai per afegir 
            //       més dades o hem de cridar ja als altres métodes
            diferencia = numPoblacions - poblacions.length;
            LlistaDadesUrb nova = new LlistaDadesUrb(numPoblacions + diferencia);
            for (int i = 0; i< numPoblacions; i++){
                poblacions[i] = this.poblacions[i].copia();
            }
            poblacions[numPoblacions] = novaDada.copia(); // s'afegeix nova dada a última posició lliure
            numPoblacions++;
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
        int posmax = -1; //posició on es troba l'element amb el municipi de major superfície
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
     * @return increment sop sol urb, null si no esta el municipi
     */
    public double modSuperficie(String nom) {
        int posIni = -1;
        int posFi = -1;
        double increment;
        
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
            return(-1);       //TODO: ficar codi d'error o fer algo en cas de que aquell municipi no es trobi a la llista
        }                       // s'ha de suposar que sempre el trobarem?
                                //TODO Et serveix com a codi d'error? null no es pot retornar pq és un double
        else {
            increment = (poblacions[posFi].getSuperfSolUrbanitzable_ha() - poblacions[posIni].getSuperfSolUrbanitzable_ha()); // calcula l'increment
            return (increment);
        }


    }

    /**Consultar si hi ha algun municipi de costa que no disposi de sòl urbanitzable (columna 05_SURB)
     en alguna de les anualitats on tenim dades. El mètode ha de retornar la primera 
     instància trobada o null si no n’hi ha cap.  
     
     @return intancia si l'ha trobat, null si no.
     */

    public DadesUrb costaSenseSolUrbanitzable (){
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
            return (poblacions[i-1]); //he canviat a i-1 perquè encara que el trobis li apliques l'increment, aleshores tornaries el trobat +1
        }
        else{
            return (null); /*com ho declaro? crec que així està bé pq es de tipus objecte*/
        }
     }

     /**Quins són els municipis més densos en població? La densitat de població d’un municipi 
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
        int j = 0; //var per incrementar al recorregut per la taula de poblacions amb densitat superior
        int nPobl = 0; //var per controlar el num de poblacions de densitat superior afegides
        nomPob[0] = "res";
        boolean noAfegit = true;
        
        for (int i = 0; i<numPoblacions; i++){
            //supTotal = poblacions[i].getSuperfEquipHabitant() +poblacions[i].getSuperfIndustrial() + poblacions[i].getSuperfLogistica() + poblacions[i].getSuperfServeis() + poblacions[i].getSuperfSolNoUrbanitzable() + poblacions[i].getSuperfSolUrbanitzable_ha() + poblacions[i].getSuperfZonesVerdes_ha() + poblacions[i].getSuperficie_ha();
            supTotal = poblacions[i].getSuperficie_ha();
            /*no se si haig d'agafar totes, JO et diria que superficie_ha es el total (ja es la suma de totes), perque de fet, al mètode de percentatge de zones verdes s'enten que aquell es una part d'aquest total. suposo que tmb es podria comprovar pero bueno */
            dens = (poblacions[i].getHabitants()/supTotal);
            if (dens > densitat){
            //comprovar que aquell municipi no s'ha afegit abans (cerca) TODO: revisar
                j = 0;
                //detectar si s'ha afegit abans
                while ((noAfegit)&&(j<nPobl)) {
                    if(nomPob[j] == poblacions[i].getNomMunicipi()) {
                        noAfegit = false;
                    }
                    j++;
                }
                //si no s'ha afegit abans (despres del bucle noAfegit continua sent cert)
                if(noAfegit) {
                    nomPob[nPobl] = poblacions[i].getNomMunicipi();
                    nPobl++;
                }
            }
        }
        return (nomPob);
    }
    /**
     * Consultar les dades d’un municipi. El nom del municipi es rep per paràmetre. 
     * Es retorna les dades que compleixen la condició en una nova LlistaDadesUrb. 
     * @param nom del municipi a buscar
     * @return LlistaDadesUrb amb municipis del nom de param
     */
    public LlistaDadesUrb dadesMunicipi (String nom) {
        final int capacitat = 16; //pq es el 
        int j = 0;

        LlistaDadesUrb subllista = new LlistaDadesUrb(numPoblacions); //nova llista
        // trobar posicio del municipi a la taula
        for(int i = 0; i<numPoblacions; i++) {
            if(nom.equalsIgnoreCase(poblacions[i].getNomMunicipi())){
                subllista.afegirDades(poblacions[i]);
  
                j++;
            }
        }
        return(subllista);
    }

    /**
     * Consutar i retornar una llistaDadesUrb que conté els municipis que siguin de costa o els que siguin de muntanya o els que no siguin cap d'aquests dos tipus
     * Per a escollir quin dels 3 tipus de dades volen, al menú es dira que segons el numero que passin estaran seleccionant una opcio o un altra
     * on costa = 0, muntanya = 1, ni costa ni muntanya = 2
     * @param op opció seleccionada per a retornar
     * @return 
     */
    public LlistaDadesUrb dadesMunicipiCaract(int op) {

        int j = 0;
        LlistaDadesUrb subllista = new LlistaDadesUrb(numPoblacions); //nova llista

        if (op == 0) { //seleccio costa
            for (int i = 0; i<numPoblacions; i++){
                if(poblacions[i].getEsMunicipiDeCosta()){
                    subllista.afegirDades(poblacions[i]);
                    j++;
                }
            }
        }
        else {
            if (op == 1){ //seleccio muntanya
                for (int i = 0; i<numPoblacions; i++){
                    if(poblacions[i].getEsMunicipiDeMuntanya()){
                        subllista.afegirDades(poblacions[i]);
                        j++;
                    }
                }
            }
            else {
                if (op == 2){ //seleccio ni costa ni muntanya
                    for (int i = 0; i<numPoblacions; i++){                                                    //nomes entra si cap dels dos ho es
                        if(!(poblacions[i].getEsMunicipiDeCosta()||poblacions[i].getEsMunicipiDeMuntanya())){ //si algun es de costa o de muntanya no entra,
                            subllista.afegirDades(poblacions[i]);
                            j++;
                        }
                    }
                }
                else { //han introduit una opcio invalida
                    return (null);//TODO: acabar TE SIRVE?
                }
            }
        }

        return(subllista);    
    }

    /**
     * Eliminar el conjunt de dades que són d’un municipi (recorda que no poden quedar posicions buides a la taula que conté la llista). 
     * El mètode rep per paràmetre el nom del municipi.
     * @param nom nom del Municipi a eliminar
     */
    public void eliminarMunicipi (String nom) {
        for(int i = 0; i<numPoblacions; i++) {
            if(nom.equalsIgnoreCase(poblacions[i].getNomMunicipi())){ //es comprova posicio
                for(int j = i; j<numPoblacions; j++) {
                        poblacions[j] = poblacions[j+1];
                        numPoblacions--;
                }
                if(nom.equalsIgnoreCase(poblacions[i+1].getNomMunicipi())){ //es comprova posicio seguent
                    //si la posició següent també es del municipi
                    i--; //es decrementa i, per a que a la propera iteració quan incrementi i avalui la casella que ha estat col·locada on estava la eliminada
                } // VAYA FACTO


            }
        }
    }
}
