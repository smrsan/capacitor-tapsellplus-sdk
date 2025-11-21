import { registerPlugin } from '@capacitor/core';

import type { TapsellPlusPlugin } from './definitions';

const TapsellPlus = registerPlugin<TapsellPlusPlugin>('TapsellPlus', {
  web: () => import('./web').then(m => new m.TapsellPlusWeb()),
});

export * from './definitions';
export { TapsellPlus };

