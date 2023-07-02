package io.springbatch.springbatchlecture.job3;

import io.springbatch.springbatchlecture.job3.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class Job3Configuration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final TestItemWriter testItemWriter;

    @Bean
    public Job testJob() {
        return jobBuilderFactory.get("testJob")
                .start(testStep())
                .build();
    }

    @Bean
    @JobScope
    public Step testStep() {
        return stepBuilderFactory.get("testStep")
                .<Customer, Customer>chunk(3)
                .reader(reader())
                .writer(testItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Customer> reader() {
        return new JpaPagingItemReaderBuilder<Customer>()
                .name("JpaPagingItemReaderBuilder")
                .queryString("select c from Customer c")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(3)
                .build();
    }

}
