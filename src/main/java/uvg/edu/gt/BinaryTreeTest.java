import org.junit.*;
import java.util.List;

public class BinaryTreeTest {

    @Test
    public void testInsert() {
        BinaryTree<Association<String, String>> dictionaryTree = new BinaryTree<>();
        
        dictionaryTree.insert(new Association<>("house", "casa"));
        dictionaryTree.insert(new Association<>("dog", "perro"));
        dictionaryTree.insert(new Association<>("homework", "tarea"));
        dictionaryTree.insert(new Association<>("woman", "mujer"));
        dictionaryTree.insert(new Association<>("town", "pueblo"));
        dictionaryTree.insert(new Association<>("yes", "sí"));

        assertNotNull(dictionaryTree);


        List<Association<String, String>> sortedAssociations = dictionaryTree.inorderTraversal();
        assertEquals(6, sortedAssociations.size());
        assertEquals("house", sortedAssociations.get(0).getKey());
        assertEquals("casa", sortedAssociations.get(0).getValue());
        assertEquals("dog", sortedAssociations.get(1).getKey());
        assertEquals("perro", sortedAssociations.get(1).getValue());
        assertEquals("homework", sortedAssociations.get(2).getKey());
        assertEquals("tarea", sortedAssociations.get(2).getValue());
        assertEquals("woman", sortedAssociations.get(3).getKey());
        assertEquals("mujer", sortedAssociations.get(3).getValue());
        assertEquals("town", sortedAssociations.get(4).getKey());
        assertEquals("pueblo", sortedAssociations.get(4).getValue());
        assertEquals("yes", sortedAssociations.get(5).getKey());
        assertEquals("sí", sortedAssociations.get(5).getValue());
    }

    @Test
    public void testSearch() {
        BinaryTree<Association<String, String>> dictionaryTree = new BinaryTree<>();
        

        dictionaryTree.insert(new Association<>("house", "casa"));
        dictionaryTree.insert(new Association<>("dog", "perro"));
        dictionaryTree.insert(new Association<>("homework", "tarea"));
        dictionaryTree.insert(new Association<>("woman", "mujer"));
        dictionaryTree.insert(new Association<>("town", "pueblo"));
        dictionaryTree.insert(new Association<>("yes", "sí"));


        assertEquals("casa", dictionaryTree.search("house"));
        assertEquals("perro", dictionaryTree.search("dog"));
        assertEquals("tarea", dictionaryTree.search("homework"));
        assertEquals("mujer", dictionaryTree.search("woman"));
        assertEquals("pueblo", dictionaryTree.search("town"));
        assertEquals("sí", dictionaryTree.search("yes"));
        assertNull(dictionaryTree.search("apple")); 
    }
}