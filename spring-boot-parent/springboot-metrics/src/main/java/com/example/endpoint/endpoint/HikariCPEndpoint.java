package com.example.endpoint.endpoint;

import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;


@Endpoint(id = "hikari")
public class HikariCPEndpoint {

  HikariDataSource dataSource;

  public HikariCPEndpoint(HikariDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @ReadOperation
  public Map<String, Object> info() {

    Map<String, Object> info = new HashMap<>();
    //连接池配置
    HikariConfigMXBean hikariConfigMXBean = dataSource.getHikariConfigMXBean();
    info.put("total", hikariConfigMXBean.getMaximumPoolSize());

    //连接池运行状态
    HikariPoolMXBean hikariPoolMXBean = dataSource.getHikariPoolMXBean();
    info.put("active", hikariPoolMXBean.getActiveConnections());
    info.put("idle", hikariPoolMXBean.getIdleConnections());
    info.put("thread", hikariPoolMXBean.getThreadsAwaitingConnection());
    return info;
  }

  @WriteOperation
  public void setMax(int max) {
    HikariConfigMXBean hikariConfigMXBean = dataSource.getHikariConfigMXBean();
    hikariConfigMXBean.setMaximumPoolSize(max);
  }

}
