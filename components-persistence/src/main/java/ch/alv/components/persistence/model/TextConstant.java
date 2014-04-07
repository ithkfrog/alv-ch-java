package ch.alv.components.persistence.model;


import ch.alv.components.core.enums.Language;
import ch.alv.components.persistence.model.BaseJpaModelItem;

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
