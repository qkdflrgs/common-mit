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

function main() {
  const command = process.argv[2];
  const directoryPath = process.argv[3];
  switch (command) {
    case "list":
      listFiles(directoryPath);
      break;
    case "hash":
      hashFiles(directoryPath);
      break;
    case "zip":
      zipFiles(directoryPath);
      break;
    default:
      console.error(`Unknown command: ${command}`);
      process.exit(1);
  }
}

main();
