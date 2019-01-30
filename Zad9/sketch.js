const url = 'https://quickdrawfiles.appspot.com/drawing/dog?isAnimated=false&format=json&key='

let strokeIndex = 0;
let index = 0;
let dog;
let prevx, prevy;
let keyInput;
let start;

function setup() {
  createCanvas(255, 255);
  newDog();
  // keyInput = createInput('');
  // keyInput.attribute('type', 'password');
  // start = createButton('start');
  // start.mousePressed(newDog);
}

function newDog() {
  let apiKey = 'AIzaSyCLxdiMV5-46xuFWFbdDhVoJi7DMwe-H9Q'; // keyInput.value();
  loadJSON(url + apiKey, gotdog);
}


function gotdog(data) {
  background(220);
  dog = data.drawing;
}

function draw() {
  if (dog) {
    let x = dog[strokeIndex][0][index];
    let y = dog[strokeIndex][1][index];
    stroke(0);
    strokeWeight(3);
    if (prevx !== undefined) {
      line(prevx, prevy, x, y);
    }
    index++;
    if (index === dog[strokeIndex][0].length) {
      strokeIndex++;
      prevx = undefined;
      prevy = undefined;
      index = 0;
      if (strokeIndex === dog.length) {
        dog = undefined;
        strokeIndex = 0;
        setTimeout(newDog, 100);
      }
    } else {
      prevx = x;
      prevy = y;
    }
  }
}