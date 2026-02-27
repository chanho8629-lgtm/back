const buttons = document.querySelectorAll(
    ".CommentNavigation_naviArea__2s32l button"
);

const sections = {
    btnSatisfaction: "satisfaction",
    btnSupport: "support",
    btnSignature: "signature",
};

buttons.forEach((btn) => {
    btn.addEventListener("click", () => {
        //   active 클래스 제거
        buttons.forEach((b) => b.classList.remove("Tab_active__-dPX3"));

        //   클릭한 버튼에 active 추가
        btn.classList.add("Tab_active__-dPX3");

        //   해당 섹션으로 스크롤 이동
        const targetId = sections[btn.id];
        if (targetId) {
            document
                .getElementById(targetId)
                .scrollIntoView({ behavior: "smooth" });
        }
    });
});


document.addEventListener("DOMContentLoaded", function() {
    const followBtn = document.getElementById("followBtn");

    followBtn.addEventListener("click", async function() {
        const followeeId = this.dataset.followeeId;
        // 현재 로그인한 사용자의 ID는 세션 또는 JWT로 서버에서 처리
        // 아래 예시는 세션에서 서버가 자동으로 추출한다고 가정
        try {
            const response = await fetch('/follow/toggle', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: `followeeId=${followeeId}`
            });

            if (!response.ok) throw new Error("서버 요청 실패");

            const data = await response.json();

            if (data.following) {
                followBtn.textContent = "팔로우 취소";
            } else {
                followBtn.textContent = "팔로우";
            }

        } catch (err) {
            console.error(err);
            alert("팔로우 처리 중 오류가 발생했습니다.");
        }
    });
});

// 댓글 토글 버튼

document
    .querySelectorAll(".CommunityArea_replyToggle__Qu23y")
    .forEach((toggle) => {
        toggle.addEventListener("click", () => {
            toggle.classList.toggle("active");

            const container = toggle.closest(".CommunityArea_container__F_81k");

            const replyInput = container.querySelector(
                ".ReplyInput_replyInputContainer__5BSrT"
            );
            const replyItem = container.querySelector(
                ".ReplyItem_replyContainer__1E_fe"
            );

            const isOpen = toggle.classList.contains("active");

            if (replyInput) replyInput.style.display = isOpen ? "flex" : "none";
            if (replyItem) replyItem.style.display = isOpen ? "block" : "none";
        });
    });
