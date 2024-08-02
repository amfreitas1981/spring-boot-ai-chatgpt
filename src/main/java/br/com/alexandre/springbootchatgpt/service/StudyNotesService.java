package br.com.alexandre.springbootchatgpt.service;

import reactor.core.publisher.Mono;

public interface StudyNotesService {
    Mono<String> createStudyNotes(String topic);
}
