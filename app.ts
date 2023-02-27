import { InputReceiver } from './input_receiver';
import { MitCommander } from './mit_commander';

class App {
  private readonly inputReceiver: InputReceiver = new InputReceiver();
  private mitCommander!: MitCommander;
  constructor() {
    (async () => {
      const commands = (await this.inputReceiver.getInput()).split(' ');
      const mainCommand = commands[0];
      const subCommand = commands[1];
      const directory = commands[2];

      if (mainCommand !== 'mit')
        throw new Error('첫번째 command가 mit가 아닙니다.');
      this.mitCommander = new MitCommander(subCommand, directory);
    })();
  }
}

const app = new App();
