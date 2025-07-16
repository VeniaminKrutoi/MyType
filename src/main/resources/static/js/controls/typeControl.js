const SECOND = 1000;
const MINUTE = 60;
const HOUR = 60;

let index, error_count, start_time, letters;

const type = document.getElementById("type");

let canType = false;
let finished = true;

const text = data['text'];
const author = data['author'];
const source = data['sourceLink'];

function fillTyping() {
    index = 0;
    error_count = 0;
    letters = [];

    type.innerHTML = "";
    for (const letter of text) {
        const letter_el = document.createElement("p");
        letter_el.className = "writing";
        letter_el.textContent = letter;
        type.appendChild(letter_el);
        letters.push(letter_el);
    }

    const author_el = document.getElementById("author");
    if (author !== null && author !== "") {
        author_el.textContent = author;
    } else {
        author_el.textContent = "Неизвестно";
    }

    const source_el = document.getElementById("source");
    if (source !== null && source !== "") {
        source_el.textContent = source;
        source_el.href = source.includes("https") ? "" : "https://" + source;
    } else {
        source_el.textContent = "Неизвестно";
    }

    const text_full_el = document.getElementById("text_full");
    text_full_el.style.visibility = "visible";

    const text_info_el = document.getElementById("text_info");
    text_info_el.style.visibility = "visible";

    const uploading_el = document.getElementById("uploading");
    uploading_el.style.visibility = "hidden";

    canType = true;
}

function setKeyBoardListeners(){
    document.addEventListener("keydown", function (event) {
        if (finished) {
            return;
        }

        if (index > 0 && event.key === "Backspace") {
            letters[index - 1].className = "writing";
            index--;
        }
    });

    document.addEventListener("keypress", function (event) {
        if (canType) {
            startType();
        }

        if (finished) {
            return;
        }

        if (text[index] === event.key) {
            letters[index].className = "written";
        } else {
            letters[index].className = "error";
            error_count++;
        }

        index++;

        if (index === text.length) {
            endType();
        }
    });
}

function startType() {
    canType = false;
    finished = false;
    error_count = 0;
    start_time = Date.now();
}

function endType() {
    finished = true;
    const end_time = Date.now()
    const time_diff = end_time - start_time;


    fillResultTime(time_diff);

    const sign_per_minute = Math.round(text.length / (time_diff / SECOND / MINUTE));
    const sign_per_minute_el = document.getElementById("result_spm");
    sign_per_minute_el.textContent = sign_per_minute.toString();

    const data = {
        typeResults:sign_per_minute,
        typeCount:1,
        time:time_diff
    }

    sendInfo(data);

    const accuracy = 100 - Math.round((error_count / text.length) * 100);
    const accuracy_el = document.getElementById("result_accuracy")
    accuracy_el.textContent = accuracy.toString();

    const result_el = document.getElementById("result");

    result_el.style.visibility = "visible";

    type.classList.add("fade-out");
    result_el.classList.add("fade-in");

    setTimeout(
        () => {
            type.style.visibility = "hidden";
            type.classList.remove("fade-out");
        },
        SECOND
    );
}

function fillResultTime(time_diff) {
    const time_el = document.getElementById("result_time");

    const hours = Math.floor(time_diff / SECOND / MINUTE / HOUR);
    const minutes = Math.floor(time_diff / SECOND / MINUTE % HOUR);
    const seconds = Math.ceil(time_diff / SECOND % MINUTE);

    if (hours !== 0) {
        time_el.textContent = `${hours}ч ${minutes}м ${seconds}с`;
    } else if (minutes !== 0) {
        time_el.textContent = `${minutes}м ${seconds}с`;
    } else {
        time_el.textContent = `${seconds}с`;
    }
}

function restart() {
    fillTyping();
}

function getNewText(){
    window.location.reload();
}

function textFail(data){
    showNotification(data, "error");

    const uploading_el = document.getElementById("uploading");
    uploading_el.style.visibility = "hidden";

    const error_button_el = document.getElementById("error_button");
    error_button_el.style.visibility = "visible";
}

if (data !== null) {
    fillTyping();
    setKeyBoardListeners();
} else {
    textFail("Проблемы с подключением");
}