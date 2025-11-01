window.addEventListener("DOMContentLoaded", async () => {
    const placeholder = document.getElementById("footer-placeholder");
    if (!placeholder) return;

    try {
        const res = await fetch("footer.html");
        const html = await res.text();
        placeholder.innerHTML = html;

        // Gắn CSS nếu chưa có
        if (!document.querySelector('link[href="css/footer.css"]')) {
            const link = document.createElement("link");
            link.rel = "stylesheet";
            link.href = "css/footer.css";
            document.head.appendChild(link);
        }

        // Gắn FontAwesome nếu cần
        if (!document.querySelector('link[href*="font-awesome"]')) {
            const font = document.createElement("link");
            font.rel = "stylesheet";
            font.href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css";
            document.head.appendChild(font);
        }

        // Gắn JS footer nếu có
        const script = document.createElement("script");
        script.src = "js/footer.js";
        document.body.appendChild(script);
    } catch (err) {
        console.error("Lỗi khi tải footer:", err);
    }
});
