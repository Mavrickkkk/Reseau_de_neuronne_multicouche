public class ReseauNeurones {
    private Couche[] couches;

    // Constructeur
    public ReseauNeurones(int[] architecture) {
        couches = new Couche[architecture.length];

        for (int i = 0; i < architecture.length; i++) {
            int nbNeurones = architecture[i];
            int nbEntrees = (i == 0) ? 0 : architecture[i - 1]; // 0 pour la première couche
            couches[i] = new Couche(nbNeurones, nbEntrees);
        }
    }

    // Méthode pour obtenir les sorties du réseau
    public double[] getSorties(double[] entrees) {
        double[] sorties = entrees;

        for (Couche couche : couches) {
            sorties = couche.getSorties(sorties);
        }

        return sorties;
    }

    // Méthode d'entraînement avec rétropropagation du gradient
    public void entrainement(double[] entrees, double[] cibles, double tauxApprentissage) {
        // Passe avant (calcul des sorties)
        double[] sorties = getSorties(entrees);

        // Calcul de l'erreur sur la couche de sortie
        double[] erreursSortie = new double[sorties.length];
        for (int i = 0; i < sorties.length; i++) {
            erreursSortie[i] = cibles[i] - sorties[i];
        }

        // Rétropropagation du gradient à travers les couches
        for (int i = couches.length - 1; i >= 0; i--) {
            Couche couche = couches[i];
            double[] erreursCouche = new double[couche.neurones.length];

            for (int j = 0; j < couche.neurones.length; j++) {
                double sommeErreurPonderee = 0;

                // Vérifier la taille des tableaux
                if (couches.length > i + 1 && couches[i + 1].neurones.length > 0) {
                    for (int k = 0; k < Math.min(couche.neurones[j].poids.length, erreursSortie.length); k++) {
                        sommeErreurPonderee += couche.neurones[j].poids[k] * erreursSortie[k];
                    }
                }

                erreursCouche[j] = sommeErreurPonderee;
            }

            // Appliquer la rétropropagation pour chaque neurone de la couche
            couche.retropropagation(erreursCouche, tauxApprentissage);
        }
    }
}
