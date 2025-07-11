import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ImmutablePerson {
    private final String name;          // Immutable field
    private final int age;              // Immutable field
    private final Map<String, String> attributes; // Mutable object (needs defensive copying)

    /**
     * Constructor to initialize the ImmutablePerson object.
     *
     * @param name       Person's name. Cannot be null.
     * @param age        Person's age.
     * @param attributes Map of attributes (e.g., "HairColor" -> "Brown"). Cannot be null.
     */
    public ImmutablePerson(String name, int age, Map<String, String> attributes) {
        if (name == null || attributes == null) {
            throw new IllegalArgumentException("Fields cannot be null.");
        }
        this.name = name;
        this.age = age;

        // Defensive copy of the mutable map
        this.attributes = Collections.unmodifiableMap(new HashMap<>(attributes));
    }

    /**
     * Getter for the name.
     *
     * @return Immutable String name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the age.
     *
     * @return Immutable integer age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter for the attributes.
     *
     * @return Unmodifiable map of attributes.
     */
    public Map<String, String> getAttributes() {
        return attributes; // Returning the already unmodifiable map
    }

    /**
     * Provides a readable string representation of the ImmutablePerson object.
     *
     * @return String representing the object's state.
     */
    @Override
    public String toString() {
        return "ImmutablePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", attributes=" + attributes +
                '}';
    }

    /**
     * Compares two ImmutablePerson objects for equality.
     *
     * @param obj Object to compare.
     * @return true if the objects are equivalent; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ImmutablePerson that = (ImmutablePerson) obj;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(attributes, that.attributes);
    }

    /**
     * Generates a hash code for the ImmutablePerson.
     *
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, age, attributes);
    }
}