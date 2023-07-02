package io.springbatch.springbatchlecture.job3;

import io.springbatch.springbatchlecture.job3.entity.Customer;
import io.springbatch.springbatchlecture.job3.entity.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
@RequiredArgsConstructor
public class TestItemWriter implements ItemWriter<Customer> {

    private final CustomerRepository customerRepository;

    @Override
    public void write(List<? extends Customer> list) throws Exception {
        customerRepository.saveAll(list);
    }
}
