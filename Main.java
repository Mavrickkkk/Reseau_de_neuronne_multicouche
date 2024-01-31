public class Main {
    public static void main(String[] args) {
        int[] architecture = { 2, 3, 1 };
        ReseauNeurones reseau = new ReseauNeurones(architecture);

        double[] entrees = { 0.5, 0.8 };
        double[] cibles = { 0.2 }; // Exemple de cible, vous devez ajuster cela en fonction de votre tâche

        // Entraînement avec rétropropagation du gradient
        double tauxApprentissage = 0.1; // Vous devez ajuster cela en fonction de votre tâche
        reseau.entrainement(entrees, cibles, tauxApprentissage);

        // Utilisation du réseau après l'entraînement
        double[] sorties = reseau.getSorties(entrees);

        // Afficher les sorties du réseau
        for (double sortie : sorties) {
            System.out.println("Sortie : " + sortie);
        }
    }
}
