package io.davis.config;

import io.davis.producer.SpringBootKafkaProducer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Davis on 17/2/13.
 */
@Configuration
public class BeanFactory {
  @Bean
  public SpringBootKafkaProducer initProducer() {
    return new SpringBootKafkaProducer();
  }
}
