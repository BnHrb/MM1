import java.util.*;

public class Ech {
	private LinkedList<Evt> list; // Liste principale de l'échéancier
	private Collection<Evt> futureDepart; // Liste des futures départs
	private double lambda; // taux d'arrivée
	private double mu; // taux de service
	private double duree; // durée de la simulation
	private boolean debug;
	
	private double derniereArrivee; // date de la dernière arrivée
	private double dernierDepart; // date du dernier départ
	private int nbArrivee; // nombre d'arrivée
	private Random rand;

	public Ech(double lambda, double mu, double duree, boolean debug) {
		this.list = new LinkedList<Evt>();
		this.futureDepart = new LinkedList<Evt>();
		this.lambda = lambda;
		this.mu = mu;
		this.duree = duree;
		this.debug = debug;
		this.derniereArrivee = 0;
		this.dernierDepart = 0;
		this.nbArrivee = 0;
		this.rand = new Random();

		// Création de l'évènement 0
		Evt e0 = new Evt(0, 0, 0);
		this.list.add(e0);
	}

	public void lancement() {
		// Tant que l'échéancier n'est pas vide
		while(!this.list.isEmpty()) {
			// On récupère le premier élément de la file
			Evt e = list.pop();

			if(debug)
				System.out.println(e);

			// Si l'évènement est une arrivée
			if(e.getType() == 0)
				arrivee(e);
		}

		if(debug)
			Stats.afficher(this);

		//Stats.log(this);
	}

	private void arrivee(Evt e) {
		// On calcule la prochaine arrivée 
		this.derniereArrivee = e.getDate() + Utile.loiExp(this.lambda, this.rand);
		// Création de la prochaine arrivée
		Evt a = new Evt(this.nbArrivee+1, 0, this.derniereArrivee);

		// On calcule le future départ
		if(this.list.isEmpty())
			this.dernierDepart = e.getDate() + Utile.loiExp(mu, this.rand);
		else 
			this.dernierDepart = this.dernierDepart + Utile.loiExp(mu, this.rand);

		// Création du future départ
		Evt d = new Evt(this.nbArrivee, 1, this.dernierDepart, e.getDate());

		// On ajoute le départ à la liste des futures départs
		this.futureDepart.add(d);

		// Si la date de la prochaine arrivée est supérieur au départ de n ou si la simulation se termine
		if(this.dernierDepart < a.getDate() || this.derniereArrivee > this.duree) {
			// On ajoute la liste des futures départs à la fin de la file de l'échéancier
			this.list.addAll(this.futureDepart);
			this.futureDepart.clear();
		}

		// Si la future arrivée ne dépasse pas la durée de la simulation
		if(this.derniereArrivee < this.duree){
			// On l'ajoute à l'échéancier
			this.list.add(a);
			// On incrément le compteur de client total
			this.nbArrivee++;
		}
	}

	public double getLambda() {
		return this.lambda;
	}

	public double getMu() {
		return this.mu;
	}

	public double getDuree() {
		return this.duree;
	}

	public double getClients() {
		return this.nbArrivee;
	}
}