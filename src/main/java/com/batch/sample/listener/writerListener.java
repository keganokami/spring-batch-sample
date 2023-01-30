package com.batch.sample.listener;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;
import org.springframework.stereotype.Component;

import com.batch.sample.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class writerListener implements ItemWriteListener<User> {

    @Override
    public void afterWrite(Chunk<? extends User> items) {
        log.debug("AfterWrite::count={}",items.size());
    }
}
