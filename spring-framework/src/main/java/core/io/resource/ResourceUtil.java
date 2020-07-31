package core.io.resource;

import java.io.IOException;
import java.io.Reader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

public class ResourceUtil {

  public static String getContent(Resource resource) {
    return getContent(resource, "utf-8");
  }

  public static String getContent(Resource resource, String charset) {

    EncodedResource encodedResource = new EncodedResource(resource, charset);
    StringBuilder sb = new StringBuilder();
    try (Reader reader = encodedResource.getReader()) {
//      sb.append()
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return sb.toString();
  }


}
