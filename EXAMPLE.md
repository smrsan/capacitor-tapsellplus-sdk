# Example Usage

## Basic Setup

```typescript
import { TapsellPlus, BannerType } from '@capacitor-community/tapsellplus';
import { App } from '@capacitor/app';

// Initialize in your app startup
App.addListener('appStateChange', async ({ isActive }) => {
  if (isActive) {
    await TapsellPlus.initialize({
      apiKey: 'YOUR_API_KEY'
    });
  }
});
```

## Rewarded Ad Example

```typescript
import { TapsellPlus } from '@capacitor-community/tapsellplus';

async function showRewardedAd() {
  try {
    // Request ad
    const response = await TapsellPlus.requestRewardedAd({
      zoneId: 'YOUR_REWARDED_ZONE_ID'
    });

    // Set up listeners
    TapsellPlus.addListener('onRewardedAdRewarded', (data) => {
      console.log('User rewarded!', data);
      // Give reward to user
    });

    TapsellPlus.addListener('onRewardedAdClosed', () => {
      console.log('Ad closed');
    });

    // Show ad
    await TapsellPlus.showRewardedAd({
      responseId: response.responseId
    });
  } catch (error) {
    console.error('Error showing rewarded ad:', error);
  }
}
```

## Banner Ad Example

```typescript
import { TapsellPlus, BannerType } from '@capacitor-community/tapsellplus';

async function showBannerAd() {
  try {
    // Request banner ad
    const response = await TapsellPlus.requestStandardAd({
      zoneId: 'YOUR_BANNER_ZONE_ID',
      bannerType: BannerType.SMART_BANNER
    });

    // Show in container (containerId should match an element in your layout)
    await TapsellPlus.showStandardAd({
      responseId: response.responseId,
      containerId: 'banner_container',
      bannerType: BannerType.SMART_BANNER
    });

    // Hide when done
    // await TapsellPlus.hideStandardAd({ responseId: response.responseId });
  } catch (error) {
    console.error('Error showing banner ad:', error);
  }
}
```

## Native Ad Example

```typescript
import { TapsellPlus } from '@capacitor-community/tapsellplus';

async function showNativeAd() {
  try {
    // Request native ad
    const response = await TapsellPlus.requestNativeAd({
      zoneId: 'YOUR_NATIVE_ZONE_ID'
    });

    // Show in container
    await TapsellPlus.showNativeAd({
      responseId: response.responseId,
      containerId: 'native_ad_container'
    });
  } catch (error) {
    console.error('Error showing native ad:', error);
  }
}
```

## React Example

```tsx
import React, { useEffect } from 'react';
import { TapsellPlus } from '@capacitor-community/tapsellplus';

function AdComponent() {
  useEffect(() => {
    // Initialize
    TapsellPlus.initialize({
      apiKey: 'YOUR_API_KEY'
    });

    // Set up listeners
    const rewardedListener = TapsellPlus.addListener('onRewardedAdRewarded', (data) => {
      console.log('Rewarded!', data);
    });

    return () => {
      rewardedListener.remove();
    };
  }, []);

  const handleShowRewarded = async () => {
    try {
      const response = await TapsellPlus.requestRewardedAd({
        zoneId: 'YOUR_ZONE_ID'
      });
      await TapsellPlus.showRewardedAd({
        responseId: response.responseId
      });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <button onClick={handleShowRewarded}>
      Show Rewarded Ad
    </button>
  );
}
```

## Vue Example

```vue
<template>
  <div>
    <button @click="showRewardedAd">Show Rewarded Ad</button>
    <div id="banner_container"></div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted } from 'vue';
import { TapsellPlus } from '@capacitor-community/tapsellplus';

onMounted(async () => {
  await TapsellPlus.initialize({
    apiKey: 'YOUR_API_KEY'
  });

  const listener = TapsellPlus.addListener('onRewardedAdRewarded', (data) => {
    console.log('Rewarded!', data);
  });
});

const showRewardedAd = async () => {
  try {
    const response = await TapsellPlus.requestRewardedAd({
      zoneId: 'YOUR_ZONE_ID'
    });
    await TapsellPlus.showRewardedAd({
      responseId: response.responseId
    });
  } catch (error) {
    console.error(error);
  }
};
</script>
```

