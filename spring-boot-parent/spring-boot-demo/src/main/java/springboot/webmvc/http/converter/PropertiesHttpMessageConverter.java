package springboot.webmvc.http.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * {@link Properties} {@link HttpMessageConverter} 实现
 *
 * @author 小马哥
 * @since 2018/5/27
 */
public class PropertiesHttpMessageConverter extends
    AbstractGenericHttpMessageConverter<Properties> {

  public PropertiesHttpMessageConverter() {
    // 设置支持的 MediaType
    super(new MediaType("text", "properties"));
  }

  @Override
  protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage)
      throws IOException, HttpMessageNotWritableException {
    // Properties -> String
    // OutputStream -> Writer
    Charset charset = getCharset(outputMessage.getHeaders());

    // 字节输出流
    OutputStream outputStream = outputMessage.getBody();
    // 字符输出流
    Writer writer = new OutputStreamWriter(outputStream, charset);
    // Properties 写入到字符输出流
    properties.store(writer, "From PropertiesHttpMessageConverter");
  }

  @Override
  protected Properties readInternal(Class<? extends Properties> clazz,
      HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

    // 字符流 -> 字符编码
    // 从 请求头 Content-Type 解析编码
    Charset charset = getCharset(inputMessage.getHeaders());

    // 字节流
    InputStream inputStream = inputMessage.getBody();
    InputStreamReader reader = new InputStreamReader(inputStream, charset);
    Properties properties = new Properties();
    // 加载字符流成为 Properties 对象
    properties.load(reader);
    return properties;
  }

  private Charset getCharset(HttpHeaders httpHeaders) {
    MediaType mediaType = httpHeaders.getContentType();
    // 获取字符编码
    Charset charset = StandardCharsets.UTF_8;
    if (mediaType != null) {
      // 当 charset 不存在时，使用 UTF-8
      charset = mediaType.getCharset() == null ? charset : mediaType.getCharset();
    }
    return charset;
  }

  @Override
  public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
      throws IOException, HttpMessageNotReadableException {
    return readInternal(null, inputMessage);
  }
}
