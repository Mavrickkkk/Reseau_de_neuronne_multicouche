public class Main {
    public static void main(String[] args) {

        int[] architecture = { 2, 10, 5, 1 };

        // Créer le réseau de neurones
        ReseauNeurones reseau = new ReseauNeurones(architecture);

        // Données d'entraînement pour la fonction XOR
        double[][] entrees = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        double[][] sortiesAttendues = { { 0 }, { 1 }, { 1 }, { 0 } };

        // Entraîner le réseau de neurones
        double tauxApprentissage = 0.1;
        int epochs = 3000;
        reseau.entrainer(entrees, sortiesAttendues, tauxApprentissage, epochs);

    }
}
