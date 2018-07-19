package com.fjtrujy.rnzendesksdk

import com.facebook.react.bridge.*
import zendesk.core.AnonymousIdentity
import zendesk.core.JwtIdentity
import zendesk.core.Zendesk
import zendesk.support.CustomField
import zendesk.support.Support
import zendesk.support.guide.HelpCenterActivity
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
    fun presentHelpCenterOverview() = HelpCenterActivity.builder().show(this.reactContext)

    @ReactMethod
    fun presentHelpCenterOverviewWithConfiguration(configuration: ReadableMap) =
            HelpCenterActivity.builder().show(this.reactContext, configuration.toUiConfig())

    @ReactMethod
    fun showCategories(categoryIds: ReadableArray, options: ReadableMap? = null) =
    // TODO not sure how the show method handle null values, we might need to refactor this
            HelpCenterActivity.builder()
                    .withArticlesForCategoryIds(categoryIds.toLongMutableList())
                    .show(this.reactContext, options?.toUiConfig())

    @ReactMethod
    fun showSections(sectionsIds: ReadableArray, options: ReadableMap? = null) =
            HelpCenterActivity.builder()
                    .withArticlesForSectionIds(sectionsIds.toLongMutableList())
                    .show(this.reactContext, options?.toUiConfig())

    @ReactMethod
    fun showLabelsWithOptions(labels: ReadableArray, options: ReadableMap) =
            HelpCenterActivity.builder()
                    .withLabelNames(labels.toStringMutableList())
                    .show(this.reactContext, options.toUiConfig())

    @ReactMethod
    fun callSupport(customField: ReadableMap) {
        val fields = mutableListOf<CustomField>()

        while (customField.keySetIterator().hasNextKey()) {
            val key = customField.keySetIterator().nextKey()
            fields.add(CustomField(key.toLong(), customField.getString(key)))
        }

        RequestActivity.builder()
                .withCustomFields(fields)
                .show(this.reactContext)
    }

    @ReactMethod
    fun supportHistory() = RequestActivity.builder().show(this.reactContext)

    private fun ReadableMap.toUiConfig() = RequestActivity.builder()
            // TODO
            .config()

    private fun ReadableArray.toLongMutableList(): MutableList<Long> {
        val categories = mutableListOf<Long>()
        for (i in 0..size()) {
            categories.add(getDouble(i).toLong())
        }
        return categories
    }

    private fun ReadableArray.toStringMutableList(): MutableList<String> {
        val categories = mutableListOf<String>()
        for (i in 0..size()) {
            categories.add(getString(i))
        }
        return categories
    }

}
