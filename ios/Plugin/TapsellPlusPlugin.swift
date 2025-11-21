import Foundation
import Capacitor

/**
 * TapsellPlus is currently only available for Android.
 * This is a placeholder implementation for iOS.
 */
@objc(TapsellPlusPlugin)
public class TapsellPlusPlugin: CAPPlugin {
    
    @objc func initialize(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func requestRewardedAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func showRewardedAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func requestNativeAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func showNativeAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func requestNativeVideoAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func showNativeVideoAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func requestStandardAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func showStandardAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func hideStandardAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func requestVastAd(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func showGDPRDialog(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
    
    @objc func setUserConsent(_ call: CAPPluginCall) {
        call.reject("TapsellPlus is not supported on iOS")
    }
}

