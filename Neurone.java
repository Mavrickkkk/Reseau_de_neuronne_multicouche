public class Neurone {
    public double[] poids;
    private double[] entrees;
    private double sortie;
    private double gradient;

    public Neurone(int nbEntrees) {
        // Initialiser les poids et le biais de manière aléatoire
        poids = new double[nbEntrees];
        for (int i = 0; i < nbEntrees; i++) {
            poids[i] = Math.random() * 2 - 1;
        }
    }

    public Neurone(double poids) {
        this.poids = new double[1];
        this.poids[0] = poids;
    }

    // Méthodes d'activation
    public double activation(double[] entrees) {
        this.entrees = entrees; // Sauvegarder les entrées
        double sommePonderee = 0;
        for (int i = 0; i < poids.length; i++) {
            sommePonderee += poids[i] * entrees[i];
        }
        sortie = fonctionActivation(sommePonderee);
        return sortie;
    }

    public double activationEntree(double entree) {
        sortie = poids[0] * entree;
        return sortie;
    }

    private double fonctionActivation(double x) {
        return 1 / (1 + Math.exp(-4 * x));
    }

    // Méthode pour calculer les gradients des poids par rapport à l'erreur
    public void calculerGradients(Couche suivante, int id) {
        double sum = 0;
        for (int i = 0; i < suivante.neurones.length; i++) {
            sum += (suivante.neurones[i].poids[id] * suivante.neurones[i].gradient);
        }
        gradient = sum * (1 - sortie) * sortie;
    }

    public void calculerGradientSortie(double valeurAttendue) {
        this.gradient = this.sortie * (1 - this.sortie) * (valeurAttendue - this.sortie);
    }

    // Méthode pour mettre à jour les poids en utilisant les gradients calculés
    // (Descente de gradient stochastique)
    public void mettreAJourPoids(double tauxApprentissage) {
        for (int i = 0; i < poids.length; i++) {
            poids[i] += tauxApprentissage * gradient * entrees[i];
        }
    }

}
