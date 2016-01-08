public class MM1 {

	public static void main(String[] args) {
		boolean debug;

		if(args.length == 4) {
			// Converti un 0 en booléen false et tout autre entrée en true
			debug = args[3].equals("0") ? false : true;
			// Création d'un échéancier
			Ech e = new Ech(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), debug);
			// Lancement de la simulation
			e.lancement();
		}
		else {
			System.out.println("Usage : java MM1 lambda mu duree debug");
		}
	}
}