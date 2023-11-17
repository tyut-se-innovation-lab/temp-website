package tyut.selab.rule.domain.VO;

import tyut.selab.rule.domain.entity.Operation;

import java.util.List;

public class LogPageVO {
    private Long total;
    private List<Operation> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Operation> getList() {
        return list;
    }

    public void setList(List<Operation> list) {
        this.list = list;
    }
}
