package tyut.selab.schedule.config.view.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Period;

@Component
public class PeriodConverter implements Converter<Integer, Period> {
    @Override
    public Period convert(Integer source) {
        Period period = null;
        for (Period p : Period.values()) {
            if (source.equals(p.getId())) {
                period = p;
                break;
            }
        }
        return period;
    }
}
