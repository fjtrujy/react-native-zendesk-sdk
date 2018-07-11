package com.fjtrujy.rnzendesksdk

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import zendesk.core.AnonymousIdentity
import zendesk.core.JwtIdentity
import zendesk.core.Zendesk
import zendesk.support.Support
import zendesk.support.request.RequestActivity

class RNZendeskSDK(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    companion object {
        private const val MODULE_NAME = "RNZendeskSDK"

        private const val RNZENDESK_APP_ID_KEY = "appID"
        private const val RNZENDESK_CLIENT_ID_KEY = "clientID"
        private const val RNZENDESK_ZENDESK_URL_KEY = "zendeskURL"

        private const val RNZENDESK_CUSTOMER_NAME_KEY = "customerName"
        private const val RNZENDESK_CUSTOMER_EMAIL_KEY = "customerEmail"
        private const val RNZENDESK_CUSTOMER_UNIQUE_ID_KEY = "customerUniqueID"
    }

    override fun getName() = MODULE_NAME

    @ReactMethod
    fun initialize(config: ReadableMap) {
        val appId = config.getString(RNZENDESK_APP_ID_KEY)
        val clientId = config.getString(RNZENDESK_CLIENT_ID_KEY)
        val zendeskUrl = config.getString(RNZENDESK_ZENDESK_URL_KEY)
        Zendesk.INSTANCE.init(this.reactContext, zendeskUrl, appId, clientId)

        val identity = AnonymousIdentity()
        Zendesk.INSTANCE.setIdentity(identity)

        Support.INSTANCE.init(Zendesk.INSTANCE)
    }

    @ReactMethod
    fun setIdentity(identityConfig: ReadableMap) {
        val name = identityConfig.getString(RNZENDESK_CUSTOMER_NAME_KEY)
        val email = identityConfig.getString(RNZENDESK_CUSTOMER_EMAIL_KEY)

        val identity = AnonymousIdentity.Builder()
                .withNameIdentifier(name)
                .withEmailIdentifier(email)
                .build()
    }

    @ReactMethod
    fun setUniqueIdentity(identityConfig: ReadableMap) {
        val token = identityConfig.getString(RNZENDESK_CUSTOMER_UNIQUE_ID_KEY)

        val identity = JwtIdentity(token)
        Zendesk.INSTANCE.setIdentity(identity)
    }

    @ReactMethod
    fun presentHelpCenterOverview() {
        RequestActivity.builder()
                .show(this.reactContext)
    }

    // @ReactMethod
    // public void presentHelpCenterOverviewWithConfiguration(ReadableMap configurations) {
    //   SupportActivityBuilder.create()
    //     .withOptions(options)
    //     .show(this.reactContext);
    // }

    // @ReactMethod
    // public void showCategoriesWithOptions(ReadableArray categoryIds, ReadableMap options) {
    //   SupportActivityBuilder.create()
    //     .withOptions(options)
    //     .withArticlesForCategoryIds(categoryIds)
    //     .show(this.reactContext);
    // }

    // @ReactMethod
    // public void showSectionsWithOptions(ReadableArray sectionIds, ReadableMap options) {
    //   SupportActivityBuilder.create()
    //     .withOptions(options)
    //     .withArticlesForSectionIds(sectionIds)
    //     .show(this.reactContext);
    // }

    // @ReactMethod
    // public void showLabelsWithOptions(ReadableArray labels, ReadableMap options) {
    //   SupportActivityBuilder.create()
    //     .withOptions(options)
    //     .withLabelNames(labels)
    //     .show(this.reactContext);
    // }

    // @ReactMethod
    // public void showHelpCenter() {
    //   showHelpCenterWithOptions(null);
    // }

    // @ReactMethod
    // public void showCategories(ReadableArray categoryIds) {
    //   showCategoriesWithOptions(categoryIds, null);
    // }

    // @ReactMethod
    // public void showSections(ReadableArray sectionIds) {
    //   showSectionsWithOptions(sectionIds, null);
    // }

    // @ReactMethod
    // public void showLabels(ReadableArray labels) {
    //   showLabelsWithOptions(labels, null);
    // }

    // @ReactMethod
    // public void callSupport(ReadableMap customFields) {

    //   List<CustomField> fields = new ArrayList<>();

    //   for (Map.Entry<String, Object> next : customFields.toHashMap().entrySet())
    //     fields.add(new CustomField(Long.parseLong(next.getKey()), (String) next.getValue()));

    //   ZendeskConfig.INSTANCE.setCustomFields(fields);

    //   Activity activity = getCurrentActivity();

    //   if(activity != null){
    //       Intent callSupportIntent = new Intent(this.reactContext, ContactZendeskActivity.class);
    //       callSupportIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //       this.reactContext.startActivity(callSupportIntent);
    //   }
    // }

    // @ReactMethod
    // public void supportHistory() {

    //   Activity activity = getCurrentActivity();

    //   if(activity != null){
    //       Intent supportHistoryIntent = new Intent(this.reactContext, RequestActivity.class);
    //       supportHistoryIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //       this.reactContext.startActivity(supportHistoryIntent);
    //   }
    // }

}