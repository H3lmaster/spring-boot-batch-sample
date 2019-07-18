package com.ef.parser.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

import com.ef.parser.model.Log;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
    private Environment env;
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Log> reader() {
        return new FlatFileItemReaderBuilder<Log>()
            .name("logItemReader")
            .resource(new FileSystemResource(env.getProperty("accesslog")))
            .delimited()
            .delimiter("|")
            .names(new String[]{"date", "ip", "request", "status", "userAgent"})
            .fieldSetMapper(new BeanWrapperFieldSetMapper<Log>() {{
                setTargetType(Log.class);
            }})
            .build();
    }

    @Bean
    public JdbcBatchItemWriter<Log> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Log>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO log (date, ip, request, status, userAgent) VALUES (:date, :ip, :request, :status, :userAgent)")
            .dataSource(dataSource)
            .build();
    }

    @Bean
    public Job importLogJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importLogJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Log> writer) {
        return stepBuilderFactory.get("step1")
            .<Log, Log> chunk(10)
            .reader(reader())
            .writer(writer)
            .build();
    }
}
