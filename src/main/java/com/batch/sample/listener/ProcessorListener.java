package com.batch.sample.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import com.batch.sample.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProcessorListener implements ItemProcessListener<User, User> {
    @Override
    public void afterProcess(User user, User user2) {
        log.info("after: processorListener");
    }
}
