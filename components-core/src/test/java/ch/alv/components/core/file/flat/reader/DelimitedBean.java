package ch.alv.components.core.file.flat.reader;

import ch.alv.components.core.file.flat.reader.annotations.FlatFileColumn;
import ch.alv.components.core.file.flat.reader.annotations.Transform;

/**
 * This is a bean that is to be filled in from a comma (default) delimited
 * record format.
 *
 * @since 1.0.0
 */
@Transform(skipFirstLine = true)
public class DelimitedBean {

    @FlatFileColumn(position = 0)
    private int id;

    @FlatFileColumn(position = 1)
    private String firstName;

    @FlatFileColumn(position = 2)
    private String lastName;

    @FlatFileColumn(position = 3)
    private Boolean active;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
