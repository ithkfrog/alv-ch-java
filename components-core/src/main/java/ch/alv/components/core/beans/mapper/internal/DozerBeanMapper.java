package ch.alv.components.core.beans.mapper.internal;

import ch.alv.components.core.beans.mapper.BeanMapper;
import ch.alv.components.core.beans.mapper.MappingException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Adds the required BeanMapper-Functions to the {@link org.dozer.DozerBeanMapper}.
 *
 * @since 1.0.0
 */
public class DozerBeanMapper extends org.dozer.DozerBeanMapper implements BeanMapper, Serializable {

    private static final long serialVersionUID = 6837230244814109891L;

    private static final String EXCEPTION_MSG_MAPPING = "Error while mapping objects.";

    private String mappingFilesPattern = "dozer-mappings-*.xml";

    private String mappingFilesFolderPath = "dozer/";

    @PostConstruct
    private void init() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<String> fileNames = new ArrayList<>();
        Resource[] resources = resolver.getResources(mappingFilesFolderPath + mappingFilesPattern);
        for (Resource resource : resources) {
            fileNames.add(mappingFilesFolderPath + resource.getFilename());
        }
        setMappingFiles(fileNames);
    }

    /**
     * (non-Javadoc)
     *
     * @see BeanMapper#mapObject(Object, Class)
     */
    @Override
    public <T> T mapObject(Object source, Class<T> targetClass) {
        try {
            if (source == null) {
                return null;
            }
            return super.map(source, targetClass);
        } catch (org.dozer.MappingException me) {
            throw new MappingException(EXCEPTION_MSG_MAPPING, me);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see BeanMapper#mapObject(Object, Object)
     */
    @Override
    public void mapObject(Object source, Object target) {
        try {
            if (source == null) {
                return;
            }
            super.map(source, target);
        } catch (org.dozer.MappingException me) {
            throw new MappingException(EXCEPTION_MSG_MAPPING, me);
        }

    }

    /**
     * (non-Javadoc)
     *
     * @see BeanMapper#mapCollection(java.util.Collection, Class)
     */
    @Override
    public <T> List<T> mapCollection(Collection<?> sourceCollection, Class<T> targetClass) {
        if (sourceCollection == null) {
            return null;
        }
        List<T> target = new ArrayList<>(sourceCollection.size());
        for (Object item : sourceCollection) {
            target.add(this.mapObject(item, targetClass));
        }
        return target;
    }

    public String getMappingFilesPattern() {
        return mappingFilesPattern;
    }

    public void setMappingFilesPattern(String mappingFilesPattern) {
        this.mappingFilesPattern = mappingFilesPattern;
    }

    public String getMappingFilesFolderPath() {
        return mappingFilesFolderPath;
    }

    public void setMappingFilesFolderPath(String mappingFilesFolderPath) {
        this.mappingFilesFolderPath = mappingFilesFolderPath;
    }

}
