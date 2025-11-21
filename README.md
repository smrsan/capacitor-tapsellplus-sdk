# Capacitor TapsellPlus Plugin

A Capacitor plugin for integrating TapsellPlus ads SDK into your Capacitor applications.

## Installation

```bash
npm install @capacitor-community/tapsellplus
npx cap sync
```

## Android Setup

### 1. Add to your `android/app/build.gradle`

Make sure you have the following repositories in your `android/build.gradle`:

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url 'https://dl.bintray.com/tapsellorg/maven'
        }
    }
}
```

### 2. Add Internet Permission

Add to `android/app/src/main/AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### 3. Add Container Views (for Native/Banner Ads)

For native ads, native video ads, and banner ads, you need to add container views to your layout XML files. The `containerId` parameter should match the `android:id` of the view in your layout.

Example in `res/layout/activity_main.xml`:

```xml
<FrameLayout
    android:id="@+id/native_ad_container"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />

<FrameLayout
    android:id="@+id/banner_ad_container"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

### 3. Initialize the Plugin

In your app's main activity or application class, initialize TapsellPlus:

```typescript
import { TapsellPlus } from '@capacitor-community/tapsellplus';

// Initialize with your API key
await TapsellPlus.initialize({
  apiKey: 'YOUR_TAPSELL_PLUS_API_KEY'
});
```

## Usage

### Rewarded/Instant Ads

```typescript
import { TapsellPlus } from '@capacitor-community/tapsellplus';

// Request a rewarded ad
const response = await TapsellPlus.requestRewardedAd({
  zoneId: 'YOUR_ZONE_ID'
});

// Show the ad
await TapsellPlus.showRewardedAd({
  responseId: response.responseId
});

// Listen to events
TapsellPlus.addListener('onRewardedAdOpened', () => {
  console.log('Rewarded ad opened');
});

TapsellPlus.addListener('onRewardedAdClosed', () => {
  console.log('Rewarded ad closed');
});

TapsellPlus.addListener('onRewardedAdRewarded', (data) => {
  console.log('User rewarded', data);
});
```

### Native Ads

```typescript
// Request a native ad
const response = await TapsellPlus.requestNativeAd({
  zoneId: 'YOUR_ZONE_ID'
});

// Show the ad in a container
await TapsellPlus.showNativeAd({
  responseId: response.responseId,
  containerId: 'native_ad_container' // ID of your container view
});
```

### Native Video Ads

```typescript
// Request a native video ad
const response = await TapsellPlus.requestNativeVideoAd({
  zoneId: 'YOUR_ZONE_ID'
});

// Show the ad
await TapsellPlus.showNativeVideoAd({
  responseId: response.responseId,
  containerId: 'native_video_ad_container'
});
```

### Standard (Banner) Ads

```typescript
import { BannerType } from '@capacitor-community/tapsellplus';

// Request a standard banner ad
const response = await TapsellPlus.requestStandardAd({
  zoneId: 'YOUR_ZONE_ID',
  bannerType: BannerType.SMART_BANNER // Optional, defaults to SMART_BANNER
});

// Show the ad
await TapsellPlus.showStandardAd({
  responseId: response.responseId,
  containerId: 'banner_ad_container',
  bannerType: BannerType.SMART_BANNER // Optional
});

// Hide the ad when done
await TapsellPlus.hideStandardAd({
  responseId: response.responseId
});
```

### VAST Ads

```typescript
// Request VAST tag
const vastResponse = await TapsellPlus.requestVastAd({
  zoneId: 'YOUR_ZONE_ID'
});

// Use the VAST tag URL with your video player
console.log('VAST Tag:', vastResponse.vastTag);
```

### GDPR

```typescript
// Show GDPR dialog
await TapsellPlus.showGDPRDialog();

// Or set consent programmatically
await TapsellPlus.setUserConsent({
  hasConsent: true,
  consentType: ConsentType.GDPR
});
```

## API Reference

### Methods

#### `initialize(options: InitializeOptions)`
Initialize the TapsellPlus SDK.

#### `requestRewardedAd(options: RewardedAdOptions)`
Request a rewarded/instant ad. Returns a promise with `AdResponse`.

#### `showRewardedAd(options: ShowAdOptions)`
Show a rewarded/instant ad.

#### `requestNativeAd(options: NativeAdOptions)`
Request a native ad. Returns a promise with `AdResponse`.

#### `showNativeAd(options: ShowNativeAdOptions)`
Show a native ad in a container.

#### `requestNativeVideoAd(options: NativeAdOptions)`
Request a native video ad. Returns a promise with `AdResponse`.

#### `showNativeVideoAd(options: ShowNativeAdOptions)`
Show a native video ad in a container.

#### `requestStandardAd(options: StandardAdOptions)`
Request a standard (banner) ad. Returns a promise with `AdResponse`.

#### `showStandardAd(options: ShowStandardAdOptions)`
Show a standard (banner) ad in a container.

#### `hideStandardAd(options: HideAdOptions)`
Hide a standard (banner) ad.

#### `requestVastAd(options: VastAdOptions)`
Request a VAST ad tag. Returns a promise with `VastAdResponse`.

#### `showGDPRDialog()`
Show the GDPR consent dialog.

#### `setUserConsent(options: ConsentOptions)`
Set user consent programmatically.

### Events

Listen to ad events using `addListener`:

- `onRewardedAdOpened` - Rewarded ad opened
- `onRewardedAdClosed` - Rewarded ad closed
- `onRewardedAdRewarded` - User was rewarded
- `onRewardedAdError` - Error showing rewarded ad
- `onNativeAdOpened` - Native ad opened
- `onNativeAdClosed` - Native ad closed
- `onNativeAdError` - Error showing native ad
- `onNativeVideoAdOpened` - Native video ad opened
- `onNativeVideoAdClosed` - Native video ad closed
- `onNativeVideoAdError` - Error showing native video ad
- `onStandardAdRequestFilled` - Standard ad request filled
- `onStandardAdNoAdAvailable` - No ad available
- `onStandardAdNoNetwork` - No network available
- `onStandardAdError` - Error with standard ad
- `onStandardAdOpened` - Standard ad opened
- `onStandardAdClosed` - Standard ad closed

### Types

#### `BannerType`
- `BANNER_320x50`
- `BANNER_320x100`
- `BANNER_250x250`
- `BANNER_300x250`
- `BANNER_468x60`
- `BANNER_728x90`
- `SMART_BANNER`

#### `ConsentType`
- `GDPR`
- `CCPA`

## Requirements

- Android SDK 22+
- Capacitor 5.0+
- TapsellPlus SDK 2.3.3

## License

MIT

## Support

For issues and feature requests, please use the [GitHub Issues](https://github.com/your-repo/capacitor-tapsellplus-sdk/issues) page.

## Credits

This plugin integrates with [TapsellPlus Android SDK](https://docs.tapsell.ir/plus-sdk/android/main/).

