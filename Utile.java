import java.util.*;

public class Utile {

	// Calcul de la durée suivant la loi exponentielle
	public static double loiExp(double lambda, Random rand) {
		/* 
		rand.nextDouble() peut renvoyer 1 ou 0, mais la probabilité est tellement faible que je n'ai pas jugé nécessaire de vérifier ces cas.
		Je ne pense pas que cela soit critique pour cette application, donc j'ai décidé de faire le plus simple possible afin d'éviter les pertes de performance, vu que cette fonction est souvent appelée.
		*/
		return -Math.log(1 - rand.nextDouble()) / lambda;
	}
}