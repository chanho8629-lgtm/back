package com.app.ggshop.v1.controller.p2p;

import com.app.ggshop.v1.common.exception.BoardNotFoundException;
import com.app.ggshop.v1.dto.BoardDTO;
import com.app.ggshop.v1.dto.BoardTagDTO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.dto.PostWithPagingDTO;
import com.app.ggshop.v1.service.BoardService;
import com.app.ggshop.v1.service.oauth.TagService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/p2p")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;
    private final TagService tagService;

    /**
     * 게시글 목록 조회 페이지
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "filter", required = false) String filter,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       Model model) {
        log.info("▶ 게시글 목록 조회 - 페이지: {}, 필터: {}, 키워드: {}", page, filter, keyword);

        try {
            // 목록 조회 (페이징 포함)
            PostWithPagingDTO postWithPagingDTO = boardService.list(page, filter, keyword);

            model.addAttribute("boards", postWithPagingDTO.getBoards());
            model.addAttribute("criteria", postWithPagingDTO.getCriteria());
            model.addAttribute("currentPage", page);
            model.addAttribute("filter", filter);
            model.addAttribute("keyword", keyword);

            return "p2p/p2p_list";

        } catch (Exception ex) {
            log.error("▶ 게시글 목록 조회 중 오류 발생", ex);
            model.addAttribute("errorMessage", "게시글 목록을 조회하는 중에 오류가 발생했습니다.");
            return "p2p/p2p_list";
        }
    }

    /**
     * 게시글 작성 페이지로 이동
     */
    @GetMapping("/write")
    public String writePage(Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        log.info("▶ 게시글 작성 페이지 이동");
        // 로그인 체크는 필요에 따라 활성화
        // if (member == null) {
        //     return "redirect:/login";
        // }

        return "p2p/p2p_write_gg";
    }

    /**
     * 게시글 작성 처리 (POST)
     * HTML Form 제출 방식
     */
    @PostMapping("/write")
    public String write(BoardDTO boardDTO,
                        @RequestParam(value = "images", required = false) List<MultipartFile> multipartFiles,
                        @RequestParam(value = "boardTagNames", required = false) List<String> tagNames,
                        RedirectAttributes redirectAttributes) {

        log.info("▶ 게시글 작성 처리 시작 - 제목: {}", boardDTO.getTitle());

        try {
            MemberDTO member = (MemberDTO) session.getAttribute("member");
            if (member == null || member.getId() == null) {
                log.warn("▶ 비로그인 작성 요청 - 기본 작성자 ID(1)로 처리");
                boardDTO.setBoardMemberId(1L);
            } else {
                boardDTO.setBoardMemberId(member.getId());
            }

            // 필수 값 검증
            if (boardDTO.getTitle() == null || boardDTO.getTitle().isEmpty()) {
                log.warn("▶ 제목이 비어있음");
                redirectAttributes.addFlashAttribute("errorMessage", "제목을 입력해주세요.");
                return "redirect:/p2p/write";
            }

            if (boardDTO.getSummary() == null || boardDTO.getSummary().isEmpty()) {
                boardDTO.setSummary(boardDTO.getTitle());
            }

            // 기본 컨텐츠 설정 (요약이 없으면 제목으로)
            if (boardDTO.getContent() == null || boardDTO.getContent().isEmpty()) {
                boardDTO.setContent(boardDTO.getSummary());
            }

            // 게시글 작성 (파일 + 태그 포함)
            boardService.write(boardDTO, multipartFiles, tagNames);

            log.info("▶ 게시글 작성 완료 - 제목: {}", boardDTO.getTitle());
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 성공적으로 등록되었습니다.");
            return "redirect:/p2p/list";

        } catch (Exception ex) {
            log.error("▶ 게시글 작성 중 오류 발생", ex);
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 작성 중 오류가 발생했습니다.");
            return "redirect:/p2p/write";
        }
    }

    /**
     * 게시글 수정 페이지로 이동
     */
    @GetMapping("/update")
    public String goToUpdateForm(@RequestParam("id") Long id,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        log.info("▶ 게시글 수정 페이지 이동 - ID: {}", id);

        try {
            BoardDTO board = boardService.detail(id);
            model.addAttribute("board", board);
            return "p2p/p2p_modify_gg";

        } catch (BoardNotFoundException ex) {
            log.warn("▶ 게시글을 찾을 수 없음 - ID: {}", id);
            redirectAttributes.addFlashAttribute("errorMessage", "해당 게시글이 존재하지 않습니다.");
            return "redirect:/p2p/list";
        }
    }

    /**
     * 게시글 수정 처리 (POST)
     */
    @PostMapping("/update")
    public String update(BoardDTO boardDTO,
                         @RequestParam(value = "file", required = false) List<MultipartFile> multipartFiles,
                         @RequestParam(value = "tags", required = false) String tagsStr,
                         @RequestParam(value = "fileIdsToDelete", required = false) String fileIdsToDelete,
                         @RequestParam(value = "tagIdsToDelete", required = false) String tagIdsToDelete,
                         RedirectAttributes redirectAttributes) {

        log.info("▶ 게시글 수정 처리 시작 - ID: {}", boardDTO.getId());

        try {
            // 태그 파싱
            List<BoardTagDTO> tags = new ArrayList<>();
            if (tagsStr != null && !tagsStr.isEmpty()) {
                String[] tagArray = tagsStr.split(",");
                tags = Arrays.stream(tagArray)
                        .filter(t -> !t.trim().isEmpty())
                        .map(t -> {
                            BoardTagDTO dto = new BoardTagDTO();
                            dto.setTagName(t.trim());
                            dto.setTagBoardId(boardDTO.getId());
                            return dto;
                        })
                        .collect(Collectors.toList());
            }
            boardDTO.setBoardTags(tags);

            // 삭제할 파일 ID 설정
            if (fileIdsToDelete != null && !fileIdsToDelete.isEmpty()) {
                boardDTO.setFileIdsToDelete(fileIdsToDelete.split(","));
            }

            // 삭제할 태그 ID 설정
            if (tagIdsToDelete != null && !tagIdsToDelete.isEmpty()) {
                boardDTO.setTagIdsToDelete(tagIdsToDelete.split(","));
            }

            // 게시글 수정 처리
            boardService.update(boardDTO, multipartFiles);

            log.info("▶ 게시글 수정 완료 - ID: {}", boardDTO.getId());
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 성공적으로 수정되었습니다.");
            return "redirect:/p2p/list";

        } catch (BoardNotFoundException ex) {
            log.warn("▶ 수정할 게시글을 찾을 수 없음 - ID: {}", boardDTO.getId());
            redirectAttributes.addFlashAttribute("errorMessage", "수정할 게시글이 존재하지 않습니다.");
            return "redirect:/p2p/list";

        } catch (Exception ex) {
            log.error("▶ 게시글 수정 중 오류 발생", ex);
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 수정 중 오류가 발생했습니다.");
            redirectAttributes.addAttribute("id", boardDTO.getId());
            return "redirect:/p2p/update?id=" + boardDTO.getId();
        }
    }

    @GetMapping("/detail/{id}")
    public String detailPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        log.info("▶ 게시글 상세 조회 - ID: {}", id);
        try {
            BoardDTO board = boardService.detail(id);
            model.addAttribute("board", board);
            return "p2p/p2p_detail";
        } catch (BoardNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "해당 게시글이 존재하지 않습니다.");
            return "redirect:/p2p/list";
        }
    }

    @GetMapping("/api/detail/{id}")
    @ResponseBody
    public BoardDTO detailApi(@PathVariable Long id) {
        return boardService.detail(id);
    }

    /**
     * 게시글 삭제
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id,
                         RedirectAttributes redirectAttributes) {
        log.info("▶ 게시글 삭제 요청 - ID: {}", id);

        try {
            boardService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "게시글이 성공적으로 삭제되었습니다.");
            log.info("▶ 게시글 삭제 완료 - ID: {}", id);

        } catch (BoardNotFoundException ex) {
            log.warn("▶ 삭제할 게시글을 찾을 수 없음 - ID: {}", id);
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 게시글이 존재하지 않습니다.");

        } catch (Exception ex) {
            log.error("▶ 게시글 삭제 중 오류 발생", ex);
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 삭제 중 오류가 발생했습니다.");
        }

        return "redirect:/p2p/list";
    }
}
