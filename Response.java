package br.com.ChatGPT3Project;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Response {
    @JsonProperty("model")
    private String model;

    @JsonProperty("choices")
    private List<ChatChoice> choices;

    @JsonProperty("messages")
    private List<Message> messages;

    @JsonProperty("temperature")
    private double temperature;

    public List<ChatChoice> getChoices() {
        return choices;
    }

    public List<Message> getMessages() {
        return messages;
    }

    
}