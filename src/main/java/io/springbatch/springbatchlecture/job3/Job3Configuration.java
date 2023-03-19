package io.springbatch.springbatchlecture.job3;

import io.springbatch.springbatchlecture.job3.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Job3Configuration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job job3() {
        return jobBuilderFactory.get("job3")
                .start(step33())
                .build();
    }

    @Bean
    public Step step33() {
        return stepBuilderFactory.get("step33")
                .<Customer, String>chunk(3)
                .reader(reader())
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> list) throws Exception {
                        System.out.println(list);
                    }
                })
                .build();
    }

    @Bean
    public ItemReader reader() {
        return new JpaCursorItemReaderBuilder()
                .name("JpaCursorItemReaderBuilder")
                .queryString("select c from Customer c")
                .entityManagerFactory(entityManagerFactory)
//                .maxItemCount(6)
                .currentItemCount(4)
                .build();
    }

}
