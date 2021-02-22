package com.print.print.service.impl;

import com.print.print.mapper.ProblemMapper;
import com.print.print.pojo.Problem;
import com.print.print.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public Integer addProblem(Problem problem){
        return problemMapper.addProblem(problem);
    }
}
