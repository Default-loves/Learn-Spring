package com.junyi.conversion;

import com.junyi.conversion.domain.BookForConversion;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditor;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description: 自定义 {@link PropertyEditorRegistrar} 实现
 * @see PropertyEditorRegistrar
 * @see StringToPropertiesPropertyEditor
 */
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 对BookForConversion中的context属性添加类型转换
        registry.registerCustomEditor(BookForConversion.class, "context", new StringToPropertiesPropertyEditor());
    }
}
