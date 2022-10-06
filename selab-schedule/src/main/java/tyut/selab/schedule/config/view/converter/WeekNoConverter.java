package tyut.selab.schedule.config.view.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.WeekNo;

@Component
public class WeekNoConverter implements Converter<String, WeekNo> {
    @Override
    public WeekNo convert(String source) {
        WeekNo weekNo = null;
        for (WeekNo w : WeekNo.values()) {
            if (w.getName().equals(source)){
                weekNo = w;
                break;
            }
        }
        return weekNo;
    }
}
