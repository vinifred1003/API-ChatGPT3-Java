package br.com.ChatGPT3Project;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

public class API {

	private static final String URL_GPT_TURBO = "https://api.openai.com/v1/chat/completions";
	private static final String token = "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";
	private static final String REQUEST_BODY_GPT_TURBO = """
			{
					"model": "gpt-3.5-turbo",
					"messages":[{"role": "user", "content": "%s"}],
					"temperature": 0.5
			}
				""";

	String chat(String Entrada) throws Exception {

		String postBody = REQUEST_BODY_GPT_TURBO.formatted(Entrada);

		var request = HttpRequest.newBuilder().uri(URI.create(URL_GPT_TURBO)).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token).POST(BodyPublishers.ofString(postBody)).build();

		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibility(
				VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		Response responseObj = objectMapper.readValue(response, Response.class);

		List<ChatChoice> choices = responseObj.getChoices();

		String finalChoiceMessage = choices.get(0).getMessage().getContent();

		return finalChoiceMessage;

	}

}
