package com.zheltoukhov.linguaneo.dto.group;

import com.zheltoukhov.linguaneo.validation.annotation.ValidGroupName;

import static com.zheltoukhov.linguaneo.Constants.Messages.GROUP_NAME_VALIDATION_MESSAGE;

public class CreateGroupDto {
    @ValidGroupName(message = GROUP_NAME_VALIDATION_MESSAGE)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
