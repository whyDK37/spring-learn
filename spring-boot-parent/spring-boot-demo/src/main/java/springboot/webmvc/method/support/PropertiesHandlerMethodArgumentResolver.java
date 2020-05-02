package springboot.webmvc.method.support;

import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import springboot.webmvc.http.converter.PropertiesHttpMessageConverter;

/**
 * {@link Properties 类型} {@link HandlerMethodArgumentResolver}
 *
 * @author 小马哥
 * @since 2018/5/27
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  // 复用 PropertiesHttpMessageConverter
  private PropertiesHttpMessageConverter converter = new PropertiesHttpMessageConverter();

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return Properties.class.equals(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
    // Servlet Request API
    HttpServletRequest request = servletWebRequest.getRequest();

    HttpInputMessage httpInputMessage = new ServletServerHttpRequest(request);

    return converter.read(null, null, httpInputMessage);
  }
}
