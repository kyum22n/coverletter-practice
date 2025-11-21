package com.example.demo.dto;

import lombok.Data;

@Data
public class CoverletterAIFeedbackDto {
    private int grammarScore;   //문법 점수
    private int readabilityScore;   //가독성 점수
    private int logicFlowScore; //논리 흐름 점수
    private String strength;    //강점
    private String improvement; //개선점
}


