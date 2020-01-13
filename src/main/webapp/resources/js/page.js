var R = 3;
var ctx;

window.onload = function () {
    document.getElementById("form:y").value = "";
    canvas = document.getElementById("canvas");
    ctx = canvas.getContext("2d");
    canvas.addEventListener('click', click);
    draw_canvas();
    draw_points();
};

function change_R(element) {
    console.log("change R");
    R = element.value;
    console.log(R);
    draw_points();
}

function click(event) {
    let {x, y} = getCursorPosition(event);
    console.log("R value from canvas click:", R);
    x = (x - canvas.width / 2) / (canvas.width / 3) * R;
    y = -(y - canvas.width / 2) / (canvas.width / 3) * R;
    document.getElementById("canvas_form:canvas_x").value = x;
    document.getElementById("canvas_form:canvas_y").value = y;
    document.getElementById("canvas_form:canvas_r").value = R;
    document.getElementById("canvas_form:canvas_but").click();
}
function getCursorPosition(event) {
    let rect = canvas.getBoundingClientRect();
    let x = event.clientX - rect.left;
    let y = event.clientY - rect.top;
    return {x, y};
}

function sub() {
    draw_points();
}

function draw_point(x, y, fill, R) {
    console.log("Зашел нарисовать конкретную точку с радиусом:", R);
    ctx.fillStyle = fill;
    ctx.beginPath();
    x = x / R * (canvas.width / 3) + canvas.width / 2;
    y = canvas.height - (y / R * (canvas.width / 3) + canvas.width / 2);
    ctx.rect(x - 2.5, y - 2.5, 5, 5);
    ctx.fill();
}

function draw_points() {
    draw_canvas();
    console.log("i want to draw some points with radius:", R);
    let rows = document.getElementById("pointTable").rows;
    console.log(rows.length);
    for (let i = 1; i < rows.length; i++) {
        let cells = rows[i].cells;
        let x = cells[0].innerHTML.trim();
        let y = cells[1].innerHTML.trim();
        let res = cells[3].innerHTML.trim();
        if (x !== "" && y !== "" && res !== "") {
            if (i == 1){
                if (res === "True")
                    draw_point(x, y, 'green', R);
                else
                    draw_point(x, y, 'red', R);
            }
            else draw_point(x, y, 'grey', R);
        }
    }
}

function draw_canvas() {
    ctx.clearRect(0, 0, 300, 300);
    ctx.fillStyle = '#95a3b3';
    ctx.fillRect(150, 100, 100, 50);

    ctx.beginPath();
    ctx.arc(150, 150, 100, 0, 1/2*Math.PI);
    ctx.lineTo(150, 150);
    ctx.fill();

    ctx.closePath();
    ctx.beginPath();
    ctx.lineTo(150, 50);
    ctx.lineTo(100, 150);
    ctx.lineTo(150, 150);
    ctx.fill();
    ctx.closePath();

    ctx.strokeStyle = "black";
    ctx.lineWidth = 0.5;

    ctx.beginPath();
    ctx.moveTo(150, 0);
    ctx.lineTo(150, 300);
    ctx.closePath();
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(0, 150);
    ctx.lineTo(300, 150);
    ctx.closePath();
    ctx.stroke();
}
