package com.goorm.goormfriends.api.dto.response;

import com.goorm.goormfriends.db.entity.Ide;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class IdeCompilerResponse {

    private Long userId;
    private Long problemId;
    private boolean solved;

    public static IdeCompilerResponse from(Ide ide) {
        return new IdeCompilerResponse(
                ide.getUser() != null ? ide.getUser().getId() : null,
                ide.getProblem() != null ? ide.getProblem().getId() : null,
                ide.isSolved()
        );
    }

}
