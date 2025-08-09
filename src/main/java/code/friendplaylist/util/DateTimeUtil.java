package code.friendplaylist.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {
    
    private static final ZoneId BRAZIL_TIMEZONE = ZoneId.of("America/Sao_Paulo");
    
    public static LocalDateTime nowInBrazil() {
        return ZonedDateTime.now(BRAZIL_TIMEZONE).toLocalDateTime();
    }
    

    public static LocalDateTime toBrazilTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.atZone(ZoneId.of("UTC"))
                      .withZoneSameInstant(BRAZIL_TIMEZONE)
                      .toLocalDateTime();
    }
}