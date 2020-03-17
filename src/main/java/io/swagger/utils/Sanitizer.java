package io.swagger.utils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

@Component
public class Sanitizer {
    public String sanitize(String param) {
        if (StringUtils.isEmpty(param)) {
            return param;
        }
        return Jsoup.clean(
                StringEscapeUtils.escapeHtml4(StringEscapeUtils.escapeEcmaScript(param))
                , Whitelist.basic());
    }
}
