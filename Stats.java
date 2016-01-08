import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Stats {
	// Affiche les résultats finaux de la simulation
	public static void afficher(Ech e) {
		double lambda = e.getLambda();
		double mu = e.getMu();
		double duree = e.getDuree();
		double total = e.getClients();
		double ro = lambda/mu;

		double lamdbaSim = total/duree;
		double roSim = lamdbaSim/mu;
		
		String res = "\n"
			+"********************"
			+"\nResultats théoriques"
			+"\n********************"
			+"\nlambda<mu : "+(lambda<mu ? "file stable" : "file instable")
			+"\nro (lambda/mu) = "+ro
			+"\nnombre de clients attendus (lambda x duree) = "+(lambda*duree)
			+"\nProb de service sans attente (1 - ro) = "+(1 - ro)
			+"\nProb file occupee (ro) = "+ro
			+"\nDebit (lambda) = "+lambda
			+"\nEsp nb clients (ro/1-ro) = "+(ro/(1-ro))
			+"\nTemps moyen de sejour (1/mu(1-ro)) = "+(1/(mu*(1-ro)))
			+"\n\n********************"
			+"\nRésultats simulation"
			+"\n********************"
			+"\nNombre total de clients = "+total
			+"\nProportion clients sans attente = "+(1-roSim)
			+"\nProportion clients avec attente = "+roSim
			+"\nDebit = "+lamdbaSim
			+"\nNb moyen de clients dans systeme = "+(roSim/(1-roSim))
			+"\nTemps moyen de sejour = "+(1/(mu*(1-roSim)));

		System.out.println(res);
	} 

	// Permet d'enregistrer les résultats dans un fichier csv
	public static void log(Ech e) {
		double duree = e.getDuree();
		double total = e.getClients();
		double mu = e.getMu();
		double lambda = total/duree;
		double ro = lambda/mu;
		double nbClient = ro/(1-ro);
		double tpsSejour = 1/(mu*(1-ro));

		try {
			File file = new File(e.getLambda()+"_"+e.getMu()+"_"+duree+".csv");

			if(!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(lambda+";"+ro+";"+(1-ro)+";"+nbClient+";"+tpsSejour+";");
			pw.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}

	}
}