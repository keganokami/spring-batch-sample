package com.batch.sample.config;

import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.batch.sample.model.User;

import lombok.Setter;

@Configuration
public class JdbcImportBatchConfig extends BatchConfig {

    @Autowired
    private DataSource dataSource;

    private static final String INSERT_SQL = "INSERT INTO sample_user(id,name,age) VALUES(:id,:name,:age)";

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> jdbcWriter() {
        BeanPropertyItemSqlParameterSourceProvider<User> provider = new BeanPropertyItemSqlParameterSourceProvider<>();
        return new JdbcBatchItemWriterBuilder<User>().itemSqlParameterSourceProvider(provider).sql(INSERT_SQL)
                .dataSource(this.dataSource).build();
    }

    @Bean
    public Step csvImportJdbcStep() {
        return this.stepBuilder.<User, User>chunk(5, null).reader(csvReader()).listener(this.readListener)
                .processor(judgeAgeProcessor).listener(processListener).writer(jdbcWriter()).listener(writeListener)
                .build();
    }

    @Bean("CsvImportJdbcJob")
    public Job csvImportJdbcJob() {
        return jobBuilder.incrementer(new RunIdIncrementer()).start(csvImportJdbcStep()).build();
    }
}
