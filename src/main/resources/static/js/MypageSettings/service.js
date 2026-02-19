const profileService = (() => {

    const updateImage = async (formData) => {
        try {
            const response = await fetch("/profile/update", {
                method: "POST",
                body: formData
            });
            return response;
        } catch (error) {
            console.error("통신 에러:", error);
        }
    };

    return { updateImage: updateImage };
})();