# MultiMediaDemo
Codova多媒体插件
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
    
    
#### 依赖流程:  

### 1.将config.gradle文件拷贝进项目根目录
### 2.项目根目录下的build.gradle文件中加入如下代码:  
```xml
   // Top-level build file where you can add configuration options common to all sub-projects/modules.
   apply from: "config.gradle" //加入这一行
   
   buildscript {
       repositories {
           jcenter()
       }
       dependencies {
           classpath 'com.android.tools.build:gradle:2.3.3'
   
           // NOTE: Do not place your application dependencies here; they belong
           // in the individual module build.gradle files
   
            //加入下面四行
           classpath 'me.tatarka:gradle-retrolambda:3.6.0'
           classpath 'com.mb14:configdroid:1.1.0'
           classpath 'com.chuyun923.android.plugin:webpcompress:3.0.0'
           classpath 'org.greenrobot:greendao-gradle-plugin:3.2.1'
   
       }
   }
   
   allprojects {
       repositories {
           jcenter()
           //加入下面四行
           maven { url "https://jitpack.io" }
           maven { url "https://maven.google.com" }
           maven { url "http://dl.bintray.com/mb-14/ConfigDroid" }
           maven { url 'https://dl.bintray.com/devzwy/maven' }
       }
   }
   
   task clean(type: Delete) {
       delete rootProject.buildDir
   }
   

 ```
### 3.在项目主moudel下的build.gradle文件中的dependencies节点下添加如下代码:
```xml
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
    //    compile(name: 'MultiMedia', ext: 'aar')
    //    compile(name: 'MvpTools', ext: 'aar')
        compile 'com.hyty.cordova:MultiMedia:1.0.0'
        compile 'com.zwy:MvpTools:2.2.1'
    //========================================================以上为插件所必须的依赖========================================================

```
### 4.在清单文件application中加入(android:name="com.hyty.cordova.BaseApplication"),如果项目本身已经有了BaseApplication类时，则使用你的BaseApplication类继承com.hyty.cordova.BaseApplication即可:
```xml
    <application
            android:allowBackup="true"
            android:icon="@mipmap/cordova_bot"
            android:label="@string/app_name"
            android:roundIcon="@mipmap/ic_launcher_round"
            android:supportsRtl="true"
            android:name="com.hyty.cordova.BaseApplication"//注意这里
            android:theme="@style/AppTheme">
            <!--注册如下activity-->
            <!--拍照页面-->
            <activity
                android:theme="@style/No_All"
                android:name="com.hyty.cordova.mvp.ui.activity.TakeCameraActivity"
                android:launchMode="singleTop">
            </activity>
        </application>
    
```  

### 插件调用:  
- 快速拍照:
```Java
    传入参数:{"flagText":"快速拍照-工作使我快乐","folderName":"cgzf","maxOptionalNum":3,"type":1}
    /**
         * 快速拍照需要传入的参数
         *
         * @param mType           业务类型 1 ~ 快速拍照   2 ~ 多图选择+拍照   3 ~ 图片预览
         * @param mMaxOptionalNum 最大可选/可拍数量 默认为9张
         * @param mFolderName     存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片
         * @param flagText        水印文字
         */
        public ConfigParams(int mType, int mMaxOptionalNum, String mFolderName, String flagText) {
            type = mType;
            maxOptionalNum = mMaxOptionalNum;
            folderName = mFolderName;
            this.flagText = flagText;
        }
        结果回调:xxxxxxxxxxxx
```
- 多图选择:
```Java
    传入参数:{"flagText":"多图选择-工作使我快乐","folderName":"cgzf","maxOptionalNum":10,"type":2}
     /**
             * 快速拍照需要传入的参数
             *
             * @param mType           业务类型 1 ~ 快速拍照   2 ~ 多图选择+拍照   3 ~ 图片预览
             * @param mMaxOptionalNum 最大可选/可拍数量 默认为9张
             * @param mFolderName     存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片
             * @param flagText        水印文字
             */
            public ConfigParams(int mType, int mMaxOptionalNum, String mFolderName, String flagText) {
                type = mType;
                maxOptionalNum = mMaxOptionalNum;
                folderName = mFolderName;
                this.flagText = flagText;
            }
    结果回调: xxxxxxxxxxxxxxxxxxx
```
- 图片预览:
```Java
     传入参数:{"data":[{"fileName":"N7M6R15505005380-1507857947765.jpg","fileName_www":"finaNeme_65_www"},{"fileName":"N7M6R15505005380-1507857939956.jpg","fileName_www":"finaNeme_56_www"},{"fileName":"141219/4-141219163521.jpg","fileName_www":"141219/4-141219163521.jpg"}],"folderName":"cgzf","isCanDelete":true,"type":3,"urlPathHeader":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image\u0026quality\u003d100\u0026size\u003db4000_4000\u0026sec\u003d1507859674\u0026di\u003d7a65819a34d0c21c3589e5060af4f502\u0026src\u003dhttp://4493bz.1985t.com/uploads/allimg"}
     /**
          * 图片预览 构造参数
          *
          * @param mType         业务类型
          * @param mFolderName   存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片
          * @param urlPathHeader 网络请求前缀 比如:http://www.baidu.image/
          * @param mIsCanDelete  是否具备删除功能
          * @param data          预览的数据源
          */
         public ConfigParams(int mType, String mFolderName, String urlPathHeader, boolean mIsCanDelete, List<DataBean> data) {
             type = mType;
             folderName = mFolderName;
             this.urlPathHeader = urlPathHeader;
             isCanDelete = mIsCanDelete;
             this.data = data;
         }
           private String fileName;//本地文件名称
             private String fileName_www;//网络存储的名称
             
             
             回调:xxxxxxxxxxxxxxx
         
```
