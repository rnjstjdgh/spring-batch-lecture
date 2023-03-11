package io.springbatch.springbatchlecture.job1;

import io.springbatch.springbatchlecture.job1.tasklet.ExecutionContextTasklet1;
import io.springbatch.springbatchlecture.job1.tasklet.ExecutionContextTasklet2;
import io.springbatch.springbatchlecture.job1.tasklet.ExecutionContextTasklet3;
import io.springbatch.springbatchlecture.job1.tasklet.ExecutionContextTasklet4;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JobInstanceConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ExecutionContextTasklet1 executionContextTasklet1;
    private final ExecutionContextTasklet2 executionContextTasklet2;
    private final ExecutionContextTasklet3 executionContextTasklet3;
    private final ExecutionContextTasklet4 executionContextTasklet4;
    private final JobRepositoryListner jobRepositoryListner;

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                .start(step1())
                .next(step2())
                .next(step3())
                .listener(jobRepositoryListner)
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(executionContextTasklet1).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<String, String>chunk(3)
                .reader(new ItemReader<String>() {
                    @Override
                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        return null;
                    }
                })
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String s) throws Exception {
                        return null;
                    }
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> list) throws Exception {

                    }
                }).build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .partitioner(step1())
                .gridSize(2)
                .build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .job(job1())
                .build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .flow(flow())
                .build();
    }

    @Bean
    public Flow flow() {
        FlowBuilder<Flow> flowFlowBuilder = new FlowBuilder<>("flow");
        flowFlowBuilder.start(step2()).end();
        return flowFlowBuilder.build();
    }
}
