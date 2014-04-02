package ch.alv.components.web.dto;

/**
 * Container for final translations
 *
 * @since 1.0.0
 */
public class TranslatedText {

    private String text;

    public TranslatedText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
