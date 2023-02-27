const crypto = require("crypto");
const zlib = require("zlib");
const gzip = zlib.createGzip();
const fs = require("fs");
const input = fs.createReadStream("public");
const output = fs.createWriteStream("public.gz");
const path = require("path")

console.log(path.resolve("/"))

// fs.readdir("~", (err, filelist) => {
//   console.log(filelist);
// });

// input.pipe(gzip).pipe(output);
// const secret = "geppetto";
// const password = "abc123";

// const hashed = crypto
//   .createHmac("sha256", secret)
//   .update(password)
//   .digest("hex");

// console.log(hashed);
