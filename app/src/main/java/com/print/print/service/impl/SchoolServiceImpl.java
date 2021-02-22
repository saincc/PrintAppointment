package com.print.print.service.impl;

import com.print.print.mapper.SchoolMapper;
import com.print.print.pojo.School;
import com.print.print.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public School selectSchoolByName(String schoolname){
        return schoolMapper.selectSchoolByName(schoolname);
    }

    @Override
    public List<School> selectAllSchool(){
        return schoolMapper.selectAllSchool();
    }

    @Override
    public School selectSchoolById(Integer id){
        return schoolMapper.selectSchoolById(id);
    }
}
