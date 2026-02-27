package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.domain.BoardFileVO;
import com.app.ggshop.v1.domain.FileVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class BoardFileDTO {
    private Long id;
    private String filePath;
    private String fileName;
    private String fileOriginalName;
    private String fileSize;
    private Long boardId;
    private String createdDate;
    private String updatedDate;

    public FileVO toFileVO(){
        return FileVO.builder()
                .id(id)
                .filePath(filePath)
                .fileName(fileName)
                .fileOriginalName(fileOriginalName)
                .fileSize(fileSize)
                .build();
    }

    public BoardFileVO toBoardFileVO(){
        return BoardFileVO.builder()
                .id(id)
                .boardid(boardId)
                .build();
    }
}
