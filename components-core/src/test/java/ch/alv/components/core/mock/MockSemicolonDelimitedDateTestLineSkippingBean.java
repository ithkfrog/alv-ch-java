package ch.alv.components.core.mock;

import ch.alv.components.core.file.flat.reader.annotations.FlatFileColumn;
import ch.alv.components.core.file.flat.reader.annotations.FlatFileConversion;

import java.util.Date;

/**
 * This is a bean that is to be filled in from a comma (default) delimited
 * record format.
 *
 * @since 1.0.0
 */
@FlatFileConversion(skipFirstLine = true)
public class MockSemicolonDelimitedDateTestLineSkippingBean {

    @FlatFileColumn(position = 0)
    private int id;

    @FlatFileColumn(position = 1, format = "yyyy-MM-dd")
    private Date simpleDate;

    @FlatFileColumn(position = 2, format = "yyyy-MM-dd HH:mm:ss")
    private Date complexDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(Date simpleDate) {
        this.simpleDate = simpleDate;
    }

    public Date getComplexDate() {
        return complexDate;
    }

    public void setComplexDate(Date complexDate) {
        this.complexDate = complexDate;
    }
}
