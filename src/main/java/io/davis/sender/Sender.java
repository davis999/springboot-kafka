package io.davis.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by Davis on 17/2/13.
 */
public class Sender {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

  @Autowired
  private KafkaTemplate<Integer, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {
    // the KafkaTemplate provides asynchronous send methods returning a
    // Future
    ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate
        .send(topic, message);

    // you can register a callback with the listener to receive the result
    // of the send asynchronously
    future.addCallback(
        new ListenableFutureCallback<SendResult<Integer, String>>() {

          @Override
          public void onSuccess(
              SendResult<Integer, String> result) {
            LOG.info("sent message='{}' with offset={}",
                message,
                result.getRecordMetadata().offset());
          }

          @Override
          public void onFailure(Throwable ex) {
            LOG.error("unable to send message='{}'",
                message, ex);
          }
        });

    // alternatively, to block the sending thread, to await the result,
    // invoke the future’s get() method
  }
}
