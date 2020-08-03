package com.junyi.conversion;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description: 模拟spring Framework中的 PropertyEditor操作
 * @see java.beans.PropertyEditor
 * @see StringToPropertiesPropertyEditor
 */
public class PropertyEditorDemo {
    public static void main(String[] args) {
        String text = "name=xiaoxiao";
        StringToPropertiesPropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        propertyEditor.setAsText(text);
        System.out.println(propertyEditor.getValue());
        System.out.println(propertyEditor.getAsText());

    }
}
