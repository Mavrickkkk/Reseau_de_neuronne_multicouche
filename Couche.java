public class Couche {
    public Neurone[] neurones;

    public Couche(int nbNeurones, int nbEntrees) {
        neurones = new Neurone[nbNeurones];
        for (int i = 0; i < nbNeurones; i++) {
            neurones[i] = new Neurone(nbEntrees);
        }
    }

    public Couche(int nbNeurones) {
        neurones = new Neurone[nbNeurones];
        for (int i = 0; i < nbNeurones; i++) {
            neurones[i] = new Neurone(1.0);
        }
    }

    // Méthode spour obtenir les sorties de la couche
    public double[] getSorties(double[] entrees) {
        double[] sorties = new double[neurones.length];
        for (int i = 0; i < neurones.length; i++) {
            sorties[i] = neurones[i].activation(entrees);
        }
        return sorties;
    }

    public double[] getSortiesEntree(double[] entrees) {
        double[] sorties = new double[neurones.length];
        for (int i = 0; i < neurones.length; i++) {
            sorties[i] = neurones[i].activationEntree(entrees[i]);
        }
        return sorties;
    }

    public void retropropagation(Couche suivante) {
        for (int i = 0; i < neurones.length; i++) {
            neurones[i].calculerGradients(suivante, i);
        }
    }

    public void retropropagationSortie(double[] valeurAttendue) {
        for (int i = 0; i < neurones.length; i++) {
            neurones[i].calculerGradientSortie(valeurAttendue[i]);
        }
    }

    public void mettreAJourPoids(double tauxApprentissage) {
        for (int i = 0; i < neurones.length; i++) {
            neurones[i].mettreAJourPoids(tauxApprentissage);
        }
    }

}
