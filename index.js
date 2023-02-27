const readline = require('readline');
const chalk = require('chalk');
const fs = require('fs');
const path = require('path');

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
    const filePath = path.join(directory, file);
    fileList.forEach((file) => {
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

function hashFiles(filePath, file) {}
