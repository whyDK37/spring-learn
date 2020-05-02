package springboot.webmvc.config;

import javax.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * {@link ResponseBodyAdvice} 可以在 body 写回之前处理响应信息。
 */
@Component
@ControllerAdvice
public class HeadResponseBodyAdvice implements ResponseBodyAdvice {

  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
      MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
      ServerHttpResponse response) {
    ServletServerHttpResponse ssResp = (ServletServerHttpResponse) response;
    HttpServletResponse resp = ssResp.getServletResponse();
    resp.setHeader("X-SPRING", "MVC");
    return body;
  }
}
