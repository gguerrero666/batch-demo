package com.gguerrero.batchdemo.reader;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;

public class CustomReader implements ItemReader<String> {

    private String param = "";
    private int index = 0;

    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobParameters();
        this.param = jobParameters.getString("files");
    }

    @Override
    public String read() throws Exception {

        char[] w = param.toCharArray();

        if (index >= w.length) {
            return null;
        }

        String data = String.valueOf(w[index]);
        index++;
        System.out.println("CustomReader    : Reading data    : " + data);
        return data;
    }
}
