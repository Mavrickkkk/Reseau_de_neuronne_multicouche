import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPomme {
    public static void main(String[] args) {

        // Charger les données à partir du fichier CSV
        String csvFile = "apple_quality_normalise.csv";
        String line = "";
        String csvSplitBy = ";";
        List<double[]> entrees = new ArrayList<>();
        List<double[]> sortiesAttendues = new ArrayList<>();

        try (
                BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int i = 0;

            while ((line = br.readLine()) != null && i < 500) {
                // Ignorer la première ligne (en-têtes) et commencer à partir de la deuxième
                // ligne
                if (i > 0) {
                    // Utiliser la méthode split() pour diviser la ligne en tableau de chaînes
                    String[] row = line.split(csvSplitBy);
                    // Créer un tableau pour stocker les valeurs des colonnes d'entrée
                    double[] entree = new double[8];
                    // Remplir le tableau avec les valeurs des colonnes d'entrée
                    for (int j = 0; j < 7; j++) {
                        entree[j] = Double.parseDouble(row[j + 1]); // Commencer à partir de la 2ème colonne
                    }
                    // Ajouter l'entrée à la liste des entrées
                    entrees.add(entree);
                    // Ajouter la sortie attendue (la 16ème colonne)
                    double[] sortieAttendue = { Double.parseDouble(row[8]) };

                    // Ajouter la sortie attendue à la liste des sorties attendues
                    sortiesAttendues.add(sortieAttendue);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convertir les listes en tableaux pour l'entraînement
        double[][] entreesArray = entrees.toArray(new double[0][]);
        double[][] sortiesAttenduesArray = sortiesAttendues.toArray(new double[0][]);

        // Définir l'architecture du réseau de neurones (8 entrées, 5 neurones dans la
        // couche cachée, 1 sortie)
        int[] architecture = { 7, 30, 20, 10, 5, 1 };

        // Créer le réseau de neurones
        ReseauNeurones reseau = new ReseauNeurones(architecture);

        // Entraîner le réseau de neurones
        double tauxApprentissage = 0.1;
        int epochs = 3000;
        reseau.entrainer(entreesArray, sortiesAttenduesArray, tauxApprentissage, epochs);

        double[] test = { 0.548031005299228, 0.419946440939786, 0.528712844031092, 0.358392354187822, 0.542429218592841,
                0.388326842623317, 0.597306336675986 };

        reseau.setSorties(test);

        double[] sortie = reseau.sorties;

        System.out.println("LA SORTIE DU TEST -> " + sortie[0]);
    }
}
