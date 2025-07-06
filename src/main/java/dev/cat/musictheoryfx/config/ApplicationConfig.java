package dev.cat.musictheoryfx.config;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

@Configuration
public class ApplicationConfig {

    private final FxmlLoader fxmlLoader;
    private final String applicationTitle;
    private final ApplicationEventPublisher eventPublisher;

    public ApplicationConfig(FxmlLoader fxmlLoader,
                             @Value("${application.title}") String applicationTitle, ApplicationEventPublisher eventPublisher) {
        this.fxmlLoader = fxmlLoader;
        this.applicationTitle = applicationTitle;
        this.eventPublisher = eventPublisher;
    }

    @Bean
    @Lazy
    public StageManager stageManager(Stage stage) throws IOException {
        return new StageManager(fxmlLoader, stage, applicationTitle, eventPublisher);
    }
}

// Сибелиус программа для печатания нот
