const fs = require("fs");
const zlib = require("zlib");
const crypto = require("crypto");

function listFiles(directoryPath) {
  const files = fs.readdirSync(directoryPath);
  for (const file of files) {
    const filePath = `${directoryPath}/${file}`;
    const stats = fs.statSync(filePath);
    if (stats.isFile()) {
      console.log(`${file} ${stats.size}KB`);
    }
  }
}

function hashFiles(directoryPath) {
  const files = fs.readdirSync(directoryPath);
  for (const file of files) {
    const filePath = `${directoryPath}/${file}`;
    const stats = fs.statSync(filePath);
    if (stats.isFile()) {
      const data = fs.readFileSync(filePath);
      const hash = crypto.createHash("sha256").update(data).digest("hex");
      console.log(`${file} = ${hash}`);
    }
  }
}

function zipFiles(directoryPath) {
  const files = fs.readdirSync(directoryPath);
  for (const file of files) {
    const filePath = `${directoryPath}/${file}`;
    const stats = fs.statSync(filePath);
    if (stats.isFile()) {
      const data = fs.readFileSync(filePath);
      const compressedData = zlib.deflateSync(data);
      const compressedFilePath = `${filePath}.z`;
      fs.writeFileSync(compressedFilePath, compressedData);
      const compressedStats = fs.statSync(compressedFilePath);
      console.log(`${compressedFilePath} ${compressedStats.size}KB`);
    }
  }
}

this.readline = require("readline");
this.stdio = this.readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

this.stdio.on("line", (line) => {
  const [_, cmd, directoryPath] = line.split(" ");

  if (cmd === "list") {
    listFiles(directoryPath);
  } else if (cmd === "hash") {
    hashFiles(directoryPath);
  } else if (cmd === "zlib") {
    zipFiles(directoryPath);
  } else {
    console.log("명령어 오류");
  }
});
