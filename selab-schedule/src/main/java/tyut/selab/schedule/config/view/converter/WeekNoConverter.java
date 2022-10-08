package tyut.selab.schedule.config.view.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.WeekNo;

@Component
public class WeekNoConverter implements Converter<Integer, WeekNo> {
    @Override
    public WeekNo convert(Integer source) {
        WeekNo weekNo = null;
        for (WeekNo w : WeekNo.values()) {
            if (source.equals(w.getId())){
                weekNo = w;
                break;
            }
        }
        return weekNo;
    }
}
