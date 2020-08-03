package com.junyi.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description: String -> Properties 的 PropertyEditor
 * @see PropertyEditor
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {
    @Override
    public void setAsText(String text) throws java.lang.IllegalArgumentException {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        // 临时存储properties
        setValue(properties);
    }
    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Object, Object> entry: properties.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }


}
