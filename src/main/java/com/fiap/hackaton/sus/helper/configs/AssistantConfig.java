package com.fiap.hackaton.sus.helper.configs;

import com.fiap.hackaton.sus.helper.services.AssistantAiService;
import com.fiap.hackaton.sus.helper.tools.AssistantAiTools;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig {

    @Value("${gemini.api-key}")
    private String geminiApiKey;

    @Value("${gemini.model}")
    private String geminiModel;

    @Bean
    public GoogleAiGeminiChatModel googleAiGeminiChatModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(geminiApiKey)
                .modelName(geminiModel)
                .build();
    }

    @Bean
    public AssistantAiService assistant(GoogleAiGeminiChatModel model, AssistantAiTools assistantAiTools){
        return AiServices.builder(AssistantAiService.class)
                .chatModel(model)
                .tools(assistantAiTools)
                .build();
    }
}