package com.fiap.hackaton.sus.helper.controllers;

import com.fiap.hackaton.sus.helper.services.AssistantAiService;
import dev.langchain4j.service.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assistant")
@RequiredArgsConstructor
public class AssistantController {

    private final AssistantAiService assistantAiService;

    @PostMapping
    public String assistantHelp(@RequestBody String userMessage){
        Result<String> result = assistantAiService.handleRequest(userMessage);
        return result.content();
    }
}
