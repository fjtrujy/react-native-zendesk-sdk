//
//  RNZendeskSDK.m
//  RNZendeskSDK
//
//  Created by Francisco Javier Trujillo Mata on 08/03/2018.
//  Copyright © 2018 fjtrujy. All rights reserved.
//

#import "RNZendeskSDK.h"
//#import "FLEXManager.h"

@implementation RNZendeskSDK

// To export a module named RNZendeskSDK
RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(showExplorer)
{
    dispatch_async(dispatch_get_main_queue(), ^{
//        [[FLEXManager sharedManager] showExplorer];
    });
}

//RCT_EXPORT_METHOD(initialize:(NSDictionary *)config){
//    NSString *appId = [RCTConvert NSString:config[@"appId"]];
//    NSString *zendeskUrl = [RCTConvert NSString:config[@"zendeskUrl"]];
//    NSString *clientId = [RCTConvert NSString:config[@"clientId"]];
//    [[ZDKConfig instance]
//     initializeWithAppId:appId
//     zendeskUrl:zendeskUrl
//     clientId:clientId];
//}
//
//RCT_EXPORT_METHOD(setupIdentity:(NSDictionary *)identity){
//    dispatch_async(dispatch_get_main_queue(), ^{
//        ZDKAnonymousIdentity *zdIdentity = [ZDKAnonymousIdentity new];
//        NSString *email = [RCTConvert NSString:identity[@"customerEmail"]];
//        NSString *name = [RCTConvert NSString:identity[@"customerName"]];
//        if (email != nil) {
//            zdIdentity.email = email;
//        }
//        if (name != nil) {
//            zdIdentity.name = name;
//        }
//        [ZDKConfig instance].userIdentity = zdIdentity;
//        
//    });
//}
//
//RCT_EXPORT_METHOD(showHelpCenterWithOptions:(NSDictionary *)options) {
//    dispatch_async(dispatch_get_main_queue(), ^{
//        UIWindow *window=[UIApplication sharedApplication].keyWindow;
//        UIViewController *vc = [window rootViewController];
//        ZDKHelpCenterOverviewContentModel *helpCenterContentModel = [ZDKHelpCenterOverviewContentModel defaultContent];
//        helpCenterContentModel.hideContactSupport = [RCTConvert BOOL:options[@"hideContactSupport"]];
//        if (helpCenterContentModel.hideContactSupport) {
//            [ZDKHelpCenter setNavBarConversationsUIType:ZDKNavBarConversationsUITypeNone];
//        }
//        vc.modalPresentationStyle = UIModalPresentationFormSheet;
//        [ZDKHelpCenter presentHelpCenterOverview:vc withContentModel:helpCenterContentModel];
//    });
//}
//
//RCT_EXPORT_METHOD(showCategoriesWithOptions:(NSArray *)categories options:(NSDictionary *)options) {
//    dispatch_async(dispatch_get_main_queue(), ^{
//        UIWindow *window=[UIApplication sharedApplication].keyWindow;
//        UIViewController *vc = [window rootViewController];
//        ZDKHelpCenterOverviewContentModel *helpCenterContentModel = [ZDKHelpCenterOverviewContentModel defaultContent];
//        helpCenterContentModel.groupType = ZDKHelpCenterOverviewGroupTypeCategory;
//        helpCenterContentModel.groupIds = categories;
//        helpCenterContentModel.hideContactSupport = [RCTConvert BOOL:options[@"hideContactSupport"]];
//        if (helpCenterContentModel.hideContactSupport) {
//            [ZDKHelpCenter setNavBarConversationsUIType:ZDKNavBarConversationsUITypeNone];
//        }
//        vc.modalPresentationStyle = UIModalPresentationFormSheet;
//        [ZDKHelpCenter presentHelpCenterOverview:vc withContentModel:helpCenterContentModel];
//    });
//}
//
//RCT_EXPORT_METHOD(showSectionsWithOptions:(NSArray *)sections options:(NSDictionary *)options) {
//    dispatch_async(dispatch_get_main_queue(), ^{
//        UIWindow *window=[UIApplication sharedApplication].keyWindow;
//        UIViewController *vc = [window rootViewController];
//        ZDKHelpCenterOverviewContentModel *helpCenterContentModel = [ZDKHelpCenterOverviewContentModel defaultContent];
//        helpCenterContentModel.groupType = ZDKHelpCenterOverviewGroupTypeSection;
//        helpCenterContentModel.groupIds = sections;
//        helpCenterContentModel.hideContactSupport = [RCTConvert BOOL:options[@"hideContactSupport"]];
//        if (helpCenterContentModel.hideContactSupport) {
//            [ZDKHelpCenter setNavBarConversationsUIType:ZDKNavBarConversationsUITypeNone];
//        }
//        vc.modalPresentationStyle = UIModalPresentationFormSheet;
//        [ZDKHelpCenter presentHelpCenterOverview:vc withContentModel:helpCenterContentModel];
//    });
//}
//
//RCT_EXPORT_METHOD(showLabelsWithOptions:(NSArray *)labels options:(NSDictionary *)options) {
//    dispatch_async(dispatch_get_main_queue(), ^{
//        UIWindow *window=[UIApplication sharedApplication].keyWindow;
//        UIViewController *vc = [window rootViewController];
//        ZDKHelpCenterOverviewContentModel *helpCenterContentModel = [ZDKHelpCenterOverviewContentModel defaultContent];
//        helpCenterContentModel.labels = labels;
//        helpCenterContentModel.hideContactSupport = [RCTConvert BOOL:options[@"hideContactSupport"]];
//        if (helpCenterContentModel.hideContactSupport) {
//            [ZDKHelpCenter setNavBarConversationsUIType:ZDKNavBarConversationsUITypeNone];
//        }
//        vc.modalPresentationStyle = UIModalPresentationFormSheet;
//        [ZDKHelpCenter presentHelpCenterOverview:vc withContentModel:helpCenterContentModel];
//    });
//}
//
//RCT_EXPORT_METHOD(showHelpCenter) {
//    [self showHelpCenterWithOptions:nil];
//}
//
//RCT_EXPORT_METHOD(showCategories:(NSArray *)categories) {
//    [self showCategoriesWithOptions:categories options:nil];
//}
//
//RCT_EXPORT_METHOD(showSections:(NSArray *)sections) {
//    [self showSectionsWithOptions:sections options:nil];
//}
//
//RCT_EXPORT_METHOD(showLabels:(NSArray *)labels) {
//    [self showLabelsWithOptions:labels options:nil];
//}
//
//RCT_EXPORT_METHOD(callSupport:(NSDictionary *)customFields) {
//    dispatch_async(dispatch_get_main_queue(), ^{
//        UIWindow *window=[UIApplication sharedApplication].keyWindow;
//        UIViewController *vc = [window rootViewController];
//        
//        NSMutableArray *fields = [[NSMutableArray alloc] init];
//        
//        for (NSString* key in customFields) {
//            id value = [customFields objectForKey:key];
//            [fields addObject: [[ZDKCustomField alloc] initWithFieldId:@(key.intValue) andValue:value]];
//        }
//        [ZDKConfig instance].customTicketFields = fields;
//        [ZDKRequests presentRequestCreationWithViewController:vc];
//    });
//}
//
//RCT_EXPORT_METHOD(supportHistory){
//    dispatch_async(dispatch_get_main_queue(), ^{
//        UIWindow *window=[UIApplication sharedApplication].keyWindow;
//        UIViewController *vc = [window rootViewController];
//        [ZDKRequests presentRequestListWithViewController:vc];
//    });
//}

@end
