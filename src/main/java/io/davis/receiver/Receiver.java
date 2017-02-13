package io.davis.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Davis on 17/2/13.
 */
public class Receiver {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  @KafkaListener(topics = "helloworld.t")
  public void receiveMessage(String message) {
    LOG.info("received message='{}'", message);
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }
}
