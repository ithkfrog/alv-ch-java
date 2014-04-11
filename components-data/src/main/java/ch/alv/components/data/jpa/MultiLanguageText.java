package ch.alv.components.data.jpa;


import ch.alv.components.core.enums.Language;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MultiLanguageText entity (works with table 'module_core_multilanguage_text')
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "module_core_multilanguage_text")
public class MultiLanguageText extends BaseJpaModelItem {

    @Column(name="text_key", nullable = false)
    private String key;

    @ManyToMany(targetEntity = TextConstant.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "module_core_constant_to_multilanguage",
            joinColumns = {@JoinColumn(name = "multilanguage_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "text_constant_id", referencedColumnName = "id")})
    private List<TextConstant> values = new ArrayList<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<TextConstant> getValues() {
        return values;
    }

    public void setValues(List<TextConstant> values) {
        this.values = values;
    }

    public String getValueFor(Language language) {
        for (TextConstant textConstant : values) {
            if (textConstant.getLanguage() == language) {
                return textConstant.getText();
            }
        }
        throw new NoSuchTextConstantException(key, language);
    }
}
