# capacitor-screen-recorder
  <a href="https://capgo.app/"><img src='https://raw.githubusercontent.com/Cap-go/capgo/main/assets/capgo_banner.png' alt='Capgo - Instant updates for capacitor'/></a>
  
<div align="center">
<h2><a href="https://capgo.app/">Check out: Capgo — Instant updates for capacitor</a></h2>
</div>

Record device's screen

## Updated by DFKI

This Fork adjusts the resolution for android to work on Android 12, and it adds the PluginMethod getVideo(PluginCall call), that returns an JSObject that includes a parameter (video), which has the value of the base64 encoding of the byte array of the recording's file.

## Install

```bash
npm install @capgo/capacitor-screen-recorder
npx cap sync
```

## IOS

Nothing special

## Android

increase project's minSdk version to at least 23.

## API

<docgen-index>

* [`start()`](#start)
* [`stop()`](#stop)
* [`getVideo()`](#getVideo)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### start()

```typescript
start() => Promise<void>
```

start the recording

--------------------


### stop()

```typescript
stop() => Promise<void>
```

stop the recording

--------------------

### getVideo()

```typescript
getVideo() => Promise<String>
```

returns a promise with a parameter (video), which has the value of the base64 encoding of the byte array of the recording's file. 

--------------------

</docgen-api>
