import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Crear un árbol binario de búsqueda para el diccionario inglés-español
        BinaryTree<Association<String, String>> dictionaryTree = new BinaryTree<>();
        
        // Leer el archivo diccionario.txt y construir el árbol
        try (BufferedReader reader = new BufferedReader(new FileReader("diccionario.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String key = parts[0].trim().substring(1);
                String value = parts[1].trim().substring(0, parts[1].length() - 1);
                dictionaryTree.insert(new Association<>(key, value));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Recorrer el árbol en orden e imprimir las asociaciones ordenadas por la palabra en inglés
        List<Association<String, String>> sortedAssociations = dictionaryTree.inorderTraversal();
        System.out.println("Diccionario ordenado:");
        for (Association<String, String> association : sortedAssociations) {
            System.out.println("(" + association.getKey() + ", " + association.getValue());
        }

        // Procesar el archivo texto.txt y traducir cada palabra al español
        StringBuilder outputText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("texto.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    // Buscar la traducción de la palabra en el diccionario
                    // Si no se encuentra, agregar la palabra original entre asteriscos
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

    // Método para buscar la traducción de una palabra en el diccionario
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