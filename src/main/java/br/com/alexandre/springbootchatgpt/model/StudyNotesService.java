package br.com.alexandre.springbootchatgpt.model;

import reactor.core.publisher.Mono;

public interface StudyNotesService {
    Mono<String> createStudyNotes(String topic);
}
