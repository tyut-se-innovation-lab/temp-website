package tyut.selab.schedule.config.view.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Week;

@Component
public class WeekConverter implements Converter<String, Week> {
    @Override
    public Week convert(String source) {
        Week week = null;
        for (Week w : Week.values()) {
            if (w.getName().equals(source)) {
                week = w;
                break;
            }
        }
        return week;
    }
}
