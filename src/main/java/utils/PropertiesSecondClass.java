package utils;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * prefix - отражает путь до параметров, но без последнего названия параметра(list)
 * имя параметра в app.yml = имя поля (так спринг поймет какое именно поле предназначено для этого параметра)
 */

@Component
@ConfigurationProperties(prefix = "application.secondproperties")
public class PropertiesSecondClass {

    private List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
