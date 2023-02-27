const readline = require('readline');
const chalk = require('chalk');
const fs = require('fs');
const path = require('path');
const crypto = require('crypto');
const zlib = require('zlib');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question(chalk.blue(''), async (input) => {
  const isValid = /^mit\s+(list|hash|zlib)\s+([~\w\s/\\:\*\?"<>\|]+)$/.test(
    input
  );
  if (!isValid) {
    console.log(chalk.red('Invalid input'));
    rl.close();
    return;
  }

  const [_, command, directory] = input.split(' ');
  try {
    const fileList = await getFileList(directory);

    fileList.forEach((file) => {
      const filePath = path.join(directory, file);
      switch (command) {
        case 'list':
          listFiles(filePath, file);
          break;
        case 'hash':
          hashFiles(filePath, file);
          break;
        case 'zlib':
          zipFiles(filePath, file, directory);
          break;
      }
    });
  } catch (err) {
    console.log(chalk.red(err.message));
    rl.close();
    return;
  }

  rl.close();
});

function getFileList(directory) {
  return new Promise((resolve, reject) => {
    fs.readdir(directory, (err, files) => {
      if (err) {
        return reject(err);
      }
      resolve(files);
    });
  });
}

function listFiles(filePath, file) {
  fs.stat(filePath, (err, stat) => {
    if (err) {
      console.log(chalk.red(err.message));
      return;
    }
    const fileSizeInKiloBytes = (stat.size / 1024).toFixed(2);

    if (stat.isDirectory()) {
      console.log(chalk.green(`${file + '/'} ${fileSizeInKiloBytes}KB`));
      return;
    }

    console.log(chalk.green(`${file} ${fileSizeInKiloBytes}KB`));
  });
}

async function hashFiles(filePath, file) {
  const hash = crypto.createHash('sha256');
  const fileStream = fs.createReadStream(filePath);
  let isDirectory = false;
  let stat;
  try {
    stat = await getStat(filePath);
    if (stat.isDirectory()) isDirectory = true;
  } catch (err) {
    console.log(chalk.red(err.message));
    return;
  }

  if (isDirectory) {
    return;
  }

  fileStream.on('data', (data) => {
    hash.update(data);
  });

  fileStream.on('error', (err) => {
    console.log(chalk.red(err.message));
    return;
  });

  fileStream.on('end', () => {
    const sha256Hash = hash.digest('hex');
    console.log(chalk.green(`${file} = ${sha256Hash}`));
  });
}

async function zipFiles(filePath, file) {
  const outputFilePath = getOutputFilePath(filePath);
  const outputFileName = path.basename(outputFilePath);
  const readStream = fs.createReadStream(filePath);
  const writeStream = fs.createWriteStream(outputFilePath);
  const zip = zlib.createDeflate();
  readStream.pipe(zip).pipe(writeStream);
  let stat;

  try {
    stat = await getStat(filePath);
  } catch (err) {
    console.log(chalk.red(err.message));
    return;
  }
  writeStream.on('error', (err) => {
    console.error(`Failed to compress file ${filePath}: ${err}`);
  });

  const fileSizeInKiloBytes = (stat.size / 1024).toFixed(2);
  writeStream.on('close', () => {
    console.log(`${outputFileName} ${fileSizeInKiloBytes}KB`);
  });
}

// utils

function getStat(filePath) {
  return new Promise((resolve, reject) => {
    fs.stat(filePath, (err, stat) => {
      if (err) {
        return reject(err);
      }
      resolve(stat);
    });
  });
}

function getOutputFilePath(filePath) {
  const directoryPath = path.dirname(filePath);
  const fileExt = path.extname(filePath);
  const fileName = path.basename(filePath, fileExt);
  const zipFileName = `${fileName}.z`;
  const outputFilePath = path.join(directoryPath, zipFileName);
  return outputFilePath;
}
