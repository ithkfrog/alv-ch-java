package ch.alv.components.web.i18n;

import ch.alv.components.core.enums.Language;
import ch.alv.components.data.model.TextConstant;
import ch.alv.components.web.context.ServletRequestProvider;
import ch.alv.components.web.dto.TranslatedText;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.web.endpoint.SpringBeansEndpointProvider} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/i18n-factories-tests.xml")
public class RequestAwareTranslatedTextFactoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private ServletRequestProvider requestProvider;

    @Resource
    private RequestAwareTranslatedTextFactory factory;

    @Test
    public void testList() {
        ((MockHttpServletRequest) requestProvider.getRequest()).removeParameter("language");
        List<Object> list = new ArrayList<>();
        TextConstant constantDe = new TextConstant();
        constantDe.setText("testInGerman");
        constantDe.setLanguage(Language.GERMAN);
        TextConstant constantFr = new TextConstant();
        constantFr.setText("testInFrench");
        constantFr.setLanguage(Language.FRENCH);
        TextConstant constantIt = new TextConstant();
        constantIt.setText("testInItalian");
        constantIt.setLanguage(Language.ITALIAN);
        list.add("nonTextConstantEntry");
        list.add(constantFr);
        list.add(constantDe);
        list.add(constantIt);


        TranslatedText text = (TranslatedText) factory.createBean(list, null, null);
        assertEquals("testInGerman", text.getText());
    }

    @Test
    public void testListWithLanguage() {
        ((MockHttpServletRequest) requestProvider.getRequest()).addParameter("language", "fr");
        List<Object> list = new ArrayList<>();
        TextConstant constantDe = new TextConstant();
        constantDe.setText("testInGerman");
        constantDe.setLanguage(Language.GERMAN);
        TextConstant constantFr = new TextConstant();
        constantFr.setText("testInFrench");
        constantFr.setLanguage(Language.FRENCH);
        list.add(constantDe);
        list.add(constantFr);
        list.add("nonTextConstantEntry");

        TranslatedText text = (TranslatedText) factory.createBean(list, null, null);
        assertEquals("testInFrench", text.getText());
    }

    @Test
    public void testNotList() {
        TranslatedText text = (TranslatedText) factory.createBean("test", null, null);
        assertEquals("", text.getText());
    }

    @Test
    public void testEmptyList() {
        TranslatedText text = (TranslatedText) factory.createBean(new ArrayList<>(), null, null);
        assertEquals("", text.getText());
    }

}
