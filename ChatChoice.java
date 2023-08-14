package br.com.ChatGPT3Project;

import com.fasterxml.jackson.annotation.JsonProperty;





public class ChatChoice {
    @JsonProperty("index")
    private int index;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("finish_reason")
    private String finishReason;

	public Message getMessage() {
		 
		return message;
	}

}

