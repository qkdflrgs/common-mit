const { list } = require("./list.js");
const { hash } = require("./hash.js");
const { zlib, printZlib } = require("./zlib.js");
this.readline = require("readline");
this.stdio = this.readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

console.log("> 명령어를 입력해 주세요.");
console.log("형식 1. : mit list 디렉토리명");
console.log("형식 2. : mit hash 디렉토리명");
console.log("형식 3. : mit zlib 디렉토리명");

this.stdio.on("line", (cmd) => {
  if (cmd === "exit") {
    this.stdio.close();
  }

  const [commandName, subCommand, directoryPath] = cmd.split(" ");

  if (commandName === "mit") {
    switch (subCommand) {
      case "list":
        list(directoryPath);
        break;
      case "hash":
        hash(directoryPath);
        break;
      case "zlib":
        zlib(directoryPath);
        break;
    }
  }
});
