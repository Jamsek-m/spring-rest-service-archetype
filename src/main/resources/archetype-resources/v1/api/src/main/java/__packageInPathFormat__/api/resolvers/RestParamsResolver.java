package ${package}.api.resolvers;

import ${package}.services.common.types.RestParams;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static ${package}.services.common.types.RestParams.DEFAULT_LIMIT;
import static ${package}.services.common.types.RestParams.DEFAULT_OFFSET;

@Component
public class RestParamsResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(RestParams.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String offsetParam = webRequest.getParameter("offset");
        String limitParam = webRequest.getParameter("limit");
        String orderParam = webRequest.getParameter("order");

        if (offsetParam != null && limitParam != null) {
            if (orderParam != null) {
                return new RestParams(
                    getOffset(offsetParam),
                    getLimit(limitParam),
                    orderParam
                );
            } else {
                return new RestParams(
                    getOffset(offsetParam),
                    getLimit(limitParam)
                );
            }
        }
        return new RestParams();
    }

    private Integer getLimit(String limitParam) {
        try {
            return Integer.min(
                Integer.parseInt(limitParam),
                RestParams.MAX_LIMIT
            );
        } catch (NumberFormatException e) {
            return DEFAULT_LIMIT;
        }
    }

    private Integer getOffset(String offsetParam) {
        try {
            return Integer.parseInt(offsetParam);
        } catch (NumberFormatException e) {
            return DEFAULT_OFFSET;
        }
    }
}
