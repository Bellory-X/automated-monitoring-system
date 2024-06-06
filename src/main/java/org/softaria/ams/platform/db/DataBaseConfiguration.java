package org.softaria.ams.platform.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataBaseConfiguration {

    @Bean
    public DataBase dataBase() {
        Map<String, String> yesterday = new HashMap<>();
        yesterday.put("yandex.ru", "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <title>HTML Document Template1</title>\n" +
                "  <style>\n" +
                "    p {\n" +
                "      font-family: Arial;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <p>Hello, world!</p>\n" +
                "  <script>\n" +
                "    console.log(document.querySelector('p').textContent);\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>");
        yesterday.put("ru.wikipedia.org", "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <title>HTML Document Template2</title>\n" +
                "  <style>\n" +
                "    p {\n" +
                "      font-family: Arial;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <p>Hello, world!</p>\n" +
                "  <script>\n" +
                "    console.log(document.querySelector('p').textContent);\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>");
        yesterday.put("google.com", "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <title>HTML Document Template3</title>\n" +
                "  <style>\n" +
                "    p {\n" +
                "      font-family: Arial;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <p>Hello, world!</p>\n" +
                "  <script>\n" +
                "    console.log(document.querySelector('p').textContent);\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>");
        Map<String, String> today = new HashMap<>();
        today.put("yandex.ru", "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <title>HTML Document Template1</title>\n" +
                "  <style>\n" +
                "    p {\n" +
                "      font-family: Arial;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <p>Hello, world!</p>\n" +
                "  <script>\n" +
                "    console.log(document.querySelector('p').textContent);\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>");
        today.put("ru.wikipedia.org", "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <title>HTML Document Template4</title>\n" +
                "  <style>\n" +
                "    p {\n" +
                "      font-family: Arial;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <p>Hello, world!</p>\n" +
                "  <script>\n" +
                "    console.log(document.querySelector('p').textContent);\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>");
        today.put("habr.com", "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <title>HTML Document Template5</title>\n" +
                "  <style>\n" +
                "    p {\n" +
                "      font-family: Arial;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <p>Hello, world!</p>\n" +
                "  <script>\n" +
                "    console.log(document.querySelector('p').textContent);\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>");
        return new DataBase(yesterday, today);
    }
}
