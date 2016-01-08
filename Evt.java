public class Evt {
	private double date; // date de l'évènement
	private double arrivee; // date d'arrivé si l'évènement est un départ
	private int type; // 0 : arrivee, 1 : depart
	private int id; // numéro de l'évènement

	// Création d'une arrivée
	public Evt(int id, int type, double date) {
		this.id = id;
		this.date = date;
		this.type = type;
	}

	// Création d'un départ, qui prend en plus la date de son arrivée
	public Evt(int id, int type, double date, double arrivee) {
		this(id, type, date);
		this.arrivee = arrivee;
	}

	public double getDate() {
		return this.date;
	} 

	public int getType() {
		return this.type;
	}

	// Convertie l'objet Evt en String
	public String toString() {
		String res;

		if (this.type == 0) {
			res = "Date="+this.date+"\t\tArrivée client #"+this.id;
		}
		else {
			res = "Date="+this.date+"\t\tDépart client #"+this.id+"\t\t arrivée à t="+this.arrivee;
		} 

		return res;
	}
}