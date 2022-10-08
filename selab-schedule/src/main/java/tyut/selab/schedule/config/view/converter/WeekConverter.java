package tyut.selab.schedule.config.view.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Week;

@Component
public class WeekConverter implements Converter<Integer, Week> {
    @Override
    public Week convert(Integer source) {
        Week week = null;
        for (Week w : Week.values()) {
            if (source.equals(w.getId())) {
                week = w;
                break;
            }
        }
        return week;
    }
}
