package ch.alv.components.web.endpoint;

import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class to ease the configuration of {@link Endpoint} objects.
 *
 * @since 1.0.0
 */
public class EndpointHelper {

    public static List<HttpMethod> createMethodList(HttpMethod... methods) {
        List<HttpMethod> list = new ArrayList<>();
        if (methods == null || methods.length == 0) {
            return list;
        }
        Collections.addAll(list, methods);
        return list;
    }

    public static List<HttpMethod> createAllMethodsList() {
        return EndpointHelper.createMethodList(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);
    }

}
