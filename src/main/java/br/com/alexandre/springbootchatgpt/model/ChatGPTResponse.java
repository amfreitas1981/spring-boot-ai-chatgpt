package br.com.alexandre.springbootchatgpt.model;

import java.util.List;

public record ChatGPTResponse(List<Choice> choices) {
}
