package com.gguerrero.batchdemo.processor;

import com.gguerrero.batchdemo.constants.Constants;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.util.List;

public class CustomWriter implements ItemProcessor<String, String> {

    @Override
    public String process(String s) throws Exception {
        FileWriter myWriter = new FileWriter(Constants.path + s + ".txt");
        myWriter.write(s);
        myWriter.close();
        return s;
    }
}
