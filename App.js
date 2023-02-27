const InputView = require("./InputView");
const crypto = require("crypto");
const zlib = require("zlib");
const fs = require("fs");
const path = require("path");

function mitList(line, callback) {
  let [, , dirPath] = line.split(" ");
  fs.readdir(dirPath, (err, filelist) => {
    filelist.forEach((name) => {
      const filePath = path.join(dirPath, name);
      const stat = fs.statSync(filePath);
      callback(`${name} ${stat.size / 1000 + "kB"}`);
    });
  });
}

function mitHash(line, callback) {
  let [, , dirPath] = line.split(" ");
  fs.readdir(dirPath, (err, filelist) => {
    filelist.forEach((name) => {
      const filePath = path.join(dirPath, name);
      const data = fs.readFileSync(filePath);
      callback(`${name} ${crypto.createHmac("sha256", data).digest("hex")}`);
    });
  });
}

async function compress(dirPath, name) {
  return new Promise((resolve, reject) => {
    const filePath = path.join(dirPath, name);
    const zlipFilePath = filePath + ".z";
    const gzip = zlib.createGzip();
    const inp = fs.createReadStream(filePath);
    const out = fs.createWriteStream(zlipFilePath);
    inp.pipe(gzip).pipe(out);
    out.on("finish", () => resolve({ zlipFilePath }));
  });
}

function mitZlib(line, callback) {
  let [, , dirPath] = line.split(" ");
  fs.readdir(dirPath, async (err, filelist) => {
    filelist.forEach(async (name) => {
      const data = await compress(dirPath, name);
      const { zlipFilePath } = data;
      const stat = fs.statSync(zlipFilePath);
      callback(`${name + ".z"} ${stat.size / 1000 + "kB"}`);
    });
  });
}

(function () {
  function print(str) {
    console.log(str);
  }

  InputView(
    (line) => {
      if (/mit list .+/.test(line)) mitList(line, print);
      if (/mit hash .+/.test(line)) mitHash(line, print);
      if (/mit zlib .+/.test(line)) mitZlib(line, print);
    },
    () => console.log("끝")
  );
})();
