# deleteRclass
delete R.class files

删除项目中R.class文件 减小apk体积 由于android studio 3.0前后 获取dex task有所不同 ThinRPlutin更改代码(参考https://github.com/meili/ThinRPlugin ) 

使用：

classpath 'com.liming.plugin:plugin:1.0.0'

添加插件
apply plugin: 'lm'

lm {
    noDeleteRclass = false
    noDeleteRclassDebug = false //debug模式不删除R.class
}

