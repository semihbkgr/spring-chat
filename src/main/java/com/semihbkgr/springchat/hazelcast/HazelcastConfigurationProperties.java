package com.semihbkgr.springchat.hazelcast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("hazelcast")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HazelcastConfigurationProperties {

    private List<String> addresses;

    private String clusterName;

    private String instanceName;

}
