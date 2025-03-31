package com.evstore.ecommerce.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;

@Service
public class ChatbotService {

    private final OpenAiService openAiService;
    private final List<Map<String, String>> knowledgeBase;

    public ChatbotService(@Value("${openai.api.key}") String apiKey) throws Exception {
        this.openAiService = new OpenAiService(apiKey);

        // Load ev_info.json
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream input = new ClassPathResource("ev_info.json").getInputStream();
        this.knowledgeBase = objectMapper.readValue(input, new TypeReference<>() {});
    }

    public String ask(String question) {
        try {
            // Build dynamic context
            StringBuilder context = new StringBuilder();
            for (Map<String, String> entry : knowledgeBase) {
                context.append("- Q: ").append(entry.get("question")).append("\n")
                       .append("  A: ").append(entry.get("answer")).append("\n");
            }

            // Prepare messages
            List<ChatMessage> messages = new ArrayList<>();
            messages.add(new ChatMessage("system", "You are a helpful assistant for an EV store. Use only the following information to answer:\n" + context));
            messages.add(new ChatMessage("user", question));

            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .messages(messages)
                    .temperature(0.3)
                    .maxTokens(150)
                    .build();

            ChatCompletionResult result = openAiService.createChatCompletion(request);

            return result.getChoices().get(0).getMessage().getContent().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, something went wrong. Please contact our customer service.";
        }
    }
}
