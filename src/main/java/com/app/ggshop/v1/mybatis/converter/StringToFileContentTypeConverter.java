package com.app.ggshop.v1.mybatis.converter;

import com.app.ggshop.v1.common.enumeration.FileContentType;
import org.springframework.core.convert.converter.Converter;

public class StringToFileContentTypeConverter implements Converter<String, FileContentType> {
    @Override
    public FileContentType convert(String source) { return FileContentType.getFileContentType(source);}
}
