package tyut.selab.schedule.config.view.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Period;

@Component
public class PeriodConverter implements Converter<String, Period> {
    @Override
    public Period convert(String source) {
        Period period = null;
        for (Period p : Period.values()) {
            if (p.getName().equals(source)){
                period = p;
                break;
            }
        }
        return period;
    }
}
