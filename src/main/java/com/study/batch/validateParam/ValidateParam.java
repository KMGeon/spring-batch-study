package com.study.batch.validateParam;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ValidateParam {


    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job ValidateParamJob(Step ValidateParamStep) {
        return jobBuilderFactory.get("ValidateParamJob")
                .incrementer(new RunIdIncrementer())
                .start(ValidateParamStep)
                .build();
    }


    @Bean
    @JobScope
    public Step ValidateParamStep(Tasklet ValidateParamTasklet) {
        return stepBuilderFactory.get("ValidateParamStep")
                .tasklet(ValidateParamTasklet())
                .build();
    }

    @Bean
    @StepScope
    public Tasklet ValidateParamTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println(" validate param spring batch ");
                return RepeatStatus.FINISHED;
            }
        };
    }


}
