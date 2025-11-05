// BUSCAR EXTENSIO PER A EDITAR EL MATEIX CODI DESDE ORDINADORS DIFERENTS I EVITAR ESTAR ENVIANT TOTA LA ESTONA FITXERS AMB LES ÚLTIMES ACTUALITZACIONS

package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dades.*;

// S'HA DE FER UN MENÚ COM A L'EXEMPLE DICCIONARI

public class UsaLlistaDadesUrb {
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		int numLinies, liniaIni, liniaFi;

		System.out.println("Indica les línies a llegir del fitxer");
		System.out.println("Quina és la primera línia a llegir del fitxer (valor >=1)");
		liniaIni = Integer.parseInt(teclat.nextLine());
		System.out.println("Quina és l'última línia a llegir del fitxer (valor <=12324)");
		liniaFi = Integer.parseInt(teclat.nextLine());

		if (liniaIni < liniaFi){
			numLinies = liniaFi - liniaIni + 1;
		}
		else{
			numLinies = (liniaFi - liniaIni)*(-1) + 1;
		}
		
		String[] dataset = llegirLiniesFitxer(liniaIni, liniaFi);

		// mostrem el contingut que hem llegit. Això ho eliminarem en les
		// versions finals del codi
		for (int i = 0; i < dataset.length; i++) {
			System.out.println("Linia " + (i + 1) + " conté " + dataset[i]);
		}

