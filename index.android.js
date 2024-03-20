import {
  NavigationContainer,
  useNavigation,
  useNavigationContainerRef,
} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import React from 'react';
import {
  AppRegistry,
  BackHandler,
  Button,
  StyleSheet,
  Text,
  View,
} from 'react-native';

import {NativeModules} from 'react-native';

const {DeepLinkHandler} = NativeModules;

const Stack = createNativeStackNavigator();

function HomeScreen(props) {
  const navigation = useNavigation();
  return (
    <View>
      <Text>ปีนี้ปี {props.route.params.testInt}</Text>
      <Button
        title={'ข้ามปี'}
        onPress={() => {
          navigation.navigate('WithdrawOverYear');
        }}
      />
      <Button
        title={'ยินยันตัวตน'}
        onPress={() => {
          navigation.navigate('Verification');
        }}
      />
    </View>
  );
}

function WithdrawOverYearScreen() {
  return (
    <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
      <Text>ถอนข้ามปี</Text>
    </View>
  );
}

function VerificationScreen() {
  return (
    <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
      <Button
        title="สมมติว่ายืนยันตัวตนเสร็จแล้ว"
        onPress={() => {
          DeepLinkHandler.openDeepLink('Hello');
        }}
      />
    </View>
  );
}

const WithdrawStack = props => {
  console.log(props);
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen
          name="Home"
          component={HomeScreen}
          initialParams={{
            ...props,
          }}
          options={() => ({
            headerLeft: () => (
              <Button
                title="Back"
                onPress={() => {
                  BackHandler.exitApp();
                }}
              />
            ),
            headerRight: () => (
              <Text>
                {props.testString} {props.testInt}
              </Text>
            ),
          })}
        />
        <Stack.Screen
          name="WithdrawOverYear"
          component={WithdrawOverYearScreen}
        />
        <Stack.Screen name="Verification" component={VerificationScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

AppRegistry.registerComponent('WithdrawStack', () => WithdrawStack);
