package ch.alv.components.core.mock;

import ch.alv.components.core.file.flat.reader.FlatFileColumnSeparatorType;
import ch.alv.components.core.file.flat.reader.annotations.FlatFileColumn;
import ch.alv.components.core.file.flat.reader.annotations.FlatFileConversion;

/**
 * This is a bean that is to be filled in from a comma (default) delimited
 * record format.
 *
 * @since 1.0.0
 */
@FlatFileConversion(skipFirstLine = true, separatorType = FlatFileColumnSeparatorType.FIXLENGTH)
public class MockFixLengthBean implements MockFlatFileBean {

    @FlatFileColumn(position = 0, start = 0, end = 5)
    private Integer id;

    @FlatFileColumn(position = 1, start = 5, end = 19)
    private String firstName;

    @FlatFileColumn(position = 2, start = 19, end = 39)
    private String lastName;

    @FlatFileColumn(position = 3, start = 39, end = 49)
    private Boolean active;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
