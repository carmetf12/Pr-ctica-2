package dades;

public class DadesUrb {
	private int anyDades;
	private String nomMunicipi;
	private String comarca;
	private String provincia;
	private boolean esMunicipiDeCosta;
	private boolean esMunicipiDeMuntanya;
	private int numHabitants;
	private double superficie_ha;
	private double superfSolUrba_ha;
	private double superfSolUrbanitzable_ha;
	private double superfSolNOurbanitzable_ha;
	private double superfIndustrial_ha;
	private double superfServeis_ha;
	private double superfLogistica_ha;
	private double superfZonesVerdes_ha;
	private double superfEquipHabitant_m2;

	private static String[] comBarcelona = { "Alt Penedès", "Anoia", "Bages", "Baix Llobregat", "Barcelonès",
			"Berguedà", "Garraf", "Lluçanès", "Maresme", "Moianès", "Osona", "Vallès Oriental", "Vallès Occidental" };

	private static String[] comTarragona = { "Alt Camp", "Baix Camp", "Baix Ebre", "Baix Penedès", "Conca de Barberà",
			"Montsià", "Priorat", "Ribera d'Ebre", "Tarragonès", "Terra Alta" };
	private static String[] comLleida = { "Alta Ribagorça", "Alt Urgell", "Garrigues", "Noguera", "Pallars Jussà",
			"Pallars Sobirà", "Pla d'Urgell", "Segarra", "Segrià", "Solsonès", "Urgell", "Val d'Aran" };
	private static String[] comGirona = { "Alt Empordà", "Baix Empordà", "Cerdanya", "Garrotxa", "Gironès",
			"Pla de l'Estany", "Selva", "Ripollès" };

	/**
	 * CONSTRUCTOR
	 * @param anyDades
	 * @param nomMunicipi
	 * @param comarca
	 * @param esMunicipiDeCosta
	 * @param esMunicipiDeMuntanya
	 * @param numHabitants
	 * @param superficie_ha
	 * @param superfSolUrba_ha
	 * @param superfSolUrbanitzable_ha
	 * @param superfSolNOurbanitzable_ha
	 * @param superfIndustrial_ha
	 * @param superfServeis_ha
	 * @param superfLogistica_ha
	 * @param superfZonesVerdes_ha
	 * @param superfEquipHabitant_m2
	 */
	public DadesUrb(int anyDades, String nomMunicipi, String comarca, boolean esMunicipiDeCosta,
			boolean esMunicipiDeMuntanya, int numHabitants, double superficie_ha, double superfSolUrba_ha,
			double superfSolUrbanitzable_ha, double superfSolNOurbanitzable_ha, double superfIndustrial_ha,
			double superfServeis_ha, double superfLogistica_ha, double superfZonesVerdes_ha,
			double superfEquipHabitant_m2) {
		this.anyDades = anyDades;
		this.nomMunicipi = nomMunicipi;
		this.comarca = comarca;
		this.provincia = cercaProvinciaComarca(comarca);
		this.esMunicipiDeCosta = esMunicipiDeCosta;
		this.esMunicipiDeMuntanya = esMunicipiDeMuntanya;
		this.numHabitants = numHabitants;
		this.superficie_ha = superficie_ha;
		this.superfSolUrba_ha = superfSolUrba_ha;
		this.superfSolUrbanitzable_ha = superfSolUrbanitzable_ha;
		this.superfSolNOurbanitzable_ha = superfSolNOurbanitzable_ha;
		this.superfIndustrial_ha = superfIndustrial_ha;
		this.superfServeis_ha = superfServeis_ha;
		this.superfLogistica_ha = superfLogistica_ha;
		this.superfZonesVerdes_ha = superfZonesVerdes_ha;
		this.superfEquipHabitant_m2 = superfEquipHabitant_m2;
	}
	/**
	 * Mètode que busca a quina provincia està una determinada comarca
	 * @param comarca
	 * @return nom de la provincia
	 */
	private String cercaProvinciaComarca(String comarca) {
		String nomProvincia = null;
		if (estaEn(comarca, comBarcelona))
			nomProvincia = "Barcelona";
		else if (estaEn(comarca, comTarragona))
			nomProvincia = "Tarragona";
		else if (estaEn(comarca, comLleida))
			nomProvincia = "Lleida";
		else if (estaEn(comarca, comGirona))
			nomProvincia = "Girona";
		else
			System.out.println("PROBLEMA, no trobem " + comarca);
		return nomProvincia;  // si no troba on està la comarca retorna NULL
	}

