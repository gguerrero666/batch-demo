package com.gguerrero.batchdemo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job processJob;

    @RequestMapping("/job")
    public String handle(@RequestParam(required = false) String param) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .addParameter("files", new JobParameter(param))
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);

        return "Batch job has been invoked " + processJob.isRestartable();
    }

    @RequestMapping("/health")
    public String health() throws Exception {
        return "OK";
    }

}
