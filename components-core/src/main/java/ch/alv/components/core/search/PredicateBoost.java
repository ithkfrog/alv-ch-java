package ch.alv.components.core.search;

/**
 * Used to indicate how important a certain {@link Predicate} is.
 *
 * @since 1.0.0
 */
public class PredicateBoost {

    private String attributeName;

    private float boostFactor;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public float getBoostFactor() {
        return boostFactor;
    }

    public void setBoostFactor(float boostFactor) {
        this.boostFactor = boostFactor;
    }
}
