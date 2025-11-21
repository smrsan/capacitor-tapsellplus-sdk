package com.capacitorcommunity.plugins.tapsellplus;

import android.app.Activity;
import android.util.Log;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusAdModel;
import ir.tapsell.plus.TapsellPlusBannerType;
import ir.tapsell.plus.TapsellPlusNativeBannerType;
import ir.tapsell.plus.TapsellPlusShowOptions;
import ir.tapsell.plus.TapsellPlusVastTag;
import ir.tapsell.plus.TapsellPlusAdRequestListener;
import ir.tapsell.plus.TapsellPlusAdShowListener;
import ir.tapsell.plus.TapsellPlusNativeAdRequestListener;
import ir.tapsell.plus.TapsellPlusNativeAdShowListener;
import ir.tapsell.plus.TapsellPlusStandardBannerListener;
import ir.tapsell.plus.TapsellPlusVastRequestListener;
import java.util.HashMap;
import java.util.Map;

@CapacitorPlugin(name = "TapsellPlus")
public class TapsellPlusPlugin extends Plugin {

    private static final String TAG = "TapsellPlusPlugin";
    private Map<String, TapsellPlusAdModel> adCache = new HashMap<>();

    @PluginMethod
    public void initialize(PluginCall call) {
        String apiKey = call.getString("apiKey");
        if (apiKey == null || apiKey.isEmpty()) {
            call.reject("API key is required");
            return;
        }

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            TapsellPlus.initialize(activity, apiKey);
            call.resolve();
        } catch (Exception e) {
            Log.e(TAG, "Error initializing TapsellPlus", e);
            call.reject("Failed to initialize TapsellPlus: " + e.getMessage());
        }
    }

    @PluginMethod
    public void requestRewardedAd(PluginCall call) {
        String zoneId = call.getString("zoneId");
        if (zoneId == null || zoneId.isEmpty()) {
            call.reject("Zone ID is required");
            return;
        }

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            TapsellPlus.requestRewardedAd(activity, zoneId, new TapsellPlusAdRequestListener() {
                @Override
                public void onResponse(TapsellPlusAdModel ad) {
                    String responseId = ad.getResponseId();
                    adCache.put(responseId, ad);

                    JSObject result = new JSObject();
                    result.put("responseId", responseId);
                    result.put("zoneId", zoneId);
                    result.put("isReady", true);
                    call.resolve(result);
                }

                @Override
                public void onError(String message) {
                    call.reject("Failed to request rewarded ad: " + message);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error requesting rewarded ad", e);
            call.reject("Failed to request rewarded ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void showRewardedAd(PluginCall call) {
        String responseId = call.getString("responseId");
        if (responseId == null || responseId.isEmpty()) {
            call.reject("Response ID is required");
            return;
        }

        TapsellPlusAdModel ad = adCache.get(responseId);
        if (ad == null) {
            call.reject("Ad not found. Please request the ad first.");
            return;
        }

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            TapsellPlus.showRewardedAd(activity, ad, new TapsellPlusAdShowListener() {
                @Override
                public void onOpened() {
                    notifyListeners("onRewardedAdOpened", new JSObject());
                }

                @Override
                public void onClosed() {
                    notifyListeners("onRewardedAdClosed", new JSObject());
                    adCache.remove(responseId);
                }

                @Override
                public void onRewarded() {
                    JSObject data = new JSObject();
                    data.put("responseId", responseId);
                    notifyListeners("onRewardedAdRewarded", data);
                }

                @Override
                public void onError(String message) {
                    JSObject data = new JSObject();
                    data.put("message", message);
                    notifyListeners("onRewardedAdError", data);
                    call.reject("Failed to show rewarded ad: " + message);
                }
            });

            call.resolve();
        } catch (Exception e) {
            Log.e(TAG, "Error showing rewarded ad", e);
            call.reject("Failed to show rewarded ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void requestNativeAd(PluginCall call) {
        String zoneId = call.getString("zoneId");
        if (zoneId == null || zoneId.isEmpty()) {
            call.reject("Zone ID is required");
            return;
        }

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            TapsellPlus.requestNativeAd(activity, zoneId, new TapsellPlusNativeAdRequestListener() {
                @Override
                public void onResponse(TapsellPlusAdModel ad) {
                    String responseId = ad.getResponseId();
                    adCache.put(responseId, ad);

                    JSObject result = new JSObject();
                    result.put("responseId", responseId);
                    result.put("zoneId", zoneId);
                    result.put("isReady", true);
                    call.resolve(result);
                }

                @Override
                public void onError(String message) {
                    call.reject("Failed to request native ad: " + message);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error requesting native ad", e);
            call.reject("Failed to request native ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void showNativeAd(PluginCall call) {
        String responseId = call.getString("responseId");
        String containerId = call.getString("containerId");
        if (responseId == null || responseId.isEmpty()) {
            call.reject("Response ID is required");
            return;
        }
        if (containerId == null || containerId.isEmpty()) {
            call.reject("Container ID is required");
            return;
        }

        TapsellPlusAdModel ad = adCache.get(responseId);
        if (ad == null) {
            call.reject("Ad not found. Please request the ad first.");
            return;
        }

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            // Find the container view by ID
            android.view.View containerView = activity.findViewById(
                getResources().getIdentifier(containerId, "id", getContext().getPackageName())
            );

            if (containerView == null) {
                call.reject("Container view not found with ID: " + containerId);
                return;
            }

            TapsellPlusShowOptions showOptions = new TapsellPlusShowOptions();
            showOptions.setNativeBannerType(TapsellPlusNativeBannerType.NATIVE_BANNER_TYPE_1);

            TapsellPlus.showNativeAd(activity, ad, containerView, showOptions, new TapsellPlusNativeAdShowListener() {
                @Override
                public void onOpened() {
                    notifyListeners("onNativeAdOpened", new JSObject());
                }

                @Override
                public void onClosed() {
                    notifyListeners("onNativeAdClosed", new JSObject());
                }

                @Override
                public void onError(String message) {
                    JSObject data = new JSObject();
                    data.put("message", message);
                    notifyListeners("onNativeAdError", data);
                    call.reject("Failed to show native ad: " + message);
                }
            });

            call.resolve();
        } catch (Exception e) {
            Log.e(TAG, "Error showing native ad", e);
            call.reject("Failed to show native ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void requestNativeVideoAd(PluginCall call) {
        String zoneId = call.getString("zoneId");
        if (zoneId == null || zoneId.isEmpty()) {
            call.reject("Zone ID is required");
            return;
        }

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            TapsellPlus.requestNativeVideoAd(activity, zoneId, new TapsellPlusNativeAdRequestListener() {
                @Override
                public void onResponse(TapsellPlusAdModel ad) {
                    String responseId = ad.getResponseId();
                    adCache.put(responseId, ad);

                    JSObject result = new JSObject();
                    result.put("responseId", responseId);
                    result.put("zoneId", zoneId);
                    result.put("isReady", true);
                    call.resolve(result);
                }

                @Override
                public void onError(String message) {
                    call.reject("Failed to request native video ad: " + message);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error requesting native video ad", e);
            call.reject("Failed to request native video ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void showNativeVideoAd(PluginCall call) {
        String responseId = call.getString("responseId");
        String containerId = call.getString("containerId");
        if (responseId == null || responseId.isEmpty()) {
            call.reject("Response ID is required");
            return;
        }
        if (containerId == null || containerId.isEmpty()) {
            call.reject("Container ID is required");
            return;
        }

        TapsellPlusAdModel ad = adCache.get(responseId);
        if (ad == null) {
            call.reject("Ad not found. Please request the ad first.");
            return;
        }

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            android.view.View containerView = activity.findViewById(
                getResources().getIdentifier(containerId, "id", getContext().getPackageName())
            );

            if (containerView == null) {
                call.reject("Container view not found with ID: " + containerId);
                return;
            }

            TapsellPlusShowOptions showOptions = new TapsellPlusShowOptions();

            TapsellPlus.showNativeVideoAd(activity, ad, containerView, showOptions, new TapsellPlusNativeAdShowListener() {
                @Override
                public void onOpened() {
                    notifyListeners("onNativeVideoAdOpened", new JSObject());
                }

                @Override
                public void onClosed() {
                    notifyListeners("onNativeVideoAdClosed", new JSObject());
                }

                @Override
                public void onError(String message) {
                    JSObject data = new JSObject();
                    data.put("message", message);
                    notifyListeners("onNativeVideoAdError", data);
                    call.reject("Failed to show native video ad: " + message);
                }
            });

            call.resolve();
        } catch (Exception e) {
            Log.e(TAG, "Error showing native video ad", e);
            call.reject("Failed to show native video ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void requestStandardAd(PluginCall call) {
        String zoneId = call.getString("zoneId");
        if (zoneId == null || zoneId.isEmpty()) {
            call.reject("Zone ID is required");
            return;
        }

        String bannerTypeStr = call.getString("bannerType", "SMART_BANNER");
        TapsellPlusBannerType bannerType = parseBannerType(bannerTypeStr);

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            TapsellPlus.requestStandardBanner(activity, zoneId, bannerType, new TapsellPlusAdRequestListener() {
                @Override
                public void onResponse(TapsellPlusAdModel ad) {
                    String responseId = ad.getResponseId();
                    adCache.put(responseId, ad);

                    JSObject result = new JSObject();
                    result.put("responseId", responseId);
                    result.put("zoneId", zoneId);
                    result.put("isReady", true);
                    call.resolve(result);
                }

                @Override
                public void onError(String message) {
                    call.reject("Failed to request standard ad: " + message);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error requesting standard ad", e);
            call.reject("Failed to request standard ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void showStandardAd(PluginCall call) {
        String responseId = call.getString("responseId");
        String containerId = call.getString("containerId");
        if (responseId == null || responseId.isEmpty()) {
            call.reject("Response ID is required");
            return;
        }
        if (containerId == null || containerId.isEmpty()) {
            call.reject("Container ID is required");
            return;
        }

        TapsellPlusAdModel ad = adCache.get(responseId);
        if (ad == null) {
            call.reject("Ad not found. Please request the ad first.");
            return;
        }

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            android.view.View containerView = activity.findViewById(
                getResources().getIdentifier(containerId, "id", getContext().getPackageName())
            );

            if (containerView == null) {
                call.reject("Container view not found with ID: " + containerId);
                return;
            }

            String bannerTypeStr = call.getString("bannerType", "SMART_BANNER");
            TapsellPlusBannerType bannerType = parseBannerType(bannerTypeStr);

            TapsellPlus.showStandardBanner(activity, ad, containerView, bannerType, new TapsellPlusStandardBannerListener() {
                @Override
                public void onRequestFilled() {
                    notifyListeners("onStandardAdRequestFilled", new JSObject());
                }

                @Override
                public void onNoAdAvailable() {
                    notifyListeners("onStandardAdNoAdAvailable", new JSObject());
                }

                @Override
                public void onNoNetwork() {
                    notifyListeners("onStandardAdNoNetwork", new JSObject());
                }

                @Override
                public void onError(String message) {
                    JSObject data = new JSObject();
                    data.put("message", message);
                    notifyListeners("onStandardAdError", data);
                }

                @Override
                public void onOpened() {
                    notifyListeners("onStandardAdOpened", new JSObject());
                }

                @Override
                public void onClosed() {
                    notifyListeners("onStandardAdClosed", new JSObject());
                }
            });

            call.resolve();
        } catch (Exception e) {
            Log.e(TAG, "Error showing standard ad", e);
            call.reject("Failed to show standard ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void hideStandardAd(PluginCall call) {
        String responseId = call.getString("responseId");
        if (responseId == null || responseId.isEmpty()) {
            call.reject("Response ID is required");
            return;
        }

        TapsellPlusAdModel ad = adCache.get(responseId);
        if (ad == null) {
            call.reject("Ad not found");
            return;
        }

        try {
            TapsellPlus.hideStandardBanner(ad);
            adCache.remove(responseId);
            call.resolve();
        } catch (Exception e) {
            Log.e(TAG, "Error hiding standard ad", e);
            call.reject("Failed to hide standard ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void requestVastAd(PluginCall call) {
        String zoneId = call.getString("zoneId");
        if (zoneId == null || zoneId.isEmpty()) {
            call.reject("Zone ID is required");
            return;
        }

        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            TapsellPlus.getVastTag(activity, zoneId, new TapsellPlusVastRequestListener() {
                @Override
                public void onResponse(TapsellPlusVastTag vastTag) {
                    JSObject result = new JSObject();
                    result.put("vastTag", vastTag.getVastTag());
                    result.put("zoneId", zoneId);
                    call.resolve(result);
                }

                @Override
                public void onError(String message) {
                    call.reject("Failed to request VAST ad: " + message);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error requesting VAST ad", e);
            call.reject("Failed to request VAST ad: " + e.getMessage());
        }
    }

    @PluginMethod
    public void showGDPRDialog(PluginCall call) {
        try {
            Activity activity = getActivity();
            if (activity == null) {
                call.reject("Activity is null");
                return;
            }

            TapsellPlus.showGDPRDialog(activity);
            call.resolve();
        } catch (Exception e) {
            Log.e(TAG, "Error showing GDPR dialog", e);
            call.reject("Failed to show GDPR dialog: " + e.getMessage());
        }
    }

    @PluginMethod
    public void setUserConsent(PluginCall call) {
        Boolean hasConsent = call.getBoolean("hasConsent", false);
        String consentType = call.getString("consentType", "GDPR");

        try {
            // TapsellPlus SDK handles consent internally
            // This is a placeholder for future implementation
            call.resolve();
        } catch (Exception e) {
            Log.e(TAG, "Error setting user consent", e);
            call.reject("Failed to set user consent: " + e.getMessage());
        }
    }

    private TapsellPlusBannerType parseBannerType(String bannerType) {
        if (bannerType == null) {
            return TapsellPlusBannerType.BANNER_320x50;
        }

        switch (bannerType) {
            case "BANNER_320x50":
                return TapsellPlusBannerType.BANNER_320x50;
            case "BANNER_320x100":
                return TapsellPlusBannerType.BANNER_320x100;
            case "BANNER_250x250":
                return TapsellPlusBannerType.BANNER_250x250;
            case "BANNER_300x250":
                return TapsellPlusBannerType.BANNER_300x250;
            case "BANNER_468x60":
                return TapsellPlusBannerType.BANNER_468x60;
            case "BANNER_728x90":
                return TapsellPlusBannerType.BANNER_728x90;
            case "SMART_BANNER":
            default:
                return TapsellPlusBannerType.SMART_BANNER;
        }
    }
}

