package why.method;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.support.UriComponentsContributor;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;
import org.springframework.web.util.UriComponentsBuilder;
import why.web.bind.annotation.RequestJSONParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.beans.PropertyEditor;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Resolves method arguments annotated with @{@link RequestParam}, arguments of
 * type {@link MultipartFile} in conjunction with Spring's {@link MultipartResolver}
 * abstraction, and arguments of type {@code javax.servlet.http.Part} in conjunction
 * with Servlet 3.0 multipart requests. This resolver can also be created in default
 * resolution mode in which simple types (int, long, etc.) not annotated with
 * {@link RequestParam @RequestParam} are also treated as request parameters with
 * the parameter name derived from the argument name.
 *
 * <p>If the method parameter type is {@link Map}, the name specified in the
 * annotation is used to resolve the request parameter String value. The value is
 * then converted to a {@link Map} via type conversion assuming a suitable
 * {@link Converter} or {@link PropertyEditor} has been registered.
 * Or if a request parameter name is not specified the
 * {@link RequestParamMapMethodArgumentResolver} is used instead to provide
 * access to all request parameters in the form of a map.
 *
 * <p>A {@link WebDataBinder} is invoked to apply type conversion to resolved request
 * header values that don't yet match the method parameter type.
 *
 * @author Arjen Poutsma
 * @author Rossen Stoyanchev
 * @author Brian Clozel
 * @see RequestParamMapMethodArgumentResolver
 * @since 3.1
 */
public class RequestJSONParamMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver
        implements UriComponentsContributor {

    private static final TypeDescriptor STRING_TYPE_DESCRIPTOR = TypeDescriptor.valueOf(String.class);

    private final boolean useDefaultResolution;


    /**
     * Create a new {@link RequestJSONParamMethodArgumentResolver} instance.
     *
     * @param useDefaultResolution in default resolution mode a method argument
     *                             that is a simple type, as defined in {@link BeanUtils#isSimpleProperty},
     *                             is treated as a request parameter even if it isn't annotated, the
     *                             request parameter name is derived from the method parameter name.
     */
    public RequestJSONParamMethodArgumentResolver(boolean useDefaultResolution) {
        this.useDefaultResolution = useDefaultResolution;
    }

    /**
     * Create a new {@link RequestJSONParamMethodArgumentResolver} instance.
     *
     * @param beanFactory          a bean factory used for resolving  ${...} placeholder
     *                             and #{...} SpEL expressions in default values, or {@code null} if default
     *                             values are not expected to contain expressions
     * @param useDefaultResolution in default resolution mode a method argument
     *                             that is a simple type, as defined in {@link BeanUtils#isSimpleProperty},
     *                             is treated as a request parameter even if it isn't annotated, the
     *                             request parameter name is derived from the method parameter name.
     */
    public RequestJSONParamMethodArgumentResolver(@Nullable ConfigurableBeanFactory beanFactory,
                                                  boolean useDefaultResolution) {

        super(beanFactory);
        this.useDefaultResolution = useDefaultResolution;
    }


    /**
     * Supports the following:
     * <ul>
     * <li>@RequestParam-annotated method arguments.
     * This excludes {@link Map} params where the annotation does not specify a name.
     * See {@link RequestParamMapMethodArgumentResolver} instead for such params.
     * <li>Arguments of type {@link MultipartFile} unless annotated with @{@link RequestPart}.
     * <li>Arguments of type {@code Part} unless annotated with @{@link RequestPart}.
     * <li>In default resolution mode, simple type arguments even if not with @{@link RequestParam}.
     * </ul>
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(RequestJSONParam.class)) {
            if (Map.class.isAssignableFrom(parameter.nestedIfOptional().getNestedParameterType())) {
                RequestJSONParam requestParam = parameter.getParameterAnnotation(RequestJSONParam.class);
                return (requestParam != null && StringUtils.hasText(requestParam.name()));
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        RequestJSONParam ann = parameter.getParameterAnnotation(RequestJSONParam.class);
        return (ann != null ? new RequestParamNamedValueInfo(ann) : new RequestParamNamedValueInfo());
    }

    @Override
    @Nullable
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);

        if (servletRequest != null) {
            Object mpArg = MultipartResolutionDelegate.resolveMultipartArgument(name, parameter, servletRequest);
            if (mpArg != MultipartResolutionDelegate.UNRESOLVABLE) {
                return mpArg;
            }
        }

        Object arg = null;
        String[] paramValues = request.getParameterValues(name);
        if (paramValues != null) {
            arg = JSON.parseObject(paramValues[0], parameter.getGenericParameterType());
//                arg = (paramValues.length == 1 ? paramValues[0] : paramValues);
        }
        return arg;
    }

    @Override
    protected void handleMissingValue(String name, MethodParameter parameter, NativeWebRequest request)
            throws Exception {

        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        if (MultipartResolutionDelegate.isMultipartArgument(parameter)) {
            if (servletRequest == null || !MultipartResolutionDelegate.isMultipartRequest(servletRequest)) {
                throw new MultipartException("Current request is not a multipart request");
            } else {
                throw new MissingServletRequestPartException(name);
            }
        } else {
            throw new MissingServletRequestParameterException(name,
                    parameter.getNestedParameterType().getSimpleName());
        }
    }

    @Override
    public void contributeMethodArgument(MethodParameter parameter, @Nullable Object value,
                                         UriComponentsBuilder builder, Map<String, Object> uriVariables, ConversionService conversionService) {

        Class<?> paramType = parameter.getNestedParameterType();
        if (Map.class.isAssignableFrom(paramType) || MultipartFile.class == paramType || Part.class == paramType) {
            return;
        }

        RequestJSONParam requestParam = parameter.getParameterAnnotation(RequestJSONParam.class);
        String name = (requestParam != null && StringUtils.hasLength(requestParam.name()) ?
                requestParam.name() : parameter.getParameterName());
        Assert.state(name != null, "Unresolvable parameter name");

        parameter = parameter.nestedIfOptional();
        if (value instanceof Optional) {
            value = ((Optional<?>) value).orElse(null);
        }

        if (value == null) {
            if (requestParam != null &&
                    (!requestParam.required() || !requestParam.defaultValue().equals(ValueConstants.DEFAULT_NONE))) {
                return;
            }
            builder.queryParam(name);
        } else if (value instanceof Collection) {
            for (Object element : (Collection<?>) value) {
                element = formatUriValue(conversionService, TypeDescriptor.nested(parameter, 1), element);
                builder.queryParam(name, element);
            }
        } else {
            builder.queryParam(name, formatUriValue(conversionService, new TypeDescriptor(parameter), value));
        }
    }

    @Nullable
    protected String formatUriValue(
            @Nullable ConversionService cs, @Nullable TypeDescriptor sourceType, @Nullable Object value) {

        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return (String) value;
        } else if (cs != null) {
            return (String) cs.convert(value, sourceType, STRING_TYPE_DESCRIPTOR);
        } else {
            return value.toString();
        }
    }


    private static class RequestParamNamedValueInfo extends NamedValueInfo {

        public RequestParamNamedValueInfo() {
            super("", false, ValueConstants.DEFAULT_NONE);
        }

        public RequestParamNamedValueInfo(RequestJSONParam annotation) {
            super(annotation.name(), annotation.required(), annotation.defaultValue());
        }
    }

}
