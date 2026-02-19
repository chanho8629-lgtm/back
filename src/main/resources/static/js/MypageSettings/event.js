// profileEvent.js

document.addEventListener("DOMContentLoaded", () => {
    const fileInput = document.getElementById("profileInput");
    const profileImage = document.getElementById("profileImage");
    const fileForm = document.getElementById("profileForm");

    if (!fileInput || !profileImage || !fileForm) return;

    fileInput.addEventListener("change", async (e) => {
        const file = e.target.files[0];
        if (!file) return;

        if (!file.type.match(/^image\/(png|jpeg|jpg)$/)) {
            alert("이미지 파일(png, jpeg, jpg)만 가능합니다");
            fileInput.value = "";
            return;
        }


        const reader = new FileReader();
        reader.onload = (ev) => { profileImage.src = ev.target.result; };
        reader.readAsDataURL(file);


        const formData = new FormData(fileForm);
        if (loggedInMemberId) {
            formData.set("memberId", loggedInMemberId);
        }

        try {

            const response = await profileService.updateImage(formData);

            if (response && response.ok) {
                console.log("성공!");

                fileInput.value = "";
                // location.reload();
            } else {
                alert("실패!");

            }
        } catch (error) {
            console.error("에러:", error);
            alert("오류");
        }
    });
});