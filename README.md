# deleteRclass
delete R.class files

删除项目中R.class文件 减小apk体积 由于android studio 3.0前后 获取dex task有所不同 ThinRPlutin更改代码(参考https://github.com/meili/ThinRPlugin ) 

使用：

classpath 'com.liming.plugin:plugin:1.0.0'

添加插件
apply plugin: 'lm'

lm {
    noDeleteRclass = false
    noDeleteRclassDebug = true //debug模式不删除R.class
}

上传Library到JCenter

center 和 Maven Central 都是远程仓库，都可以通过依赖的形式添加jar包。在之前Android Studio 默认使用的是Maven Central ，但发现其有一定的入门难度，将其改为了jcenter。

想要把代码上传到jcenter，必须先有账号，登陆网站，直接可以选择使用github登陆。

网站地址https://bintray.com/

在网战中我们需要获取两个重要的信息，用户名和秘钥 ,在编写代码之前，需要添加一些Gradle 的依赖，在项目的根目录的build.gradle中，添加依赖
buildscript {

    // 添加远程仓库
    repositories {
        jcenter()
    }

    // 添加依赖，此依赖从远程仓库中查找
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.0'

       // classpath 'com.liming.plugin:lm:2.0.0'
        // 将项目发布到JCenter 所需要的jar   添加依赖
        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.0'
        classpath 'com.github.dcendents:android-maven-plugin:1.2'
    }
}

在项目的根目录下编写bintray.gradle,添加如下代码:

// 应用插件
apply plugin: 'com.jfrog.bintray'
apply plugin: 'maven-publish'


def baseUrl = 'https://github.com/AlexSmille'
def siteUrl = baseUrl
def gitUrl = "${baseUrl}/Android-Gradle-Demo"
def issueUrl = "${baseUrl}/issues"



install {
    repositories {
        mavenInstaller {
            // This generates POM.xml with proper paramters
            pom.project {

                //添加项目描述
                name 'Gradle Plugin for Android'
                url siteUrl

                //设置开源证书信息
                licenses {
                    license {
                        name 'The Apache Software License, Version 2.0'
                        url 'http://www.apache.org/licenses/LICENSE-2.0.txt'
                    }
                }
                //添加开发者信息
                developers {
                    developer {
                        name 'careyLi'
                        email '313685617@qq.com'
                    }
                }

                scm {
                    connection gitUrl
                    developerConnection gitUrl
                    url siteUrl
                }
            }
        }

    }
}


//配置上传Bintray相关信息
bintray {
    user = BINTRAY_USER //隐私信息
    key = BINTRAY_KEY //隐私信息

    configurations = ['archives']
    pkg {
        repo = 'maven' // 上传到中央仓库的名称
        name = 'tt' // 上传到jcenter 的项目名称
        desc = 'test gradle' // 项目描述
        websiteUrl = siteUrl
        issueTrackerUrl = issueUrl
        vcsUrl = gitUrl
        labels = ['gradle', 'plugin']
        licenses = ['Apache-2.0']
        publish = true
    }
}

在我们需要上传的Gradle plugin module下，应用插件
// 应用插件
apply plugin: "groovy"
apply plugin: "maven"

// 添加依赖
dependencies{
    compile gradleApi()
    compile localGroovy()
}

// 代码仓库
repositories{
   jcenter()
}

group = 'com.liming.gradle.plugin'  // 组名
version = '1.0.0' // 版本

/*// 上传到本地代码库
uploadArchives{
    repositories{
        mavenDeployer{
            repository(url:uri('../repo'))
            pom.groupId = 'com.liming.gradle.plugin' // 组名
            pom.artifactId = 'lm' // 插件名
            pom.version = '1.0.0' // 版本号
        }
    }
}*/

// 应用插件
apply from: '../bintray.gradle'

group和version和之前的一样，artifactId没办法定义，他会默认和你的项目名一样 ,sync now之后，Gradle会出现两个任务，先点击install,再点击bintrayUpload。在项目的build.gradle下，添加依赖：

buildscript {

    // 添加远程仓库
    repositories {
        jcenter()
        // Gradle 插件的仓库
        maven {
            url  "http://dl.bintray.com/alexsmille/maven"
        }
    }

    // 添加依赖，此依赖从远程仓库中查找
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.0'
        //自己编写的Gradle插件
        classpath 'com.liming.gradle.plugin:gradleplugin:1.0.0'
        // 将项目发布到JCenter 所需要的jar
        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.0'
        classpath 'com.github.dcendents:android-maven-plugin:1.2'
    }
}

上面的url和classpath;这两个值怎么获取，找到上传的项目的页面 右上角为url  左下角为classpath
