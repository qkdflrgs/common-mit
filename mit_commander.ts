const fs = require('fs/promises');
const os = require('os');

export class MitCommander {
  private readonly subCommand: string;
  private readonly directory: string;
  constructor(subCommand: string, directory: string) {
    this.subCommand = subCommand;
    this.directory = directory;

    switch (subCommand) {
      case 'list':
        this.list();
    }
  }

  private async list(): Promise<void> {
    // test path: ~/dev-github/codesquad/cs16_common_mit/Work/Masters
    const path = `${os.homedir()}${this.directory.replace('~', '')}`;
    const fileNames = await fs.readdir(path);
    for (const fileName of fileNames) {
      console.log(
        `${fileName} ${(await fs.stat(`${path}/${fileName}`)).size / 1000}KB`,
      );
    }
  }
}
