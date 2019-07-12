# JavaKit
[![](https://jitpack.io/v/da0ke/JavaKit.svg)](https://jitpack.io/#da0ke/JavaKit)
[![GitHub release](https://img.shields.io/github/tag/da0ke/JavaKit.svg)](https://github.com/da0ke/JavaKit/tags)

封装一部分常用工具，适用于android及java开发

## 集成方式
Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
	...
	maven { url 'https://www.jitpack.io' }
	}
}
```

Step 2. Add the dependency
[![](https://jitpack.io/v/da0ke/JavaKit.svg)](https://jitpack.io/#da0ke/JavaKit)
```
dependencies {
	implementation 'com.github.da0ke:JavaKit:${version}'
}
```

## 使用方法
### NetUtils
```
// get请求
NetUtils.builder().url(url).get(new CallBack() {
	@Override
	public void onSuccess(String value) {
	
	}
			
	@Override
	public void onFail() {
	
	}
});

// post请求，参数可以是文件
NetUtils.builder().url(url)
	.add("file", new File("face.jpg"))
	.add("name", "da0ke")
	.add("age", 33)
	.add("marry", true)
	.post(new CallBack() {
		@Override
		public void onSuccess(String value) {
	
		}
	
		@Override
		public void onFail() {
	
		}
	});
```

### RegexUtils
```
RegexUtils.isEmail("");
RegexUtils.isIdCardNumber("");
RegexUtils.isLetter('a');
RegexUtils.isMobile("");
RegexUtils.isPassword("");
RegexUtils.isTel("");
RegexUtils.isUnsignedInt("");
```

### StringUtils
```
StringUtils.isEmpty("");
StringUtils.isNotEmpty("");
StringUtils.isTrimEmpty("");
StringUtils.isNotTrimEmpty("");
StringUtils.isAnyEmpty("", "", "");
StringUtils.isNotEmpty("", "", "");
```

### TimeUtils
```
TimeUtils.date2String(new Date(), "yyyy-MM-dd");
TimeUtils.millis2String(System.currentTimeMillis(), "yyyy-MM-dd");
TimeUtils.isToday(new Date());
TimeUtils.getCalendarAfterDay(1);
TimeUtils.getCalendarAfterMonth(1);
```
