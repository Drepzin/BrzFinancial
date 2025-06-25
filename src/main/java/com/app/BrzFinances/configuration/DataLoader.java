package com.app.BrzFinances.configuration;

import com.app.BrzFinances.entity.PaymentMethod;
import com.app.BrzFinances.repository.PaymentMethodsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private PaymentMethodsRepository paymentMethodsRepository;

    public DataLoader(PaymentMethodsRepository paymentMethodsRepository) {
        this.paymentMethodsRepository = paymentMethodsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        populatePaymentMethods();
    }

    public void populatePaymentMethods(){
        var paymentMethods = PaymentMethod.PaymentType.values();
        Arrays.stream(paymentMethods).forEach(e -> paymentMethodsRepository.save(e.get()));
    }
}
