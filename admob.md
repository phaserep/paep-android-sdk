# AdMob Mediation AddOn Guide

* 애드몹 미디에이션 기능을 지원하는 애드온입니다.

## 시작하기
* 최신버전의 SDK 사용을 권장합니다.

### 1. 운영앱 프로젝트에 PAEP AdMob Addon 추가하기
1. App 모듈 build.gradle 파일에 최신 버전의 PAEP AdMob Addon을 추가합니다.
```
dependencies {
    implementation 'kr.co.paep:addon-admob:1.0.3'
}
```

2. AndroidManifest.xml 파일에 애드몹에서 발급받은 Application Id를 추가합니다.
```
<meta-data android:name="com.google.android.gms.ads.APPLICATION_ID" android:value="ca-app-pub-xxxxxxxxxxxxxxxx~xxxxxxxxxx"/>
```

3. Sync Project with Gradle Files를 클릭하여 변경사항을 적용합니다.
