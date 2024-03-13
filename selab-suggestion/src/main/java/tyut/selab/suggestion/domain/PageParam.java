package tyut.selab.suggestion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: PageParam
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 12:41
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Integer PAGE_NUM = 1;
    private static final Integer PAGE_SIZE = 10;

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNum = PAGE_NUM;


    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 100")
    private Integer pageSize = PAGE_SIZE;
}

