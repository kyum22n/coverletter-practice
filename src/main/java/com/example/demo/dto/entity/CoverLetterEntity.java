package com.example.demo.dto.entity;

import lombok.Data;

@Data
public class CoverLetterEntity {
    private Integer coverLetterId;
    private Integer userId;

    private String supportMotive;
    private String growthExperience;
    private String jobCapability;
    private String futurePlan;

    private String aiFeedback;  // JSONB
}

