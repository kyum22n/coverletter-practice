package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.entity.CoverLetterEntity;

@Mapper
public interface CoverLetterDao {
  int insert(CoverLetterEntity entity);
  CoverLetterEntity findById(Integer coverLetterId);
  String findFeedbackField(@Param("id") int id, @Param("field") String field);

}
