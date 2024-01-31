public class Couche {
    public Neurone[] neurones;

    public Couche(int nbNeurones, int nbEntrees) {
        neurones = new Neurone[nbNeurones];
        for (int i = 0; i < nbNeurones; i++) {
            neurones[i] = new Neurone(nbEntrees);
        }
    }

    // Méthode pour obtenir les sorties de la couche
    public double[] getSorties(double[] entrees) {
        double[] sorties = new double[neurones.length];
        for (int i = 0; i < neurones.length; i++) {
            sorties[i] = neurones[i].activation(entrees);
        }
        return sorties;
    }

    // Méthode de rétropropagation du gradient pour une couche
    public void retropropagation(double[] erreurs, double tauxApprentissage) {
        for (int i = 0; i < neurones.length; i++) {
            double erreurNeurone = erreurs[i];
            neurones[i].retropropagation(erreurNeurone, tauxApprentissage);
        }
    }
}
