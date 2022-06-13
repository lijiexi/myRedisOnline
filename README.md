## 项目简介

实现一个可以输入Redis命令的[网站](http://redis.lijiexi.com/)，用户可以通过点击或者输入操作来使用常用的Redis命令，本网站会自动保存用户的缓存，同时根据用户的命令返回相应的结果。

该网站用来测试基于Java实现的本地Redis缓存：[myRedis](https://github.com/lijiexi/myRedis)

## 项目演示

### 网站首页

![](https://raw.githubusercontent.com/lijiexi/Picbed_PicGo/main/blogImg/%E6%88%AA%E5%B1%8F2022-05-29%2020.21.04.png)

### 常用命令及介绍

用户可以通过点击或输入以下格式指令来获取网站支持的Redis命令。

```
> help + command
```

#### Set命令

![](https://raw.githubusercontent.com/lijiexi/Picbed_PicGo/main/blogImg/%E6%88%AA%E5%B1%8F2022-05-29%2020.32.16.png)

#### Expire命令

![](https://raw.githubusercontent.com/lijiexi/Picbed_PicGo/main/blogImg/%E6%88%AA%E5%B1%8F2022-05-29%2020.22.40.png)

### 常用命令

用户可以使用set, get, del, expire常用命令操作缓存。

![](https://raw.githubusercontent.com/lijiexi/Picbed_PicGo/main/blogImg/%E6%88%AA%E5%B1%8F2022-05-29%2020.24.54.png)

### 常见报错

当用户键入错误参数或者网站不支持的指令时，会自动报错。

![](https://raw.githubusercontent.com/lijiexi/Picbed_PicGo/main/blogImg/%E6%88%AA%E5%B1%8F2022-05-29%2020.26.46.png)

