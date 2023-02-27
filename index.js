const readline = require('readline');
const chalk = require('chalk');
const fs = require('fs');
const path = require('path');
const crypto = require('crypto');

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
      let result = '';
      switch (command) {
        case 'list':
          listFiles(filePath, file);
        case 'hash':
          hashFiles(filePath, file);
        case 'zlib':
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

function checkIsDirectory(filePath) {
  return new Promise((resolve, reject) => {
    fs.stat(filePath, (err, stat) => {
      if (err) {
        console.log(chalk.red(err.message));
        return reject(err);
      }
      resolve(stat.isDirectory());
    });
  });
}

async function hashFiles(filePath, file) {
  const hash = crypto.createHash('sha256');
  const fileStream = fs.createReadStream(filePath);
  let isDirectory = false;
  try {
    isDirectory = await checkIsDirectory(filePath);
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
