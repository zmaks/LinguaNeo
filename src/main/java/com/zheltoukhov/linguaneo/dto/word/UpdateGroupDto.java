package com.zheltoukhov.linguaneo.dto.word;

/**
 * Created by mazh0416 on 12/12/2016.
 */
public class UpdateGroupDto {
    private Long wordId;
    private Long groupId;

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
