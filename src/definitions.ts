export interface TapsellPlusPlugin {
  /**
   * Initialize TapsellPlus SDK
   * @param options Configuration options
   */
  initialize(options: InitializeOptions): Promise<void>;

  /**
   * Request a rewarded/instant ad
   * @param options Ad request options
   */
  requestRewardedAd(options: RewardedAdOptions): Promise<AdResponse>;

  /**
   * Show a rewarded/instant ad
   * @param options Ad show options
   */
  showRewardedAd(options: ShowAdOptions): Promise<void>;

  /**
   * Request a native ad
   * @param options Ad request options
   */
  requestNativeAd(options: NativeAdOptions): Promise<AdResponse>;

  /**
   * Show a native ad
   * @param options Ad show options
   */
  showNativeAd(options: ShowNativeAdOptions): Promise<void>;

  /**
   * Request a native video ad
   * @param options Ad request options
   */
  requestNativeVideoAd(options: NativeAdOptions): Promise<AdResponse>;

  /**
   * Show a native video ad
   * @param options Ad show options
   */
  showNativeVideoAd(options: ShowNativeAdOptions): Promise<void>;

  /**
   * Request a standard (banner) ad
   * @param options Ad request options
   */
  requestStandardAd(options: StandardAdOptions): Promise<AdResponse>;

  /**
   * Show a standard (banner) ad
   * @param options Ad show options
   */
  showStandardAd(options: ShowStandardAdOptions): Promise<void>;

  /**
   * Hide a standard (banner) ad
   * @param options Ad hide options
   */
  hideStandardAd(options: HideAdOptions): Promise<void>;

  /**
   * Request a VAST ad
   * @param options Ad request options
   */
  requestVastAd(options: VastAdOptions): Promise<VastAdResponse>;

  /**
   * Show GDPR dialog
   */
  showGDPRDialog(): Promise<void>;

  /**
   * Set user consent
   * @param options Consent options
   */
  setUserConsent(options: ConsentOptions): Promise<void>;
}

export interface InitializeOptions {
  /**
   * Your TapsellPlus API key
   */
  apiKey: string;
}

export interface RewardedAdOptions {
  /**
   * Zone ID for the ad
   */
  zoneId: string;
}

export interface NativeAdOptions {
  /**
   * Zone ID for the ad
   */
  zoneId: string;
}

export interface StandardAdOptions {
  /**
   * Zone ID for the ad
   */
  zoneId: string;
  /**
   * Banner size
   */
  bannerType?: BannerType;
}

export interface VastAdOptions {
  /**
   * Zone ID for the ad
   */
  zoneId: string;
}

export interface ShowAdOptions {
  /**
   * Response ID from the ad request
   */
  responseId: string;
}

export interface ShowNativeAdOptions {
  /**
   * Response ID from the ad request
   */
  responseId: string;
  /**
   * Container element ID in your HTML
   */
  containerId: string;
}

export interface ShowStandardAdOptions {
  /**
   * Response ID from the ad request
   */
  responseId: string;
  /**
   * Container element ID in your HTML
   */
  containerId: string;
  /**
   * Banner size
   */
  bannerType?: BannerType;
}

export interface HideAdOptions {
  /**
   * Response ID from the ad
   */
  responseId: string;
}

export interface ConsentOptions {
  /**
   * Whether user has given consent
   */
  hasConsent: boolean;
  /**
   * Consent type (GDPR, CCPA, etc.)
   */
  consentType?: ConsentType;
}

export interface AdResponse {
  /**
   * Response ID to use when showing the ad
   */
  responseId: string;
  /**
   * Zone ID
   */
  zoneId: string;
  /**
   * Whether the ad is ready
   */
  isReady: boolean;
}

export interface VastAdResponse {
  /**
   * VAST tag URL
   */
  vastTag: string;
  /**
   * Zone ID
   */
  zoneId: string;
}

export enum BannerType {
  BANNER_320x50 = 'BANNER_320x50',
  BANNER_320x100 = 'BANNER_320x100',
  BANNER_250x250 = 'BANNER_250x250',
  BANNER_300x250 = 'BANNER_300x250',
  BANNER_468x60 = 'BANNER_468x60',
  BANNER_728x90 = 'BANNER_728x90',
  SMART_BANNER = 'SMART_BANNER',
}

export enum ConsentType {
  GDPR = 'GDPR',
  CCPA = 'CCPA',
}

