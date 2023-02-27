import * as readline from "node:readline/promises";
import { stdin as input, stdout as output } from "node:process";

class CommandLine {
  constructor() {
    this.rl = readline.createInterface({ input, output });
  }

  async start() {
    this.rl.setPrompt("> ");
    this.rl.prompt();

    for await (const input of this.rl) {
      // do something
      console.log("\n");
      this.rl.prompt();
    }
  }

  end() {
    this.rl.close();
  }
}

export default CommandLine;
