package tyut.selab.vote.domain.vo;

import tyut.selab.vote.enums.VoteOptionType;

/**
 * 选项
 * @author Big_bai on 2022/11/19
 */
public class VoteOption {
    //id
    private long id;
    //内容
    private String content;
    //用户是否选择   上传时默认为0
    private int isSelect;
    //类型
    private String type;
    //文本框的值（有的话）
    private String other;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public String getType() {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
