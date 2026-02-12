package com.app.ggshop.v1.domain;

import com.app.ggshop.v1.audit.dateTime.Period;
import com.app.ggshop.v1.common.enumeration.FileContentType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class FileVO extends Period {
    private Long id;
    private String fileName;
    private String fileOriginalName;
    private String fileSize;
    private String filePath;
    private FileContentType fileContentType;
}
