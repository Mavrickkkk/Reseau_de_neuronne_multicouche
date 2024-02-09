public class Neurone {
    public double[] poids;
    private double biais;
    private double sortie;
    private double[] entrees;

    public Neurone(int nbEntrees) {
        // Initialiser les poids et le biais de manière aléatoire
        poids = new double[nbEntrees];
        for (int i = 0; i < nbEntrees; i++) {
            poids[i] = Math.random();
        }
        biais = Math.random();
    }

    // Méthode d'activation (sigmoid)
    public double activation(double[] entrees) {
        this.entrees = entrees; // Sauvegarder les entrées
        double sommePonderee = 0;
        for (int i = 0; i < poids.length; i++) {
            sommePonderee += poids[i] * entrees[i];
        }
        sommePonderee += biais;
        sortie = fonctionActivation(sommePonderee);
        return sortie;
    }

    private double fonctionActivation(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    // Méthode de rétropropagation du gradient pour un neurone
    public void retropropagation(double erreur, double tauxApprentissage) {
        double deriveeSortie = sortie * (1 - sortie);
        double gradient = erreur * deriveeSortie;

        for (int i = 0; i < poids.length; i++) {
            double gradientPoids = gradient * entrees[i];
            poids[i] -= tauxApprentissage * gradientPoids;
        }

        // Mise à jour du biais
        biais -= tauxApprentissage * gradient;
    }
}
