let r, g, b;
let brain;

let wButton;
let bButton;

function pickColor() {
  r = random(255);
  g = random(255);
  b = random(255);
  redraw();
}

function setup() {
  createCanvas(600, 300);
  noLoop();
  brain = new NeuralNetwork(3, 3, 2);

  for (let i = 0; i < 10000; i++) {
    const color = [random(255), random(255), random(255)]
    let targets = trainColor(...color);
    let inputs = color.map(c=>c/255);
    brain.train(inputs, targets);
  }

  pickColor();
}

function mousePressed() {
  pickColor();
}

function calculateColorValue(c) {
  const Crsgb = c / 255;

  if (Crsgb <= 0.03928) {
    return Crsgb / 12.92;
  }

  return ((Crsgb + 0.055) / 1.055) ** 2.4;
}

function calculateLuminance(color) {
  const [R, G, B] = color.map(calculateColorValue);

  const L = 0.2126 * R + 0.7152 * G + 0.0722 * B;

  return L;
}

function calculateRatio(light, dark) {
  const L1 = calculateLuminance(light);
  const L2 = calculateLuminance(dark);

  return (L1 + 0.05) / (L2 + 0.05);
}

function colorPredictor(...colors) {
  const colorsMapped = colors.map(c=>Math.round(c));
  const [r, g, b] = colorsMapped;

  const ratioWhite = calculateRatio([255, 255, 255], [r, g, b]);
  const ratioBlack = calculateRatio([r, g, b], [0, 0, 0]);

  const displayRatios = ratio => {
    const [int, dec = "0"] = String(Math.floor(ratio * 100) / 100).split(".");

    return `${int.padStart(2, " ")}.${dec.padEnd(2, "0")}:1`
  }

  console.log(
    displayRatios(ratioBlack),
    displayRatios(ratioWhite),

    `Should be ${(ratioBlack > ratioWhite) ? "black" : "white"}`
  );
  

  let inputs = colors.map(c=> c/255);
  let outputs = brain.predict(inputs);
  

  if (outputs[0] > outputs[1]) {
    return "black";
  } else {
    return "white";
  }
}

function trainColor(r, g, b) {
  const ratioWhite = calculateRatio([255,255,255], [r,g,b]);
  const ratioBlack = calculateRatio([r,g,b], [0,0,0]);

  return (ratioBlack > ratioWhite) ? [1, 0] : [0, 1]
}


function draw() {
  background(r, g, b);
  strokeWeight(4);
  stroke(0);
  line(width / 2, 0, width / 2, height);

  textSize(64);
  noStroke();
  fill(0);
  textAlign(CENTER, CENTER);
  text("black", 150, 100);
  fill(255);
  text("white", 450, 100);

  const which = colorPredictor(r, g, b);
  if (which === "black") {
    fill(0);
    ellipse(150, 200, 60);
  } else {
    fill(255);
    ellipse(450, 200, 60);
  }
}
