package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CoverLetter;
import com.example.demo.dto.CoverletterAIFeedbackDto;
import com.example.demo.dto.entity.CoverLetterEntity;
import com.example.demo.service.CoverLetterService;
import com.example.demo.service.CoverletterAIFeedbackService;

@RestController
@RequestMapping("/coverletter")
public class CoverLetterController {

  @Autowired
  private CoverletterAIFeedbackService feedbackService;

  @Autowired
  private CoverLetterService coverLetterService;

  @PostMapping("/submit")
  public ResponseEntity<?> submit(@RequestBody CoverLetter coverLetter) throws Exception {

    // userId는 실제론 SecurityContextHolder에서 가져오는 게 정석
    Integer userId = 1;

    CoverletterAIFeedbackDto response =
            coverLetterService.processCoverLetter(coverLetter, userId);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{coverLetterId}")
  public ResponseEntity<?> getCoverLetter(@PathVariable("coverLetterId") int coverLetterId) {
    CoverLetterEntity entity = coverLetterService.getCoverLetter(coverLetterId);
    return ResponseEntity.ok(entity);
  }
  
  @GetMapping("/{id}/feedback/{field}")
  public ResponseEntity<?> getFeedbackField(
          @PathVariable("id") int coverLetterId,
          @PathVariable("field") String field
  ) {
      String value = coverLetterService.getFeedbackField(coverLetterId, field);
      return ResponseEntity.ok(value);
  }


}
