package com.evstore.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;

@Service
public class ChatbotService {

    private final OpenAiService openAiService;

    public ChatbotService(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    public String ask(String message) {
        try {
            ChatMessage chatMessage = new ChatMessage("user", message);

            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .messages(List.of(chatMessage))
                    .temperature(0.7)
                    .maxTokens(100)
                    .build();

            ChatCompletionResult result = openAiService.createChatCompletion(request);

            return result.getChoices().get(0).getMessage().getContent().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "Oops! Something went wrong with the assistant.";
        }
    }
}
