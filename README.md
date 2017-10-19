# MultiMediaDemo
MultiMedia多媒体插件集成步骤:
-
**********  

## 1.将配置文件(点击获取)放入项目根目录
## 2.修改项目根目录下的build.gradle文件(该文件共修改三处，修改完成后请检查)
```gradle
// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply from: "config.gradle"//加入这一行
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        classpath 'me.tatarka:gradle-retrolambda:3.6.0'//加入这一行
    }
}

allprojects {
    repositories {
        jcenter()
        //加入下面两行
        maven { url "https://jitpack.io" }
        maven { url "https://maven.google.com" }
    }
}

```
## 3.修改app下的build.gradle,在dependencies节点下加入下面的代码(修改完成后请编译项目,库本身包括cordova.jar、fastjson.jar,如遇编译失败时可手动删除冲突的jar)
```gradle
//========================================================以下为插件所必须的依赖========================================================
    compile rootProject.ext.dependencies["dagger2"]
    compile rootProject.ext.dependencies["gson"]
    compile rootProject.ext.dependencies["timber"]
    compile rootProject.ext.dependencies["Utils"]
    compile rootProject.ext.dependencies["LuBan"]
    compile rootProject.ext.dependencies["glide"]
    compile rootProject.ext.dependencies["recyclerview-v7"]
    compile rootProject.ext.dependencies["androideventbus"]
    compile rootProject.ext.dependencies["okhttp3"]
    compile rootProject.ext.dependencies["rxjava2"]
    provided rootProject.ext.dependencies["javax.annotation"]
    compile 'com.android.support:multidex:1.0.1'
    compile 'com.github.chrisbanes.photoview:library:1.2.4'
    annotationProcessor(rootProject.ext.dependencies["butterknife-compiler"]) {
        exclude module: 'support-annotations' exclude module: 'butterknife-annotations'
    }
    compile(rootProject.ext.dependencies["butterknife"]) { exclude module: 'support-annotations' }
    annotationProcessor(rootProject.ext.dependencies["dagger2-compiler"]) {
        exclude module: 'dagger'
    }
    compile(rootProject.ext.dependencies["design"]) {
        exclude module: 'support-annotations'
        exclude module: 'appcompat-v7'
        exclude module: 'support-v4'
    }
    compile(rootProject.ext.dependencies["rxandroid2"]) {
        exclude module: 'rxjava'
    }
    compile(rootProject.ext.dependencies["rxcache2"]) {
        exclude module: 'rxjava'
        exclude module: 'dagger'
    }
    compile(rootProject.ext.dependencies["rxcache-jolyglot-gson"]) {
        exclude module: 'gson'
    }
    compile(rootProject.ext.dependencies["rxlifecycle2"]) {
        exclude module: 'rxjava'
        exclude module: 'jsr305'
    }
    compile(rootProject.ext.dependencies["rxlifecycle2-components"]) {
        exclude module: 'support-v4'
        exclude module: 'appcompat-v7'
        exclude module: 'support-annotations'
        exclude module: 'rxjava'
        exclude module: 'rxandroid'
        exclude module: 'rxlifecycle'
    }
    compile(rootProject.ext.dependencies["rxpermissions2"]) {
        exclude module: 'rxjava'
        exclude module: 'support-annotations'
    }
    compile(rootProject.ext.dependencies['rxerrorhandler2']) {
        exclude module: 'rxjava'
        exclude module: 'appcompat-v7'
    }
    compile rootProject.ext.dependencies["MultiMediaPlugin"]
    compile rootProject.ext.dependencies["MvpTools"]

//========================================================以上为插件所必须的依赖========================================================

```
## 4.修改AndroidManifest.xml文件，如果项目没有使用自定义application时，请在application节点下加入下面代码，如果有自定义application时需要将原application继承自下面的application。增加注册下面的activity

```xml
 android:name="com.hyty.cordova.BaseApplication"
```
```xml
    <activity
            android:theme="@style/No_All"
            android:name="com.hyty.cordova.mvp.ui.activity.TakeCameraActivity"
    </activity>
```
**********
插件调用
-
## 插件名称:MultiMediaPlugin,完整路径:com.hyty.cordova.plugins.MultiMediaPlugin，请自行在js中注册.
#### 所有业务使用同一插件，由带入json串(调用cordorva插件时，请在参数位置传入该json串)的type字段来区分业务类型，业务分类:
#### 1.快速拍照 
###### 调用案例json串:
```log
    {"type":1,"folderName":"cygs","maxOptionalNum":3,"flagText":"202 公里 999 米"}
```
- type:代表快速拍照
- folderName:存储文件的名称,该文件将在SD卡根目录下出现,存储压缩过的图片.
- maxOptionalNum:最大可选/可拍数量,默认为9张
- flagText:水印文字
###### 用户点击"确认使用"后回传给js的数据:
```log
[{"js_out":"图片Base64字符串","pictureName":"图片名称"},{"js_out":"图片Base64字符串","pictureName":"图片名称"}]
```
#### 2.多图选择(传入参数和回传参数同快速拍照,传入参数type改为2即可)
##### 3.图片预览
```log
    {"type":3,"folderName":"cygs","isCanDelete":true,"data":[{"fileName":"YVF6R17330001250-1508381890890.jpg","filePath_www":"http://182.92.158.82:8089undefined"},{"fileName":"YVF6R17330001250-1508381886932.jpg","filePath_www":"http://182.92.158.82:8089undefined"}]}
```
- isCanDelete:预览的图片是否可以删除
- data:预览的数据源，放集合
- fileName:文件名称
- filePath_www:网络路径

###### 后回传给js的数据(仅isCanDelete==true时有效):
```log
[{"fileName":"已删除图片名称"},{"fileName":"已删除图片名称"}]
```























**********以下文字和文档无关**********
业务逻辑梳理：
    插件采用MVP架构开发
    所有业务使用同一插件，由带入json串的某一特定值来区分业务类型，业务分类:
    1:快速拍照
    2:多图选择+拍照
    3:快速录像
    4:录像选择+录像
    5:快速录音
    6:录音选取列表+录音
    7:图片、视频预览列表
    8:流媒体播放(单一页面，全屏播放)
