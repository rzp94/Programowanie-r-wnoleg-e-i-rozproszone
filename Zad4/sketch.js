let model;
let strokePath = null;

let x, y;
let pen;
let Width = 1000;
let Height = 800;

function setup() {
  createCanvas(Width, Height);
  setupNewSketch();
  model = ml5.SketchRNN("cat", modelReady);
  background(0);
}

function modelReady() {
  console.log("model ready");
  model.reset();
  model.generate(gotSketch);
}

function draw() {
  translate(width / 2, height / 2);
  if (strokePath != null) {
    let newX = x + strokePath.dx * 0.2;
    let newY = y + strokePath.dy * 0.2;
    if (pen == "down") {
      stroke(255);
      strokeWeight(2);
      line(x, y, newX, newY);
    }
    pen = strokePath.pen;
    strokePath = null;
    x = newX;
    y = newY;

    if (pen !== "end") {
      model.generate(gotSketch);
    } else {
      console.log("drawing complete");
      setupNewSketch(); 
      model.reset();
      model.generate(gotSketch);
    }
  }
}

function gotSketch(error, s) {
  if (error) {
    console.error(error);
  } else {
    strokePath = s;
  }
}

function setupNewSketch() {
  pen = "down";
  x = random(-width / 2, width / 2);
  y = random(-height / 2, height / 2);
}
