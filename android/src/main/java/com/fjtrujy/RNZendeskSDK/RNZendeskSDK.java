/**
 * Created by Francisco Javier Trujillo Mata on 5/16/18.
 * https://github.com/fjtrujy/react-native-zendesk-sdk
 */

package com.fjtrujy.RNZendeskSDK;

import android.content.Intent;
import android.app.Activity;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import zendesk.core.AnonymousIdentity;
import zendesk.core.Identity;
import zendesk.core.JwtIdentity;
import zendesk.core.Zendesk;
import zendesk.support.Support;
import zendesk.support.request.RequestActivity;

public class RNZendeskSDK extends ReactContextBaseJavaModule {
  private final static String MODULE_NAME = "RNZendeskSDK";

  private final static String RNZENDESK_APP_ID_KEY = "appID";
  private final static String RNZENDESK_CLIENT_ID_KEY = "clientID";
  private final static String RNZENDESK_ZENDESK_URL_KEY = "zendeskURL";

  private final static String RNZENDESK_CUSTOMER_NAME_KEY = "customerName";
  private final static String RNZENDESK_CUSTOMER_EMAIL_KEY = "customerEmail";
  private final static String RNZENDESK_CUSTOMER_UNIQUE_ID_KEY = "customerUniqueID";

  private final ReactApplicationContext reactContext;

  public RNZendeskSDK(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return MODULE_NAME;
  }

  @ReactMethod
  public void initialize(ReadableMap config) {
    String appId = config.getString(RNZENDESK_APP_ID_KEY);
    String clientId = config.getString(RNZENDESK_CLIENT_ID_KEY);
    String zendeskUrl = config.getString(RNZENDESK_ZENDESK_URL_KEY);
    Zendesk.INSTANCE.init(this.reactContext, zendeskUrl, appId, clientId);

    Identity identity = new AnonymousIdentity();
    Zendesk.INSTANCE.setIdentity(identity);

    Support.INSTANCE.init(Zendesk.INSTANCE);
  }

  @ReactMethod
  public void setIdentity(ReadableMap identityConfig) {
    String name = identityConfig.getString(RNZENDESK_CUSTOMER_NAME_KEY);
    String email = identityConfig.getString(RNZENDESK_CUSTOMER_EMAIL_KEY);

    Identity identity = new AnonymousIdentity.Builder()
      .withNameIdentifier(name)
      .withEmailIdentifier(email)
      .build();
  }

  @ReactMethod
  public void setUniqueIdentity(ReadableMap identityConfig) {
    String token = identityConfig.getString(RNZENDESK_CUSTOMER_UNIQUE_ID_KEY);

    Identity identity = new JwtIdentity(token);
    Zendesk.INSTANCE.setIdentity(identity);
  }

  @ReactMethod
  public void presentHelpCenterOverview() {
    RequestActivity.builder()
      .show(this.reactContext);
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
