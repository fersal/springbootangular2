import { MytestappPage } from './app.po';

describe('mytestapp App', function() {
  let page: MytestappPage;

  beforeEach(() => {
    page = new MytestappPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
