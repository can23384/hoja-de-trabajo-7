import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Association<String, String>> dictionaryTree = new BinaryTree<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("diccionario.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    dictionaryTree.insert(new Association<>(key, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Association<String, String>> sortedAssociations = dictionaryTree.inorderTraversal();
        System.out.println("Diccionario ordenado:");
        for (Association<String, String> association : sortedAssociations) {
            System.out.println("(" + association.getKey() + ", " + association.getValue() + ")");
        }

        StringBuilder outputText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("texto.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    String translatedWord = searchInDictionary(dictionaryTree, word.toLowerCase());
                    outputText.append(translatedWord).append(" ");
                }
                outputText.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Texto traducido:");
        System.out.println(outputText.toString().trim());
    }

    // Método para traducir una palabra
    private static String searchInDictionary(BinaryTree<Association<String, String>> dictionaryTree, String word) {
        List<Association<String, String>> sortedAssociations = dictionaryTree.inorderTraversal();
        for (Association<String, String> association : sortedAssociations) {
            if (association.getKey().equalsIgnoreCase(word)) {
                return association.getValue();
            }
        }
        return "*" + word + "*";
    }
}