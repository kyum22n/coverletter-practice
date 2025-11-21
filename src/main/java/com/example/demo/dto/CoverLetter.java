package com.example.demo.dto;

import lombok.Data;

@Data
public class CoverLetter {
  // ID
  private Integer coverLetterId;
  // 지원 동기
  private String supportMotive;
  // 성장 배경
  private String growthExperience;
  // 직무 역량
  private String jobCapability;
  // 입사 후 포부
  private String futurePlan;
}
