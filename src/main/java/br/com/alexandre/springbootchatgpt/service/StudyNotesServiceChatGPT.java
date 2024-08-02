package br.com.alexandre.springbootchatgpt.service;

import br.com.alexandre.springbootchatgpt.model.ChatGPTRequest;
import br.com.alexandre.springbootchatgpt.model.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudyNotesServiceChatGPT implements StudyNotesService {
    private WebClient webClient;

    public StudyNotesServiceChatGPT(WebClient.Builder builder, @Value("${openai.api.key}") String apiKey) {
        this.webClient = builder
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization",
                        String.format("Bearer %s", apiKey))
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Override
    public Mono<String> createStudyNotes(String topic) {
        ChatGPTRequest request = createStudyRequest(topic);

        return webClient.post()
                .uri("/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatGPTResponse.class)
                .map(response -> response.choices().get(0).text());
    }

    private ChatGPTRequest createStudyRequest(String topic) {
        String question = """
        Quais são os pontos chaves que devo estudar
        sobre o seguinte assunto:
        """ + topic;

        return new ChatGPTRequest(
                "text-davinci-003", question, 0.3,
                2000, 1.0, 0.0, 0.0);
    }
}
