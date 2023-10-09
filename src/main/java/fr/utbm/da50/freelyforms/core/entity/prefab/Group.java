package fr.utbm.da50.freelyforms.core.entity.prefab;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The Group class holds the form fields that will be displayed on the front-end for the user to fill.
 *
 * @author Pourriture
 */
public class Group {

    /** Name of the group. Should be unique within a prefab.
     */
    private String name;
    /** Group display label on the frontend
     * */
    private String label;

    /** Mouse-over text over the group
     * */
    private String caption;

    /** A group holds one or more form fields and ensures they will be grouped together.
     * Field names are identifying within the group and therefore unique within the fields.
     * */
    ArrayList<Field> fields;




    @PersistenceCreator
    public Group(String name, String label, String caption, ArrayList<Field> fields) {
        this.name = name;
        this.label = label;
        this.caption = caption;
        this.fields = fields;
    }

    // (test constructor value)
    static int count = 0;
    // Test constructor
    public Group() {
        this.name = "DefaultGroupName" + count;
        this.label="DefaultGroupLabel";
        this.caption = "caption";
        this.fields = new ArrayList<>();
        count++;

        this.fields.add(new Field());
        this.fields.add(new Field());
        this.fields.add(new Field());
    }

    /**
     * @return a string representing the prefab
     */
    public String inspect() {
        StringBuilder ret;
        ret = new StringBuilder("\ngroup " + name + " label " + label + " caption " + caption + " fields");
        for (Field f : fields) {
            ret.append("\n\t").append(f.inspect());
        }
        return ret.toString();
    }

    /**
     * Verifies that a group has its members, that no two of its fields share a name, and that those fields are valid themselves
     * @return true if the groiup is valid, false if it is not
     */
    public boolean verifyGroupValidity() {
        boolean check = true;

        if (name == null || label == null || caption == null)
            check = false;

        Set<String> fieldNames = new HashSet<>();
        for (Field f : fields) {
            if (!fieldNames.add(f.getName())){
                // Set.add returns false if the set does not change (duplicate entry)
                System.out.println("At least one duplicate entry in field list for group " + this.name);
                check = false;
            }
            if (!f.verifyFieldValidity()){
                check = false;
                System.out.println("Field " + f.getName() + " invalid");
            }
        }
        return check;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public ArrayList<Field> getFields() {
        return fields;
    }
    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }
}