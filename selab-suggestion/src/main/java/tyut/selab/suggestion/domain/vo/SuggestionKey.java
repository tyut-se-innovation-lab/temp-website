package tyut.selab.suggestion.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: SuggestionKey
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-14 15:35
 * @Version: 1.0
 **/
@Data
public class SuggestionKey {
    @NotNull(message = "密钥不能为空！")
    private String suggestionKey;
}
