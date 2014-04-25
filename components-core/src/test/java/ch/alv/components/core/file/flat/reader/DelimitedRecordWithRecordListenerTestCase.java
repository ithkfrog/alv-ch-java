package ch.alv.components.core.file.flat.reader;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the Flat file loader.
 *
 * @since 1.0.0
 */
public class DelimitedRecordWithRecordListenerTestCase {

    static int recCount = 0;

    @Before
    public void init() {
        recCount = 0;
    }

    @Test
    public void testDelimitedColumnRead() {
        Transformer spec = TransformerFactory
                .getTransformer(DelimitedBean.class);
        spec.parseFlatFile(DelimitedRecordWithRecordListenerTestCase.class
                .getResourceAsStream("multi-record-delcol-file.txt"),
                new RecordListener() {

                    public boolean foundRecord(Object o) {
                        recCount++;
                        DelimitedBean bean = (DelimitedBean) o;
                        System.out.println(bean.getId());
                        // there are 2 records in the file and the assertions check for data
                        // in each
                        if (recCount == 1) {
                            assertFalse(bean.getActive());
                        } else {
                            assertTrue(bean.getActive());
                        }
                        return true;
                    }

                    public boolean unresolvableRecord(String rec) {
                        // nothing in here for now
                        fail("Not expecting this call. test not setup for this.");
                        return true;
                    }
                });
        // assertions are in the listener class below
    }

}
