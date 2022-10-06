package tyut.selab.schedule.config.view.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Status;

@Component
public class StatusConverter implements Converter<String, Status> {
    @Override
    public Status convert(String source) {
        Status status = null;
        for (Status s : Status.values()) {
            if (s.getComment().equals(source)) {
                status = s;
                break;
            }
        }
        return status;
    }
}
