package tyut.selab.vote.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.vote.enums.User;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResult {
    private Long id;
    private Long voteOptionId;
    private String userId;
    private String content;
    private int isEnable;
    private Date createTime;
    private int weight;


}
