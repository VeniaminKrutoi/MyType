const SECOND = 1000;
const MINUTE = 60;
const HOUR = 60;

let index, error_count, start_time, finished, letters, text, author, source, waiting;

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
    if (finished && !waiting) {
        start_type();
    }

    if (text[index] === event.key) {
        letters[index].className = "written"
    } else {
        letters[index].className = "error"
        error_count++;
    }

    index++;

    if (index === text.length) {
        end_type()
    }
});

const type = document.getElementById("type");

// const data = {"text": "12345", "author": "я", "sourceLink": "youtube.com"}
// set_data(data);

function wait_data() {
    waiting = true;
    window.dataPromise.then(data => {
        console.log(data);
        if ('text' in data) {
            set_data(data);
            fill_typing();
        } else {
            connection_fail();
        }
    });
}

function connection_fail(){
    const uploading_el = document.getElementById("uploading");
    uploading_el.style.visibility = "hidden";

    const error_button_el = document.getElementById("error_button");
    error_button_el.style.visibility = "visible";
}

function set_data(data) {
    text = data['text'];
    author = data['author'];
    source = data['sourceLink'];
}

function fill_typing() {
    index = 0;
    error_count = 0;
    finished = true;
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

    waiting = false;
}

function start_type() {

    finished = false;

    error_count = 0;

    start_time = Date.now();
}

function end_type() {
    finished = true;

    const end_time = Date.now()
    const time_diff = end_time - start_time;

    fill_result_time(time_diff);

    const sign_per_minute = Math.round(text.length / (time_diff / SECOND / MINUTE));
    const sign_per_minute_el = document.getElementById("result_spm");
    sign_per_minute_el.textContent = sign_per_minute.toString();

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

function restart() {
    fill_typing();
}

function fill_result_time(time_diff) {
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

wait_data();