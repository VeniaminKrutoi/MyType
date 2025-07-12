localStorage.setItem("text_status", "-1");

function getText() {
    // console.log(window.dataPromise);
    window.dataPromise = null;
    const post_data = {
        key: Math.floor(Math.random() * 100000000)
    }

    const get = 'http://localhost:8080';

    window.dataPromise = fetch(get, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(post_data)
    }).then(response => response.json());
}

function changeText() {
    const error_button_el = document.getElementById("error_button");
    error_button_el.style.visibility = "hidden";

    const text_full_el = document.getElementById("text_full");
    text_full_el.style.visibility = "hidden";

    const text_info_el = document.getElementById("text_info")
    text_info_el.style.visibility = "hidden";

    const uploading_el = document.getElementById("uploading");
    uploading_el.style.visibility = "visible";

    getText();
}

getText();

