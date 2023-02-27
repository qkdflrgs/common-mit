const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

function InputView(callback, end) {
  rl.on("line", (line) => {
    try {
      callback(line);
    } catch (e) {
      console.log(e);
      rl.close();
    }
  }).on("close", () => {
    end();
  });
}

module.exports = InputView;
