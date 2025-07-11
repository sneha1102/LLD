//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.HashMap;
import java.util.Map;
public class Main {
        public static void main(String[] args) {
            // Original attributes map
            Map<String, String> attributes = new HashMap<>();
            attributes.put("HairColor", "Brown");
            attributes.put("EyeColor", "Blue");

            // Create an ImmutablePerson object
            ImmutablePerson person = new ImmutablePerson("John", 30, attributes);

            // Display initial state
            System.out.println("ImmutablePerson: " + person);

            // Try modifying the original attributes map
            attributes.put("Height", "6ft");
            System.out.println("Original attributes modified: " + attributes);
            System.out.println("ImmutablePerson after original map modification: " + person);

            // Try modifying the returned attributes map
            Map<String, String> retrievedAttributes = person.getAttributes();
            try {
                retrievedAttributes.put("Weight", "75kg"); // This will throw an exception
            } catch (UnsupportedOperationException e) {
                System.out.println("Cannot modify attributes map: " + e);
            }

            // Verify the immutable state
            System.out.println("ImmutablePerson remains unchanged: " + person);
        }
    }
