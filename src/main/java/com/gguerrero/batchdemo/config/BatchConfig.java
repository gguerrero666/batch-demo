package com.gguerrero.batchdemo.config;

import com.gguerrero.batchdemo.reader.CustomReader;
import com.gguerrero.batchdemo.processor.CustomWriter;
import com.gguerrero.batchdemo.writer.CustomZip;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("DemoJob")
                .incrementer(new RunIdIncrementer())
                .start(createFiles())
                .build();
    }

    @Bean
    public Step createFiles() {
        return stepBuilderFactory.get("CreateFiles")
                .<String, String>chunk(1)
                .reader(new CustomReader())
                .processor(new CustomWriter())
                .writer(new CustomZip())
                .build();
    }

}
