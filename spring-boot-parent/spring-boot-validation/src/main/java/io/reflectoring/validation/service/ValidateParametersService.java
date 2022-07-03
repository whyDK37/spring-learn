package io.reflectoring.validation.service;

import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.stereotype.Service;

@Service
public class ValidateParametersService {

  @Trace(operationName = "返回默认值")
  @Tag(key = "id", value = "${id}")
  @Tag(key = "id2", value = "${id}")
  public static String validatePathVariable(int id) {
    String traceId = TraceContext.traceId();
    System.out.println("traceId = " + traceId);
    return "valid " + id;
  }
}
