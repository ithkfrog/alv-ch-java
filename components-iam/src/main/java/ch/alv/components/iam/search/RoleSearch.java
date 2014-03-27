package ch.alv.components.iam.search;

import ch.alv.components.persistence.repository.ParamValuesProvider;
import ch.alv.components.persistence.search.SearchParam;
import ch.alv.components.web.search.WebSearch;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Default {@link WebSearch} for {@link ch.alv.components.iam.model.Role} entities
 *
 * @since 1.0.0
 */
@Component
public class RoleSearch implements WebSearch {

    public String getName() {
        return "roleSearch";
    }

    @Override
    public List<SearchParam> getParams() {
        List<SearchParam> params = new ArrayList<>();
        params.add(new SearchParam("name", String.class));
        return params;
    }

    @Override
    public String getQueryFactoryName() {
        return "andQueryFactoryImpl";
    }

    @Override
    public Class<? extends ParamValuesProvider> getParamValuesProviderClass() {
        return RoleSearchParamValuesProvider.class;
    }
}
