let mobilenet;
let tiger;

function modelReady() {
  console.log('Model is ready!!!');
  mobilenet.predict(tiger, gotResults);
}

function gotResults(error, results) {
  if (error) {
    console.error(error);
  } else {
    console.log(results);
    let label = results[0].className;
    let prob = results[0].probability;
    fill(0);
    textSize(64);
    text(label, 10, height - 100);
    createP(label);
    createP(prob);
  }
}

function imageReady() {
  image(tiger, 0, 0, width, height);
}

function setup() {
  createCanvas(640, 480);
  tiger = createImg('images/tiger.jpg', imageReady);
  tiger.hide();
  background(0);
  mobilenet = ml5.imageClassifier('MobileNet', modelReady);
}