	/**
	 * Mètode que busca si una comarca està a una determinada provincia
	 * @param comarca
	 * @param comarquesProvincia llista amb les comarques d'aquella província
	 * @return booleà si aquella comarca està o no a la província
	 */
	private boolean estaEn(String comarca, String[] comarquesProvincia) {
		int pos = 0;
		boolean trobat = false;

		while (!trobat && pos < comarquesProvincia.length) {
			if (comarquesProvincia[pos].equalsIgnoreCase(comarca))
				trobat = true;
			else
				pos++;
		}
		return trobat;
	}

	/**
	 * Mètode toSting que retorna la informació de DadesUrb en forma de text
	 * @return string amb DadesUrb
	 */
	@Override
	public String toString() {
		String aux="\n\nDadesUrb";
		aux = aux +"\n\tanyDades=" + anyDades + "\n\tnomMunicipi=" + nomMunicipi + "\n\tcomarca=" + comarca
				+ "\n\tprovincia=" + provincia + "\n\tesMunicipiDeCosta=" + esMunicipiDeCosta + "\n\tesMunicipiDeMuntanya="
				+ esMunicipiDeMuntanya + "\n\tnumHabitants=" + numHabitants + "\n\tsuperficie_ha=" + superficie_ha
				+ "\n\tsuperfSolUrba_ha=" + superfSolUrba_ha + "\n\tsuperfSolUrbanitzable_ha=" + superfSolUrbanitzable_ha
				+ "\n\tsuperfSolNOurbanitzable_ha=" + superfSolNOurbanitzable_ha + "\n\tsuperfIndustrial_ha="
				+ superfIndustrial_ha + "\n\tsuperfServeis_ha=" + superfServeis_ha + "\n\tsuperfLogistica_ha="
				+ superfLogistica_ha + "\n\tsuperfZonesVerdes_ha=" + superfZonesVerdes_ha + "\n\tsuperfEquipHabitant_m2="
				+ superfEquipHabitant_m2;
		return aux;
	}

	/**
	 * Mètode que crea una nova instància d'un objecte a partir de la info d'un altre objecte
	 * @return nou objecte del tipus DadesUrb
	 */
	public DadesUrb copia() {
		DadesUrb duplicat = new DadesUrb(anyDades, nomMunicipi, comarca, esMunicipiDeCosta,
				esMunicipiDeMuntanya, numHabitants, superficie_ha, superfSolUrba_ha,
				superfSolUrbanitzable_ha, superfSolNOurbanitzable_ha, superfIndustrial_ha,
				superfServeis_ha, superfLogistica_ha, superfZonesVerdes_ha,
				superfEquipHabitant_m2);
		return duplicat;
	}

	/**
	 * getter de la superfície_ha
	 * @return
	 */
	public double getSuperficie_ha() {
		return(superficie_ha);
	}

	/**
	 * getter de la superfície de zones verdes
	 * @return
	 */
	public double getSuperfZonesVerdes_ha() {
		return(superfZonesVerdes_ha);
	}

	/**
	 * getter del nom del municipi
	 * @return
	 */
	public String getNomMunicipi() {
		return(nomMunicipi);
	}

	/**
	 * getter del nom del municipi
	 * @return
	 */
	public double getSuperfSolUrbanitzable_ha() {
		return(superfSolUrbanitzable_ha);
	}
<<<<<<< HEAD
=======

	public boolean getEsMunicipiDeCosta (){
		return (esMunicipiDeCosta);
	}

	public double getSuperfSolNoUrbanitzable(){
		return (superfSolNOurbanitzable_ha);
	}

	public double getSuperfIndustrial(){
		return (superfIndustrial_ha);
	}
		
	public double getSuperfServeis(){
		return (superfServeis_ha);
	}

	public double getSuperfLogistica(){
		return (superfLogistica_ha);
	}

	public double getSuperfEquipHabitant(){
		return (superfEquipHabitant_m2);
	}

	public int getHabitants(){
		return (numHabitants);
	}

>>>>>>> 1890b04 (Dos metodes mes)
}
