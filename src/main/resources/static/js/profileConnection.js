function checkUser() {
    console.log(document.cookie.includes("user"))
    if (!document.cookie.includes("user")) {
        window.location.href = "http://localhost:8080/profile/login"
    }
}

checkUser();

function getUserInfo(){
    const cookie = document.cookie;
    console.log(cookie);

    const index = cookie.indexOf("user");
    console.log(index);

    const valueAndOther = document.cookie.substring(index);
    console.log(valueAndOther);

    const value = valueAndOther.substring(0, valueAndOther.indexOf(";"));
    console.log(value);
}

getUserInfo();