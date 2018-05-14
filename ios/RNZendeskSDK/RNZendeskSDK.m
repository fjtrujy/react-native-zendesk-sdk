//
//  RNZendeskSDK.m
//  RNZendeskSDK
//
//  Created by Francisco Javier Trujillo Mata on 08/03/2018.
//  Copyright © 2018 fjtrujy. All rights reserved.
//

#import "RNZendeskSDK.h"
#import <React/RCTConvert.h>

@import ZendeskSDK;
@import ZendeskCoreSDK;

@implementation RNZendeskSDK

static NSString * const RNZendeskAppIDKey = @"appID";
static NSString * const RNZendeskZendeskClientIDKey = @"clientID";
static NSString * const RNZendeskZendeskURLKey = @"zendeskURL";

static NSString * const RNZendeskZendeskCustomerNameKey = @"customerName";
static NSString * const RNZendeskZendeskCustomerEmailKey = @"customerEmail";
static NSString * const RNZendeskZendeskCustomerUniqueIDKey = @"customerUniqueID";

// To export a module named RNZendeskSDK
RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(initialize:(NSDictionary *)config) {
    NSString *appID = [RCTConvert NSString:config[RNZendeskAppIDKey]];
    NSString *clientID = [RCTConvert NSString:config[RNZendeskZendeskClientIDKey]];
    NSString *zendeskURL = [RCTConvert NSString:config[RNZendeskZendeskURLKey]];
    
    [ZDKZendesk initializeWithAppId:appID clientId:clientID zendeskUrl:zendeskURL];
    [ZDKSupport initializeWithZendesk:[ZDKZendesk instance]];
}


RCT_EXPORT_METHOD(setIdentity:(NSDictionary *)identity) {
    NSString *name = [RCTConvert NSString:identity[RNZendeskZendeskCustomerNameKey]];
    NSString *email = [RCTConvert NSString:identity[RNZendeskZendeskCustomerEmailKey]];
    
    ZDKObjCAnonymous *userIdentity = [[ZDKObjCAnonymous alloc] initWithName:name email:email];
    [[ZDKZendesk instance] setIdentity:userIdentity];
}

RCT_EXPORT_METHOD(setUniqueIdentity:(NSDictionary *)identity) {
    NSString *token = [RCTConvert NSString:identity[RNZendeskZendeskCustomerUniqueIDKey]];
    
    ZDKObjCJwt *uniqueUserIdentity = [[ZDKObjCJwt alloc] initWithToken:token];
    [[ZDKZendesk instance] setIdentity:uniqueUserIdentity];
}

RCT_EXPORT_METHOD(presentHelpCenterOverview) {
    [self presentHelpCenterOverviewWithConfiguration:nil];
}

RCT_EXPORT_METHOD(presentHelpCenterOverviewWithConfiguration:(NSArray<ZDKUiConfiguration> *)configurations) {
    UIViewController *helpCenter = [ZDKHelpCenterUi buildHelpCenterOverviewWithConfigs:configurations];
    [self presentZendeskViewController:helpCenter];
}

RCT_EXPORT_METHOD(presentbuildHelpCenterWithArticle:(ZDKHelpCenterArticle *)article) {
    [self presentbuildHelpCenterWithArticle:article configs:nil];
}

RCT_EXPORT_METHOD(presentbuildHelpCenterWithArticle:(ZDKHelpCenterArticle *)article configs:(NSArray<ZDKUiConfiguration> *)configurations) {
    UIViewController *helpCenter = [ZDKHelpCenterUi buildHelpCenterArticle:article andConfigs:configurations];
    [self presentZendeskViewController:helpCenter];
}

RCT_EXPORT_METHOD(presentbuildHelpCenterWithArticleId:(NSString *)articleId) {
    [self presentbuildHelpCenterWithArticleId:articleId configs:nil];
}

RCT_EXPORT_METHOD(presentbuildHelpCenterWithArticleId:(NSString *)articleId configs:(NSArray<ZDKUiConfiguration> *)configurations) {
    UIViewController *helpCenter = [ZDKHelpCenterUi buildHelpCenterArticleWithArticleId:articleId andConfigs:configurations];
    [self presentZendeskViewController:helpCenter];
}

RCT_EXPORT_METHOD(presentbuildRequestList) {
    [self presentbuildRequestListWithConfiguration:nil];
}

RCT_EXPORT_METHOD(presentbuildRequestListWithConfiguration:(NSArray<ZDKUiConfiguration> *)configurations) {
    UIViewController *requestList = [ZDKRequestUi buildRequestListWith:configurations];
    [self presentZendeskViewController:requestList];
}

RCT_EXPORT_METHOD(presentbuildRequestUI) {
    [self presentbuildRequestUIWithConfiguration:nil];
}

RCT_EXPORT_METHOD(presentbuildRequestUIWithConfiguration:(NSArray<ZDKUiConfiguration> *)configurations) {
    UIViewController *requestUI = [ZDKRequestUi buildRequestUiWith:configurations];
    [self presentZendeskViewController:requestUI];
}

RCT_EXPORT_METHOD(presentbuildRequestUIWithRequestId:(NSString *)requestId) {
    [self presentbuildRequestUIWithRequestId:requestId configs:nil];
}

RCT_EXPORT_METHOD(presentbuildRequestUIWithRequestId:(NSString *)requestId configs:(NSArray<ZDKUiConfiguration> *)configurations) {
    UIViewController *requestUI = [ZDKRequestUi buildRequestUiWithRequestId:requestId configurations:configurations];
    [self presentZendeskViewController:requestUI];
}

#pragma mark - Helper Methods

- (UIViewController *)rootViewController {
    UIWindow *window=[UIApplication sharedApplication].keyWindow;
    return [window rootViewController];
}

- (void)presentZendeskViewController:(UIViewController *)viewController {
    dispatch_async(dispatch_get_main_queue(), ^{
        UIViewController *rootVC = [self rootViewController];
        UINavigationController *navController = [[UINavigationController alloc] initWithRootViewController:viewController];
        [rootVC presentViewController:navController animated:YES completion:nil];
    });
}

@end
