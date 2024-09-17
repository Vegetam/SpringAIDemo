package com.francescomalagrino.ai.SpringAIDemo.Controller;

import com.francescomalagrino.ai.SpringAIDemo.Service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenAiController {

    ChatService chatService;

    public GenAiController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("ask-ai")
    public String getRespose(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }
}
