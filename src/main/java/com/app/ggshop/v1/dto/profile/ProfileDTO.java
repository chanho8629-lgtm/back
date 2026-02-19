package com.app.ggshop.v1.dto.profile;

import com.app.ggshop.v1.common.enumeration.FileContentType;
import com.app.ggshop.v1.domain.FileVO;
import com.app.ggshop.v1.domain.MemberFileVO;
import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ProfileDTO {
    private Long id;
    private String fileName;
    private String fileOriginalName;
    private String fileType;
    private String fileSize;
    private String filePath;
    private FileContentType fileContentType;
    private Long memberId;
    private String createdDate;
    private String updatedDate;

    public FileVO tofileVO() {
        return FileVO.builder()
        .id(id)
        .fileName(fileName)
        .fileOriginalName(fileOriginalName)
        .fileSize(fileSize)
        .filePath(filePath)
        .fileContentType(fileContentType)
        .build();
    };
    public MemberFileVO toMemberFileVO() {
        return  MemberFileVO.builder()
        .id(id)
        .memberId(memberId)
        .build();
    };
}
