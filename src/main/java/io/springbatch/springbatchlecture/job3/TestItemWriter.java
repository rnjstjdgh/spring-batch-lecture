package io.springbatch.springbatchlecture.job3;

import io.springbatch.springbatchlecture.job3.entity.Customer;
import io.springbatch.springbatchlecture.job3.entity.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@StepScope
@Component
@RequiredArgsConstructor
public class TestItemWriter implements ItemWriter<Customer> {

    private final CustomerRepository customerRepository;

    @Override
    public void write(List<? extends Customer> list) throws Exception {
        List<Customer> newList = new ArrayList<>();
        for(Customer c: list) {
            Customer newCustomer = Customer.builder()
                    .id(c.getId())
                    .firstName(c.getFirstName())
                    .lastName(c.getLastName())
                    .birthdate("fds")
                    .build();
            newList.add(newCustomer);
            c.setBirthdate("12");
        }
        customerRepository.saveAllAndFlush(newList);
    }
}
