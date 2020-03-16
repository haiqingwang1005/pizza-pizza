package io.swagger.utils;
//import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

@Component
public class Sanitizer {
    public String sanitize(String param) {
        return Jsoup.clean(
                StringEscapeUtils.escapeHtml4(StringEscapeUtils.escapeEcmaScript(param))
                , Whitelist.basic());
    }
}
