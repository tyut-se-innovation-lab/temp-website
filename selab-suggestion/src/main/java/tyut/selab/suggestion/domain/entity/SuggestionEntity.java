package tyut.selab.suggestion.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    private int suggestionId;
    @NotBlank(message = "建议主题不能为空")
    @Size(min=1, max=20,message="建议主题最多20字")
    private String suggestionTopic;
    @NotBlank(message = "建议内容不能为空")
    @Size(min=1, max=300,message="建议内容最多300字")
    private String suggestionContent;
    private String suggestionUser;
    private String creatTime;

}
