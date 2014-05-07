package ch.alv.components.core.beans.mapper.internal;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Adapter class for the apache commons {@link org.apache.commons.beanutils.BeanUtils}.
 *
 * @since 1.0.0
 */
public class BeanUtilsAdapter extends BeanUtils {

    private final static Log LOG = LogFactory.getLog(BeanUtilsAdapter.class);

    public static boolean populateQuietly(Object bean, Map properties) {
        try {
            BeanUtilsBean.getInstance().populate(bean, properties);
            return true;
        } catch (Exception e) {
            LOG.error("Error while mapping data.", e);
            return false;
        }
    }
}
