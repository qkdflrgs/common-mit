import readline from 'readline';

export class InputReceiver {
  private rl: readline.Interface;
  constructor() {
    this.rl = readline.createInterface({
      input: process.stdin,
      output: process.stdout,
    });
  }

  async getInput(): Promise<string> {
    return new Promise((resolve, reject) => {
      this.rl.on('line', (line) => {
        resolve(line);
      });
    });
  }

  close() {
    this.rl.close();
    process.exit();
  }
}
