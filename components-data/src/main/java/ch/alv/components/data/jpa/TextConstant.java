package ch.alv.components.data.jpa;


import ch.alv.components.core.enums.Language;

import javax.persistence.*;

/**
 * The TextConstant entity (works with table 'module_core_text_constant')
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "module_core_text_constant")
public class TextConstant extends BaseJpaModelItem {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(nullable = false)
    private String text;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
