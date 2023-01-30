package com.batch.sample.listener;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import com.batch.sample.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReadListner implements ItemReadListener<User> {
    @Override
    public void afterRead(User user) {
        log.debug("AfterRead: {}", user);
    }
}
