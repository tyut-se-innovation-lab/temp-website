package tyut.selab.vote.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.beans.factory.annotation.Value;

/**
 *  TODO 投票类型
 *  Time: 2023.10.7
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum VoteType {
    ASSUME_BOSS("1", "控诉管理人员"),
    SEEK_OPTIONS("2", "征求意见"),
    VOTE("3","投票"),
    CUSTOM("100","自定义类型");
   @JsonValue
    private final String id;
    private final String name;


    VoteType(String id, String name) {
        this.id = id;
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
