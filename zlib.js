const fs = require("fs");
const path = require("path");
const zlib = require("zlib");
const { promisify } = require("util");
const pipeline = promisify(require("stream").pipeline);

const options = {
  filter: (filename) => {
    return !/(^|\/)\.[^\/\.]/g.test(filename); // Ignore hidden files
  },
};

async function zlibFunc(directoryPath) {
  const files = fs.readdirSync(directoryPath);
  for (const file of files) {
    // zlib 파일로 저장
    if (!file.includes(".z")) {
      const filePath = path.resolve(directoryPath, file);
      //   const readStream = fs.createReadStream(filePath);
      const writeStream = fs.createWriteStream(filePath + ".z");
      const zlibStream = zlib.createGzip(options);
      await pipeline(fs.createReadStream(filePath), zlibStream, writeStream);
    }
  }
  await printZlib(directoryPath);
}

async function printZlib(directoryPath) {
  const zlibFiles = fs
    .readdirSync(directoryPath)
    .filter((file) => file.includes(".z"));
  for (const zlibFile of zlibFiles) {
    const filePath = path.resolve(directoryPath, zlibFile);
    const stats = fs.statSync(filePath);
    const fileSize = stats.size;
    console.log(`${zlibFile} : ${fileSize} Bytes`);
  }
}
module.exports = { zlib: zlibFunc, printZlib: printZlib };
//mit zlib Work/Masters
