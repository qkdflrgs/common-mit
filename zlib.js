const fs = require("fs");
const path = require("path");
const zlib = require("zlib");

const options = {
  filter: (filename) => {
    return !/(^|\/)\.[^\/\.]/g.test(filename); // Ignore hidden files
  },
};

async function zlibFunc(directoryPath) {
  const files = fs.readdirSync(directoryPath);
  files.forEach((file) => {
    // zlib 파일로 저장
    if (!file.includes(".z")) {
      const filePath = path.resolve(directoryPath, file);
      const readStream = fs.createReadStream(filePath);
      const writeStream = fs.createWriteStream(filePath + ".z");
      const zlibStream = zlib.createGzip(options);
      readStream.pipe(zlibStream).pipe(writeStream);
    }
  });
  await printZlib(directoryPath);
}

async function printZlib(directoryPath) {
  const zlibFiles = fs
    .readdirSync(directoryPath)
    .filter((file) => file.includes(".z"));
  zlibFiles.forEach((zlibFile) => {
    const filePath = path.resolve(directoryPath, zlibFile);
    const stats = fs.statSync(filePath);
    const fileSize = stats.size;
    console.log(`${zlibFile} : ${fileSize}Bytes`);
  });
}
module.exports = { zlib: zlibFunc, printZlib: printZlib };
//mit zlib Work/Masters
