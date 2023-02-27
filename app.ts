import { InputReceiver } from './input_receiver';

class App {
  private readonly inputReceiver: InputReceiver = new InputReceiver();
  private readonly mitCommander: MitCommander = new MitCommander();
  constructor() {
    (async () => {
      const command = await this.inputReceiver.getInput();
    })();
  }
}
