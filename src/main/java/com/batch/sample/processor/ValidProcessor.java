package com.batch.sample.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.sample.model.User;

import lombok.extern.slf4j.Slf4j;

@Component("ValidProcessor")
@StepScope
@Slf4j
public class ValidProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User arg0) throws Exception {
        log.info("processor");
        return arg0;
    }

}
