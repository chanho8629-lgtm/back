package com.app.ggshop.v1.dto.profile;

import com.app.ggshop.v1.common.enumeration.FileContentType;
import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class FileImageDTO {
    private Long id;
    private String fileName;
    private String fileOriginalName;
    private String fileSize;
    private String filePath;
    private FileContentType fileContentType;
    private String createDate;
    private String UpdateDate;
}