		// Completar el codi a partir d'aquí

		

	}

	private static String[] llegirLiniesFitxer(int liniaIni, int liniaFi) throws FileNotFoundException {
		String[] result;
		String dada;
		int nLinies;
		if (liniaIni < 1)
			liniaIni = 1;
		if (liniaFi > 12324)
			liniaFi = 12324;
		nLinies = liniaFi - liniaIni + 1;
		if (liniaIni < liniaFi){
			result = new String[nLinies];
		}
		else{
			result = new String[nLinies*(-1)];
		}
		Scanner f = new Scanner(new File("Dades_Urban.csv"));

		String capcalera = f.nextLine();
		System.out.println("El format de les dades en cada línia és el següent\n" + capcalera);
		// ens saltem les línies fins a la liniaIni
		for (int i = 1; i < liniaIni; i++) {
			dada = f.nextLine();
		}
		// ara anirem guardant les nLinies que sí volem llegir
		for (int i = 0; i < nLinies; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		return result;
	}

	//Interactuar amb la taula
	//MENU
	//Mostrar el conjunt de dades de la llista. 
//Eliminar el conjunt de dades d’un municipi. El nom del municipi es llegirà de teclat. Per comprovar el bon funcionament de l’operació hem de fer: o primer mostrarem les dades d’aquest municipi, o cridarem a l’operació eliminar i o després tornarem a mostrar les dades del mateix municipi per comprovar que ja no n’hi ha cap. 
//Mostrar la informació del municipi que té la superfície total més gran de Catalunya. 
//Mostrar la informació del municipi que té el percentatge de superfície destinat a zones verdes més alt de Catalunya. 
//Mostrar l’increment o decrement de la superfície de sòl urbanitzable al llarg dels anys d’un municipi. El nom del municipi es llegirà de teclat. 
//Mostrar les dades del primer municipi de costa que no disposa de sòl urbanitzable. 
//Mostrar el nom dels municipis que tenen una densitat de població superior a un valor. El valor es llegirà de teclat. 
//Mostrar el nom dels municipis de muntanya que tenen una densitat de població superior a un valor. El valor es llegirà de teclat. 
//Mostrar la informació del municipi de costa que té el percentatge de superfície destinat a zones verdes més alt de Catalunya.
//Mostrar la informació del municipi que no és ni de costa ni de muntanya que té la superfície total més gran de Catalunya. 
//Mostrar l’evolució de la població anual d’un municipi en els diferents anys on tenim dades. El nom del municipi es llegirà de teclat. 
//Sortir del programa. 

	private static void mostrarMenu(){
		System.out.println("\n\nOpcions del menu:");
		System.out.println("\n\t1. Mostrar el conjunt de dades de la llista.");
		System.out.println("\t2. Eliminar el conjunt de dades d’un municipi.");
		System.out.println("\t3. Mostrar la informació del municipi que té la superfície total més gran de Catalunya.");
		System.out.println("\t4. Mostrar la informació del municipi que té el percentatge de superfície destinat a zones verdes més alt de Catalunya. ");
		System.out.println("\t5. Mostrar l’increment o decrement de la superfície de sòl urbanitzable al llarg dels anys d’un municipi.");
		System.out.println("\t6. Mostrar les dades del primer municipi de costa que no disposa de sòl urbanitzable. ");
		System.out.println("\t7. Mostrar el nom dels municipis que tenen una densitat de població superior a un valor. ");
		System.out.println("\t8. Mostrar el nom dels municipis de muntanya que tenen una densitat de població superior a un valor.");
		System.out.println("\t9. Mostrar la informació del municipi de costa que té el percentatge de superfície destinat a zones verdes més alt de Catalunya. ");
		System.out.println("\t10.Mostrar la informació del municipi que no és ni de costa ni de muntanya que té la superfície total més gran de Catalunya. ");
		System.out.println("\t11. Mostrar l’evolució de la població anual d’un municipi en els diferents anys on tenim dades. El nom del municipi es llegirà de teclat.");
		System.out.println("\t12. Sortir");
		System.out.print("\n\t\t\tIndica opcio:\n");
	}

		public static void opcio1(LlistaDadesUrb municipis) {
	//Mostrar el conjunt de dades de la llista. 
		System.out.print("\n\n\tEls valors de la llista son:\t");
		System.out.println(municipis);
	}

	public static void opcio2(LlistaDadesUrb municipis ,string nom) {
//Eliminar el conjunt de dades d’un municipI.
		System.out.print("\n\n\tEls valors del municipi son:\t");
		System.out.println(municipis.getDadesUrb(nom));//TODO Hem de canviar el metode que ho faci pel nom

		municipis.eliminarMunicipi(nom);
		System.out.println(municipis.getDadesUrb(nom));
	}

	public static void opcio3(LlistaDadesUrb municipis) {
//Mostrar la informació del municipi que té la superfície total més gran de Catalunya. 
		System.out.print("\n\n\tEl municipi més gran de Catalunya és:\t");
		System.out.println(municipis.municipiMajorSuperficie());
	}
	
	public static void opcio4(LlistaDadesUrb municipis) {
//Mostrar la informació del municipi que té el percentatge de superfície destinat a zones verdes més alt de Catalunya. 
		System.out.print("\n\n\tEl municipi amb més percentatge de superfície zona verda és:\t");
		System.out.println(municipis.municipiMajorPercentZonesVerdes());
	}

		public static void opcio5(LlistaDadesUrb municipis, string nom) {
//Mostrar l’increment o decrement de la superfície de sòl urbanitzable al llarg dels anys d’un municipi.
		System.out.print("\n\n\tL'increment o decrement de superfície de sòl urbanitzable de"+nom+"és:\t");
		System.out.println(municipis.modSuperficie(nom));
	}
	
	public static void opcio6(LlistaDadesUrb municipis) {
//Mostrar les dades del primer municipi de costa que no disposa de sòl urbanitzable. 
		System.out.print("\n\n\tEl primer municipi de costa sense sòl urbanitzable és:\t");
		System.out.println(municipis.costaSenseSolUrbanitzable());
	}

	public static void opcio7(LlistaDadesUrb municipis, double dpoblacio) {
//Mostrar el nom dels municipis que tenen una densitat de població superior a un valor.
		System.out.print("\n\n\tEls noms dels municipis amb densitat de població superior a"+dpoblacio+ "són:\t");
		System.out.println(municipis.densitatPoblacions(dpoblacio));
	}

	public static void opcio8(LlistaDadesUrb municipis, double dpoblacio) {
//Mostrar el nom dels municipis de muntanya que tenen una densitat de població superior a un valor. 
		LlistaDadesUrb muntanya = new LlistaDadesUrb(1000000000);
		muntanya = municipis.dadesMunicipiCaract(1);
		System.out.print("\n\n\tEls noms dels municipis de muntanya amb densitat de població superior a"+dpoblacio+ "són:\t");
		System.out.println(muntanya.densitatPoblacions(dpoblacio));
	}


	public static void opcio9(LlistaDadesUrb municipis) {
//Mostrar la informació del municipi de costa que té el percentatge de superfície destinat a zones verdes més alt de Catalunya.
		LlistaDadesUrb costa = new LlistaDadesUrb(1000000000);
		costa = municipis.dadesMunicipiCaract(0);
		System.out.print("\n\n\tEl municipi de costa amb més percentatge de superfície zona verda és:\t");
		System.out.println(costa.municipiMajorPercentZonesVerdes());
	}

	public static void opcio10(LlistaDadesUrb municipis) {
//Mostrar la informació del municipi que no és ni de costa ni de muntanya que té la superfície total més gran de Catalunya. 
		LlistaDadesUrb normal = new LlistaDadesUrb(1000000000);
		normal = municipis.dadesMunicipiCaract(2);
		System.out.print("\n\n\tEl municipi ni de costa ni de muntanya més gran de Catalunya és:\t");
		System.out.println(normal.municipiMajorSuperficie());
	}

	public static void opcio11(LlistaDadesUrb municipis, string nom) {
//Mostrar l’evolució de la població anual d’un municipi en els diferents anys on tenim dades. 
		LlistaDadesUrb municipiNom = new LlistaDadesUrb(1000000000);
		municipiNom = municipis.dadesMunicipi(nom);
		System.out.println("\n\n\t La població del municipi "+nom+ " durant els anys ha estat:");
		for (int i = 0; i < municipiNom.length; i++){//TODO mirar com col·locar bé el length estic molt cansada i ja no em funcionen les neurones
			System.out.println("\n"+municipiNom.getDadesUrb(i).getAnyDades()+":\t "+municipiNom.getDadesUrb(i).getHabitants());

		}

	}
	//TODO he passat els valors per paràmetre però haig d'utilitzar la següent funció pq me'ls passin per teclat:	paraula = teclat.nextLine();
	
}