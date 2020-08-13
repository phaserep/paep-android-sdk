# PAEP Android SDK Guide (ver 1.3.1)

* PAEP SDK를 이용한 앱광고 송출 가이드입니다.

## PAPE SDK Release Note
| Version | Comment |
|:---:|:---:|
| `1.1.2` | Criteo 미디에이션 AddOn 추가 |
| `1.0.8` | AdMob 미디에이션 AddOn 추가 |
| `1.0.7` | AdPopCorn 미디에이션 추가 |
| `1.0.6` | AdFit 미디에이션 추가 |
| `1.0.5` | 안정화 |

## PAPE SDK 시작하기
* 최신버전의 PAEP Adx SDK 사용을 권장합니다.

### 1. 광고단위ID (Client ID) 발급받기
* 발급받은 paep계정을 통하여 로그인합니다.
* 영역 리스트 메뉴의 TAG열람을 통하여 광고단위ID를 확인할 수 있습니다. (PAD-xxxxxxxxxxxx)

### 2. 운영앱 프로젝트에 PAEP Adx SDK 추가하기
1. App 모듈 build.gradle 파일에 최신 버전의 PAEP Adx SDK 추가
```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar', '*.aar'])
    implementation 'kr.co.paep:paep-sdk:1.3.1'
}
```

2. 카카오 Adfit 사용 준비
```
//Project build.gradle 파일 저장소 추가

repositories {
    google()
    jcenter()
    maven { url 'http://devrepo.kakao.com:8088/nexus/content/groups/public/' }
}
```

3. 애드팝콘 사용 준비

[라이브러리 다운로드](https://github.com/phaserep/paep-android-sdk/blob/master/IgawSSP_v2.2.9a.aar?raw=true)

다운로드 받은 IgawSSP-.aar 파일을 프로젝트 /app/libs 경로에 복사하신 후 gradle 을 업데이트합니다.

```
//AndroidManifest.xml 파일 발급 받으신 키 추가
<meta-data android:name="igaworks_app_key" android:value="xxxxxxxxx" />
<meta-data android:name="igaworks_hash_key" android:value="xxxxxxxxxxxxxxxx" />
```

4. Proguard 설정
```
-keep class kr.co.paep.** { public *; }
-keep class com.kkao.adfit.** { *; }
-keep class com.igaworks.** { *; }
-dontwarn com.igaworks.**

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.igaworks.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.igaworks.adbrix.model.** { *; }
##---------------End: proguard configuration for Gson  ----------

```

5. Sync Project with Gradle Files를 클릭하여 변경사항을 적용합니다.


## 배너 광고 등록하기
### 레이아웃 변경
* 광고배너를 추가할 레이아웃 파일에 PaepAdView 뷰를 추가합니다.

```
<kr.co.paep.paepsdk.PaepAdView
        android:id="@+id/adView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="false"
        android:layout_centerHorizontal="false"
        android:layout_gravity="center_horizontal" />

```
        
* 광고의 크기는 자동으로 조절됩니다.
* 광고가 없을 시 0x0 크기가 됩니다.

### PaepAdView추가 및 광고 요청

```
private PaepAdView adView;

@Override
protected void onCreate(Bundle savedInstanceState) {

  adView = findViewById(R.id.adView);
  adView.setClientId("PAD-xxxxxxxxxxxx"); //광고단위ID
  adView.setAdHandler(new Handler() {
      public void handleMessage(Message message) {
          try {
              switch (message.what) {
                  case PaepAdView.AD_LOAD:
                      break;
                  case PaepAdView.AD_CLICK:
                      break;
                  case PaepAdView.AD_FAILED302:
                      break;                      
                  default:
                      break;
              }
          } catch (Exception e) {}
      }
  });

  adView.setAdQuery(); //광고 요청
}
```

## 미디에이션 애드온 가이드
| 플랫폼 | AddOn SDK | 가이드 |
|:---:|:---:|:---:|
| `AdMob` | implementation 'kr.co.paep:addon-admob:1.0.3' | [연동가이드](https://github.com/phaserep/paep-android-sdk/blob/master/admob.md) |
| `Criteo` | implementation 'kr.co.paep:addon-criteo:1.0.2' | [연동가이드](https://github.com/phaserep/paep-android-sdk/blob/master/criteo.md) |

## HELP
### 에러 코드
| message.what | 의미 |
|:---:|:---:|
| `101` | 광고 요청 |
| `201` | 광고 클릭 |
| `301` | 광고 요청 실패 |
| `302` | 광고 소재 없음 |
| `401` | 광고 로딩 실패 |
| `402` | SDK 내부 오류 |

### PAEP SDK, ADDON Gradle sync 오류시
1. Project build.gradle 파일에 paep의 repository를 추가하여 해결합니다.
```
repositories {
    google()
    jcenter()
    maven { url 'http://devrepo.kakao.com:8088/nexus/content/groups/public/' }
    maven { url "https://pubsdk-bin.criteo.com/publishersdk/android" }
    maven { url 'https://dl.bintray.com/phase/paep' }
}
```

    
Copyright © PAEP. All rights reserved.
