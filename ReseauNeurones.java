public class ReseauNeurones {
    private Couche[] couches;
    public double[] sorties;

    // Constructeur
    public ReseauNeurones(int[] architecture) {
        couches = new Couche[architecture.length];
        sorties = new double[architecture[architecture.length - 1]];

        couches[0] = new Couche(architecture[0]);

        for (int i = 1; i < architecture.length; i++) {
            int nbNeurones = architecture[i];
            int nbEntrees = architecture[i - 1];
            couches[i] = new Couche(nbNeurones, nbEntrees);
        }
    }

    // Méthode pour obtenir les sorties du réseau
    public void setSorties(double[] entrees) {
        double[] sorties = entrees;

        sorties = couches[0].getSortiesEntree(entrees);

        for (int i = 1; i < couches.length; i++) {
            sorties = couches[i].getSorties(sorties);
        }

        this.sorties = sorties;
    }

    public void entrainer(double[][] entrees, double[][] sortiesAttendues, double tauxApprentissage, int epochs) {
        // Boucle sur le nombre d'époques
        for (int epoch = 0; epoch < epochs; epoch++) {

            // Boucle sur chaque exemple d'entraînement
            for (int i = 0; i < entrees.length; i++) {
                // Calculer la sortie du réseau pour l'exemple d'entraînement actuel
                double[] entree = entrees[i];
                double[] sortieAttendue = sortiesAttendues[i];
                setSorties(entree);

                // Rétropropagation de l'erreur à travers les couches du réseau
                retropropagationGlobal(sortieAttendue, tauxApprentissage);
            }
        }

        // Tester le réseau de neurones après l'entraînement
        System.out.println("Test du réseau de neurones après l'entraînement :");
        for (int k = 0; k < entrees.length; k++) {
            double[] entree = entrees[k];
            setSorties(entree);
            System.out.println("ligne -> " + k + " Sortie : " + sorties[0] + " - Sortie Attendue : "
                    + sortiesAttendues[k][0]);
        }
    }

    // Méthode pour tester le réseau de neurones
    public void tester(double[][] entreesTest, double[][] sortiesAttenduesTest) {
        System.out.println("Test du réseau de neurones avec les données de test :");
        for (int k = 0; k < entreesTest.length; k++) {
            double[] entree = entreesTest[k];
            setSorties(entree);
            System.out.println("ligne -> " + k + " Sortie : " + sorties[0] + " - Sortie Attendue : "
                    + sortiesAttenduesTest[k][0]);
        }
    }

    public void retropropagationGlobal(double[] sortieAttendue, double tauxApprentissage) {
        couches[couches.length - 1].retropropagationSortie(sortieAttendue);

        // Rétropropagation de l'erreur à travers les couches
        for (int i = couches.length - 2; i >= 1; i--) {
            couches[i].retropropagation(couches[i + 1]);
        }

        for (int i = 1; i < couches.length; i++) {
            couches[i].mettreAJourPoids(tauxApprentissage);
        }

        for (Couche c : couches) {
            for (Neurone n : c.neurones) {
                for (int i = 0; i < n.poids.length; i++) {
                }
            }
        }
    }

}
