import { WebPlugin } from '@capacitor/core';

import type {
  TapsellPlusPlugin,
  InitializeOptions,
  RewardedAdOptions,
  NativeAdOptions,
  StandardAdOptions,
  VastAdOptions,
  ShowAdOptions,
  ShowNativeAdOptions,
  ShowStandardAdOptions,
  HideAdOptions,
  ConsentOptions,
  AdResponse,
  VastAdResponse,
} from './definitions';

export class TapsellPlusWeb extends WebPlugin implements TapsellPlusPlugin {
  async initialize(_options: InitializeOptions): Promise<void> {
    console.warn('TapsellPlus is not supported on web platform');
  }

  async requestRewardedAd(_options: RewardedAdOptions): Promise<AdResponse> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async showRewardedAd(_options: ShowAdOptions): Promise<void> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async requestNativeAd(_options: NativeAdOptions): Promise<AdResponse> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async showNativeAd(_options: ShowNativeAdOptions): Promise<void> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async requestNativeVideoAd(_options: NativeAdOptions): Promise<AdResponse> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async showNativeVideoAd(_options: ShowNativeAdOptions): Promise<void> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async requestStandardAd(_options: StandardAdOptions): Promise<AdResponse> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async showStandardAd(_options: ShowStandardAdOptions): Promise<void> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async hideStandardAd(_options: HideAdOptions): Promise<void> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async requestVastAd(_options: VastAdOptions): Promise<VastAdResponse> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async showGDPRDialog(): Promise<void> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }

  async setUserConsent(_options: ConsentOptions): Promise<void> {
    throw this.unimplemented('TapsellPlus is not supported on web platform');
  }
}

