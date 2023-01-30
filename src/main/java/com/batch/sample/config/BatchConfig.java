package com.batch.sample.config;

import java.nio.charset.StandardCharsets;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.batch.sample.model.User;

@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    protected JobBuilder jobBuilder;

    @Autowired
    protected StepBuilder stepBuilder;

    @Autowired
    @Qualifier("ValidProcessor")
    protected ItemProcessor<User, User> judgeAgeProcessor;

    @Autowired
    protected ItemReadListener<User> readListener;

    @Autowired
    protected ItemProcessListener<User,User> processListener;

    @Autowired
    protected ItemWriteListener<User> writeListener;

    @Autowired
    protected SampleProperty sampleProperty;

    @Bean
    @StepScope
    public FlatFileItemReader<User> csvReader() {
        String[] columnNameArray = new String[] {"id", "name", "age"};

        return new FlatFileItemReaderBuilder<User>()
        .name("userCsvReader")
        .resource(new ClassPathResource(sampleProperty.getCsvPath()))
        .linesToSkip(1)
        .encoding(StandardCharsets.UTF_8.name())
        .delimited()
        .names(columnNameArray)
        .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
            {
                setTargetType(User.class);
            }
        }).build();
    }
}
