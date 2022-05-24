package why.springcloudalibabanacos.controller;


import com.alibaba.fastjson2.JSONObject;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RefreshScope
public class ConfigController {

  private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

  @Value("${why}")
  private String why;
  @NacosValue(value = "${why}", autoRefreshed = true)
  private String whyWithNacos;

  private Environment environment;

  public ConfigController(Environment environment) {
    this.environment = environment;
  }

  @GetMapping("/echo")
  public String echo(String key) {
    logger.info("key=" + key);
    logger.debug("key=" + key);
    return environment.getProperty(key);
  }

  @GetMapping("/why")
  public JSONObject why() {
    logger.info("why=" + why);
    logger.debug("why=" + why);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("why", why);
    jsonObject.put("whyWithNacos", whyWithNacos);
    return jsonObject;
  }


}
