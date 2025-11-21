package com.example.demo.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CoverletterAIFeedbackDto;

@Service
public class CoverletterAIFeedbackService {

  private ChatClient chatClient;

  public CoverletterAIFeedbackService(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder.build();
  }

  public CoverletterAIFeedbackDto generateFeedback(String content) {

    // Bean 객체->JSON 출력 변환기 생성
    BeanOutputConverter<CoverletterAIFeedbackDto> converter = new BeanOutputConverter<>(CoverletterAIFeedbackDto.class);

    // DTO 구조 제공 -> JSON 출력 포맷 지정
    String format = converter.getFormat();

    // 프롬프트 생성
    String prompt = """
        아래 자기소개서 내용을 분석해 점수와 피드백을 반드시 JSON 형태만으로 생성해줘.

        %s

        자기소개서:
        %s

        """.formatted(format, content);

    // LLM에 전달하고 응답 받기
    String json = chatClient.prompt()
        .user(prompt)
        .call()
        .content();

    // JSON으로 변환해 반환
    return converter.convert(json);

  }
}