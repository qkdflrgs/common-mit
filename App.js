const InputView = require("./InputView");
const crypto = require("crypto");
const zlib = require("zlib");
const gzip = zlib.createGzip();
const fs = require("fs");
const path = require("path");

(function () {
  InputView(
    (line) => {
      if (/mit list .+/.test(line)) {
        let [, , dirPath] = line.split(" ");
        fs.readdir(dirPath, (err, filelist) => {
          filelist.forEach((name) => {
            const filePath = path.join(dirPath, name);
            const stat = fs.statSync(filePath);
            console.log(name, stat.size / 1000 + "kB");
          });
        });
      }
      if (/mit hash .+/.test(line)) {
        let [, , dirPath] = line.split(" ");
        fs.readdir(dirPath, (err, filelist) => {
          filelist.forEach((name) => {
            const filePath = path.join(dirPath, name);
            const data = fs.readFileSync(filePath);
            console.log(name, crypto.createHmac("sha256", data).digest("hex"));
          });
        });
      }
    },
    () => console.log("끝")
  );
})();
