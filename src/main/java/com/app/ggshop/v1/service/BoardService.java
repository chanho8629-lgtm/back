package com.app.ggshop.v1.service;

import com.app.ggshop.v1.common.enumeration.FileContentType;
import com.app.ggshop.v1.common.exception.BoardNotFoundException;
import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.domain.BoardTagVO;
import com.app.ggshop.v1.domain.FileVO;
import com.app.ggshop.v1.dto.*;
import com.app.ggshop.v1.mapper.BoardMapper;
import com.app.ggshop.v1.mapper.BoardTagMapper;
import com.app.ggshop.v1.mapper.FileMapper;
import com.app.ggshop.v1.repository.board.BoardDAO;
import com.app.ggshop.v1.repository.board.BoardFileDAO;
import com.app.ggshop.v1.repository.board.FileDAO;
import com.app.ggshop.v1.repository.board.TagDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class BoardService {

    private final BoardMapper boardMapper;
    private final BoardTagMapper boardTagMapper;
    private final FileMapper fileMapper;
    private final BoardDAO boardDAO;
    private final TagDAO tagDAO;
    private final FileDAO fileDAO;
    private final BoardFileDAO boardFileDAO;

    private static final String ROOT_PATH = "C:/file/";
    private static final int MAX_FILES = 3;
    private static final int MAX_TAGS = 10;

    /**
     * 게시글 작성 (파일 + 태그 포함) - HTML Form 제출용
     */
    public void write(BoardDTO boardDTO, List<MultipartFile> multipartFiles, List<String> tagNames) {
        // 1. 제목 파싱 ([구매자] 또는 [판매자])
        boardDTO.parseTitleAndSetFilter();

        // 2. 게시글 등록
        boardDAO.save(boardDTO);
        Long boardId = boardDTO.getId();

        log.info("▶ 게시글 등록 완료: ID={}, 제목={}, 필터={}",
                boardId, boardDTO.getTitle(), boardDTO.getBoardFilter());

        // 3. 파일 등록 (최대 3개)
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            saveFiles(multipartFiles, boardId);
        }

        // 4. 태그 등록 (최대 10개)
        if (tagNames != null && !tagNames.isEmpty()) {
            saveTags(tagNames, boardId);
        }

        log.info("▶ 게시글 작성 완료 (파일 {}개, 태그 {}개)",
                multipartFiles != null ? Math.min(multipartFiles.size(), MAX_FILES) : 0,
                tagNames != null ? Math.min(tagNames.size(), MAX_TAGS) : 0);
    }

    /**
     * 파일 저장 로직
     */
    private void saveFiles(List<MultipartFile> multipartFiles, Long boardId) {
        String todayPath = getTodayPath();
        int fileCount = Math.min(multipartFiles.size(), MAX_FILES);

        for (int i = 0; i < fileCount; i++) {
            MultipartFile multipartFile = multipartFiles.get(i);

            if (multipartFile.isEmpty() || multipartFile.getOriginalFilename() == null ||
                    multipartFile.getOriginalFilename().isEmpty()) {
                continue;
            }

            try {
                UUID uuid = UUID.randomUUID();
                String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();

                FileDTO fileDTO = new FileDTO();
                fileDTO.setFilePath(todayPath);
                fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
                fileDTO.setFileName(fileName);

                // 파일 저장
                fileDAO.save(fileDTO);

                // 게시글-파일 연결
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setId(fileDTO.getId());
                boardFileDTO.setBoardId(boardId);
                boardFileDAO.save(boardFileDTO.toBoardFileVO());

                // 물리적 파일 저장
                File directory = new File(ROOT_PATH + todayPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                multipartFile.transferTo(new File(ROOT_PATH + todayPath, fileName));

                log.info("▶ 파일 저장 완료: ID={}, 파일명={}, 크기={}",
                        fileDTO.getId(), fileName, multipartFile.getSize());

            } catch (IOException e) {
                log.error("▶ 파일 저장 실패", e);
                throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
            }
        }
    }

    /**
     * 태그 저장 로직
     */
    private void saveTags(List<String> tagNames, Long boardId) {
        int tagCount = Math.min(tagNames.size(), MAX_TAGS);

        for (int i = 0; i < tagCount; i++) {
            String tagName = tagNames.get(i).trim();

            if (tagName.isEmpty()) {
                continue;
            }

            BoardTagVO tagVO = BoardTagVO.builder()
                    .tagName(tagName)
                    .tagBoardId(boardId)
                    .build();

            boardTagMapper.insertTag(tagVO);

            log.info("▶ 태그 저장 완료: ID={}, 태그명={}", tagVO.getId(), tagName);
        }
    }

    /**
     * 게시글 목록 조회 (페이징 + 검색)
     */
    public PostWithPagingDTO list(int page, String filter, String keyword) {
        PostWithPagingDTO postWithPagingDTO = new PostWithPagingDTO();

        // 전체 게시글 수 조회
        int total = boardDAO.findTotal(filter, keyword);
        Criteria criteria = new Criteria(page, total);

        // 페이징된 게시글 조회
        List<BoardDTO> boards = boardDAO.findAll(criteria, filter, keyword);

        // hasMore 설정 (다음 페이지 여부)
        criteria.setHasMore(boards.size() > criteria.getRowCount());
        postWithPagingDTO.setCriteria(criteria);

        // 한 개 초과 조회했다면 마지막 건 제거 (hasMore 확인용)
        if (criteria.isHasMore()) {
            boards.remove(boards.size() - 1);
        }

        postWithPagingDTO.setBoards(boards);

        log.info("▶ 게시글 목록 조회: 페이지={}, 조회건수={}, 전체={}",
                page, boards.size(), total);

        return postWithPagingDTO;
    }

    /**
     * 게시글 상세 조회
     */
    public BoardDTO detail(Long id) {
        Optional<BoardDTO> foundBoard = boardDAO.findById(id);
        BoardDTO boardDTO = foundBoard.orElseThrow(BoardNotFoundException::new);

        // 태그 조회
        boardDTO.setBoardTags(tagDAO.findAllByBoardId(boardDTO.getId())
                .stream()
                .map(this::toTagDTO)
                .collect(Collectors.toList()));

        // 파일 조회
        boardDTO.setBoardFiles(boardFileDAO.findAllByBoardId(boardDTO.getId()));

        log.info("▶ 게시글 상세 조회: ID={}, 제목={}", id, boardDTO.getTitle());

        return boardDTO;
    }

    /**
     * 게시글 수정
     */
    public void update(BoardDTO boardDTO, List<MultipartFile> multipartFiles) {
        String todayPath = getTodayPath();

        // 1. 게시글 업데이트
        boardDAO.setBoard(boardDTO.toVO());

        // 2. 태그 업데이트 (새로운 태그 추가)
        if (boardDTO.getBoardTags() != null && !boardDTO.getBoardTags().isEmpty()) {
            boardDTO.getBoardTags().forEach(boardtagDTO -> {
                boardtagDTO.setTagBoardId(boardDTO.getId());
                tagDAO.save(boardtagDTO.toVO());
            });
        }

        // 3. 파일 추가
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            saveFilesForUpdate(multipartFiles, boardDTO.getId(), todayPath);
        }

        // 4. 삭제할 태그 처리
        if (boardDTO.getTagIdsToDelete() != null && boardDTO.getTagIdsToDelete().length > 0) {
            Arrays.stream(boardDTO.getTagIdsToDelete())
                    .forEach(tagId -> {
                        tagDAO.delete(Long.valueOf(tagId));
                        log.info("▶ 태그 삭제: ID={}", tagId);
                    });
        }

        // 5. 삭제할 파일 처리
        if (boardDTO.getFileIdsToDelete() != null && boardDTO.getFileIdsToDelete().length > 0) {
            deleteFiles(boardDTO.getFileIdsToDelete());
        }

        log.info("▶ 게시글 수정 완료: ID={}", boardDTO.getId());
    }

    /**
     * 수정 시 파일 저장
     */
    private void saveFilesForUpdate(List<MultipartFile> multipartFiles, Long boardId, String todayPath) {
        int fileCount = Math.min(multipartFiles.size(), MAX_FILES);

        for (int i = 0; i < fileCount; i++) {
            MultipartFile multipartFile = multipartFiles.get(i);

            if (multipartFile.isEmpty() || multipartFile.getOriginalFilename() == null ||
                    multipartFile.getOriginalFilename().isEmpty()) {
                continue;
            }

            try {
                UUID uuid = UUID.randomUUID();
                String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();

                FileDTO fileDTO = new FileDTO();
                fileDTO.setFilePath(todayPath);
                fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
                fileDTO.setFileName(fileName);

                fileDAO.save(fileDTO);

                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setBoardId(boardId);
                boardFileDTO.setId(fileDTO.getId());
                boardFileDAO.save(boardFileDTO.toBoardFileVO());

                File directory = new File(ROOT_PATH + todayPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                multipartFile.transferTo(new File(ROOT_PATH + todayPath, fileName));

                log.info("▶ 파일 추가 저장: ID={}, 파일명={}", fileDTO.getId(), fileName);

            } catch (IOException e) {
                log.error("▶ 파일 저장 실패", e);
                throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
            }
        }
    }

    /**
     * 파일 삭제
     */
    private void deleteFiles(String[] fileIds) {
        Arrays.stream(fileIds).forEach(fileId -> {
            Optional<FileVO> fileVO = fileDAO.findById(Long.valueOf(fileId));

            if (fileVO.isEmpty()) {
                log.warn("▶ 삭제할 파일을 찾을 수 없음: ID={}", fileId);
                return;
            }

            // 물리적 파일 삭제
            File file = new File(ROOT_PATH + fileVO.get().getFilePath(), fileVO.get().getFileName());
            if (file.exists()) {
                if (file.delete()) {
                    log.info("▶ 물리적 파일 삭제: {}", file.getAbsolutePath());
                } else {
                    log.warn("▶ 물리적 파일 삭제 실패: {}", file.getAbsolutePath());
                }
            }

            // DB에서 삭제
            boardFileDAO.delete(Long.valueOf(fileId));
            fileDAO.delete(Long.valueOf(fileId));

            log.info("▶ 파일 DB 삭제: ID={}", fileId);
        });
    }

    /**
     * 게시글 삭제
     */
    public void delete(Long id) {
        // 1. 태그 삭제
        tagDAO.deleteByBoardId(id);

        // 2. 파일 삭제
        List<BoardFileDTO> boardFiles = boardFileDAO.findAllByBoardId(id);
        boardFiles.forEach(boardFileDTO -> {
            File file = new File(ROOT_PATH + boardFileDTO.getFilePath(), boardFileDTO.getFileName());
            if (file.exists()) {
                if (file.delete()) {
                    log.info("▶ 물리적 파일 삭제: {}", file.getAbsolutePath());
                }
            }

            Long fileId = boardFileDTO.getId();
            boardFileDAO.delete(fileId);
            fileDAO.delete(fileId);
        });

        // 3. 게시글 삭제
        boardDAO.delete(id);

        log.info("▶ 게시글 삭제 완료: ID={}", id);
    }

    /**
     * 오늘 날짜 경로 생성 (yyyy/MM/dd)
     */
    public String getTodayPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    /**
     * BoardTagVO to BoardTagDTO
     */
    private BoardTagDTO toTagDTO(BoardTagVO tagVO) {
        BoardTagDTO tagDTO = new BoardTagDTO();
        tagDTO.setId(tagVO.getId());
        tagDTO.setTagBoardId(tagVO.getTagBoardId());
        tagDTO.setTagName(tagVO.getTagName());
        return tagDTO;
    }
}
