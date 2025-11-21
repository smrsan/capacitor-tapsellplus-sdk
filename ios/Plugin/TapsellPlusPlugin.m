#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(TapsellPlusPlugin, "TapsellPlus",
           CAP_PLUGIN_METHOD(initialize, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(requestRewardedAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(showRewardedAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(requestNativeAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(showNativeAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(requestNativeVideoAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(showNativeVideoAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(requestStandardAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(showStandardAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(hideStandardAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(requestVastAd, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(showGDPRDialog, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setUserConsent, CAPPluginReturnPromise);
)

