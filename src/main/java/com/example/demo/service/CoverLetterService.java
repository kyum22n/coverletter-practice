package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CoverLetterDao;
import com.example.demo.dto.CoverLetter;
import com.example.demo.dto.CoverletterAIFeedbackDto;
import com.example.demo.dto.entity.CoverLetterEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoverLetterService {

  @Autowired
  private CoverletterAIFeedbackService feedbackService;

  @Autowired
  private CoverLetterDao coverLetterDao;

  @Autowired
  private ObjectMapper objectMapper;  

  public CoverletterAIFeedbackDto processCoverLetter(CoverLetter coverLetter, Integer userId) throws Exception {
    // 1. 텍스트 병합
    String fullText = """
      [지원 동기]
      %s

      [성장 경험]
      %s

      [직무 역량]
      %s

      [입사 후 포부]
      %s
      """.formatted(
        coverLetter.getSupportMotive(), 
        coverLetter.getGrowthExperience(), 
        coverLetter.getJobCapability(), 
        coverLetter.getFuturePlan()
      );

    // 2. AI 피드백 생성
    CoverletterAIFeedbackDto aiFeedback = feedbackService.generateFeedback(fullText);
    
    // DTO->JSON 문자열 변환해서 저장
    String jsonString  = objectMapper.writeValueAsString(aiFeedback);

    // 3. DB 저장 엔티티
    CoverLetterEntity entity = new CoverLetterEntity();
    entity.setUserId(userId);
    entity.setSupportMotive(coverLetter.getSupportMotive());
    entity.setGrowthExperience(coverLetter.getGrowthExperience());
    entity.setJobCapability(coverLetter.getJobCapability());
    entity.setFuturePlan(coverLetter.getFuturePlan());
    entity.setAiFeedback(jsonString );

    coverLetterDao.insert(entity);

    return aiFeedback;
  }

  public CoverLetterEntity getCoverLetter(int coverLetterId) {
    CoverLetterEntity entity = coverLetterDao.findById(coverLetterId);
    return entity;
  }

  public String getFeedbackField(int id, String field) {
    return coverLetterDao.findFeedbackField(id, field);
  }

}
