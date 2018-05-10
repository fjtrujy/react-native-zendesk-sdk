
import { NativeModules } from 'react-native';

let { RNZendeskSDK } = NativeModules;

if (!RNZendeskSDK) {
    RNZendeskSDK = require('./default');
}

export default RNZendeskSDK;
    