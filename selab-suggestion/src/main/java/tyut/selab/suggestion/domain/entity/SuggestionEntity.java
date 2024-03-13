package tyut.selab.suggestion.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: SuggestionEntity
 * @Description:意见实体类
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 11:22
 * @Version: 1.0
 **/
@TableName("tyut_directory")
@Data
public class SuggestionEntity {

    @TableField("suggestion_id")
    private int suggestionId;
    @NotBlank(message = "建议内容不能为空")
    @TableField("suggestion_content")
    private String suggestionContent;
    @TableField("suggestion_user")
    private String suggestionUser;
    @TableField("creat_time")
    private String creatTime;

}
