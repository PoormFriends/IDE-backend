package com.goorm.goormfriends.api.service;

import com.goorm.goormfriends.db.entity.Ide;
import com.goorm.goormfriends.db.entity.ProblemTestCase;
import com.goorm.goormfriends.db.repository.IdeRepository;
import com.goorm.goormfriends.db.repository.ProblemTestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdeCompilerService {
    private final IdeRepository ideRepository;
    private final ProblemTestCaseRepository problemTestCaseRepository;

    public void executeCode(Long ideId) {
        Ide ide = ideRepository.findById(ideId).orElseThrow(
                () -> new RuntimeException("IDE not found with id: " + ideId)
        );

        String userCode = ide.getUsercode();
        Long problemId = ide.getProblem().getId();

        // 해당 문제의 모든 테스트 케이스 가져오기
        List<ProblemTestCase> testCases = problemTestCaseRepository.findByProblemId(problemId);

        boolean allTestsPassed = testCases.stream().allMatch(testCase -> {
            String output = externalCompilerService.compileAndRun(userCode, testCase.getInput());
            return output.equals(testCase.getOutput());
        });

        ide.setSolved(allTestsPassed);
        ideRepository.save(ide);
    }

}
