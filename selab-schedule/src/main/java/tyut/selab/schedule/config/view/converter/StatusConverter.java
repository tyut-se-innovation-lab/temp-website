package tyut.selab.schedule.config.view.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Status;

@Component
public class StatusConverter implements Converter<Integer, Status> {
    @Override
    public Status convert(Integer source) {
        Status status = null;
        for (Status s : Status.values()) {
            if (source.equals(s.getId())) {
                status = s;
                break;
            }
        }
        return status;
    }
}
