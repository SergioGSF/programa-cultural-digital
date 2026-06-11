package br.com.projeto.arenapernambuco.config;

import java.lang.management.ManagementFactory;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class MonitoringInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("uptimeMs", ManagementFactory.getRuntimeMXBean().getUptime());
    }
}